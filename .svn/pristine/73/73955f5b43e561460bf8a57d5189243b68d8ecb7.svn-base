package logic;

import java.util.List;

/**
 * Represents the current status of a players board. While saving a game it is
 * required to store the status of each players board parameters so that this
 * saved game can be loaded later.
 *
 * @author SHWETHA
 */
public class Players {

    private final boolean active;
    private final List<Grids> checked;
    private final List<Grids> diceOn;
    private final List<Grids> exploded;
    private final int flagReachedAs;

    /**
     * Constructor for updating the parameters of a players board
     *
     * @param active true - player is active in the current turn. False - player
     * has dropped out of the current turn
     * @param checked list of cell positions where the dice were placed in the
     * previous turns
     * @param diceOn list of cell positions where the dice are placed
     * @param exploded list of cells positions where the bomb was ignited in the
     * previous turns
     * @param flagReachedAs point gained by the player on reaching the flag
     */
    protected Players(boolean active, List<Grids> checked, List<Grids> diceOn, List<Grids> exploded, int flagReachedAs) {
        this.active = active;
        this.checked = checked;
        this.diceOn = diceOn;
        this.exploded = exploded;
        this.flagReachedAs = flagReachedAs;
    }

    /**
     * Retrieves the status of the player
     *
     * @return true - player is active in the current round.False - player has
     * dropped out of the turn
     */
    protected boolean getStatus() {
        return active;
    }

    /**
     * Retrieves the list of positions where the dice were placed in previous
     * turns
     *
     * @return list of crossed cell positions
     */
    protected List<Grids> getCheckedCells() {
        return checked;
    }

    /**
     * Retrieves the list of positions where the dice are placed in current turn
     *
     * @return list of dice placed positions
     */
    protected List<Grids> getDicePlacedCells() {
        return diceOn;
    }

    /**
     * Retrieves the list of positions where the bombs were exploded in previous
     * turns
     *
     * @return list of exploded cells
     */
    protected List<Grids> getExplodedCells() {
        return exploded;
    }

    /**
     * Retrieves the points gained on reaching the flag
     *
     * @return flag points
     */
    protected int getFlagReached() {
        return flagReachedAs;
    }
}
