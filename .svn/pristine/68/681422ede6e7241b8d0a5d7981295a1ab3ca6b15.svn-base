package logic;

import java.util.List;

/**
 * Flag is specialised Cell. This class describes the position of the flags and
 * the points associated with them
 *
 * @author SHWETHA
 */
public class Flag extends Cell {

    private final List<Integer> points;
    private Grids position;

    /**
     * Constructor
     *
     * @param value integer value of the cell which has flag as special field
     * @param points integer points associated with the flag
     */
    protected Flag(Integer value, List<Integer> points) {
        super(value, SpecialFields.FLAG);
        this.points = points;
    }

    /**
     * Getter for the flag position
     *
     * @return position where the flag is located
     */
    protected Grids getPositions() {
        return position;
    }

    /**
     * Getter for the flag points
     *
     * @return list of points associated with the flag
     */
    protected List<Integer> getFlagPoints() {
        return points;
    }

}
