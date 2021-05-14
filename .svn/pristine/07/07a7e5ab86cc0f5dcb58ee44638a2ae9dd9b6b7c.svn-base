package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import logic.exception.InvalidJSONFileException;
import logic.exception.JsonInvalidException;

/**
 * This class describes all the components of the game including the player's
 * board, dice on the table
 *
 * @author SHWETHA
 */
public class Game {

    private int level;

    private GUIConnector gui;

    private LogData logData;

    private List<Integer> arrayOfDices;

    private int roundNumber;

    private int turnNumber;

    private int currentPlayer;

    private int diceNumber;

    private int numberPlayers;

    private Board[] players;

    private boolean[] status;

    private int maxRounds;

    private boolean rollAgain;

    private List<Grids> humanValidClick;

    private final ErrorHandler errorHandler;

    private int playerToReachFlag;

    /*
    array representing the possible number of rounds
     */
    private final int[] numberRounds = new int[]{3, 4, 6};

    /*
    array representing the possible number of dices
     */
    private final int[] numberDice = new int[]{7, 10, 13};

    /**
     * Constant representing the maximum pip value of the dice
     */
    private final int MAXDICEVALUE = 6;
    /**
     * Array of string containing the parameter categories that will be obtained
     * from a file during load game operation
     */

    private final String[] loadFileParameters = new String[]{"CHECKED CELLS",
        "DICE PLACED CELLS", "EXPLODED CELLS"};

    /**
     * Array of string containing the player names
     */
    private final String[] playerNames = new String[]{"Human Player", "AI Player 1",
        "AI Player 2", "AI Player 3"};

    /**
     * Constant representing the number 0
     */
    private final int ZERO = 0;
    /**
     * Constant representing the number 1
     */
    private final int ONE = 1;
    /**
     * Constant representing the number 2
     */
    private final int TWO = 2;
    /**
     * Constant representing the number 3
     */
    private final int THREE = 3;
    /**
     * Constant representing the number 4
     */
    private final int FOUR = 4;

    /**
     * Constructor to create a game
     *
     * @param level chosen level of the game
     * @param numberPlayers chosen number of players of the game
     * @param gui GUI connector
     * @param log true - log the moves. False - do not log the moves. This field
     * is added for testing purpose. It is set to false for testing
     * @throws InvalidJSONFileException Any errors that occur while loading the
     * level JSON file
     * @throws IOException Any errors that occur whenever input/output operation
     * is failed
     * @throws URISyntaxException Any errors that occur when parsing a string as
     * a URI reference
     */
    public Game(int level, int numberPlayers, GUIConnector gui, boolean log) throws InvalidJSONFileException, IOException, URISyntaxException {
        this.level = level;
        this.numberPlayers = numberPlayers;
        roundNumber = ONE;
        turnNumber = ONE;
        this.gui = gui;
        errorHandler = new ErrorHandler();
        this.arrayOfDices = new ArrayList<>();
        gui.resetBoards();
        initialiseParameters(log);
        for (int i = ZERO; i < numberPlayers; i++) {
            status[i] = true;
        }
    }

    /**
     * Constructor for testing
     *
     * @param level chosen level of the game
     * @param numberPlayers chosen number of players of the game
     * @param gui GUI connector
     * @param dices the array of dices on the table
     * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     * @throws java.io.IOException Any errors that occur whenever input/output
     * operation is failed
     * @throws java.net.URISyntaxException Any errors that occur when parsing a
     * string as a URI reference
     */
    protected Game(int level, int numberPlayers, GUIConnector gui,
            ArrayList<Integer> dices) throws InvalidJSONFileException, IOException, URISyntaxException {
        this(level, numberPlayers, gui, false);
        arrayOfDices.addAll(dices);
    }

    /**
     * Initialises all the parameters required to start a new game
     *
     * @param log true - log the moves. False - do not log the moves. This field
     * is added for testing purpose. It is set to false for testing
     * * @throws logic.exception.InvalidJSONFileException Any errors that occur
     * while loading the level JSON file
     * @throws java.io.IOException Any errors that occur whenever input/output
     * operation is failed
     * @throws java.net.URISyntaxException Any errors that occur when parsing a
     * string as a URI reference
     */
    private void initialiseParameters(boolean log) throws InvalidJSONFileException, IOException, URISyntaxException {
        this.currentPlayer = ZERO;
        playerToReachFlag = ONE;
        this.maxRounds = getRounds(numberPlayers);
        this.diceNumber = getDiceNumber(numberPlayers);
        status = new boolean[numberPlayers];
        rollAgain = false;
        this.humanValidClick = new ArrayList<>();
        gui.resetFields();
        gui.setPlayerBoards(numberPlayers);
        players = new Board[numberPlayers];
        for (int i = ZERO; i < numberPlayers; i++) {
            players[i] = new Board(retrieveJSONData(level));
        }
        if (log) {
            logData = new LogData(level, numberPlayers);
        }
        gui.showWarning(false);
        gui.updateRound(roundNumber);
        gui.updateTurn(turnNumber);
        gui.enableRoll(true);
        gui.enableDropOut(false);
        loadBoard(players[ZERO].getCells());
        if (players[ZERO].getHorizontalStartPositions() != null) {
            gui.loadHorizontalLines(players[ZERO].getHorizontalStartPositions());
        }
        if (players[ZERO].getVerticalStartPositions() != null) {
            gui.loadVerticalLines(players[ZERO].getVerticalStartPositions());
        }
        gui.loadDices(arrayOfDices);
    }

    /**
     * Loads a game from the given file
     *
     * @param file file which contains the necessary information to start a game
     * @param log true - log the moves. False - do not log the moves. This field
     * is added for testing purpose. It is set to false for testing
     * @throws InvalidJSONFileException Any errors that occur while loading the
     * given file
     * @throws IOException Any errors that occur whenever input/output operation
     * is failed
     * @throws URISyntaxException Any errors that occur when parsing a string as
     * a URI reference
     * @throws FileNotFoundException File with the specified path name does not
     * exist
     */
    public void loadGame(File file, boolean log) throws InvalidJSONFileException,
            IOException,
            URISyntaxException, FileNotFoundException {
        try {
            Reader read = new FileReader(file);
            Gson gson = new Gson();
            Status currentStatus = gson.fromJson(read, Status.class);
            validateLoadedFileData(currentStatus);
            gui.clearLog();
            gui.resetBoards();
            loadGameFromFile(currentStatus, log);
        } catch (JsonParseException ex) {
            throw new JsonInvalidException(ex);
        }
    }

