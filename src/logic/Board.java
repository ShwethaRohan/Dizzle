package logic;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import logic.exception.InvalidJSONFileException;
import logic.exception.JsonInvalidException;
import logic.exception.OutOfRangeException;

/**
 * Describes the board of a player with the all components. Initialises all the
 * cells and special fields for each player. Board is composed of several cells
 *
 * @author SHWETHA
 */
public class Board {

    private GraphDataJSON gd;

    private Cell[][] cells;

    ErrorHandler errorHandler;

    private HashMap<Integer, List<Integer[]>> horizontalLines;

    private HashMap<Integer, List<Integer[]>> verticalLines;

    private List<Grids> crossedPositions;

    private Grids planetPosition;

    private List<Grids> dicePlaced;

    private int cellMaxRowIndex;

    private int cellMaxColumnIndex;

    private List<Grids> relevantNeighbours;

    private List<Integer> hLinesStartPosition;

    private List<Integer> vLinesStartPosition;

    private PlayerScore score;

    private List<Integer> flagPoints;

    private List<Grids> explodedBombs;

    private int flagReachedAs;

    private HashMap<Integer, Integer> puzzlePositionsCount;

    private HashMap<Integer, Integer> keyPositionsCount;

    protected boolean fieldsEmpty;
    /**
     * Constants to represent the points of special horizontal and vertical
     * lines. Few of these constants might be used for other scenarios too
     */
    private final int FIVE = 5;

    private final int TEN = 10;

    private final int FIFTEEN = 15;

    private final int THREE = 3;
    /**
     * Constant to represent the array size required to combine the start and
     * end coordinates of special horizontal and vertical lines
     */
    private final int FOUR = 4;

    /**
     * Constant to represent the number 2. This is used for different scenarios
     * like the special horizontal and vertical lines list must contain two
     * entries to represent start and end positions, length of an array to hold
     * x and y coordinates etc
     */
    private final int TWO = 2;
    /**
     * Constant to represent the number 1
     */
    private final int ONE = 1;

    /**
     * Constant to represent the number 0
     */
    private final int ZERO = 0;

    /**
     * Constant to represent the highest row dimension of the player board
     */
    private final int MAX_ROW_DIMENSION = 7;
    /**
     * Constant to represent the highest column dimension of the player board
     */
    private final int MAX_COLUMN_DIMENSION = 9;

    /**
     * Constructor to create a player board. Initialises the player grids with
     * cells and also special fields
     *
     * @param r JSON string reader
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected Board(Reader r) throws InvalidJSONFileException {
        try {
            Gson gson = new Gson();
            this.gd = gson.fromJson(r, GraphDataJSON.class);
            if (gd.getFileds() == null || gd.getFileds().length == ZERO) {
                throw new OutOfRangeException("Field values must be defined");
            }
            checkBoardUpperLimit(gd.getFileds());
            score = new PlayerScore();
            initialiseParameters();
            initialiseJewels();
            initialiseBombs();
            initialisePuzzles();
            initialiseKeys();
            initialiseFlags();
            initialiseRocket();
            initialisePlanet();
            initialiseGrid();
            initialiseHorizontalLines();
            initialiseVerticalLines();
        } catch (JsonParseException ex) {
            throw new JsonInvalidException(ex);
        }
    }

    /**
     * Constructor used for testing
     *
     * @param r JSON string reader
     * @param dice the positions where the dices are placed
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected Board(Reader r, List<Grids> dice) throws InvalidJSONFileException {
        this(r);
        dicePlaced.addAll(dice);
    }

    /**
     * Initialises the parameters at the start of a new game
     */
    protected final void initialiseParameters() {
        dicePlaced = new ArrayList<>();
        crossedPositions = new ArrayList<>();
        relevantNeighbours = new ArrayList<>();
        hLinesStartPosition = new ArrayList<>();
        vLinesStartPosition = new ArrayList<>();
        flagPoints = new ArrayList<>();
        explodedBombs = new ArrayList<>();
        puzzlePositionsCount = new HashMap<>();
        keyPositionsCount = new HashMap<>();
        cells = new Cell[gd.getFileds().length][gd.getFileds()[ZERO].size()];
        cellMaxColumnIndex = gd.getFileds()[ZERO].size();
        cellMaxRowIndex = gd.getFileds().length;
        errorHandler = new ErrorHandler(cellMaxRowIndex, cellMaxColumnIndex);
        fieldsEmpty = false;
    }

    /**
     * Checks if the board dimension exceeds the predefined range. In our
     * implementation we have considered 7X9 board. Any board dimension
     * exceeding this dimension would be considered invalid and hence an error
     * would be thrown
     *
     * @param fieldValues the field values obtained from the level file
     * @throws OutOfRangeException field values exceed the predefined range
     */
    protected final void checkBoardUpperLimit(List<Integer>[] fieldValues) throws OutOfRangeException {
        if (fieldValues.length > MAX_ROW_DIMENSION) {
            throw new OutOfRangeException("Invalid board dimensions");
        }
        List<Integer> rowSize = new ArrayList<>();
        for (List<Integer> fieldValue : fieldValues) {
            if (fieldValue.size() > MAX_COLUMN_DIMENSION) {
                throw new OutOfRangeException("Invalid board dimensions");
            } else {
                rowSize.add(fieldValue.size());
            }
        }
        /*
         * Boards with lower dimensions can be uploaded. 
        *  The number of coulmns in every row should be the same.
         */
        int maxRowSize = Collections.max(rowSize);
        for (List<Integer> fieldValue : fieldValues) {
            if (fieldValue.size() != maxRowSize) {
                throw new OutOfRangeException("Invalid board dimensions");
            }
        }
    }

