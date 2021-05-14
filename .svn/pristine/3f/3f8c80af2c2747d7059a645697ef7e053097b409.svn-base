package logic;

import java.util.List;

/**
 * Key is specialised Cell. This class describes the position of the keys and
 * the corresponding holes
 *
 * @author SHWETHA
 */
public class Key extends Cell {

    private final Grids position;
    private final List<Grids> holes;

    /**
     * Constructor
     *
     * @param value integer value of the cell which has key as special field
     * @param position position of the key
     * @param holes list of position of the key holes
     */
    protected Key(Integer value, Grids position, List<Grids> holes) {
        super(value, SpecialFields.KEY);
        this.position = position;
        this.holes = holes;
    }

    /**
     * Getter for the positions of the key holes
     *
     * @return list of hole positions
     */
    protected List<Grids> getHolePositions() {
        return holes;
    }

    /**
     * Getter for the position of the key
     *
     * @return position of the key
     */
    protected Grids getKeyPositions() {
        return position;
    }

    /**
     * Determines if a key is present at a given position
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @return true - key is present in the given position. False - if there is
     * no key at the given position
     */
    protected boolean isKey(int xPos, int yPos) {
        if (position != null) {
            return ((position.getXPos() == xPos) && (position.getYPos() == yPos));
        }
        return false;

    }
}
