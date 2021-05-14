package logic;

/**
 * Grids are the building blocks of cells. Each cell has unique coordinates
 * associated with it. Every cell has a x coordinate and a y coordinate.
 *
 *
 * @author SHWETHA
 */
public class Grids {

    private final int x;
    private final int y;

    /**
     * Constructor for creating grids at given coordinates
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Grids(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x coordinate of the grid
     *
     * @return x coordinate value
     */
    protected int getXPos() {
        return x;
    }

    /**
     * Getter for y coordinate of the grid
     *
     * @return y coordinate value
     */
    protected int getYPos() {
        return y;
    }

//    /**
//     * 
//     * @param xPos
//     * @param yPos
//     * @return
//     */
//    protected boolean equals(int xPos, int yPos) {
//        return (this.x == xPos && this.y == yPos);
//    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Grids && ((Grids) obj).x == this.x
                && ((Grids) obj).y == this.y);
    }
}
