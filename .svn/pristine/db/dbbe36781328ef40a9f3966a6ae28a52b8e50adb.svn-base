package logic;

/**
 * Cells are the building blocks of the player board. Every board of a player is
 * composed of several cells. Every cell has a field value and few cells have
 * special fields
 *
 * @author SHWETHA
 */
public class Cell {

    /*
     * This is the super class and contails all the general properties of a Cell
     * Integer field to represent the field value. Dice whose pip matches with
     * this integer can only be placed on this cell
     *
     */
    private Integer fieldValue;
    public SpecialFields specialFieldType;
    private boolean hasSpecialField;

    /**
     * Constructor to create a new cell which does not have any special field
     *
     * @param value the integer value of the cell
     */
    public Cell(Integer value) {
        this.fieldValue = value;
        hasSpecialField = false;
    }

    /**
     * Constructor to create a cell with a special field
     *
     * @param value the integer value of the cell
     * @param specialFieldType special field type of the cell
     */
    protected Cell(Integer value, SpecialFields specialFieldType) {
        this.fieldValue = value;
        hasSpecialField = true;
        this.specialFieldType = specialFieldType;
    }

    /**
     * Setter for the cell integer field value
     *
     * @param value integer value to be assigned
     */
    protected void setfieldValue(Integer value) {
        this.fieldValue = value;
    }

    /**
     * Setter for the cell special field type
     *
     * @param specialFieldType special field type to be assigned
     */
    protected void setSpecialField(SpecialFields specialFieldType) {
        this.specialFieldType = specialFieldType;
        hasSpecialField = true;
    }

    /**
     * Getter for the cell integer value
     *
     * @return field value
     */
    protected Integer getfieldValue() {
        return this.fieldValue;
    }

    /**
     * Getter for the special field type of a cell
     *
     * @return special field type
     */
    protected SpecialFields getSpecialField() {
        return this.specialFieldType;
    }

    /**
     * Checks if the cell has a special field
     *
     * @return true - cell has a special field. False - cell does not have a
     * special field
     */
    protected boolean hasSpecialField() {
        return hasSpecialField;
    }

}