    /**
     * Initialises the cells with field values which are obtained from JSON file
     *
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected final void initialiseGrid() throws InvalidJSONFileException {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == null && gd.getFieldValue(j, i) != null) {
                    checkFieldValueErrors(gd.getFieldValue(j, i), j, i);
                    cells[i][j] = new Cell(gd.getFieldValue(j, i));
                }
            }
        }
    }

    /**
     * Initialises cells with jewel positions and points
     *
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected final void initialiseJewels() throws InvalidJSONFileException {
        List<Jewel> jewels = gd.getJewels();
        if (jewels != null) {
            checkJewelPoints(jewels);
            for (int i = 0; i < jewels.size(); i++) {
                List<Grids> positions = jewels.get(i).getPositions();
                if (positions != null) {
                    checkPositionErrors(positions, SpecialFields.JEWEL);
                    for (int j = 0; j < positions.size(); j++) {
                        int xPos = positions.get(j).getXPos();
                        int yPos = positions.get(j).getYPos();
                        // Initialise the cell only if the field value is not null
                        if (gd.getFieldValue(xPos, yPos) != null) {
                            checkFieldValueErrors(gd.getFieldValue(xPos, yPos), xPos, yPos);
                            cells[yPos][xPos] = new Jewel(gd.getFieldValue(xPos, yPos),
                                    jewels.get(i).getPoints());
                        }
                    }
                }
            }
        }
    }

    /**
     * Initialises cells with bomb positions
     *
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected final void initialiseBombs() throws InvalidJSONFileException {
        Bomb bomb = gd.getBombs();
        if (bomb != null) {
            List<Grids> bombPositions = bomb.getPositions();
            if (bombPositions != null) {
                checkPositionErrors(bombPositions, SpecialFields.BOMB);
                for (int i = 0; i < bombPositions.size(); i++) {
                    int xPos = bombPositions.get(i).getXPos();
                    int yPos = bombPositions.get(i).getYPos();
                    // Initialise the cell only if the field value is not null
                    if (gd.getFieldValue(xPos, yPos) != null) {
                        checkFieldValueErrors(gd.getFieldValue(xPos, yPos), xPos, yPos);
                        cells[yPos][xPos] = new Bomb(gd.getFieldValue(xPos, yPos),
                                bomb.getPoints());
                    }
                }
            }
        }
    }

    /**
     * Initialises cells with puzzle positions and points
     *
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected final void initialisePuzzles() throws InvalidJSONFileException {
        List<Puzzle> puzzles = gd.getPuzzles();
        if (puzzles != null) {
            for (int i = 0; i < puzzles.size(); i++) {
                List<Grids> positions = puzzles.get(i).getPositions();
                if (positions != null) {
                    checkPositionErrors(positions, SpecialFields.PUZZLE);
                    for (int j = 0; j < positions.size(); j++) {
                        int xPos = positions.get(j).getXPos();
                        int yPos = positions.get(j).getYPos();
                        // Initialise the cell only if the field value is not null
                        if (gd.getFieldValue(xPos, yPos) != null) {
                            //keep a track of the number of types of puzzles
                            puzzlePositionsCount.put(Integer.parseInt(yPos + "" + xPos), i + ONE);
                            checkFieldValueErrors(gd.getFieldValue(xPos, yPos), xPos, yPos);
                            cells[yPos][xPos] = new Puzzle(gd.getFieldValue(xPos, yPos),
                                    puzzles.get(i).getPoints(), puzzles.get(i).getPositions());
                        }
                    }
                }
            }
        }
    }

    /**
     * Initialises cells with keys and keyholes positions
     *
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected final void initialiseKeys() throws InvalidJSONFileException {
        List<Key> keys = gd.getKeys();
        if (keys != null) {
            for (int i = 0; i < keys.size(); i++) {
                Grids keyPosition = keys.get(i).getKeyPositions();
                if (keyPosition != null) {
                    int xPos = keyPosition.getXPos();
                    int yPos = keyPosition.getYPos();
                    errorHandler.JSONFilePositionsCheck(xPos, yPos, SpecialFields.KEY);
                    // Initialise the cell only if the field value is not null
                    if (gd.getFieldValue(xPos, yPos) != null) {
                        checkFieldValueErrors(gd.getFieldValue(xPos, yPos), xPos, yPos);
                        //keep a track of the number of key and key holes pair
                        keyPositionsCount.put(Integer.parseInt(yPos + "" + xPos), i + ONE);
                        cells[yPos][xPos] = new Key(gd.getFieldValue(xPos, yPos),
                                keyPosition, keys.get(i).getHolePositions());

                        List<Grids> holePositions = keys.get(i).getHolePositions();
                        if (holePositions != null) {
                            checkPositionErrors(holePositions, SpecialFields.KEY);
                            for (int j = 0; j < holePositions.size(); j++) {
                                xPos = holePositions.get(j).getXPos();
                                yPos = holePositions.get(j).getYPos();
                                errorHandler.JSONFilePositionsCheck(xPos, yPos, SpecialFields.KEY);
                                // Initialise the cell only if the field value is not null
                                if (gd.getFieldValue(xPos, yPos) != null) {
                                    checkFieldValueErrors(gd.getFieldValue(xPos, yPos), xPos, yPos);
                                    // add the key hole to the same category of the key
                                    keyPositionsCount.put(Integer.parseInt(yPos + "" + xPos), i + ONE);
                                    cells[yPos][xPos] = new Key(gd.getFieldValue(xPos, yPos),
                                            keyPosition, keys.get(i).getHolePositions());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Initialises cells with flag positions and points
     *
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected final void initialiseFlags() throws InvalidJSONFileException {
        Flag flag = gd.getFlags();
        if (flag != null) {
            Grids flagPosition = flag.getPositions();
            if (flagPosition != null) {
                int xPos = flagPosition.getXPos();
                int yPos = flagPosition.getYPos();
                errorHandler.JSONFilePositionsCheck(xPos, yPos, SpecialFields.FLAG);
                // Initialise the cell only if the field value is not null
                if (gd.getFieldValue(xPos, yPos) != null) {
                    checkFieldValueErrors(gd.getFieldValue(xPos, yPos), xPos, yPos);
                    cells[yPos][xPos] = new Flag(gd.getFieldValue(xPos, yPos),
                            flag.getFlagPoints());
                    for (int i = 0; i < flag.getFlagPoints().size(); i++) {
                        flagPoints.add(flag.getFlagPoints().get(i));
                    }
                }
            }
        }
    }

    /**
     * Initialises cells with rocket positions
     *
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected final void initialiseRocket() throws InvalidJSONFileException {
        Grids rocket = gd.getRocket();
        if (rocket != null) {
            int xPos = rocket.getXPos();
            int yPos = rocket.getYPos();
            errorHandler.JSONFilePositionsCheck(xPos, yPos, SpecialFields.ROCKET);
            // Initialise the cell only if the field value is not null
            if (gd.getFieldValue(xPos, yPos) != null) {
                checkFieldValueErrors(gd.getFieldValue(xPos, yPos), xPos, yPos);
                cells[yPos][xPos] = new Cell(gd.getFieldValue(xPos, yPos), SpecialFields.ROCKET);
            }
        }
    }

    /**
     * Initialises cells with planet positions
     *
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected final void initialisePlanet() throws InvalidJSONFileException {
        Grids planet = gd.getPlanet();
        if (planet != null) {
            int xPos = planet.getXPos();
            int yPos = planet.getYPos();
            errorHandler.JSONFilePositionsCheck(xPos, yPos, SpecialFields.PLANET);
            // Initialise the cell only if the field value is not null
            if (gd.getFieldValue(xPos, yPos) != null) {
                checkFieldValueErrors(gd.getFieldValue(xPos, yPos), xPos, yPos);
                cells[yPos][xPos] = new Cell(gd.getFieldValue(xPos, yPos), SpecialFields.PLANET);
                // store the location of the planet
                planetPosition = new Grids(yPos, xPos);
            }
        }
    }

    /**
     * Initialises a hash map containing the start and end of special horizontal
     * lines which is obtained from JSON file
     *
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected final void initialiseHorizontalLines() throws InvalidJSONFileException {
        List<HorizontalLines> hlines = gd.getHorizontalLines();
        if (hlines != null) {
            checkHLinePoints(hlines);
            horizontalLines = new HashMap<>();

            //Initialise four lists for four possible horizontal line points
            List<Integer[]> hvalueFive = new ArrayList<>();
            List<Integer[]> hvalueTen = new ArrayList<>();
            List<Integer[]> hvalueFifteen = new ArrayList<>();
            List<Integer[]> hvalueThree = new ArrayList<>();

            for (int i = 0; i < hlines.size(); i++) {
                int j = 0;
                int points = hlines.get(i).getLinePoints();
                if (hlines.get(i).getLinePositions() != null
                        && !hlines.get(i).getLinePositions().isEmpty()
                        && hlines.get(i).getLinePositions().size() == TWO) {
                    Grids startPosition = hlines.get(i).getStartPosition();
                    Grids EndPosition = hlines.get(i).getEndPosition();
                    // check if the start and end position of the horizontal line is valid
                    errorHandler.JSONFilePositionsCheck(startPosition.getXPos(), startPosition.getYPos(), SpecialFields.HLINE);
                    errorHandler.JSONFilePositionsCheck(EndPosition.getXPos(), EndPosition.getYPos(), SpecialFields.HLINE);

                    //array to store start and end coordinates
                    Integer[] linesArray = new Integer[FOUR];
                    linesArray[j++] = startPosition.getXPos();
                    linesArray[j++] = startPosition.getYPos();
                    linesArray[j++] = EndPosition.getXPos();
                    linesArray[j++] = EndPosition.getYPos();
                    /**
                     * store the start coordinate of every special horizontal
                     * line. This will be useful to check if placing a dice on a
                     * line which has this start position will fetch extra
                     * points or not
                     *
                     */
                    hLinesStartPosition.add(startPosition.getYPos());

