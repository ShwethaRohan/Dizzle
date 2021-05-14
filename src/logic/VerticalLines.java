package logic;

import java.util.List;

/**
 * Position of the special vertical line and the points associated with them.
 * This class describes the start and end positions of special vertical lines
 *
 * @author SHWETHA
 */
public class VerticalLines {

    private int points;

    List<Grids> positions;

    /**
     * Getter for the points gained on completion of the vertical line
     *
     * @return points associated with the line
     */
    protected int getLinePoints() {
        return points;
    }

    /**
     * Getter for the list of special vertical line positions
     *
     * @return list of special vertical line positions
     */
    protected List<Grids> getLinePositions() {
        return positions;
    }

    /**
     * Getter for the start position of special vertical line
     *
     * @return coordinates of the start position of special vertical line
     */
    protected Grids getStartPosition() {
        return positions.get(0);
    }

    /**
     * Getter for the end position of special vertical line
     *
     * @return coordinates of the end position of special vertical line
     */
    protected Grids getEndPosition() {
        return positions.get(1);
    }
}
