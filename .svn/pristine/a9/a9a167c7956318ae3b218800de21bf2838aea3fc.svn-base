package logic;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for the Cell and specialised cell classes
 *
 * @author SHWETHA
 */
public class CellTest {

    @Test
    public void Constructor_ValueOnly() {
        Cell c = new Cell(5);
        assertEquals(5, c.getfieldValue().intValue());
        assertFalse(c.hasSpecialField());
        assertNull(c.getSpecialField());
    }

    @Test
    public void Constructor_SpecialField() {
        Cell c = new Cell(4, SpecialFields.JEWEL);
        assertEquals(4, c.getfieldValue().intValue());
        assertTrue(c.hasSpecialField());
        assertEquals(SpecialFields.JEWEL, c.getSpecialField());
    }

    @Test
    public void cell_jewel() {
        Cell c = new Jewel(3, 2);
        assertEquals(3, c.getfieldValue().intValue());
        assertEquals(2, ((Jewel) c).getPoints());
        assertTrue(c.hasSpecialField());
        assertEquals(SpecialFields.JEWEL, c.getSpecialField());
    }

    @Test
    public void cell_bomb() {
        Cell c = new Bomb(5, 2);
        assertEquals(5, c.getfieldValue().intValue());
        assertTrue(c.hasSpecialField());
        assertEquals(SpecialFields.BOMB, c.getSpecialField());
    }

    @Test
    public void cell_flag() {
        List<Integer> points = new ArrayList<>();
        points.add(10);
        points.add(6);
        Cell c = new Flag(5, points);
        assertEquals(5, c.getfieldValue().intValue());
        assertTrue(c.hasSpecialField());
        assertEquals(SpecialFields.FLAG, c.getSpecialField());
        assertEquals(2, ((Flag) c).getFlagPoints().size());
        Integer result = 10;
        assertEquals(result, ((Flag) c).getFlagPoints().get(0));
        result = 6;
        assertEquals(result, ((Flag) c).getFlagPoints().get(1));
    }

    @Test
    public void cell_key() {
        List<Grids> holes = new ArrayList<>();
        holes.add(new Grids(0, 1));
        holes.add(new Grids(2, 3));
        Cell c = new Key(6, new Grids(2, 1), holes);
        assertEquals(6, c.getfieldValue().intValue());
        assertTrue(c.hasSpecialField());
        assertEquals(SpecialFields.KEY, c.getSpecialField());
        assertTrue(((Key) c).isKey(2, 1));
        assertFalse(((Key) c).isKey(0, 1));
        assertFalse(((Key) c).isKey(2, 3));
        List<Grids> result = ((Key) c).getHolePositions();
        assertEquals(2, result.size());
        assertTrue(result.contains(new Grids(0, 1)));
        assertTrue(result.contains(new Grids(2, 3)));
        assertFalse(result.contains(new Grids(2, 1)));
    }

    @Test
    public void cell_puzzle() {
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(0, 1));
        positions.add(new Grids(2, 3));
        Cell c = new Puzzle(5, 10, positions);
        assertEquals(5, c.getfieldValue().intValue());
        assertTrue(c.hasSpecialField());
        assertEquals(SpecialFields.PUZZLE, c.getSpecialField());
        assertEquals(2, ((Puzzle) c).getPositions().size());
        List<Grids> result = ((Puzzle) c).getPositions();
        assertEquals(2, result.size());
        assertTrue(result.contains(new Grids(0, 1)));
        assertTrue(result.contains(new Grids(2, 3)));
        assertFalse(result.contains(new Grids(2, 2)));
    }

    @Test
    public void cell_rocket() {
        Cell c = new Cell(5, SpecialFields.ROCKET);
        assertEquals(5, c.getfieldValue().intValue());
        assertTrue(c.hasSpecialField());
        assertEquals(SpecialFields.ROCKET, c.getSpecialField());
    }

    @Test
    public void cell_planet() {
        Cell c = new Cell(5, SpecialFields.PLANET);
        assertEquals(5, c.getfieldValue().intValue());
        assertTrue(c.hasSpecialField());
        assertEquals(SpecialFields.PLANET, c.getSpecialField());
    }

    @Test
    public void test_setFieldValue() {
        Cell c = new Cell(5);
        c.setfieldValue(4);
        assertEquals(4, c.getfieldValue().intValue());
    }

}