                    // add coordinates to respective list based on their points
                    switch (points) {
                        case FIVE:
                            hvalueFive.add(linesArray);
                            break;
                        case TEN:
                            hvalueTen.add(linesArray);
                            break;
                        case FIFTEEN:
                            hvalueFifteen.add(linesArray);
                            break;
                        case THREE:
                            hvalueThree.add(linesArray);
                            break;
                        default:
                            break;
                    }

                    // add the lists to the hash map
                    horizontalLines.put(FIVE, hvalueFive);
                    horizontalLines.put(TEN, hvalueTen);
                    horizontalLines.put(FIFTEEN, hvalueFifteen);
                    horizontalLines.put(THREE, hvalueThree);
                }
            }
        }
    }

    /**
     * Initialises a hash map containing the start and end of special vertical
     * lines which is obtained from JSON file
     *
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     */
    protected final void initialiseVerticalLines() throws InvalidJSONFileException {
        List<VerticalLines> vlines = gd.getVerticalLines();
        if (vlines != null) {
            checkVLinePoints(vlines);
            verticalLines = new HashMap<>();

            //Initialise two lists for two possible vertical line points
            List<Integer[]> vValueFive = new ArrayList<>();
            List<Integer[]> vValueTen = new ArrayList<>();

            for (int i = 0; i < vlines.size(); i++) {
                int j = 0;
                int points = vlines.get(i).getLinePoints();
                if (vlines.get(i).getLinePositions() != null
                        && !vlines.get(i).getLinePositions().isEmpty()
                        && vlines.get(i).getLinePositions().size() == TWO) {
                    Grids startPosition = vlines.get(i).getStartPosition();
                    Grids EndPosition = vlines.get(i).getEndPosition();
                    // check if the start and end position of the vertical line is valid
                    errorHandler.JSONFilePositionsCheck(startPosition.getXPos(), startPosition.getYPos(), SpecialFields.VLINE);
                    errorHandler.JSONFilePositionsCheck(EndPosition.getXPos(), EndPosition.getYPos(), SpecialFields.VLINE);

                    //array to store start and end coordinates
                    Integer[] linesArray = new Integer[FOUR];
                    linesArray[j++] = startPosition.getXPos();
                    linesArray[j++] = startPosition.getYPos();
                    linesArray[j++] = EndPosition.getXPos();
                    linesArray[j++] = EndPosition.getYPos();
                    /**
                     * store the start coordinate of every special vertical
                     * line. This will be useful to check if placing a dice on a
                     * line which has this start position will fetch extra
                     * points or not
                     *
                     */
                    vLinesStartPosition.add(startPosition.getXPos());

                    // add coordinates to respective list based on their points
                    switch (points) {
                        case FIVE:
                            vValueFive.add(linesArray);
                            break;
                        case TEN:
                            vValueTen.add(linesArray);
                            break;
                        default:
                            break;
                    }
                }
            }
            // add the lists to the hash map
            verticalLines.put(FIVE, vValueFive);
            verticalLines.put(TEN, vValueTen);
        }
    }

    /**
     * Checks if the field values read from the JSON file are valid
     *
     * @param fieldValue integer value of the field
     * @param xPos x coordinate
     * @throws InvalidJSONFileException Any errors that occur while loading the
     * level JSON file
     */
    private void checkFieldValueErrors(int fieldValue, int xPos, int yPos) throws InvalidJSONFileException {
        errorHandler.JSONFileFieldCheck(fieldValue, xPos, yPos);
    }

    /**
     * Checks if the coordinates of the list elements are valid
     *
     * @param positions list of grids
     * @param fieldType special type of the cell
     * @throws InvalidJSONFileException Any errors that occur while loading the
     * level JSON file
     */
    private void checkPositionErrors(List<Grids> positions, SpecialFields fieldType) throws InvalidJSONFileException {
        for (int i = 0; i < positions.size(); i++) {
            int xPos = positions.get(i).getXPos();
            int yPos = positions.get(i).getYPos();
            errorHandler.JSONFilePositionsCheck(xPos, yPos, fieldType);
        }
    }

    /**
     * Checks if the jewel points associated with every jewel in the given list
     * is valid
     *
     * @param jewels list of jewels
     * @throws InvalidJSONFileException Any errors that occur while loading the
     * level JSON file
     */
    private void checkJewelPoints(List<Jewel> jewels) throws InvalidJSONFileException {
        for (int i = 0; i < jewels.size(); i++) {
            errorHandler.JSONFilePointsCheck(jewels.get(i).getPoints(), SpecialFields.JEWEL);
        }
    }

    /**
     * Checks if the points associated with every special horizontal line
     * positions in the given list are valid
     *
     * @param hlines list of special horizontal lines positions
     * @throws InvalidJSONFileException Any errors that occur while loading the
     * level JSON file
     */
    private void checkHLinePoints(List<HorizontalLines> hlines) throws InvalidJSONFileException {
        for (int i = 0; i < hlines.size(); i++) {
            errorHandler.JSONFilePointsCheck(hlines.get(i).getLinePoints(), SpecialFields.HLINE);
        }
    }

    /**
     * Checks if the points associated with every special vertical line
     * positions in the given list are valid
     *
     * @param vlines list of special horizontal lines positions
     * @throws InvalidJSONFileException Any errors that occur while loading the
     * level JSON file
     */
    private void checkVLinePoints(List<VerticalLines> vlines) throws InvalidJSONFileException {
        for (int i = 0; i < vlines.size(); i++) {
            errorHandler.JSONFilePointsCheck(vlines.get(i).getLinePoints(), SpecialFields.VLINE);
        }
    }

    /**
     * Returns a map containing the start and end positions of the special
     * horizontal lines
     *
     * @return special horizontal line positions
     */
    protected HashMap<Integer, List<Integer[]>> getHorizontalStartPositions() {
        return horizontalLines;
    }

    /**
     * Returns a map containing the start and end positions of the special
     * vertical lines
     *
     * @return special horizontal line positions
     */
    protected HashMap<Integer, List<Integer[]>> getVerticalStartPositions() {
        return verticalLines;
    }

    /**
     * Getter for the player cells
     *
     * @return array of cells
     */
    protected Cell[][] getCells() {
        return this.cells;
    }

    /**
     * Getter for the individual player cell at given coordinates
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @return player cells
     */
    protected Cell getIndividualCell(int xPos, int yPos) {
        return cells[xPos][yPos];
    }

    /**
     * Getter for the key and corresponding key hole pairs
     *
     * @return map containing the positions of keys and key holes
     */
    protected HashMap<Integer, Integer> getKeyAndHolePositions() {
        return keyPositionsCount;
    }

    /**
     * Getter for the cell value given coordinates
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @return cell value
     */
    protected Integer getCellValue(int xPos, int yPos) {
        if (cells[xPos][yPos] != null) {
            return cells[xPos][yPos].getfieldValue();
        } else {
            return null;
        }
    }

    /**
     * Getter for the crossed out positions on the players board
     *
     * @return list of coordinates that are crossed out
     */
    protected List<Grids> getCrossedPositions() {
        return crossedPositions;
    }

    /**
     * Getter for the dice positions on the players board
     *
     * @return list of coordinates on which the dices are placed
     */
    protected List<Grids> getDicePlacedPositions() {
        return this.dicePlaced;
    }

    /**
     * Updates the cell values after every turn. After every turn the dice
     * placed cells must be replaced with a cross
     */
    protected void updateCrossPositions() {
        crossedPositions = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if ((cells[i][j] != null)
                        && (Objects.equals(cells[i][j].getfieldValue(), ZERO))) {
                    crossedPositions.add(new Grids(i, j));
                }
            }
        }
    }

    /**
     * Updates the crossed positions of a player. Used only for testing
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     */
    protected void addCrossPositions(int xPos, int yPos) {
        cells[xPos][yPos].setfieldValue(ZERO);
        crossedPositions.add(new Grids(xPos, yPos));
    }

    /**
     * Determines the relevant neighbours of all the crossed positions or the
     * positions where the dices are placed
     *
     * @param newTurn true - determine neighbours of the crossed cells. False -
     * determine the neighbours of the dice placed cells
     * @param arrayOfDices the dices being played during the game
     * @return list of relevant neighbours
     */
    protected List<Grids> getCrossedNeighbours(boolean newTurn, List<Integer> arrayOfDices) {
        List<Grids> crossedNeighbours = new ArrayList<>();
        List<Grids> positions = new ArrayList<>();
        updateCrossPositions();
        // check if there cells available on the board
        if (checkForAvaialbleFields()) {
            // pick the neighbours of the crossed positions
            if (newTurn) {
                positions.addAll(crossedPositions);
            } // pick the neighbours of the dice placed positions
            else {
                positions.addAll(dicePlaced);
            }
            for (int i = 0; i < positions.size(); i++) {
                int xPos = positions.get(i).getXPos();
                int yPos = positions.get(i).getYPos();

                // top neighbour
                if (xPos > ZERO) {
                    crossedNeighbours.add(new Grids(xPos - ONE, yPos));
                }
                // left neighbour
                if (yPos > ZERO) {
                    crossedNeighbours.add(new Grids(xPos, yPos - ONE));
                }
                // right neighbour
                if (yPos <= cellMaxColumnIndex - TWO) {
                    crossedNeighbours.add(new Grids(xPos, yPos + ONE));
                }
                // bottom neighbour
                if (xPos <= cellMaxRowIndex - TWO) {
                    crossedNeighbours.add(new Grids(xPos + ONE, yPos));
                }

            }
            crossedNeighbours = checkForKeyHole(crossedNeighbours);
            crossedNeighbours = checkForPlanet(crossedNeighbours);
            // sort the list of neighbours obtained by their x and y coordinates
            sortList(crossedNeighbours);
            // filter only the positions where one of the dices from the table can fit
            findRelevantNeighbours(crossedNeighbours, arrayOfDices);
        } else {
            relevantNeighbours = new ArrayList<>();
        }
        return relevantNeighbours;
    }

    /**
     * Find the relevant neighbours from the list of all the neighbours
     *
     * @param neighbours all the neighbours of a crossed cells or dice placed
     * cells
     * @param arrayOfDices the dices being played during the game
     */
    private void findRelevantNeighbours(List<Grids> neighbours,
            List<Integer> arrayOfDices) {
        relevantNeighbours = new ArrayList<>();
        boolean jumpCross = true;
        for (int i = 0; i < neighbours.size(); i++) {
            int xPos = neighbours.get(i).getXPos();
            int yPos = neighbours.get(i).getYPos();
            // Check if the cell can be occupied by a dice
            if (canBeOccupiedByDice(xPos, yPos)) {
                jumpCross = false;
                // Check if any of the rolled dices fit
                if (diceFitsACell(getCellValue(xPos, yPos), arrayOfDices)) {
                    relevantNeighbours.add(new Grids(xPos, yPos));
                }
            }
        }
        // the dices are closed in
        if (relevantNeighbours.isEmpty() && jumpCross) {
            getCrossedNeighbours(true, arrayOfDices);
        }
    }

    /**
     * Checks if there are cells left on the board which can be occupied by a
     * die
     *
     * @return true - cells not exhausted, false - cells are exhausted
     */
    private boolean checkForAvaialbleFields() {
        boolean available = false;
        for (int i = 0; i < cells.length && !available; i++) {
            for (int j = 0; j < cells[i].length && !available; j++) {
                if (canBeOccupiedByDice(i, j)) {
                    available = true;
                }
            }
        }
        if (!available) {
            fieldsEmpty = true;
        }
        return available;
    }

    /**
     * Checks if a cell can be occupied by one of the dice on the table
     *
     * @param cellValue the integer field value of the cell
     * @param dice list of dice values on the table
     * @return true - one of the dice can be placed on the cell, false - none of
     * the dice fits the cell
     */
    protected boolean diceFitsACell(int cellValue, List<Integer> dice) {
        boolean found = false;
        for (int i = 0; i < dice.size() && !found; i++) {
            if (Objects.equals(cellValue, dice.get(i))) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Updates the field value at given coordinates
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param value value to be updated
     */
    protected void updateValues(int xPos, int yPos, Integer value) {
        if (cells[xPos][yPos] != null) {
            cells[xPos][yPos].setfieldValue(value);
        }
    }

    /**
     * Chooses one cell among the different options given according to the
     * following preference 1.Puzzle piece 2.Flag 3.Jewel, the most valuable
     * first 4.Rocket 5.Bomb 6.Key 7.Keyhole 8.Line 1.Completing a line with a
     * die most 2.significant first any cell 9.Any cell
     *
     * @param values available cells
     * @return one chosen cell
     */
    protected int[] chooseAICell(List<Grids> values) {
        SpecialFields[] obj = new SpecialFields[]{SpecialFields.PUZZLE,
            SpecialFields.FLAG, SpecialFields.JEWEL, SpecialFields.ROCKET, SpecialFields.BOMB,
            SpecialFields.KEY};
        boolean result = false;
        int count = ZERO;
        int chosenCell[] = new int[TWO];

        while (!result && count < obj.length) {
            for (int i = 0; i < values.size() && !result; i++) {
                if (checkPreference(values.get(i), obj[count])) {
                    switch (obj[count]) {
                        case JEWEL: {
                            int index = selectJewel(values, i);
                            chosenCell[ZERO] = values.get(index).getXPos();
                            chosenCell[ONE] = values.get(index).getYPos();
                            break;
                        }
                        case KEY: {
                            int index = selectKeyForAI(values, i);
                            chosenCell[ZERO] = values.get(index).getXPos();
                            chosenCell[ONE] = values.get(index).getYPos();
                            break;
                        }
                        default:
                            chosenCell[ZERO] = values.get(i).getXPos();
                            chosenCell[ONE] = values.get(i).getYPos();
                            break;
                    }
                    result = true;
                }
            }
            count++;
        }
        // if no special field is found, check for the completion of special horizontal or vertical lines
        if (!result) {
            int[] lineCell = lineCompleted(values);
            if (lineCell.length != ZERO) {
                chosenCell[ZERO] = lineCell[ZERO];
                chosenCell[ONE] = lineCell[ONE];
                result = true;
            }
        }
        // none of the preference match. Pick a random cell
        if (!result) {
            chosenCell[ZERO] = values.get(ZERO).getXPos();
            chosenCell[ONE] = values.get(ZERO).getYPos();
        }
        return chosenCell;
    }

    /**
     * Checks if the given cell contains the given special field
     *
     * @param position coordinates of the cell
     * @param specialField special field type
     * @return true - the cell contains the specified special field. False - the
     * cell does not contain the specified special field
     */
    protected boolean checkPreference(Grids position, SpecialFields specialField) {
        return ((cells[position.getXPos()][position.getYPos()] != null)
                && (cells[position.getXPos()][position.getYPos()].hasSpecialField())
                && (cells[position.getXPos()][position.getYPos()].
                        getSpecialField().equals(specialField)));
    }

    /**
     * Checks if the list of neighbours given as input are keys or keyholes.
     * Only if key positions are unlocked keyholes can be occupied by dice. If
     * the keys are not unlocked yet, the corresponding key holes are removed
     * from the list of neighbours
     *
     * @param crossedNeighbours neighbours of a cell
     * @return list of keys or keyholes which can be occupied
     */
    private List<Grids> checkForKeyHole(List<Grids> crossedNeighbours) {
        for (int i = 0; i < crossedNeighbours.size(); i++) {
            int xPos = crossedNeighbours.get(i).getXPos();
            int yPos = crossedNeighbours.get(i).getYPos();
            if ((cells[xPos][yPos] != null) && (cells[xPos][yPos].hasSpecialField())
                    && (cells[xPos][yPos].getSpecialField().equals(SpecialFields.KEY))
                    && (!((Key) cells[xPos][yPos]).isKey(yPos, xPos))) {
                Grids keyPos = ((Key) cells[xPos][yPos]).getKeyPositions();
                // check if key is unlocked
                if (!(Objects.equals(cells[keyPos.getYPos()][keyPos.getXPos()]
                        .getfieldValue(), ZERO))) {
                    crossedNeighbours.remove(i);
                }
            }
        }
        return crossedNeighbours;
    }

    /**
     * Selects index of the jewel with highest points from the list of cells
     * given and starting from the given index
     *
     * @param values list of cells
     * @param index starting index of the cell
     * @return the index of jewel with highest points
     */
    private int selectJewel(List<Grids> values, int index) {
        int chosenIndex = index;
        int xPos = values.get(index).getXPos();
        int yPos = values.get(index).getYPos();
        int points = ((Jewel) cells[xPos][yPos]).getPoints();
        for (int i = index + ONE; i < values.size(); i++) {
            if (checkPreference(values.get(i), SpecialFields.JEWEL)) {
                xPos = values.get(i).getXPos();
                yPos = values.get(i).getYPos();
                // jewel with highest point
                if (((Jewel) cells[xPos][yPos]).getPoints() > points) {
                    points = ((Jewel) cells[xPos][yPos]).getPoints();
                    chosenIndex = i;
                }
            }
        }
        return chosenIndex;
    }

    /**
     * Selects index of the key from the list of positions starting from the
     * given index. If a key is not found, key hole position is returned
     *
     * @param values list of positions
     * @param index index of a cell
     * @return the index of key
     */
    private int selectKeyForAI(List<Grids> values, int index) {
        boolean foundKey = false;
        int chosenIndex = index;
        int xPos = values.get(index).getXPos();
        int yPos = values.get(index).getYPos();
        if (!((Key) cells[xPos][yPos]).isKey(yPos, xPos)) {
            for (int i = index + ONE; i < values.size() && !foundKey; i++) {
                if (checkPreference(values.get(i), SpecialFields.KEY)) {
                    xPos = values.get(i).getXPos();
                    yPos = values.get(i).getYPos();
                    if (((Key) cells[xPos][yPos]).isKey(yPos, xPos)) {
                        foundKey = true;
                        chosenIndex = i;
                    }
                }
            }
        }
        return chosenIndex;
    }

    /**
     * Check if there is a planet in the given list. This is used to remove the
     * planet position from the list of neighbours chosen.
     *
     * @param crossedNeighbours the list in which the planet has to be searched
     * for
     * @return list after removing planet position
     */
    private List<Grids> checkForPlanet(List<Grids> crossedNeighbours) {
        for (int i = 0; i < crossedNeighbours.size(); i++) {
            int xPos = crossedNeighbours.get(i).getXPos();
            int yPos = crossedNeighbours.get(i).getYPos();
            if (cells[xPos][yPos] != null && cells[xPos][yPos].hasSpecialField()
                    && cells[xPos][yPos].getSpecialField().equals(SpecialFields.PLANET)) {
                crossedNeighbours.remove(i);
            }
        }
        return crossedNeighbours;
    }

    /**
     * Determines if the given position is occupied by a dice
     *
     * @param pos the coordinates of the cell
     * @return true - if the cell is occupied by dice. False - if the cell is
     * not occupied by dice
     */
    protected boolean occupiedByDice(int[] pos) {
        boolean occupied = false;
        for (int i = 0; i < dicePlaced.size() && !occupied; i++) {
            if (dicePlaced.get(i).getXPos() == pos[ZERO]
                    && dicePlaced.get(i).getYPos() == pos[ONE]) {
                occupied = true;
            }
        }
        return occupied;
    }

    /**
     * Replaces all the dices on the board with cross
     */
    protected void replaceDices() {
        for (int i = 0; i < dicePlaced.size(); i++) {
            int xPos = dicePlaced.get(i).getXPos();
            int yPos = dicePlaced.get(i).getYPos();
            updateValues(xPos, yPos, ZERO);
            // if dice is placed on rocket position, the planet field must be crossed
            if (cells[xPos][yPos] != null && cells[xPos][yPos].hasSpecialField()
                    && cells[xPos][yPos].getSpecialField().equals(SpecialFields.ROCKET)
                    && planetPosition != null) {
                updateValues(planetPosition.getXPos(), planetPosition.getYPos(), ZERO);
            }
        }
    }

    /**
     * Checks if there is a rocket at given coordinates
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @return true - rocket is present. false - rocket not present
     */
    protected boolean checkSatelliteLanding(int xPos, int yPos) {
        return (cells[xPos][yPos] != null && cells[xPos][yPos].hasSpecialField()
                && cells[xPos][yPos].getSpecialField().equals(SpecialFields.ROCKET));
    }

    /**
     * Getter for the position of the planet
     *
     * @return coordinates of the planet
     */
    protected Grids getPlanetPosition() {
        return planetPosition;
    }

    /**
     * Adds the cell at given coordinates to the list of dices placed positions
     *
     * @param pos coordinates of the cell
     */
    protected void placeDiceOnBoard(int[] pos) {
        dicePlaced.add(new Grids(pos[ZERO], pos[ONE]));
    }

    /**
     * Sorts the list given according to the coordinates
     *
     * @param listToBeSorted list to be sorted
     */
    protected final void sortList(List<Grids> listToBeSorted) {
        Collections.sort(listToBeSorted, (Grids g1, Grids g2) -> {
            int result = Integer.compare(g1.getXPos(), g2.getXPos());
            if (result == ZERO) {
                result = Integer.compare(g1.getYPos(), g2.getYPos());
            }
            return result;
        });
    }

    /**
     * Clears the dice placed positions list and replaces them with cross
     */
    protected void clearDices() {
        this.dicePlaced = new ArrayList<>();
        updateCrossPositions();
    }

    /**
     * Determines if a cell can be occupied by a dice or not
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     *
     * @return true if the cell can be occupied by dice. False if it cannot be
     * occupied
     */
    protected boolean canBeOccupiedByDice(int xPos, int yPos) {
        return ((cells[xPos][yPos] != null)
                && (cells[xPos][yPos].getfieldValue() != null)
                && (!Objects.equals(cells[xPos][yPos].getfieldValue(), ZERO))
                && (!occupiedByDice(new int[]{xPos, yPos})));

    }

    /**
     * Checks if placing a dice on one of the given positions from the list will
     * complete a line. If there are multiple such positions the line with
     * highest points is chosen
     *
     * @param values list of relevant positions where the dice can be placed
     * @return coordinate where the dice must be placed
     */
    protected int[] lineCompleted(List<Grids> values) {
        int maxPoints = ZERO;
        int largestIndex = ZERO;
        for (int i = 0; i < values.size(); i++) {
            // check horizontal line completion
            if (hLineCompletion(values.get(i)) > maxPoints) {
                largestIndex = i;
                maxPoints = hLineCompletion(values.get(i));
            } //check vertical line completion
            if (vLineCompletion(values.get(i)) > maxPoints) {
                largestIndex = i;
                maxPoints = vLineCompletion(values.get(i));
            }
        }
        // found a position to place dice
        if (maxPoints > ZERO) {
            return new int[]{values.get(largestIndex).getXPos(), values.get(largestIndex).getYPos()};
        } // placing a dice will not complete a special line
        else {
            return new int[ZERO];
        }

    }

    /**
     * Checks if the given position is the only empty position of any special
     * horizontal line and return the points associated with the line
     *
     * @param values cell coordinates
     * @return points associated with the special horizontal line. 0 is returned
     * if placing a dice on the cell would not complete a line
     */
    protected int hLineCompletion(Grids values) {
        int points = ZERO;
        // check if the coordinates belong to special horizontal line
        if (belongsToHLine(values.getXPos())) {
            points = checkHLineLastCoordinate(values.getXPos(), values.getYPos());
        }
        return points;
    }

    /**
     * Checks if any special horizontal lines starts with the given x coordinate
     *
     * @param xPos x coordinate
     * @return true if the coordinate belongs to horizontal line else false
     */
    protected boolean belongsToHLine(int xPos) {
        return hLinesStartPosition.contains(xPos);
    }

    /**
     * Checks if the given coordinates are the only coordinates left empty from
     * the special horizontal line. It returns the points associated with this
     * special horizontal line
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @return points associated with the special horizontal line. 0 is returned
     * if placing a dice on the cell would not complete a line
     */
    protected int checkHLineLastCoordinate(int xPos, int yPos) {
        int count = ZERO;
        int chosenY = Integer.MAX_VALUE;
        int points = ZERO;

        /**
         * fetch the start and end positions of the desired horizontal line.
         * Store the y coordinates of the start and end position as we need to
         * iterate between these two coordinates. Format of the buffer obtained
         * : selectLine[0] - y coordinate start selectLine[1] - x coordinate
         * start selectLine[2] - y coordinate end selectLine[3] - x coordinate
         * end selectLine[4] - points allocated
         */
        Integer[] selectLine = selectHLine(xPos);
        int yPosStart = selectLine[ZERO];
        int yPosEnd = selectLine[TWO];

        for (int i = yPosStart; i <= yPosEnd; i++) {
            if (canBeOccupiedByDice(xPos, i)) {
                if (i == yPos) {
                    points = selectLine[FOUR];
                }
                chosenY = i;
                count++;
            }
        }
        // if there is one empty spce, placing a dice on this will complete a line
        if (count == ONE && chosenY == yPos) {
            return points;
        } else {
            return ZERO;
        }
    }

    /**
     * Checks if the given position is the only empty position of any special
     * vertical line and return the points associated with the line
     *
     * @param values cell coordinates
     * @return points associated with the special vertical line. 0 is returned
     * if placing a dice on the cell would not complete a line
     */
    protected int vLineCompletion(Grids values) {
        int points = ZERO;
        if (belongsToVLine(values.getYPos())) {
            points = checkVLineLastCoordinate(values.getXPos(), values.getYPos());
        }
        return points;
    }

    /**
     * Checks if any special vertical lines starts with the given y coordinate
     *
     * @param yPos y coordinate
     * @return true if the coordinate belongs to vertical line else false
     */
    protected boolean belongsToVLine(int yPos) {
        return vLinesStartPosition.contains(yPos);
    }

    /**
     * Checks if the given coordinates are the only coordinates left empty from
     * the special vertical line. It returns the points associated with this
     * special vertical line
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @return points associated with the special vertical line. 0 is returned
     * if placing a dice on the cell would not complete a line
     */
    protected int checkVLineLastCoordinate(int xPos, int yPos) {
        int count = ZERO;
        int chosenX = Integer.MAX_VALUE;
        int points = ZERO;

        Integer[] chosenLine = selectVLine(yPos);

        int xPosStart = chosenLine[ONE];
        int xPosEnd = chosenLine[THREE];

        for (int i = xPosStart; i <= xPosEnd; i++) {
            if (canBeOccupiedByDice(i, yPos)) {
                if (i == xPos) {
                    points = chosenLine[FOUR];
                }
                chosenX = i;
                count++;
            }
        }

        // if there is one empty spce, placing a dice on this will complete a line
        if (count == ONE && chosenX == xPos) {
            return points;
        } else {
            return ZERO;
        }
    }

    /**
     * Fetches the start and end positions of the desired horizontal line.
     *
     * selectLine[0] - y coordinate start selectLine[1] - x coordinate start
     * selectLine[2] - y coordinate end selectLine[3] - x coordinate end
     * selectLine[4] - points allocated
     *
     * @param xPos start coordinate of the horizontal line
     * @return selectLine[0] - y coordinate start; selectLine[1] - x coordinate
     * start; selectLine[2] - y coordinate end; selectLine[3] - x coordinate
     * end; selectLine[4] - points allocated
     */
    protected Integer[] selectHLine(int xPos) {
        Integer[] selectLine = new Integer[FIVE];
        boolean found = false;
        Iterator<Map.Entry<Integer, List<Integer[]>>> iterator
                = horizontalLines.entrySet().iterator();
        while (iterator.hasNext() && !found) {
            Map.Entry<Integer, List<Integer[]>> entry = iterator.next();
            List<Integer[]> startPos = entry.getValue();
            for (int i = 0; i < startPos.size() && !found; i++) {
                if (Objects.equals(startPos.get(i)[ONE], xPos)) {
                    for (int j = 0; j < startPos.get(i).length; j++) {
                        selectLine[j] = startPos.get(i)[j];
                    }
                    selectLine[FOUR] = entry.getKey();
                    found = true;
                }
            }
        }
        return selectLine;
    }

    /**
     * Fetches the start and end positions of the desired vertical line.
     *
     * selectLine[0] - y coordinate start selectLine[1] - x coordinate start
     * selectLine[2] - y coordinate end selectLine[3] - x coordinate end
     * selectLine[4] - points allocated
     *
     * @param yPos start coordinate of the vertical line
     * @return selectLine[0] - y coordinate start; selectLine[1] - x coordinate
     * start; selectLine[2] - y coordinate end; selectLine[3] - x coordinate
     * end; selectLine[4] - points allocated
     */
    protected Integer[] selectVLine(int yPos) {
        Integer[] selectLine = new Integer[FIVE];
        boolean found = false;
        Iterator<Map.Entry<Integer, List<Integer[]>>> iterator
                = verticalLines.entrySet().iterator();
        while (iterator.hasNext() && !found) {
            Map.Entry<Integer, List<Integer[]>> entry = iterator.next();
            List<Integer[]> startPos = entry.getValue();
            for (int i = 0; i < startPos.size() && !found; i++) {
                if (Objects.equals(startPos.get(i)[0], yPos)) {
                    for (int j = 0; j < startPos.get(i).length; j++) {
                        selectLine[j] = startPos.get(i)[j];
                    }
                    selectLine[FOUR] = entry.getKey();
                    found = true;
                }
            }
        }
        return selectLine;
    }

    /**
     * Fetches the bomb cells on which the dices are placed. At the end of each
     * turn we have to check if a dice is placed on a bomb.
     *
     *
     * @return list of bomb positions on which the dices are placed
     */
    protected List<Grids> getBombPositions() {
        List<Grids> totalDices = getDicePlacedPositions();
        List<Grids> bombs = new ArrayList<>();
        for (int i = 0; i < totalDices.size(); i++) {
            int xPos = totalDices.get(i).getXPos();
            int yPos = totalDices.get(i).getYPos();
            if (cells[xPos][yPos] != null && cells[xPos][yPos].hasSpecialField()
                    && cells[xPos][yPos].getSpecialField().equals(SpecialFields.BOMB)) {
                bombs.add(new Grids(xPos, yPos));
            }
        }
        return bombs;
    }

    /**
     * Explodes a bomb at given coordinates. Cell value at the given coordinates
     * are set to null. This means no more dice can be placed on this position
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     */
    protected void explodeBombs(int xPos, int yPos) {
        // keep a track of number of bombs exploded
        score.addBomb();
        updateValues(xPos, yPos, null);
        explodedBombs.add(new Grids(xPos, yPos));
    }

    /**
     * Checks if a dice is placed on the flag
     *
     * @return true - dice on placed on flag cell. False - dice is not placed on
     * flag cell
     */
    protected boolean checkFlagPoints() {
        List<Grids> totalDices = getDicePlacedPositions();
        boolean found = false;
        for (int i = 0; i < totalDices.size() && !found; i++) {
            int xPos = totalDices.get(i).getXPos();
            int yPos = totalDices.get(i).getYPos();
            if (cells[xPos][yPos] != null && cells[xPos][yPos].hasSpecialField()
                    && cells[xPos][yPos].getSpecialField().equals(SpecialFields.FLAG)) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Adds the flag points to the score when dice is placed on the flag cell.
     * Also store the position at which the flag was reached
     *
     * @param position rank at which the flag was reached. This relative with
     * other players
     */
    protected void addFlagPoints(int position) {
        score.addFlagScore(flagPoints.get(ZERO));
        // store the position at which the flag was reached
        flagReachedAs = position;
    }

    /**
     * Getter for the rank at which the flag was reached.
     *
     * @return
     */
    protected int getFlagReachedPosition() {
        return flagReachedAs;
    }

    /**
     * Removes the top most flag points from the list
     */
    protected void addFlagPointsFromFile() {
        if (!flagPoints.isEmpty()) {
            score.addFlagScore(flagPoints.get(ZERO));
        }
    }

    /**
     * Getter for flag points list
     *
     * @return integer list of the flag points
     */
    protected List<Integer> getFlagPointList() {
        return flagPoints;
    }

    /**
     * Getter for the points gained on reaching the flag
     *
     * @return flag points
     */
    protected int getFlagPoint() {
        return score.getFlagPoints();
    }

    /**
     * Once the flag is reached by a player/s the points obtained are now
     * removed from the list
     *
     * @param count number of points to be removed from the list
     */
    protected void removeFlagPoints(int count) {
        int i = ONE;
        // only the available number of points can be removed
        if (count > flagPoints.size()) {
            count = flagPoints.size();
        }
        while (i <= count) {
            flagPoints.remove(ZERO);
            i++;
        }
    }

    /**
     * Removes the dice from the list of the list of dice placed. This is used
     * when the human player returns back a dice placed on the board
     *
     * @param pos the coordinates to be removed
     */
    protected void removeDice(int[] pos) {
        Grids tobeRemoved = new Grids(pos[ZERO], pos[ONE]);
        if (dicePlaced.contains(tobeRemoved)) {
            dicePlaced.remove(tobeRemoved);
        }
    }

    /**
     * Getter for the points gained by a player
     *
     * @return points
     */
    protected int getTotalScore() {
        return score.calculatePoints(cells, horizontalLines, verticalLines,
                puzzlePositionsCount);
    }

    /**
     * Getter for the list of coordinates where the bombs are exploded
     *
     * @return list of bomb exploded coordinates
     */
    protected List<Grids> getExplodedBombsList() {
        return this.explodedBombs;
    }

    /**
     * Copies the current status of the players board. This is used for saving
     * the game
     *
     * @param status status of the player
     * @return new player with the current status saved
     */
    protected Players getPlayer(boolean status) {
        List<Grids> checkedGrids = deepCopyList(crossedPositions);
        List<Grids> diceOn = deepCopyList(dicePlaced);
        List<Grids> exploded = deepCopyList(explodedBombs);
        return new Players(status, checkedGrids, diceOn, exploded, flagReachedAs);
    }

    /**
     * Makes a deep copy of the given list
     *
     * @param originalList original list
     * @return duplicated list
     */
    private List<Grids> deepCopyList(List<Grids> originalList) {
        List<Grids> cloneList = new ArrayList<>();
        if (originalList != null) {
            Iterator<Grids> iterator = originalList.iterator();
            while (iterator.hasNext()) {
                cloneList.add((Grids) iterator.next());
            }
        }
        return cloneList;
    }

    /**
     * Updates the crossed cell positions, the dice placed positions and the
     * bomb exploded positions. Used to set up the board from a loaded file
     *
     * @param crossedCells crossed cell positions
     * @param dicePlaced dice placed positions
     * @param exploded bomb exploded positions
     * @param flagReachedPosition
     */
    protected void updateData(List<Grids> crossedCells, List<Grids> dicePlaced,
            List<Grids> exploded, int flagReachedPosition) {
        if (crossedCells != null) {
            updateCrossedCellPosition(crossedCells);
        }
        if (dicePlaced != null) {
            this.dicePlaced = deepCopyList(dicePlaced);
        }
        if (exploded != null) {
            updateBombExplodes(exploded);
        }
        this.flagReachedAs = flagReachedPosition;
    }

    /**
     * Makes a deep copy of the crossed cell list and updates the values to zero
     *
     * @param crossedCells crossed Cells list
     */
    private void updateCrossedCellPosition(List<Grids> crossedCells) {
        this.crossedPositions = deepCopyList(crossedCells);
        for (int i = 0; i < crossedCells.size(); i++) {
            int xPos = crossedCells.get(i).getXPos();
            int yPos = crossedCells.get(i).getYPos();
            updateValues(xPos, yPos, ZERO);
        }
    }

    /**
     * Makes a deep copy of the exploded bomb list and sets the value to null
     *
     * @param exploded exploded bomb list
     */
    private void updateBombExplodes(List<Grids> exploded) {
        this.explodedBombs = deepCopyList(exploded);
        for (int i = 0; i < exploded.size(); i++) {
            int xPos = exploded.get(i).getXPos();
            int yPos = exploded.get(i).getYPos();
            score.addBomb();
            updateValues(xPos, yPos, null);
        }
    }

    /**
     * Checks if there is a flag at the given coordinates
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @return true - flag present. False- flag not present
     */
    protected boolean checkForFlag(int xPos, int yPos) {
        boolean found = false;
        if (cells[xPos][yPos] != null && cells[xPos][yPos].hasSpecialField()) {
            found = cells[xPos][yPos].getSpecialField().equals(SpecialFields.FLAG);
        }
        return found;
    }

    /**
     * Getter for the flag points
     *
     * @return flag point
     */
    protected int getFlagPoints() {
        if (flagPoints.size() > ZERO) {
            return flagPoints.get(ZERO);
        } else {
            return ZERO;
        }
    }

    /**
     * Getter for the detailed score statistics of the player
     *
     * @return playerScore score statistics
     */
    protected PlayerScore getScoreStatistics() {
        return this.score;
    }

    /**
     * Getter for the position of the puzzle in the given coordinates
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @return puzzle position value merged into a single value.
     */
    protected int getPuzzlePosition(int xPos, int yPos) {
        return puzzlePositionsCount.get(Integer.parseInt(xPos + "" + yPos));
    }

    /**
     * Getter for the position of the key in the given coordinates
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @return key position value
     */
    protected int getkeyPosition(int xPos, int yPos) {
        return keyPositionsCount.get(Integer.parseInt(xPos + "" + yPos));
    }

    /**
     * Places dice on every cell of the board. This is used only for testing
     */
    protected void exhaustFields() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] != null) {
                    placeDiceOnBoard(new int[]{i, j});
                }
            }
        }
        checkForAvaialbleFields();
    }

    /**
     * Retrieves the cell coordinates where the cell value is null. This is used
     * by the game class when a file is loaded during load game to check if any
     * of the parameters are using the null cells
     *
     * @return list of coordinates of the null cells
     */
    protected List<Grids> getNullCellCoordinates() {
        List<Grids> nullCells = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == null) {
                    nullCells.add(new Grids(i, j));
                }
            }
        }
        return nullCells;
    }

    /**
     * Retrieves the cell coordinates where the bomb value is not present. This
     * is used by the game class when a file is loaded during load game to check
     * if any of the parameters are using the non bomb cells cells
     *
     * @return list of cells which do not contain bomb
     */
    protected List<Grids> getInvalidBombCellCoordinates() {
        List<Grids> bombCells = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == null) {
                    bombCells.add(new Grids(i, j));
                } else if (!cells[i][j].hasSpecialField()) {
                    bombCells.add(new Grids(i, j));
                } else if (cells[i][j].hasSpecialField()
                        && !cells[i][j].getSpecialField().equals(SpecialFields.BOMB)) {
                    bombCells.add(new Grids(i, j));
                }
            }
        }
        return bombCells;
    }

    /**
     * Retrieves the cell coordinates where the flag value is present. This is
     * used by the game class when a file is loaded during load game to validate
     * the flag positions
     *
     * @return list of cells which do not contain bomb
     */
    protected Grids getFlagPositions() {
        boolean found = false;
        Grids flagCoordinates = null;
        for (int i = 0; i < cells.length && !found; i++) {
            for (int j = 0; j < cells[i].length && !found; j++) {
                if (cells[i][j] != null && cells[i][j].hasSpecialField()
                        && cells[i][j].getSpecialField().equals(SpecialFields.FLAG)) {
                    flagCoordinates = new Grids(i, j);
                    found = true;
                }
            }
        }
        return flagCoordinates;
    }
}
