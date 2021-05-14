package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import logic.exception.*;

/**
 * This class handles all the errors and formulates the error messages
 *
 * @author SHWETHA
 */
public class ErrorHandler {

    private int maxRows;

    private int maxColumns;

    /**
     * Constant to denote the highest level of the game
     */
    private final int MAX_LEVEL = 3;
    /**
     * Constant to denote the maximum number of players in the game
     */
    private final int MAX_PLAYERS = 4;
    /**
     * Constant to denote the minimum number of players in the game
     */
    private final int MIN_PLAYERS = 2;
    /**
     * Constant to denote the maximum value of a cell
     */
    private final int MAX_FIELD_VALUE = 7;
    /**
     * Constant denote the maximum value of y coordinate
     */
    private final int MAX_COLUMN = 8;
    /**
     * Constant to denote the lowest values of x and coordinates
     */
    private final int ZERO = 0;
    /**
     * Constant to denote the number one. This represents lowest level of the
     * game, minimum value of a cell, minimum value of a die
     */
    private final int ONE = 1;
    /**
     * Constant to denote the number 6. This represents the maximum value of a
     * die, maximum value of the x coordinate value
     */
    private final int SIX = 6;

    /**
     * List that contains all the possible jewel points
     */
    private final List<Integer> jewelPoints = new ArrayList<Integer>() {
        {
            add(1);
            add(2);
            add(3);
        }

    };
    /**
     * List that contains all the points that be gained on completing special
     * horizontal lines
     */
    private final List<Integer> hLinePoints = new ArrayList<Integer>() {
        {
            add(3);
            add(5);
            add(10);
            add(15);
        }

    };
    /**
     * List that contains all the points that be gained on completing special
     * vertical lines
     */
    private final List<Integer> vLinePoints = new ArrayList<Integer>() {
        {
            add(5);
            add(10);
        }

    };

    /**
     * Constructor for creating error handler. This is used by the board class
     * to validate the level file
     *
     * @param rows number of rows in the player board
     * @param columns number of columns in the player board
     */
    protected ErrorHandler(int rows, int columns) {
        this.maxRows = rows;
        this.maxColumns = columns;
    }

    /**
     * Constructor for creating error handler. This is used by the game class to
     * validate the loaded file during the load game operation
     */
    protected ErrorHandler() {
    }

    /**
     * Checks if the coordinates of the special fields in the level file are
     * within the pre determined range
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param fieldType special field type
     * @throws NullObjectException coordinates of special fields are out of
     * bounds
     */
    protected void JSONFilePositionsCheck(int xPos, int yPos, SpecialFields fieldType) throws NullObjectException {
        if (xPos < ZERO || xPos > maxColumns - ONE || yPos < ZERO || yPos > maxRows - ONE) {
            throw new NullObjectException(fieldType.name(), xPos, yPos, "level file");
        }
    }

    /**
     * Checks if the field values of the level file are within the pre
     * determined range
     *
     * @param fieldValue field value of a cell
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @throws OutOfRangeException field value of a cell is invalid
     */
    protected void JSONFileFieldCheck(int fieldValue, int xPos, int yPos) throws OutOfRangeException {
        if (fieldValue < ZERO || fieldValue > MAX_FIELD_VALUE) {
            throw new OutOfRangeException("The field value " + fieldValue + " at position [" + xPos + " ,"
                    + yPos + "] in the level file is out of range");
        }
    }

