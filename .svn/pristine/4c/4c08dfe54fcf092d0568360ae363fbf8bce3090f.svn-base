package logic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SHWETHA
 */
public class LogDataTest {

    @Test
    public void testConstructor() throws IOException, URISyntaxException {
        LogData log = new LogData(1, 3);
        assertEquals("level :1, players :3", log.getLatestLog());
    }

    @Test
    public void testlogMoves() throws IOException, URISyntaxException {
        LogData log = new LogData(1, 3);
        List<Integer> dice = new ArrayList<>();
        dice.add(2);
        dice.add(3);
        //roll again
        log.logMoves("Human Player", new int[]{2, 1}, true, true, true, dice,
                0, new Cell(8), true, null);
        assertEquals("Human Player rolls again, new dice [2, 3]", log.getLatestLog());

        // new turn
        log.logMoves("Human Player", new int[]{2, 1}, true, false, true, dice,
                0, new Cell(8), true, null);
        assertEquals("Human Player starts a new turn and rolls the dice :[2, 3]", log.getLatestLog());

        // jump
        log.logMoves("Human Player", new int[]{2, 1}, true, false, false, dice,
                0, new Cell(8), true, null);
        assertEquals("Human Player dropping out", log.getLatestLog());

        // new round
        log.logMoves("Human Player", new int[]{2, 1}, false, false, false, dice,
                0, new Cell(8), true, null);
        assertEquals("New round begins", log.getLatestLog());

        // dice placement
        log.logMoves("Human Player", new int[]{2, 1}, false, false, false, dice,
                0, new Cell(8), false, null);
        assertEquals("Human Player places dice 8 at [2,1]", log.getLatestLog());

        // false move
        log.logMoves("Human Player", null, false, false, false, null,
                0, null, false, null);
        assertEquals("Human Player places dice 8 at [2,1]", log.getLatestLog());

        // false move
        log.logMoves("Human Player", null, false, false, false, null,
                0, null, false, new ArrayList<>());
        assertEquals("Human Player places dice 8 at [2,1]", log.getLatestLog());

        // false move
        log.logMoves("Human Player", null, false, false, false, null,
                0, null, false, null);
        assertEquals("Human Player places dice 8 at [2,1]", log.getLatestLog());
    }

    @Test
    public void testSpecialFields() throws IOException, URISyntaxException {
        // jewel
        Cell cell = new Jewel(3, 2);
        LogData log = new LogData(2, 4);
        log.logMoves("Human Player", new int[]{2, 1}, false, false, false, null,
                0, cell, false, null);
        assertEquals("Human Player places dice 3 at [2,1]-------jewel collected", log.getLatestLog());

        // puzzle
        cell = new Puzzle(3, 5, new ArrayList<>());
        log.logMoves("Human Player", new int[]{2, 1}, false, false, false, null,
                0, cell, false, null);
        assertEquals("Human Player places dice 3 at [2,1]-------puzzle collected", log.getLatestLog());

        // bomb
        cell = new Bomb(3, -2);
        log.logMoves("Human Player", new int[]{2, 1}, false, false, false, null,
                0, cell, false, null);
        assertEquals("Human Player places dice 3 at [2,1]-------bomb ignited", log.getLatestLog());

        //key and lock
        List<Grids> keyHoles = new ArrayList<>();
        keyHoles.add(new Grids(2, 1));
        keyHoles.add(new Grids(1, 1));
        Grids key = new Grids(3, 1);
        cell = new Key(4, key, keyHoles);
        log.logMoves("Human Player", new int[]{2, 1}, false, false, false, null,
                0, cell, false, null);
        assertEquals("Human Player places dice 4 at [2,1]-------lock opened", log.getLatestLog());

        log.logMoves("Human Player", new int[]{1, 1}, false, false, false, null,
                0, cell, false, null);
        assertEquals("Human Player places dice 4 at [1,1]-------lock opened", log.getLatestLog());

        log.logMoves("Human Player", new int[]{1, 3}, false, false, false, null,
                0, cell, false, null);
        assertEquals("Human Player places dice 4 at [1,3]-------key collected", log.getLatestLog());

        //rocket
        cell = new Cell(5, SpecialFields.ROCKET);
        log.logMoves("Human Player", new int[]{2, 1}, false, false, false, null,
                0, cell, false, null);
        assertEquals("Human Player places dice 5 at [2,1]-------rocket launched", log.getLatestLog());

        //flag
        List<Integer> points = new ArrayList<>();
        points.add(2);
        points.add(3);
        cell = new Flag(4, points);
        log.logMoves("Human Player", new int[]{2, 1}, false, false, false, null,
                2, cell, false, null);
        assertEquals("Human Player places dice 4 at [2,1]-------reached flag with 2 points", log.getLatestLog());
    }
    
    @Test
    public void testHumanPlayerReturnDice() throws IOException, URISyntaxException {
         LogData log = new LogData(2, 4);
        List<Integer> dice = new ArrayList<>();
        dice.add(2);
        dice.add(3);
        log.humanPlayerDiceReturnLog(new int[]{1,2}, dice, 4);
        assertEquals("Human Player returns dice 4 placed at [1,2]\nDices : [2, 3]", log.getLatestLog());
    }

}