    /**
     * Initialises the required parameters and also prepares the game when a
     * game is loaded from a previously saved file
     *
     * @param currentStatus status of the previously saved game
     * @param log true - log the moves. False - do not log the moves. This field
     * is added for testing purpose. It is set to false for testing
     * @throws IOException Any errors that occur whenever input/output operation
     * is failed
     * @throws InvalidJSONFileException Any errors that occur while loading the
     * level JSON file
     * @throws URISyntaxException Any errors that occur when parsing a string as
     * a URI reference
     */
    protected void loadGameFromFile(Status currentStatus, boolean log) throws IOException, InvalidJSONFileException, URISyntaxException {
        this.level = currentStatus.getLevel();
        this.numberPlayers = currentStatus.getPlayers().size();
        this.roundNumber = currentStatus.getRoundNumber();
        this.turnNumber = currentStatus.getTurnNumber();
        this.arrayOfDices = deepCopyList(currentStatus.getDices());
        initialiseParameters(log);
        loadData(currentStatus);
        playAfterLoad();
    }

    /**
     * Validates if a level file selected by the user is valid. For this a
     * instance of a board with the selected level number is created. If the
     * corresponding level files has errors a relevant exception will be thrown.
     * This is used when the user decides to load a new game while a game is
     * ongoing. If the level file for the new game is invalid, the user must go
     * back to the previous game. Hence it is necessary to check if the new game
     * can be loaded or not before aborting the current game
     *
     *
     * @param levelNumber level chosen for the new game
     * @throws InvalidJSONFileException Any errors that occur while loading the
     * level JSON file
     */
    public void validateNewGameStart(int levelNumber) throws InvalidJSONFileException {
        Board board = new Board(retrieveJSONData(levelNumber));
    }

    /**
     * Handles the action after a saved game is loaded. Human player should
     * always make his move after loading a saved game.
     *
     * @throws IOException Any errors that occur whenever input/output operation
     * is failed
     */
    private void playAfterLoad() throws IOException {
        // no dices left on the table
        if (arrayOfDices.isEmpty()) {
            gui.enableRoll(true);
        } else {
            gui.enableRoll(false);
            initialiseHumanPlayerTurn();
        }
    }

    /**
     * Loads the GUI and the board data obtained from the loaded file
     *
     * @param currentStatus status of the game when it was saved
     */
    private void loadData(Status currentStatus) {
        List<Players> gamePlayers = currentStatus.getPlayers();
        List<Integer> flagPosition = new ArrayList<>();
        for (int i = 0; i < gamePlayers.size(); i++) {
            // update the player boards
            players[i].updateData(gamePlayers.get(i).getCheckedCells(),
                    gamePlayers.get(i).getDicePlacedCells(),
                    gamePlayers.get(i).getExplodedCells(),
                    gamePlayers.get(i).getFlagReached());

            // update the baords in the gui
            if (gamePlayers.get(i).getDicePlacedCells() != null) {
                loadDices(gamePlayers.get(i).getDicePlacedCells(), i);
            }
            if (gamePlayers.get(i).getCheckedCells() != null) {
                loadCrosses(gamePlayers.get(i).getCheckedCells(), i);
            }
            if (gamePlayers.get(i).getExplodedCells() != null) {
                loadBombExplodes(gamePlayers.get(i).getExplodedCells(), i);
            }
            flagPosition.add(gamePlayers.get(i).getFlagReached());
            //update the status of each player
            status[i] = gamePlayers.get(i).getStatus();
            if (!status[i]) {
                gui.droppedOutPlayerUpdate(i);
            }
        }
        // next rank to be assigned for a player who reaches flag
        this.playerToReachFlag = Collections.max(flagPosition) + ONE;
        assignFlagReachedPositions();
    }

    /**
     * Assigns flag points to the players based on the order in which they
     * reached flag before saving the game
     */
    private void assignFlagReachedPositions() {
        int count = ZERO;
        for (int i = 1; i < playerToReachFlag; i++) {
            for (int j = 0; j < numberPlayers; j++) {
                if (players[j].getFlagReachedPosition() == i) {
                    players[j].addFlagPointsFromFile();
                    count++;
                }
            }
            if (count > ZERO) {
                for (int k = 0; k < numberPlayers; k++) {
                    players[k].removeFlagPoints(count);
                }
            }
            // reset before checking for the next rank
            count = ZERO;
        }
    }

    /**
     * Getter for the current level of the game
     *
     * @return level number
     */
    protected int getLevel() {
        return this.level;
    }

    /**
     * Getter for number of players playing the game
     *
     * @return number of players
     */
    protected int getNoPlyrs() {
        return this.numberPlayers;
    }

    /**
     * Getter for the current round number of the game
     *
     * @return round number
     */
    protected int getRoundNumber() {
        return this.roundNumber;
    }

    /**
     * Getter for the current turn number of the game
     *
     * @return turn number
     */
    protected int getTurnNumber() {
        return this.turnNumber;
    }

    /**
     * Retrieves the current status of the given player index
     *
     * @param plyrIdx player index
     * @return true - player is active in the turn. False - player has dropped
     * out of the current game
     */
    protected boolean getPlyrStatus(int plyrIdx) {
        return status[plyrIdx];
    }

    /**
     * Retrieves the board of the given player index
     *
     * @param plyrIdx player index
     * @return board
     */
    protected Board getPlayer(int plyrIdx) {
        return players[plyrIdx];
    }

    /**
     * Updates the dice placed positions on the board of the player specified in
     * the GUI. This data is obtained from the loaded file.
     *
     * @param positions coordinates where the dices are placed
     * @param idx player index to indicate the board on which the dice must be
     * placed
     */
    private void loadDices(List<Grids> positions, int idx) {
        for (int i = 0; i < positions.size(); i++) {
            int xPos = positions.get(i).getXPos();
            int yPos = positions.get(i).getYPos();
            gui.placeDiceOnBoard(players[idx].getCellValue(xPos, yPos),
                    new int[]{xPos, yPos}, idx);
        }
    }

