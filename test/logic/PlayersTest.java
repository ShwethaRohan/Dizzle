package logic;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SHWETHA
 */
public class PlayersTest {
    
    @Test
    public void testConstructorAndGetters() {
        boolean status = false;
        List<Grids> checked = new ArrayList<>();
        checked.add(new Grids(1,2));
        checked.add(new Grids(0,3));
        List<Grids> diceOn = new ArrayList<>();
        diceOn.add(new Grids(2,2));
        diceOn.add(new Grids(1,3));
        List<Grids> exploded = new ArrayList<>();
        exploded.add(new Grids(3,2));
        exploded.add(new Grids(3,0));
        int flagPoints = 10;
        Players p = new Players(status, checked, diceOn, exploded, flagPoints);
        assertFalse(p.getStatus());
        assertEquals(2, p.getCheckedCells().size());
        assertTrue(p.getCheckedCells().contains(new Grids(1,2)));
        assertTrue(p.getCheckedCells().contains(new Grids(0,3)));
        assertEquals(2, p.getDicePlacedCells().size());
        assertTrue(p.getDicePlacedCells().contains(new Grids(2,2)));
        assertTrue(p.getDicePlacedCells().contains(new Grids(1,3)));
        assertEquals(2, p.getExplodedCells().size());
        assertTrue(p.getExplodedCells().contains(new Grids(3,2)));
        assertTrue(p.getExplodedCells().contains(new Grids(3,0)));
        assertEquals(flagPoints, p.getFlagReached());
    }
    
    @Test
    public void testSameCheckedAndDicePlaced() {
        List<Grids> checked = new ArrayList<>();
        checked.add(new Grids(1,2));
        checked.add(new Grids(0,3));
        List<Grids> diceOn = new ArrayList<>();
        diceOn.add(new Grids(1,2));
        diceOn.add(new Grids(0,3));
        Players p = new Players(true, checked, diceOn, new ArrayList<>(), 0);
        assertTrue(p.getStatus());
        assertEquals(2, p.getCheckedCells().size());
        assertTrue(p.getCheckedCells().contains(new Grids(1,2)));
        assertTrue(p.getCheckedCells().contains(new Grids(0,3)));
        assertEquals(2, p.getDicePlacedCells().size());
        assertTrue(p.getDicePlacedCells().contains(new Grids(1,2)));
        assertTrue(p.getDicePlacedCells().contains(new Grids(0,3)));
        assertTrue(p.getExplodedCells().isEmpty());
        assertEquals(0, p.getFlagReached());
    }
}
