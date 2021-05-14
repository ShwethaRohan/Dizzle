package logic;

import java.util.List;

/**
 * Represents the current status of the game and individual player boards. This
 * class describes the structured data for the status of the current game in
 * exactly the representation as it has to be stored in a file or that is used
 * in an already saved game file.
 *
 *
 * @author SHWETHA
 */
public class Status {

    private final int levelNo;
    private final int round;
    private final int turnOf;
    private final List<Integer> dice;
    private final List<Players> players;

    /**
     * Constructor for updating the status of the current game
     *
     * @param levelNo the current level of the game
     * @param round the current round number of the game
     * @param turnOf the current player to has to make move
     * @param dice list of dice that are present on the table
     * @param players status of individual players
     */
    protected Status(int levelNo, int round, int turnOf, List<Integer> dice, List<Players> players) {
        this.levelNo = levelNo;
        this.round = round;
        this.turnOf = turnOf;
        this.dice = dice;
        this.players = players;
    }

    /**
     * Retrieves the list of individual player's status
     *
     * @return list of player's status
     */
    protected List<Players> getPlayers() {
        return players;
    }

    /**
     * Retrieves the level of the ongoing game
     *
     * @return level of the game
     */
    protected int getLevel() {
        return levelNo;
    }

    /**
     * Retrieves the round number of the ongoing game
     *
     * @return round number
     */
    protected int getRoundNumber() {
        return round;
    }

    /**
     * Retrieves the turn number of the ongoing game
     *
     * @return turn number
     */
    protected int getTurnNumber() {
        return turnOf;
    }

    /**
     * Retrieves the list of dice on the table
     *
     * @return list of dice
     */
    protected List<Integer> getDices() {
        return dice;
    }
}