    /**
     * Updates the positions where the cells are crossed in the GUI. This data
     * is obtained from the loaded file.
     *
     * @param positions coordinates where the cells are crossed
     * @param idx player index to indicate the board on which the cross must be
     * marked
     */
    private void loadCrosses(List<Grids> positions, int idx) {
        for (int i = 0; i < positions.size(); i++) {
            int xPos = positions.get(i).getXPos();
            int yPos = positions.get(i).getYPos();
            gui.replaceDicesWithCross(xPos, yPos, idx);
        }
    }

    /**
     * Updates the positions where the bombs have exploded in the GUI. This data
     * is obtained from the loaded file.
     *
     * @param positions coordinates where the cells are exploded
     * @param idx player index to indicate the board on which the explosion must
     * be marked
     */
    private void loadBombExplodes(List<Grids> positions, int idx) {
        for (int i = 0; i < positions.size(); i++) {
            int xPos = positions.get(i).getXPos();
            int yPos = positions.get(i).getYPos();
            gui.explodeBomb(idx, xPos, yPos);
        }
    }

    /**
     * Checks for any errors in the coordinates of the loaded data during load
     * game operation. The coordinates(x and y) must be within the limits
     * predefined
     *
     * @param currentStatus status of the game when it was saved
     * @throws InvalidJSONFileException Any errors that occur while loading the
     * file
     */
    protected void validateLoadedFileData(Status currentStatus) throws InvalidJSONFileException {
        // validate level, turn number and players
        errorHandler.loadedFileRangeCheck(currentStatus);
        // validate round number and the dice on the table
        errorHandler.loadedFileRoundNumberAndDicesCheck(currentStatus,
                getRounds(currentStatus.getPlayers().size()),
                getDiceNumber(currentStatus.getPlayers().size()));
        // validate proper start of a new turn
        errorHandler.validateEmptyDiceTableWithBoards(currentStatus);
        List<Players> gamePlayers = currentStatus.getPlayers();
        // validate the status of the human player
        errorHandler.validateHumanPlayerStatus(gamePlayers.get(ZERO).getStatus());

        for (int i = 0; i < gamePlayers.size(); i++) {
            // validate coordinates of checked cells, dice placed cells and exploded cells
            validiateFieldPositions(gamePlayers.get(i).getCheckedCells(),
                    loadFileParameters[ZERO], getPlayerName(i));
            validiateFieldPositions(gamePlayers.get(i).getDicePlacedCells(),
                    loadFileParameters[ONE], getPlayerName(i));
            validiateFieldPositions(gamePlayers.get(i).getExplodedCells(),
                    loadFileParameters[TWO], getPlayerName(i));

            // coordinates of checked cells, dice placed cells and exploded cells should not overlap
            if (gamePlayers.get(i).getCheckedCells() != null) {
                if (gamePlayers.get(i).getDicePlacedCells() != null) {
                    errorHandler.validateUsedUpCells(gamePlayers.get(i).getCheckedCells(),
                            gamePlayers.get(i).getDicePlacedCells(), loadFileParameters[ONE]);
                }
                if (gamePlayers.get(i).getExplodedCells() != null) {
                    errorHandler.validateUsedUpCells(gamePlayers.get(i).getCheckedCells(),
                            gamePlayers.get(i).getExplodedCells(), loadFileParameters[TWO]);
                }
            }
            if (gamePlayers.get(i).getDicePlacedCells() != null && gamePlayers.get(i).getExplodedCells() != null) {
                errorHandler.validateUsedUpCells(gamePlayers.get(i).getDicePlacedCells(),
                        gamePlayers.get(i).getExplodedCells(), loadFileParameters[TWO]);
            }

            // validate the order in which the flags are reached
            errorHandler.validateFlagReachedOrder(gamePlayers, playerNames);

            /**
             * coordinates of checked cells, dice placed cells and exploded
             * cells should be validated logically. It is not sufficient only to
             * check if these coordinates are within range. Additional logical
             * checks : a null(empty) cell must not be crossed, nor have a die
             * on it and nor be exploded. Bomb must be only exploded on the
             * cells which have bomb. Player could have reached a flag only if
             * the level had a flag. If a player has reached a flag the
             * corresponding flag cell must be crossed
             *
             */
            logicalCoordinatesCheck(currentStatus.getLevel(), currentStatus.getPlayers());
        }
    }

    /**
     * Validates if the list of coordinates of the mentioned value are valid
     *
     * @param positions list of cell coordinates
     * @param value parameter to be validated
     * @param playerName player name
     * @throws InvalidJSONFileException InvalidJSONFileException Any errors that
     * occur while loading the file
     */
    private void validiateFieldPositions(List<Grids> positions, String value, 
            String playerName) throws InvalidJSONFileException {
        if (positions != null) {
            for (int i = 0; i < positions.size(); i++) {
                int xPos = positions.get(i).getXPos();
                int yPos = positions.get(i).getYPos();
                errorHandler.loadedFilePositionsCheck(xPos, yPos, value, playerName);
            }
        }
    }

    /**
     * Determines the number of rounds to be played based on the number of
     * players
     *
     * @param numberPlyrs
     * @return the number of rounds
     */
    public final int getRounds(int numberPlyrs) {
        int rounds = ZERO;
        switch (numberPlyrs) {
            case TWO:
                rounds = numberRounds[TWO];
                break;
            case THREE:
                rounds = numberRounds[ONE];
                break;
            case FOUR:
                rounds = numberRounds[ZERO];
                break;
        }
        return rounds;
    }

    /**
     * Retrieves the dices being played currently
     *
     * @return list of dices
     */
    protected List<Integer> getArrayOfDices() {
        return arrayOfDices;
    }

    /**
     * Retrieves the current player index
     *
     * @return current player index
     */
    protected int getPlayerIdx() {
        return currentPlayer;
    }

