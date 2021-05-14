package logic;

import java.util.List;

/**
 * Jewel is specialised Cell. This class describes the position of the Jewels
 * and the points associated with them
 *
 * @author SHWETHA
 */
public class Jewel extends Cell {

    private final int points;
    private List<Grids> positions;

    /**
     * Constructor
     *
     * @param value integer value of the cell which has jewel as special field
     * @param points integer points associated with the jewel
     */
    protected Jewel(Integer value, int points) {
        super(value, SpecialFields.JEWEL);
        this.points = points;
    }

    /**
     * Getter for the jewel point
     *
     * @return points associated with jewel
     */
    protected int getPoints() {
        return points;
    }

    /**
     * Getter of jewel positions
     *
     * @return list of positions where the jewels are located
     */
    protected List<Grids> getPositions() {
        return positions;
    }
}