    /**
     * Checks if the points assigned to jewels, special horizontal and vertical
     * lines are valid
     *
     * @param points points assigned to the special field
     * @param fieldType special field type
     * @throws IllegalPointsException points associated with the special fields
     * are invalid
     */
    protected void JSONFilePointsCheck(int points, SpecialFields fieldType) throws IllegalPointsException {
        switch (fieldType) {
            case JEWEL:
                if (!jewelPoints.contains(points)) {
                    throw new IllegalPointsException(points, fieldType);
                }
                break;
            case HLINE:
                if (!hLinePoints.contains(points)) {
                    throw new IllegalPointsException(points, fieldType);
                }
                break;
            case VLINE:
                if (!vLinePoints.contains(points)) {
                    throw new IllegalPointsException(points, fieldType);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Checks if the parameters obtained from the loaded file are within pre
     * determined range
     *
     * @param currentStatus status of the previously saved game
     * @throws OutOfRangeException Any errors with the parameters of the
     * previously saved game
     */
    protected void loadedFileRangeCheck(Status currentStatus) throws OutOfRangeException {
        // level check
        if (currentStatus.getLevel() < ONE || currentStatus.getLevel() > MAX_LEVEL) {
            throw new OutOfRangeException("The level " + currentStatus.getLevel() + " in the loaded file is invalid");
        } // number of players check 
        else if (currentStatus.getPlayers() == null) {
            throw new OutOfRangeException("No players found in the loaded file");
        } else if (currentStatus.getPlayers().size() < MIN_PLAYERS
                || currentStatus.getPlayers().size() > MAX_PLAYERS) {
            throw new OutOfRangeException("The number of players  "
                    + currentStatus.getPlayers().size() + " in the loaded file are invalid");
        } // turn number check 
        else if (currentStatus.getTurnNumber() <= ZERO
                || currentStatus.getTurnNumber() > currentStatus.getPlayers().size()) {
            throw new OutOfRangeException("The turn number  "
                    + currentStatus.getTurnNumber() + " in the loaded file is invalid");
        }
    }

    /**
     * Checks if the round number and the dice on the table obtained from the
     * loaded file are valid
     *
     * @param currentStatus status of the game
     * @param maxRounds maximum rounds possible for the level
     * @param maxDices maximum dice possible for the number of players
     * @throws OutOfRangeException Any errors with round number and the dice on
     * the table
     */
    protected void loadedFileRoundNumberAndDicesCheck(Status currentStatus,
            int maxRounds, int maxDices) throws OutOfRangeException {
        // round number check
        if (currentStatus.getRoundNumber() <= ZERO || currentStatus.getRoundNumber() > maxRounds) {
            throw new OutOfRangeException("The round number  "
                    + currentStatus.getRoundNumber() + " in the loaded file is invalid");
        }
        List<Integer> dices = currentStatus.getDices();
        // dice on the table entry missing in the loaded file
        if (dices == null) {
            throw new OutOfRangeException("No dice found in the loaded file");
        }
        // number of dice exceeds the limit
        if (dices.size() > maxDices) {
            throw new OutOfRangeException("The number of dices on the table in the loaded file exceeds the limit");
        }
        // check if value of the dice is within predefined range
        for (int i = 0; i < dices.size(); i++) {
            if (dices.get(i) < ONE || dices.get(i) > SIX) {
                throw new OutOfRangeException("The dice value at position " + i + " in the loaded file is invalid");
            }
        }
    }

    /**
     * Checks if the coordinates obtained from the loaded file are within the
     * pre determined range
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param fieldType special field type
     * @param playerName player name
     * @throws NullObjectException coordinates of field specified are out of
     * bounds
     */
    protected void loadedFilePositionsCheck(int xPos, int yPos, String fieldType,
            String playerName) throws NullObjectException {
        if (xPos < ZERO || xPos > SIX || yPos < ZERO || yPos > MAX_COLUMN) {
            String sufffix = "loaded file of the " +playerName;
            throw new NullObjectException(fieldType, xPos, yPos, sufffix);
        }
    }

    /**
     * Checks if the players have reached the flag in the correct order in the
     * loaded file
     *
     * @param gamePlayers players of the game
     * @param playerNames names of the players
     * @throws OutOfRangeException Any errors in the order in which the players
     * have reached the flag
     */
    protected void validateFlagReachedOrder(List<Players> gamePlayers,
            String[] playerNames) throws OutOfRangeException {
        List<Integer> flagPositions = new ArrayList<>();
        for (int i = 0; i < gamePlayers.size(); i++) {
            // flag reached rank greater than number of players
            if (gamePlayers.get(i).getFlagReached() > gamePlayers.size()) {
                throw new OutOfRangeException("Flag reached position of player "
                        + playerNames[i] + " in the loaded file is invalid");
            } else {
                flagPositions.add(gamePlayers.get(i).getFlagReached());
            }
        }

        // flag reached ranks must be alligned. They should be consecutive
        int lastPosition = Collections.max(flagPositions);
        if (lastPosition > 0) {
            for (int j = 1; j < lastPosition; j++) {
                if (!flagPositions.contains(j)) {
                    throw new OutOfRangeException("Flag reached positions in the "
                            + "loaded file are not aligned");
                }
            }
        }
    }

    /**
     * Checks for the status of the human player in the loaded file. The status
     * of the human player cannot be false. Since the game can be saved only
     * during the human player's turn, it is practically impossible for the
     * human player status to be false
     *
     * @param status status of the human player
     * @throws InvalidPlayerRecords human player status is false
     */
    protected void validateHumanPlayerStatus(boolean status) throws InvalidPlayerRecords {
        if (!status) {
            throw new InvalidPlayerRecords("No active Human Player found in the loaded file");
        }
    }

    /**
     * Checks if the provided two lists are unique. The coordinates of the
     * checked cells, dice placed cells and the exploded cells in the loaded
     * file must be unique. The coordinates of these cells must not overlap.
     *
     * @param firstList first list of coordinates
     * @param secondList second list of coordinates
     * @param listTwoName name of the second list
     * @throws NonUniqueCoordinates two lists are not unique
     */
    protected void validateUsedUpCells(List<Grids> firstList, List<Grids> secondList,
            String listTwoName) throws NonUniqueCoordinates {
        if (!Collections.disjoint(firstList, secondList)) {
            throw new NonUniqueCoordinates(listTwoName);
        }
    }

    /**
     * Checks if the players board do no contain dice on the fields when there
     * are no dice left on the table. if there are no dice on the table a new
     * turn begins. During a new turn all the dice placed on the players board
     * are marked with cross. Since only human player can save the game, there
     * cannot be a scenario where there are no dice on the table but dice on
     * players board
     *
     * @param currentStatus status of the game
     * @throws InvalidPlayerRecords Dice on the board but no dice on the table
     */
    protected void validateEmptyDiceTableWithBoards(Status currentStatus) throws InvalidPlayerRecords {
        if (currentStatus.getDices() != null && currentStatus.getDices().isEmpty()) {
            boolean dicePlaced = false;
            List<Players> gamePlayers = currentStatus.getPlayers();
            for (int i = 0; i < gamePlayers.size() && !dicePlaced; i++) {
                if (gamePlayers.get(i).getDicePlacedCells() != null
                        && !gamePlayers.get(i).getDicePlacedCells().isEmpty()) {
                    dicePlaced = true;
                }
            }
            if (dicePlaced) {
                throw new InvalidPlayerRecords("No dice available on the table, "
                        + "but dice placed in the loaded file"
                        + "Invalid scenario");
            }
        }
    }

    /**
     * Checks if the provided two lists are unique. The crossed cells, dice
     * placed cells and exploded cells must be located in the appropriate
     * positions. This is used to validate situations like checked cells, dice
     * placed cells and exploded cells in the loaded file cannot have
     * coordinates of a null cell, the bombs cannot explode on cells when there
     * are no bombs
     *
     * @param firstList first list of coordinates
     * @param secondList second list of coordinates
     * @param listTwoName name of the second list
     * @param playerName player name
     * @throws InvalidPlayerRecords two lists are not unique
     */
    protected void validateLoadedFileLogicalCoordinates(List<Grids> firstList,
            List<Grids> secondList, String listTwoName, String playerName) throws InvalidPlayerRecords {
        if (!Collections.disjoint(firstList, secondList)) {
            throw new InvalidPlayerRecords(listTwoName + " for " + playerName
                    + " located in an inappropriate position in the loaded file");
        }
    }

    /**
     * Checks if a player has reached a flag in a level which has no flag. If a
     * player has reached a flag the corresponding flag cell must be crossed
     *
     * @param flagCell coordinates of the flag cell
     * @param flagRank the rank in which the player has reached the flag
     * @param checkedCells crossed cells on the player's board
     * @param playerName player name
     * @throws InvalidPlayerRecords any errors that occur during the logical
     * check of the flag reached rank
     */
    protected void logicalValidationFlagReached(Grids flagCell, int flagRank,
            List<Grids> checkedCells, String playerName) throws InvalidPlayerRecords {
        // no flag cell but the flag reached
        if (flagCell == null && flagRank > ZERO) {
            throw new InvalidPlayerRecords("Flag reached for a level "
                    + "without flag for " + playerName);
        }
        // flag reached but flag cell not crossed
        if (flagCell != null && flagRank > ZERO) {
            if (!checkedCells.contains(flagCell)) {
                throw new InvalidPlayerRecords("Flag reached but the flag cell "
                        + "not crossed for " + playerName);
            }
        }
    }
}