    /**
     * Loads the player boards with cells which contain the field value and the
     * special fields
     *
     * @param cells cells to be loaded
     */
    private void loadBoard(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                loadFields(cells[i][j], i, j, true);
            }
        }
    }

    /**
     * Loads the GUI with individual cells
     *
     * @param cells the cell to be loaded into players board in the GUI
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param initialize True - cell is being loaded for the first time during
     * the game initialisation. False - the cell is being loaded after a dice
     * placed on it was removed
     */
    private void loadFields(Cell cells, int xPos, int yPos, boolean initialize) {
        if (cells != null) {
            if (cells.hasSpecialField()) {
                SpecialFields specialField = cells.getSpecialField();
                switch (specialField) {
                    case JEWEL:
                        gui.loadJewel(xPos, yPos, ((Jewel) cells).getPoints(), initialize);
                        break;
                    case BOMB:
                        gui.loadBomb(xPos, yPos, ((Bomb) cells).getPoints(), initialize);
                        break;
                    case FLAG:
                        gui.loadFlag(xPos, yPos, ((Flag) cells)
                                .getFlagPoints(), initialize);
                        break;
                    case KEY:
                        if (((Key) cells).isKey(yPos, xPos)) {
                            gui.loadKey(xPos, yPos, players[ZERO].getkeyPosition(xPos, yPos), initialize);
                        } else {
                            gui.loadKeyHole(xPos, yPos, players[ZERO].getkeyPosition(xPos, yPos), initialize);
                        }
                        break;
                    case PUZZLE:
                        gui.loadPuzzle(xPos, yPos, players[ZERO].getPuzzlePosition(xPos, yPos),
                                ((Puzzle) players[ZERO].getIndividualCell(xPos, yPos)).getPoints(), initialize);
                        break;
                    case ROCKET:
                        gui.loadRocket(xPos, yPos, initialize);
                        break;
                }
            }
            // load integer value
            gui.loadFields(xPos, yPos, cells.getfieldValue(), initialize);
        }
    }

    /**
     * Generates random values for the dices
     *
     * @throws java.io.IOException Any errors that occur whenever input/output
     * operation is failed
     */
    public void determineDiceValues() throws IOException {
        int numberDices;
        // new turn if the dices on the table are empty
        if (arrayOfDices.isEmpty()) {
            numberDices = diceNumber;
        } else {
            // human player decides to roll again
            rollAgain = true;
            numberDices = arrayOfDices.size();
        }
        arrayOfDices = new ArrayList<>();
        Random random = new Random();
        for (int i = ZERO; i < numberDices; i++) {
            arrayOfDices.add(random.nextInt(MAXDICEVALUE - ONE + ONE) + ONE);
        }
        Collections.sort(arrayOfDices);
        gui.loadDices(arrayOfDices);
        gui.enableRoll(false);
        if (logData != null) {
            if (rollAgain) {
                // log the new dice values
                logData.logMoves(getPlayerName(currentPlayer), null, false,
                        true, false, arrayOfDices, ZERO, null, false, null);
                gui.logMoves(logData.getLatestLog());
            } else {
                logData.logMoves(getPlayerName(currentPlayer), null, false,
                        false, true, arrayOfDices, ZERO, null, false, null);
                gui.logMoves(logData.getLatestLog());
            }
        }
    }

    /**
     * Determines all the neighbours of the crossed positions or the dice placed
     * positions. After computing all the possible neighbours it filters out to
     * determine only the relevant neighbours
     *
     * @param newTurn true- new turn begins, determine neighbours of crossed
     * positions. False - determine neighbours of dice placed positions
     * @return list of neighbours
     */
    public List<Grids> determineNeighbours(boolean newTurn) {
        List<Grids> relevantNeighbours = players[currentPlayer].getCrossedNeighbours(newTurn, arrayOfDices);
        if (currentPlayer == ZERO) {
            highlightHumanFields(relevantNeighbours);
        }
        return relevantNeighbours;
    }

    /**
     * Highlights the relevant neighbours of the human player
     *
     * @param relevantNeighbours
     */
    protected void highlightHumanFields(List<Grids> relevantNeighbours) {
        for (int i = 0; i < relevantNeighbours.size(); i++) {
            int xPos = relevantNeighbours.get(i).getXPos();
            int yPos = relevantNeighbours.get(i).getYPos();
            gui.higlightField(xPos, yPos);
        }
    }

    /**
     * Handles the turn of the human player
     *
     * @param pos the coordinates of the selected field by the human player
     * @throws java.io.IOException Any errors that occur whenever input/output
     * operation is failed
     */
    public void playerTurn(int[] pos) throws IOException {
        gui.showWarning(false);
        if (currentPlayer == ZERO && status[currentPlayer]) {
            if (rollAgain) {
                if (handleRollAgainOption(pos)) {
                    // human player returns one of the dices
                    players[currentPlayer].removeDice(pos);
                    gui.resetHumanPlayerCell(pos[ZERO], pos[ONE]);
                    // load the original cell after returning the die
                    loadFields(players[ZERO].getIndividualCell(pos[ZERO], pos[ONE]),
                            pos[ZERO], pos[ONE], false);
                    int diceValue = players[currentPlayer]
                            .getIndividualCell(pos[ZERO], pos[ONE]).getfieldValue();
                    // add the die back to the table
                    arrayOfDices.add(diceValue);
                    Collections.sort(arrayOfDices);
                    if (logData != null) {
                        logData.humanPlayerDiceReturnLog(pos, arrayOfDices, diceValue);
                        gui.logMoves(logData.getLatestLog());
                    }
                    gui.loadDices(arrayOfDices);
                    rollAgain = false;
                    gui.disableLoadOption(false);
                    gui.disableSaveOption(false);
                    handleMove();
                } else {
                    gui.showWarning(true);
                }
            } else {
                if (handleHumanPlayerClick(pos)) {
                    placeDiceOnBoard(pos);
                } else {
                    gui.showWarning(true);
                }
            }
        }
    }

    /**
     * Checks if the die selected by the human player to return back is valid
     *
     * @param pos the coordinates of the selected dice
     * @return true - the selected die is valid. False - the selected die is
     * invalid
     */
    private boolean handleRollAgainOption(int[] pos) {
        boolean match = false;
        List<Grids> dicePlaced = players[currentPlayer].getDicePlacedPositions();
        for (int i = ZERO; i < dicePlaced.size() && !match; i++) {
            if (dicePlaced.get(i).getXPos() == pos[ZERO]
                    && dicePlaced.get(i).getYPos() == pos[ONE]) {
                match = true;
            }
        }
        return match;
    }

    /**
     * Handles the turn of AI players. Rolls the dice if it is a new turn. Else
     * selects a cell according to the pre defined preference and places the die
     * on the board
     *
     * @param currPlayer index of current player
     * @param diceRoll true - new turn. False - not a new turn
     * @throws java.io.IOException Any errors that occur whenever input/output
     * operation is failed
     */
    public void playerTurn(int currPlayer, boolean diceRoll) throws IOException {
        if (currPlayer != ZERO && status[currPlayer]) {
            // new turn
            if (diceRoll) {
                determineDiceValues();
                gui.loadDices(arrayOfDices);
            }
            int[] cell = handleAITurn();
            if (cell.length == ZERO) {
                dropOutTurn();
            } else {
                placeDiceOnBoard(cell);
            }
        } else {
            currentPlayer = (currentPlayer + ONE) % numberPlayers;
            if (currentPlayer != ZERO) {
                playerTurn(currentPlayer, false);
            } else {
                initialiseHumanPlayerTurn();
            }
        }
    }

    /**
     * Checks if the cell selected by the human player can be occupied by a dice
     *
     * @param pos the coordinates of the selected cell
     * @return true - if the selected position is valid. False - if the selected
     * field is invalid
     */
    private boolean handleHumanPlayerClick(int[] pos) {
        boolean match = false;
        for (int i = ZERO; i < humanValidClick.size() && !match; i++) {
            if (humanValidClick.get(i).getXPos() == pos[ZERO]
                    && humanValidClick.get(i).getYPos() == pos[ONE]) {
                if (!players[currentPlayer].occupiedByDice(pos)) {
                    match = true;
                }
            }
        }
        return match;
    }

    /**
     * Replaces all the dice on the board with cross
     */
    private void replaceDices() {
        for (int i = 0; i < players.length; i++) {
            List<Grids> positions = players[i].getDicePlacedPositions();
            for (int j = 0; j < positions.size(); j++) {
                int xPos = positions.get(j).getXPos();
                int yPos = positions.get(j).getYPos();
                gui.replaceDicesWithCross(xPos, yPos, i);
                // if there is a die on the satellite mark the planet with a cross
                if (players[i].checkSatelliteLanding(xPos, yPos)) {
                    Grids planet = players[i].getPlanetPosition();
                    if (planet != null) {
                        gui.replaceDicesWithCross(planet.getXPos(), planet.getYPos(), i);
                    }
                }
            }
            players[i].replaceDices();
        }
    }

    /**
     * Places a dice in the specified position and handles the subsequent steps
     *
     * @param pos the coordinates where the dice has to be placed
     * @throws java.io.IOException Any errors that occur whenever input/output
     * operation is failed
     */
    protected void placeDiceOnBoard(int[] pos) throws IOException {
        Integer value = players[currentPlayer].getCellValue(pos[ZERO], pos[ONE]);
        int flagPoints = ZERO;

        if (value != null) {
            handleDicePlacement(value);
            gui.placeDiceOnBoard(value, pos, currentPlayer);
            players[currentPlayer].placeDiceOnBoard(pos);
            // check if flag is reached
            if (players[currentPlayer].checkForFlag(pos[ZERO], pos[ONE])) {
                flagPoints = players[currentPlayer].getFlagPoints();
            }
            if (logData != null) {
                logData.logMoves(getPlayerName(currentPlayer), pos, false,
                        false, false, null, flagPoints,
                        players[currentPlayer].getIndividualCell(pos[ZERO], pos[ONE]),
                        false, null);
                gui.logMoves(logData.getLatestLog());
            }
            handleMove();
        }
    }

    /**
     * Determines if the game has ended or not. If the game has ended fetches
     * the winner name
     *
     * @return true if the game ends. False if the game not ended yet
     * @throws java.io.IOException Any errors that occur whenever input/output
     * operation is failed
     */
    protected boolean checkGameEnd() throws IOException {
        boolean gameEnd = false;
        // rounds reached the threshold or player's fileds exhausted
        if (roundNumber > maxRounds || playersFieldEmpty()) {
            gameEnd = true;
            if (roundNumber > maxRounds) {
                turnNumber = turnNumber - ONE;
            }
            gui.updateTurn(turnNumber);
            gui.enableDropOut(false);
            gui.enableRoll(false);
            gui.disableSaveOption(true);
            LinkedHashMap<String, PlayerScore> stats = new LinkedHashMap<>();
            for (int i = 0; i < players.length; i++) {
                String name = getPlayerName(i);
                PlayerScore score = players[i].getScoreStatistics();
                stats.put(name, score);
            }
            List<String> winner = getWinnerName(getPlayersScore());
            if (logData != null) {
                logData.logMoves(null, null, false,
                        false, false, null, ZERO, null, false, winner);
                gui.logMoves(logData.getLatestLog());
            }
            gui.gameEnd(stats, winner);
        }
        return gameEnd;
    }

    /**
     * Checks if any of the player board cells are exhausted
     *
     * @return true - cells exhausted. False - cells not exhausted
     */
    protected boolean playersFieldEmpty() {
        boolean empty = false;
        for (int i = 0; i < players.length && !empty; i++) {
            if (players[i].fieldsEmpty) {
                empty = true;
            }
        }
        return empty;
    }

    /**
     * Fetches the total score of every player
     *
     * @return array of total scores of all the players
     */
    private int[] getPlayersScore() {
        int[] totalScore = new int[numberPlayers];
        for (int i = 0; i < totalScore.length; i++) {
            totalScore[i] = players[i].getTotalScore();
        }
        return totalScore;
    }

    /**
     * Retrieves the name(s) of the winner(s)
     *
     * @param totalScore array of total score of all the players
     * @return list of winners
     */
    protected List<String> getWinnerName(int[] totalScore) {
        List<String> winners = new ArrayList<>();
        // Fetch the indices of the player(s) with highest points
        List<Integer> winnerIndices = fetchLargestScore(totalScore);

        // Fetch the names of the players corresponding to their indices
        for (int i = 0; i < winnerIndices.size(); i++) {
            String name = getPlayerName(winnerIndices.get(i));
            winners.add(name);
        }
        return winners;
    }

    /**
     * Retrieves the list of indices of player(s) with highest points. If there
     * is a tie, a tie breaker is invoked and the player(s) with maximum crossed
     * cells win
     *
     * @param scores array of total score of all the players
     * @return list of player(s) indices who have won
     */
    protected List<Integer> fetchLargestScore(int[] scores) {
        int largest = ZERO;
        List<Integer> equalScores = new ArrayList<>();
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[largest]) {
                largest = i;
            }
        }
        // check if multiple players share the highest score
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == scores[largest]) {
                equalScores.add(i);
            }
        }
        // multiple players share the same highest scare
        if (!equalScores.isEmpty() && equalScores.size() > ONE) {
            // invoke tie breaker
            return tieBreaker(equalScores);
        } else {
            List<Integer> winnerIndex = new ArrayList<>();
            winnerIndex.add(largest);
            return winnerIndex;
        }
    }

    /**
     * Compares the checked cells of the players as a tie breaker. When there is
     * a tie between players, the number of checked cells of each player is
     * checked. The player with fewer number of checked cells wins. If there is
     * a tie between checked cells the victory is shared
     *
     * @param playerIndices indices of the players who share the same total
     * score
     * @return indices of winners
     */
    protected List<Integer> tieBreaker(List<Integer> playerIndices) {
        int[] checkedCellsCount = new int[playerIndices.size()];
        // get the number of crossed cells of players with same highest score
        for (int i = 0; i < playerIndices.size(); i++) {
            checkedCellsCount[i] = players[playerIndices.get(i)].getCrossedPositions().size();
        }
        List<Integer> leastCount = compareCheckedCells(checkedCellsCount);
        List<Integer> winnerIndices = new ArrayList<>();
        for (int j = 0; j < leastCount.size(); j++) {
            winnerIndices.add(playerIndices.get(leastCount.get(j)));
        }
        return winnerIndices;
    }

    /**
     * Retrieves the indices of the player(s) with fewer number of crossed cells
     *
     * @param checkedCellsCount array of checked cells count of players
     * @return indices of player(s) with fewer checked cells
     */
    protected List<Integer> compareCheckedCells(int[] checkedCellsCount) {
        int smallest = ZERO;
        List<Integer> winnerIndices = new ArrayList<>();
        for (int i = 1; i < checkedCellsCount.length; i++) {
            if (checkedCellsCount[i] < checkedCellsCount[smallest]) {
                smallest = i;
            }
        }
        // multiple players have crossed the same number of cells
        for (int i = 0; i < checkedCellsCount.length; i++) {
            if (checkedCellsCount[i] == checkedCellsCount[smallest]) {
                winnerIndices.add(i);
            }
        }
        if (winnerIndices.isEmpty()) {
            winnerIndices.add(smallest);
        }
        return winnerIndices;
    }

    /**
     * Handles the jump operation of a player
     *
     * @throws java.io.IOException Any errors that occur whenever input/output
     * operation is failed
     */
    public void dropOutTurn() throws IOException {
        status[currentPlayer] = false;
        gui.droppedOutPlayerUpdate(currentPlayer);
        if (logData != null) {
            logData.logMoves(getPlayerName(currentPlayer), null, true,
                    false, false, null, ZERO, null, false, null);
            gui.logMoves(logData.getLatestLog());
        }
        // all the players have dropped out
        if (!checkDropOutStatus()) {
            turnNumber++;
            currentPlayer = (turnNumber - ONE) % numberPlayers;
            startNewTurn();
        } else {
            currentPlayer = (currentPlayer + ONE) % numberPlayers;
            if (currentPlayer != ZERO) {
                playerTurn(currentPlayer, false);
            } else {
                initialiseHumanPlayerTurn();
            }
        }
    }

    /**
     * Parses the relevant JSON file using GSON based on the level number
     *
     * @param levelNumber level chosen
     * @throws InvalidJSONFileException Any errors that occur while loading the
     * level file
     *
     * @return parsed data string
     */
    private Reader retrieveJSONData(int levelNumber) throws InvalidJSONFileException {
        String file = "";
        InputStream ipstream;
        switch (levelNumber) {
            case ONE:
                file = "logic/levels/Level1.json";
                break;
            case TWO:
                file = "logic/levels/Level2.json";
                break;
            case THREE:
                file = "logic/levels/Level3.json";
                break;
        }
        ipstream = this.getClass().getClassLoader().getResourceAsStream(file);
        Reader r = new InputStreamReader(ipstream);
        return r;
    }

    /**
     * Selects an appropriate cell according to the described preference for the
     * AI players
     *
     * @return the coordinates of the selected cell
     */
    public int[] handleAITurn() {
        List<Grids> relevantNeighbours = new ArrayList<>();
        relevantNeighbours = determineNeighbours(players[currentPlayer].
                getDicePlacedPositions().isEmpty());
        if (!relevantNeighbours.isEmpty()) {
            return players[currentPlayer].chooseAICell(relevantNeighbours);
        }
        // no relevant neighbour found
        return new int[ZERO];
    }

    /**
     * Checks if all the players have dropped out from their turns
     *
     * @return true if all the players have dropped out. False if all the
     * players have not dropped out
     */
    private boolean checkDropOutStatus() {
        boolean flag = false;
        for (int i = 0; i < numberPlayers && !flag; i++) {
            if (status[i]) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Checks if the current player is the last active player
     *
     * @return true - current player is the last active player. False - current
     * player is not the last active player
     */
    private boolean checkLastActivePlayer() {
        int count = ZERO;
        for (int i = 0; i < numberPlayers; i++) {
            if (status[i]) {
                count++;
            }
        }
        return count == ONE;
    }

    /**
     * Initialises the parameters to start a new turn
     *
     * @throws java.io.IOException Any errors that occur whenever input/output
     * operation is failed
     */
    protected void startNewTurn() throws IOException {
        // Reset the status of the all the players
        for (int i = 0; i < status.length; i++) {
            status[i] = true;
        }
        arrayOfDices = new ArrayList<>();
        gui.loadDices(arrayOfDices);
        replaceBombs();
        checkFlagPoints();
        replaceDices();
        for (Board player : players) {
            player.clearDices();
        }
        gui.resetJump();
        gui.updateTurn(turnNumber);
        gui.enableDropOut(false);
        if (currentPlayer != ZERO && !playersFieldEmpty()) {
            playerTurn(currentPlayer, true);
        } else {
            // new round starts
            if (!playersFieldEmpty()) {
                roundNumber++;
            }
            if (!checkGameEnd()) {
                if (logData != null) {
                    logData.logMoves(null, null, false,
                            false, false, null, ZERO, null, true, null);
                    gui.logMoves(logData.getLatestLog());
                }
                gui.enableRoll(true);
                gui.enableDropOut(false);
                turnNumber = ONE;
                gui.updateTurn(turnNumber);
                gui.updateRound(roundNumber);
                replaceDices();
            }
        }
    }

    /**
     * Initialises the parameters before the human player turn and after the AI
     * players turn. Highlights the relevant neighbouring cells.
     */
    private void initialiseHumanPlayerTurn() throws IOException {
        gui.disableLoadOption(false);
        gui.disableSaveOption(false);
        if (!status[ZERO]) {
            currentPlayer = (currentPlayer + ONE) % numberPlayers;
            playerTurn(currentPlayer, false);
        } else {
            humanValidClick = determineNeighbours(players[currentPlayer].
                    getDicePlacedPositions().isEmpty());
            // fields exhausted
            if (humanValidClick.isEmpty() && players[currentPlayer].fieldsEmpty) {
                gui.enableDropOut(true);
            } else if (humanValidClick.isEmpty() && !rollAgain) {
                gui.enableDropOut(true);
                gui.enableRoll(true);
            }
        }
    }

    /**
     * Handles the actions when the human player rolls the dice
     *
     * @throws IOException Any errors that occur whenever input/output operation
     * is failed
     */
    public void handleRoll() throws IOException {
        gui.enableDropOut(false);
        gui.enableRoll(false);
        gui.disableLoadOption(false);
        gui.disableSaveOption(false);
        humanValidClick = determineNeighbours(players[currentPlayer].getDicePlacedPositions().isEmpty());
        // no relevant neighbours found for human player
        if (humanValidClick.isEmpty()) {
            // human player must return a die
            if (rollAgain) {
                gui.enableDropOut(false);
                gui.enableRoll(false);
                gui.disableLoadOption(true);
                gui.disableSaveOption(true);
                // no die to return
                if (players[currentPlayer].getDicePlacedPositions().isEmpty()) {
                    rollAgain = false;
                    dropOutTurn();
                    handleMove();
                }
            } else {
                gui.enableDropOut(true);
                gui.enableRoll(true);
            }
        } else {
            rollAgain = false;
        }
    }

    /**
     * Enables roll again option. Used only for testing
     */
    protected void enableRollAgain() {
        rollAgain = true;
    }

    /**
     * Retrieves if the dice is rolled for the first time during a turn or not
     *
     * @return true - rolled again by the human player. False- rolled for the
     * first time
     */
    protected boolean getRollAgain() {
        return rollAgain;
    }

    /**
     * Determines the number of dices for the game based on the number of
     * players
     *
     * @param players number of players
     * @return the number of dices
     */
    protected final int getDiceNumber(int players) {
        int count = ZERO;
        switch (players) {
            case TWO:
                count = numberDice[ZERO];
                break;
            case THREE:
                count = numberDice[ONE];
                break;
            case FOUR:
                count = numberDice[TWO];
                break;
        }
        return count;
    }

    /**
     * Handles the move for each player after placing the dice on the board
     *
     * @throws java.io.IOException Any errors that occur whenever input/output
     * operation is failed
     */
    protected void handleMove() throws IOException {
        if (!determinePlayerIndex()) {
            if (currentPlayer != ZERO) {
                playerTurn(currentPlayer, false);
            } else {
                initialiseHumanPlayerTurn();
            }
        } else {
            startNewTurn();
        }

    }

    /**
     * Determines the index of the current players. Also indicates if it is a
     * new turn or not
     *
     * @return true if it is a new turn. False if it is not a new turn
     */
    protected boolean determinePlayerIndex() {
        boolean startNewTurn;
        /**
         * if the dices are empty or current player is the last active player
         * new turn starts
         *
         */
        if (arrayOfDices.isEmpty() || checkLastActivePlayer()) {
            turnNumber++;
            currentPlayer = (turnNumber - ONE) % numberPlayers;
            startNewTurn = true;
        } else {
            currentPlayer = (currentPlayer + ONE) % numberPlayers;
            startNewTurn = false;
        }
        return startNewTurn;
    }

    /**
     * Removes the dice from the array after a player places a dice on the board
     *
     * @param value value of the dice which has to be removed
     */
    protected void handleDicePlacement(int value) {
        int index = arrayOfDices.indexOf(value);
        removeDice(index);
        gui.loadDices(arrayOfDices);
    }

    /**
     * Removes the dice present at the index specified
     *
     * @param index index of the dice to be removed
     */
    protected void removeDice(int index) {
        arrayOfDices.remove(index);
    }

    /**
     * Determines if a cell can be occupied by a dice or not
     *
     * @param cell the cell
     * @return true if the cell can be occupied by dice. False if it cannot be
     * occupied
     */
    protected boolean canBeOcuppiedByDice(Cell cell) {
        return (cell != null && !Objects.equals(cell.getfieldValue(), ZERO));
    }

    /**
     * Checks if the cell given can be occupied by one of the dice in the list
     *
     * @param cellValue integer value of the cell
     * @param dice list of dice values
     * @return true - cell can be occupied by one of the dice. False- cell
     * cannot be occupied by one the dice
     */
    protected boolean diceFitsACell(int cellValue, ArrayList<Integer> dice) {
        boolean found = false;
        for (int i = 0; i < dice.size() && !found; i++) {
            if (Objects.equals(cellValue, dice.get(i))) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Replace the existing bombs with exploded bombs
     */
    protected void replaceBombs() {
        List<Grids> explodedBombs = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            List<Grids> bombs = players[i].getBombPositions();
            for (int j = 0; j < bombs.size(); j++) {
                int xPos = bombs.get(j).getXPos();
                int yPos = bombs.get(j).getYPos();
                if (!explodedBombs.contains(new Grids(xPos, yPos))) {
                    explodedBombs.add(new Grids(xPos, yPos));
                    for (int k = 0; k < players.length; k++) {
                        // check if other players have die placed on the same position
                        if ((k != i) && (!(players[k].occupiedByDice(new int[]{xPos, yPos})))) {
                            players[k].explodeBombs(xPos, yPos);
                            gui.explodeBomb(k, xPos, yPos);
                        }
                    }
                }
            }
        }
    }

    /**
     * Check if any player has reached flag after every turn.
     */
    protected void checkFlagPoints() {
        int count = ZERO;
        List<Integer> playerIndices = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            if (players[i].checkFlagPoints()) {
                players[i].addFlagPoints(playerToReachFlag);
                count++;
            } else {
                playerIndices.add(i);
            }
        }
        if (count > ZERO) {
            playerToReachFlag++;
            /**
             * if any player had reached a flag, the corresponding flag point is
             * removed for all the other players
             *
             */
            for (int i = 0; i < playerIndices.size(); i++) {
                players[playerIndices.get(i)].removeFlagPoints(count);
            }
        }
    }

    /**
     * Retrieves the current rank of flag reached
     *
     * @return flag rank
     */
    protected int getPlayerToReachFlag() {
        return playerToReachFlag;
    }

    /**
     * Handles the actions required when save game option is selected
     *
     * @param f file to which the game status has to be saved
     * @throws IOException Any errors that occur whenever input/output operation
     * is failed
     */
    public void saveGame(File f) throws IOException {
        FileWriter writer = null;
        if (f != null) {
            try {
                writer = new FileWriter(f);
                buildJSONFile(writer);
                writer.flush();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    /**
     * Converts the current java objects to JSON format and writes it to
     * appropriate JSON file
     *
     * @param writer file writer
     */
    protected void buildJSONFile(FileWriter writer) {
        List<Integer> arraycloneList = new ArrayList<>();
        if (arrayOfDices != null) {
            Iterator<Integer> iterator = arrayOfDices.iterator();
            while (iterator.hasNext()) {
                arraycloneList.add(iterator.next());
            }
        }

        // save the status of exh player's board
        List<Players> playerStatus = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            playerStatus.add(players[i].getPlayer(status[i]));
        }
        Status s = new Status(level, roundNumber, turnNumber, arraycloneList,
                playerStatus);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(s, writer);
    }

    /**
     * Makes a deep copy of the given list
     *
     * @param originalList original list
     * @return duplicated list
     */
    private List<Integer> deepCopyList(List<Integer> originalList) {
        List<Integer> cloneList = new ArrayList<>();
        if (originalList != null) {
            Iterator<Integer> iterator = originalList.iterator();
            while (iterator.hasNext()) {
                cloneList.add(iterator.next());
            }
        }
        return cloneList;
    }

    /**
     * Retrieves the name of the player corresponding to the specified player
     * index
     *
     * @param playerIdx player index
     * @return name of the player
     */
    private String getPlayerName(int playerIdx) {
        return playerNames[playerIdx];
    }

    /**
     * Adds dice to the list of dice on the table that is available for the
     * players during the game. This is used only for testing
     *
     * @param dices list of dice values
     */
    protected void addDices(List<Integer> dices) {
        arrayOfDices.addAll(dices);
    }

    /**
     * Sets the status of the given player. This is used only for testing
     *
     * @param plyrIdx player index
     * @param dropOutStatus true - player remains active. False - player drops
     * out of the turn
     */
    protected void setStatus(int plyrIdx, boolean dropOutStatus) {
        status[plyrIdx] = dropOutStatus;
    }

    /**
     * Logical validation of the crossed cells, dice placed cells, exploded
     * cells in the loaded file
     *
     * @param levelNumber level of the loaded file
     * @param gamePlayers players of the game
     * @throws InvalidJSONFileException any errors that occur during the logical
     * check
     */
    protected void logicalCoordinatesCheck(int levelNumber, List<Players> gamePlayers) throws InvalidJSONFileException {
        Board board = new Board(retrieveJSONData(levelNumber));
        List<Grids> nullCells = board.getNullCellCoordinates();
        List<Grids> bombCells = board.getInvalidBombCellCoordinates();
        Grids flagCell = board.getFlagPositions();
        for (int i = 0; i < gamePlayers.size(); i++) {
            if (gamePlayers.get(i).getCheckedCells() != null) {
                // null cells must not be crossed
                errorHandler.validateLoadedFileLogicalCoordinates(nullCells,
                        gamePlayers.get(i).getCheckedCells(),
                        loadFileParameters[ZERO], getPlayerName(i));
            }
            // null cells must not have dice on them
            if (gamePlayers.get(i).getDicePlacedCells() != null) {
                errorHandler.validateLoadedFileLogicalCoordinates(nullCells,
                        gamePlayers.get(i).getDicePlacedCells(),
                        loadFileParameters[ONE], getPlayerName(i));
            }
            // bomb can be exploded only on the cells which have bomb
            if (gamePlayers.get(i).getExplodedCells() != null) {
                errorHandler.validateLoadedFileLogicalCoordinates(bombCells,
                        gamePlayers.get(i).getExplodedCells(),
                        loadFileParameters[TWO], getPlayerName(i));
            }
            /**
             * flag can be reached only if the chosen level has flag. If a flag
             * is reached corresponding flag cell must be crossed
             */
            errorHandler.logicalValidationFlagReached(flagCell,
                    gamePlayers.get(i).getFlagReached(),
                    gamePlayers.get(i).getCheckedCells(), getPlayerName(i));
        }
    }
}
