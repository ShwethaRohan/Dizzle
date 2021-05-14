package logic;

import java.util.List;

/**
 * Bomb is specialised Cell. This class describes the positions of the bombs on
 * the players board.
 *
 * @author SHWETHA
 */
public class Bomb extends Cell {

    private final int points;
    private List<Grids> positions;

    /**
     * Constructor
     *
     * @param value integer value of the cell which has bomb
     * @param points points which would be deducted when bomb is ignited
     */
    protected Bomb(Integer value, int points) {
        super(value, SpecialFields.BOMB);
        this.points = points;
    }

    /**
     * Getter for the bomb point
     *
     * @return points associated on bomb ignition
     */
    protected int getPoints() {
        return points;
    }

    /**
     * Getter of bomb positions
     *
     * @return list of positions where the bombs are located
     */
    protected List<Grids> getPositions() {
        return positions;
    }
}
