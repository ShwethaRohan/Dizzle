package logic;

import java.util.List;

/**
 * Puzzle is specialised Cell. This class describes the position of the puzzles
 * and the points associated with them
 *
 * @author SHWETHA
 */
public class Puzzle extends Cell {

    private final int points;

    private final List<Grids> positions;

    /**
     * Constructor
     *
     * @param value integer value of the cell which has puzzle as special field
     * @param points points associated with the puzzle
     * @param positions list of positions which contain the puzzle positions
     */
    protected Puzzle(Integer value, int points, List<Grids> positions) {
        super(value, SpecialFields.PUZZLE);
        this.points = points;
        this.positions = positions;
    }

    /**
     * Getter for the puzzle points
     *
     * @return points associated with the puzzle
     */
    protected int getPoints() {
        return points;
    }

    /**
     * Getter for puzzle positions
     *
     * @return list of positions where the puzzle pieces are located
     */
    protected List<Grids> getPositions() {
        return positions;
    }

}
