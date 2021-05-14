package logic;

import java.util.List;

/**
 * Position of the special horizontal line and the points associated with them.
 * This class describes the start and end positions of special horizontal lines
 *
 * @author SHWETHA
 */
public class HorizontalLines {

    private int points;

    List<Grids> positions;

    /**
     * Getter for the points gained on completion of the horizontal line
     *
     * @return points associated with the line
     */
    protected int getLinePoints() {
        return points;
    }

    /**
     * Getter for the list of special horizontal line positions
     *
     * @return list of special horizontal line positions
     */
    protected List<Grids> getLinePositions() {
        return positions;
    }

    /**
     * Getter for the start position of special horizontal line
     *
     * @return coordinates of the start position of special horizontal line
     */
    protected Grids getStartPosition() {
        return positions.get(0);
    }

    /**
     * Getter for the end position of special horizontal line
     *
     * @return coordinates of the end position of special horizontal line
     */
    protected Grids getEndPosition() {
        return positions.get(1);
    }
}
