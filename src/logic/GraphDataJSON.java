package logic;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Structured data for a graph in exactly the representation that is used in a
 * JSON file. Required for reading in a graph file using the GSON parser.
 *
 * @author SHWETHA
 */
public class GraphDataJSON {

    private List<Integer> field[];
    private List<Jewel> jewels;
    private Bomb bombs;
    private List<Puzzle> puzzles;
    private List<Key> keys;
    private Flag flag;
    private Grids rocket;
    private Grids planet;
    @SerializedName("horizontal-lines")
    private List<HorizontalLines> hlines;
    @SerializedName("vertical-lines")
    private List<VerticalLines> vlines;

    /**
     * Retrieves the field values of each cell of the board. This corresponds
     * the value of the die which can be placed on a particular cell
     *
     * @return list arrays corresponding to the field values
     */
    protected List<Integer>[] getFileds() {
        return field;
    }

    /**
     * Retrieves the list containing jewel positions and points associated with
     * them
     *
     * @return list of jewels
     */
    protected List<Jewel> getJewels() {
        return this.jewels;
    }

    /**
     * Returns the bomb positions and points associated with them
     *
     * @return bomb properties
     */
    protected Bomb getBombs() {
        return bombs;
    }

    /**
     * Returns the list containing puzzle pieces and points associated with them
     *
     * @return puzzles positions list
     */
    protected List<Puzzle> getPuzzles() {
        return this.puzzles;
    }

    /**
     * Returns the list containing keys with the corresponding key hole
     * positions and points associated with them
     *
     * @return list of keys
     */
    protected List<Key> getKeys() {
        return keys;
    }

    /**
     * Returns the position of flags and the positions associated with them
     *
     * @return flags
     */
    protected Flag getFlags() {
        return flag;
    }

    /**
     * Returns the position of rockets
     *
     * @return rockets
     */
    protected Grids getRocket() {
        return rocket;
    }

    /**
     * Returns the position of planets
     *
     * @return planets
     */
    protected Grids getPlanet() {
        return planet;
    }

    /**
     * List of special horizontal lines start and end positions with points
     * associated with them
     *
     * @return list of special horizontal lines
     */
    protected List<HorizontalLines> getHorizontalLines() {
        return hlines;
    }

    /**
     * List of special vertical lines start and end positions with points
     * associated with them
     *
     * @return list of special vertical lines
     */
    protected List<VerticalLines> getVerticalLines() {
        return vlines;
    }

    /**
     * Retrieves the filed value of the cell at given coordinates
     *
     * @param xPos x coordinate of the cell
     * @param yPos y coordinate of the cell
     * @return field value of the cell
     */
    protected Integer getFieldValue(int xPos, int yPos) {
        return field[yPos].get(xPos);
    }

}
