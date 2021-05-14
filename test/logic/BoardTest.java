package logic;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logic.exception.InvalidJSONFileException;
import logic.exception.NullObjectException;
import logic.exception.OutOfRangeException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SHWETHA
 */
public class BoardTest {

    String jsonString = "{"
            + "\"field\": ["
            + "[1, 2, 3, 5],"
            + "[4, 3, 6, 1],"
            + "[5, 0, 6, 1],"
            + "[5, null, 0, 3]"
            + "],"
            + "\"jewels\": ["
            + "{"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 2, \"y\": 0}"
            + "]"
            + "},"
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 1}"
            + "]"
            + "}"
            + "],"
            + "\"bombs\": {"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 2},"
            + "{\"x\" : 1, \"y\": 1}"
            + "]"
            + "},"
            + "\"puzzles\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 1, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"horizontal-lines\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 0},"
            + "{\"x\" : 3, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"vertical-lines\": ["
            + "{"
            + "\"points\"    : 10,"
            + "\"positions\" : ["
            + "{\"x\" : 2, \"y\": 0},"
            + "{\"x\" : 2, \"y\": 2}"
            + "]"
            + "}"
            + "],"
            + "\"keys\": ["
            + "{"
            + "\"position\" : {\"x\" : 1, \"y\": 2},"
            + "\"holes\" : ["
            + "{\"x\" : 0, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"flag\": {"
            + "\"points\"   : [10, 6, 3, 1],"
            + "\"position\" : {\"x\" : 2, \"y\": 1}"
            + "},"
            + "\"rocket\": {\"x\" : 2, \"y\": 2},"
            + "\"planet\": null"
            + "}";

    String jsonStringTwo = "{"
            + "\"field\": ["
            + "[1, null, 3, 5],"
            + "[null, 3, null, 1],"
            + "[5, 3, 6, 1],"
            + "[5, null, 0, 3]"
            + "],"
            + "\"jewels\": ["
            + "{"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 2, \"y\": 0}"
            + "]"
            + "},"
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 3, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"bombs\": {"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 2},"
            + "{\"x\" : 1, \"y\": 1}"
            + "]"
            + "},"
            + "\"puzzles\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 3, \"y\": 3}"
            + "]"
            + "}"
            + "],"
            + "\"horizontal-lines\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 0},"
            + "{\"x\" : 2, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"vertical-lines\": ["
            + "{"
            + "\"points\"    : 5,"
            + "\"positions\" : ["
            + "{\"x\" : 2, \"y\": 0},"
            + "{\"x\" : 2, \"y\": 2}"
            + "]"
            + "}"
            + "],"
            + "\"keys\": ["
            + "{"
            + "\"position\" : {\"x\" : 1, \"y\": 2},"
            + "\"holes\" : ["
            + "{\"x\" : 0, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"flag\": {"
            + "\"points\"   : [10, 6, 3, 1],"
            + "\"position\" : {\"x\" : 3, \"y\": 1}"
            + "},"
            + "\"rocket\": {\"x\" : 3, \"y\": 2},"
            + "\"planet\": null"
            + "}";

    String jsonStringThree = "{"
            + "\"field\": ["
            + "[5, 4, 3, 5],"
            + "[0, 0, 0, 5],"
            + "[5, 0, 6, 1],"
            + "[5, 4, 0, 1]"
            + "],"
            + "\"jewels\": ["
            + "{"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 2, \"y\": 0}"
            + "]"
            + "},"
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 1}"
            + "]"
            + "}"
            + "],"
            + "\"bombs\": {"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 2},"
            + "{\"x\" : 1, \"y\": 1}"
            + "]"
            + "},"
            + "\"puzzles\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 1, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"horizontal-lines\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 1},"
            + "{\"x\" : 3, \"y\": 1}"
            + "]"
            + "}"
            + "],"
            + "\"vertical-lines\": ["
            + "{"
            + "\"points\"    : 5,"
            + "\"positions\" : ["
            + "{\"x\" : 1, \"y\": 0},"
            + "{\"x\" : 1, \"y\": 3}"
            + "]"
            + "}"
            + "],"
            + "\"keys\": ["
            + "{"
            + "\"position\" : {\"x\" : 1, \"y\": 2},"
            + "\"holes\" : ["
            + "{\"x\" : 0, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"flag\": {"
            + "\"points\"   : [10, 6, 3, 1],"
            + "\"position\" : {\"x\" : 0, \"y\": 3}"
            + "},"
            + "\"rocket\": {\"x\" : 1, \"y\": 3},"
            + "\"planet\": null"
            + "}";

    String jsonStringFour = "{"
            + "\"field\": ["
            + "[5, 4, 3, 5],"
            + "[0, 0, 6, 5],"
            + "[5, 0, 6, 1],"
            + "[0, 0, 0, 1]"
            + "],"
            + "\"jewels\": ["
            + "{"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 2, \"y\": 0}"
            + "]"
            + "},"
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 1}"
            + "]"
            + "}"
            + "],"
            + "\"bombs\": {"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 2},"
            + "{\"x\" : 1, \"y\": 1}"
            + "]"
            + "},"
            + "\"puzzles\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 1, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"horizontal-lines\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 1},"
            + "{\"x\" : 3, \"y\": 1}"
            + "]"
            + "},"
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 3},"
            + "{\"x\" : 3, \"y\": 3}"
            + "]"
            + "}"
            + "],"
            + "\"vertical-lines\": ["
            + "{"
            + "\"points\"    : 5,"
            + "\"positions\" : ["
            + "{\"x\" : 2, \"y\": 0},"
            + "{\"x\" : 2, \"y\": 3}"
            + "]"
            + "},"
            + "{"
            + "\"points\"    : 5,"
            + "\"positions\" : ["
            + "{\"x\" : 1, \"y\": 0},"
            + "{\"x\" : 1, \"y\": 3}"
            + "]"
            + "}"
            + "],"
            + "\"keys\": ["
            + "{"
            + "\"position\" : {\"x\" : 1, \"y\": 2},"
            + "\"holes\" : ["
            + "{\"x\" : 0, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"flag\": {"
            + "\"points\"   : [10, 6, 3, 1],"
            + "\"position\" : {\"x\" : 0, \"y\": 3}"
            + "},"
            + "\"rocket\": {\"x\" : 1, \"y\": 3},"
            + "\"planet\": null"
            + "}";

    String jsonStringFive = "{"
            + "\"field\": ["
            + "[5, 0, 3, 5],"
            + "[3, 0, 1, 5],"
            + "[5, 4, 6, 1],"
            + "[5, 0, 3, 1]"
            + "],"
            + "\"jewels\": ["
            + "{"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 2, \"y\": 0}"
            + "]"
            + "},"
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 1}"
            + "]"
            + "}"
            + "],"
            + "\"bombs\": {"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 2},"
            + "{\"x\" : 1, \"y\": 1}"
            + "]"
            + "},"
            + "\"puzzles\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 1, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"horizontal-lines\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 1},"
            + "{\"x\" : 3, \"y\": 1}"
            + "]"
            + "}"
            + "],"
            + "\"vertical-lines\": ["
            + "{"
            + "\"points\"    : 5,"
            + "\"positions\" : ["
            + "{\"x\" : 1, \"y\": 0},"
            + "{\"x\" : 1, \"y\": 3}"
            + "]"
            + "}"
            + "],"
            + "\"keys\": ["
            + "{"
            + "\"position\" : {\"x\" : 2, \"y\": 2},"
            + "\"holes\" : ["
            + "{\"x\" : 0, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"flag\": {"
            + "\"points\"   : [10, 6, 3, 1],"
            + "\"position\" : {\"x\" : 3, \"y\": 3}"
            + "},"
            + "\"rocket\": {\"x\" : 1, \"y\": 3},"
            + "\"planet\": null"
            + "}";

    String jsonStringSix = "{"
            + "\"field\": ["
            + "[5, 2, 3, 5],"
            + "[3, 1, 1, 5],"
            + "[5, 4, 6, 1],"
            + "[5, 0, 3, 1]"
            + "],"
            + "\"jewels\": ["
            + "{"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 2, \"y\": 0}"
            + "]"
            + "},"
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 1}"
            + "]"
            + "}"
            + "],"
            + "\"bombs\": {"
            + "\"points\"    : 2,"
            + "\"positions\" : ["
            + "{\"x\" : 1, \"y\": 2},"
            + "{\"x\" : 3, \"y\": 1}"
            + "]"
            + "},"
            + "\"puzzles\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 1, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"horizontal-lines\": ["
            + "{"
            + "\"points\"    : 3,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 1},"
            + "{\"x\" : 3, \"y\": 1}"
            + "]"
            + "}"
            + "],"
            + "\"vertical-lines\": ["
            + "{"
            + "\"points\"    : 5,"
            + "\"positions\" : ["
            + "{\"x\" : 0, \"y\": 0},"
            + "{\"x\" : 0, \"y\": 3}"
            + "]"
            + "}"
            + "],"
            + "\"keys\": ["
            + "{"
            + "\"position\" : {\"x\" : 2, \"y\": 2},"
            + "\"holes\" : ["
            + "{\"x\" : 0, \"y\": 0}"
            + "]"
            + "}"
            + "],"
            + "\"flag\": {"
            + "\"points\"   : [10, 6, 3, 1],"
            + "\"position\" : {\"x\" : 3, \"y\": 3}"
            + "},"
            + "\"rocket\": {\"x\" : 1, \"y\": 3},"
            + "\"planet\": null"
            + "}";

    /**
     * Test case to check if a cell can be occupied by a dice
     */
    @Test
    public void canCellBeOccupied() throws InvalidJSONFileException {
        List<Grids> dice = new ArrayList<>();
        dice.add(new Grids(1, 2));
        dice.add(new Grids(1, 0));
        Board b = new Board(new StringReader(jsonString), dice);
        assertTrue(b.canBeOccupiedByDice(1, 1));
        assertFalse(b.canBeOccupiedByDice(2, 1));
        assertFalse(b.canBeOccupiedByDice(3, 1));
        assertFalse(b.canBeOccupiedByDice(1, 2));
        assertTrue(b.canBeOccupiedByDice(0, 0));
        assertFalse(b.canBeOccupiedByDice(1, 0));
    }

    /**
     * Test cases to check if a cell can be occupied by one of the dices present
     */
    @Test
    public void diceFitsACell_true() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        ArrayList<Integer> dice = new ArrayList<>();
        dice.add(3);
        dice.add(5);
        dice.add(6);
        Integer cellValue = b.getCellValue(0, 2);
        assertTrue(b.diceFitsACell(cellValue, dice));
    }

    @Test
    public void diceFitsACell_false() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        ArrayList<Integer> dice = new ArrayList<>();
        dice.add(3);
        dice.add(5);
        dice.add(6);
        Integer cellValue = b.getCellValue(0, 0);
        assertFalse(b.diceFitsACell(cellValue, dice));
    }

    /**
     * Test cases to check if relevant neighbours are determined
     */
    @Test
    public void getRelevantNeighbours_withoutDices() throws InvalidJSONFileException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(2);
        dices.add(3);
        dices.add(6);
        Board b = new Board(new StringReader(jsonString));
        List<Grids> neighbours = b.getCrossedNeighbours(true, dices);
        //List<Grids> neighbours = b.getCrossedNeighbours(dices);
        assertFalse(neighbours.isEmpty());
        assertEquals(4, neighbours.size());
    }

    @Test
    public void getRelevantNeighbours_withDices() throws InvalidJSONFileException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(5);
        dices.add(2);
        dices.add(3);
        dices.add(1);
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 1));
        dicePlaced.add(new Grids(3, 3));
        Board b = new Board(new StringReader(jsonString), dicePlaced);
        List<Grids> neighbours = b.getCrossedNeighbours(false, dices);
        assertFalse(neighbours.isEmpty());
        assertEquals(2, neighbours.size());
    }

    @Test
    public void getRelevantNeighbours_empty() throws InvalidJSONFileException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(2);
        dices.add(4);
        Board b = new Board(new StringReader(jsonString));
        List<Grids> neighbours = b.getCrossedNeighbours(true, dices);
        assertTrue(neighbours.isEmpty());
    }

    @Test
    public void getRelevantNeighbours_emptyDices() throws InvalidJSONFileException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(2);
        dices.add(4);
        Board b = new Board(new StringReader(jsonString));
        List<Grids> neighbours = b.getCrossedNeighbours(true, dices);
        assertTrue(neighbours.isEmpty());
    }

    @Test
    public void getRelevantNeighbours_jumpCross() throws InvalidJSONFileException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(5);
        dices.add(6);
        dices.add(3);
        dices.add(4);
        dices.add(2);
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 1));
        Board b = new Board(new StringReader(jsonStringTwo), dicePlaced);
        List<Grids> neighbours = b.getCrossedNeighbours(false, dices);
        assertFalse(neighbours.isEmpty());
    }

    @Test
    public void check_AI_Preference() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        // puzzle
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(0, 0));
        positions.add(new Grids(0, 1));
        positions.add(new Grids(0, 2));
        positions.add(new Grids(0, 3));
        positions.add(new Grids(1, 0));
        positions.add(new Grids(1, 1));
        positions.add(new Grids(1, 2));
        positions.add(new Grids(1, 3));
        positions.add(new Grids(2, 0));
        positions.add(new Grids(2, 1));
        positions.add(new Grids(2, 2));
        positions.add(new Grids(2, 3));
        positions.add(new Grids(3, 0));
        positions.add(new Grids(3, 2));
        positions.add(new Grids(3, 3));
        int[] chosen = new int[]{0, 1};
        assertArrayEquals(chosen, b.chooseAICell(positions));

        //Flag
        positions = new ArrayList<>();
        positions.add(new Grids(0, 0));
        positions.add(new Grids(0, 2));
        positions.add(new Grids(0, 3));
        positions.add(new Grids(1, 0));
        positions.add(new Grids(1, 1));
        positions.add(new Grids(1, 2));
        positions.add(new Grids(1, 3));
        positions.add(new Grids(2, 0));
        positions.add(new Grids(2, 1));
        positions.add(new Grids(2, 2));
        positions.add(new Grids(2, 3));
        positions.add(new Grids(3, 0));
        positions.add(new Grids(3, 2));
        positions.add(new Grids(3, 3));
        chosen = new int[]{1, 2};
        assertArrayEquals(chosen, b.chooseAICell(positions));

        // jewel
        positions = new ArrayList<>();
        positions.add(new Grids(0, 0));
        positions.add(new Grids(0, 2));
        positions.add(new Grids(1, 0));
        positions.add(new Grids(3, 0));
        positions.add(new Grids(3, 2));
        positions.add(new Grids(3, 3));
        chosen = new int[]{1, 0};
        assertArrayEquals(chosen, b.chooseAICell(positions));

        //Rocket
        positions = new ArrayList<>();
        positions.add(new Grids(0, 0));
        positions.add(new Grids(1, 1));
        positions.add(new Grids(2, 0));
        positions.add(new Grids(2, 1));
        positions.add(new Grids(2, 2));
        chosen = new int[]{2, 2};
        assertArrayEquals(chosen, b.chooseAICell(positions));

        //Bomb
        positions = new ArrayList<>();
        positions.add(new Grids(0, 0));
        positions.add(new Grids(1, 1));
        positions.add(new Grids(2, 0));
        positions.add(new Grids(2, 1));
        chosen = new int[]{1, 1};
        assertArrayEquals(chosen, b.chooseAICell(positions));

        //key
        positions = new ArrayList<>();
        positions.add(new Grids(0, 0));
        positions.add(new Grids(2, 1));

        chosen = new int[]{2, 1};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void checkHorizontal_1() throws InvalidJSONFileException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(5);
        dices.add(6);
        dices.add(1);
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(2, 3));
        Board b = new Board(new StringReader(jsonStringThree), dicePlaced);
        List<Grids> neighbours = b.getCrossedNeighbours(false, dices);
        //List<Grids> neighbours = b.getCrossedNeighbours(dices);
        assertFalse(neighbours.isEmpty());
        assertEquals(3, neighbours.size());
        int[] result = b.lineCompleted(neighbours);
        assertEquals(1, result[0]);
        assertEquals(3, result[1]);
    }

    @Test
    public void checkHorizontal_2() throws InvalidJSONFileException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(5);
        dices.add(6);
        dices.add(1);
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(2, 3));
        Board b = new Board(new StringReader(jsonStringFour), dicePlaced);
        List<Grids> neighbours = b.getCrossedNeighbours(false, dices);
        //List<Grids> neighbours = b.getCrossedNeighbours(dices);
        assertFalse(neighbours.isEmpty());
        assertEquals(3, neighbours.size());
        int[] result = b.lineCompleted(neighbours);
        assertEquals(3, result[0]);
        assertEquals(3, result[1]);
    }

    @Test
    public void checkVertical_1() throws InvalidJSONFileException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(4);
        dices.add(5);
        dices.add(3);
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(3, 1));
        Board b = new Board(new StringReader(jsonStringFive), dicePlaced);
        List<Grids> neighbours = b.getCrossedNeighbours(false, dices);
        //List<Grids> neighbours = b.getCrossedNeighbours(dices);
        assertFalse(neighbours.isEmpty());
        assertEquals(3, neighbours.size());
        int[] result = b.lineCompleted(neighbours);
        assertEquals(2, result[0]);
        assertEquals(1, result[1]);
    }

    @Test
    public void checkAI_preference_hLines() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonStringThree));
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(1, 3));
        positions.add(new Grids(2, 2));
        positions.add(new Grids(3, 3));
        int[] chosen = new int[]{1, 3};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void checkAI_preference_hLines_twoLines() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonStringFour));
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(1, 3));
        positions.add(new Grids(2, 2));
        positions.add(new Grids(3, 3));
        int[] chosen = new int[]{3, 3};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void checkAI_preference_vLines() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonStringFive));
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(2, 1));
        positions.add(new Grids(3, 0));
        positions.add(new Grids(3, 2));
        int[] chosen = new int[]{2, 1};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void checkAI_preference_mostSignificant() throws InvalidJSONFileException {
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 0));
        dicePlaced.add(new Grids(1, 2));
        dicePlaced.add(new Grids(1, 3));
        dicePlaced.add(new Grids(0, 0));
        dicePlaced.add(new Grids(1, 0));
        dicePlaced.add(new Grids(3, 0));
        Board b = new Board(new StringReader(jsonStringSix), dicePlaced);
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(1, 1));
        positions.add(new Grids(2, 0));
        int[] chosen = new int[]{2, 0};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void AI_Preference_puzzle_flag() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        // puzzle
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(0, 1));
        positions.add(new Grids(1, 2));
        int[] chosen = new int[]{0, 1};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void AI_Preference_flag_jewel() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        // puzzle
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(0, 2));
        positions.add(new Grids(1, 2));
        int[] chosen = new int[]{1, 2};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void AI_Preference_jewels() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        // puzzle
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(0, 2));
        positions.add(new Grids(1, 0));
        int[] chosen = new int[]{1, 0};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void AI_Preference_jewel_rocket() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        // puzzle
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(0, 2));
        positions.add(new Grids(2, 2));
        int[] chosen = new int[]{0, 2};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void AI_Preference_rocket_bomb() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        // puzzle
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(1, 1));
        positions.add(new Grids(2, 2));
        int[] chosen = new int[]{2, 2};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void AI_Preference_bomb_key() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        // puzzle
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(2, 1));
        positions.add(new Grids(1, 1));
        int[] chosen = new int[]{1, 1};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void AI_Preference_bomb_keyHole() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        // puzzle
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(2, 0));
        positions.add(new Grids(0, 0));
        int[] chosen = new int[]{2, 0};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void AI_Preference_key_keyHole() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        // puzzle
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(2, 1));
        positions.add(new Grids(0, 0));
        int[] chosen = new int[]{2, 1};
        assertArrayEquals(chosen, b.chooseAICell(positions));
    }

    @Test
    public void test_belongsHline_true() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        assertTrue(b.belongsToHLine(0));
    }

    @Test
    public void test_belongsHline_false() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        assertFalse(b.belongsToHLine(3));
        assertFalse(b.belongsToHLine(1));
        assertFalse(b.belongsToHLine(2));
    }

    @Test
    public void test_belongsVline_true() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        assertTrue(b.belongsToVLine(2));
    }

    @Test
    public void test_belongsVline_false() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        assertFalse(b.belongsToVLine(0));
        assertFalse(b.belongsToVLine(1));
        assertFalse(b.belongsToVLine(3));
    }

    @Test
    public void test_checkHLineCompletion_true() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonStringThree));
//        assertTrue(b.checkHLineLastCoordinate(1, 3));
        assertEquals(3, b.checkHLineLastCoordinate(1, 3));
    }

    @Test
    public void test_checkHLineCompletion_false() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
//        assertFalse(b.checkHLineLastCoordinate(0, 3));
        assertEquals(0, b.checkHLineLastCoordinate(0, 3));
    }

//    @Test
//    public void test_hLinesPoint_points_true() throws InvalidJSONFileException {
//        List<Grids> dicePlaced = new ArrayList<>();
//        dicePlaced.add(new Grids(1, 3));
//        Board b = new Board(new StringReader(jsonStringThree), dicePlaced);
//        int points = b.hLinesPoint(new int[]{1, 3});
//        assertEquals(3, points);
//    }
//    @Test
//    public void test_hLinesPoint_points_false() throws InvalidJSONFileException {
//        List<Grids> dicePlaced = new ArrayList<>();
//        dicePlaced.add(new Grids(0, 0));
//        dicePlaced.add(new Grids(0, 1));
//        dicePlaced.add(new Grids(0, 3));
//        Board b = new Board(new StringReader(jsonString), dicePlaced);
//        int points = b.hLinesPoint(new int[]{0, 3});
//        assertEquals(0, points);
//    }
    @Test
    public void test_checkVLineCompletion_true() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonStringFour));
//        assertTrue(b.checkVLineLastCoordinate(0, 1));
        assertEquals(5, b.checkVLineLastCoordinate(0, 1));
    }

    @Test
    public void test_checkVLineCompletion_false() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonStringFour));
//        assertFalse(b.checkVLineLastCoordinate(1, 2));
//        assertFalse(b.checkVLineLastCoordinate(0, 2));
//        assertFalse(b.checkVLineLastCoordinate(2, 2));
        assertEquals(0, b.checkVLineLastCoordinate(1, 2));
        assertEquals(0, b.checkVLineLastCoordinate(0, 2));
        assertEquals(0, b.checkVLineLastCoordinate(2, 2));
    }

//    @Test
//    public void test_vLinesPoint_points_true() throws InvalidJSONFileException {
//        List<Grids> dicePlaced = new ArrayList<>();
//        dicePlaced.add(new Grids(0, 1));
//        Board b = new Board(new StringReader(jsonStringFour), dicePlaced);
//        int points = b.vLinesPoint(new int[]{0, 1});
//        assertEquals(5, points);
//    }
//
//    @Test
//    public void test_vLinesPoint_points_false() throws InvalidJSONFileException {
//        List<Grids> dicePlaced = new ArrayList<>();
//        dicePlaced.add(new Grids(1, 2));
//        dicePlaced.add(new Grids(0, 2));
//        Board b = new Board(new StringReader(jsonStringFour), dicePlaced);
//        int points = b.vLinesPoint(new int[]{0, 2});
//        assertEquals(0, points);
//    }
    @Test
    public void test_getHorizontalAndVerticalLines() throws InvalidJSONFileException {
        String jString = "{"
                + "\"field\": ["
                + "[1, 2, 5],"
                + "[4, 3, 1],"
                + "[5, 0, 1]"
                + "],"
                + "\"horizontal-lines\": ["
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 0},"
                + "{\"x\" : 2, \"y\": 0}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 5,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 1},"
                + "{\"x\" : 2, \"y\": 1}"
                + "]"
                + "}"
                + "],"
                + "\"vertical-lines\": ["
                + "{"
                + "\"points\"    : 5,"
                + "\"positions\" : ["
                + "{\"x\" : 2, \"y\": 0},"
                + "{\"x\" : 2, \"y\": 2}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(jString));
        assertEquals(4, b.getHorizontalStartPositions().size());
        assertEquals(2, b.getVerticalStartPositions().size());
        assertEquals(1, b.getHorizontalStartPositions().get(3).size());
        assertEquals(1, b.getHorizontalStartPositions().get(5).size());
        assertEquals(0, b.getHorizontalStartPositions().get(10).size());
        assertEquals(0, b.getHorizontalStartPositions().get(15).size());
        assertEquals(1, b.getVerticalStartPositions().get(5).size());
        assertEquals(0, b.getVerticalStartPositions().get(10).size());
    }

    @Test
    public void test_getDices() throws InvalidJSONFileException {
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(0, 0));
        dicePlaced.add(new Grids(1, 1));
//        ArrayList<Grids> sdices = new ArrayList<>();
//        sdices.add(new Grids(2, 2));
        Board b = new Board(new StringReader(jsonStringThree), dicePlaced);
        //b.setSkippedDices(sdices);
        assertEquals(2, b.getDicePlacedPositions().size());
        assertEquals(new Grids(0, 0), b.getDicePlacedPositions().get(0));
        assertEquals(new Grids(1, 1), b.getDicePlacedPositions().get(1));
    }

    @Test
    public void test_crossPositions() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 0, 5],"
                + "[0, 3, 1],"
                + "[5, 0, 1]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(jString1));
        b.updateCrossPositions();
        assertEquals(3, b.getCrossedPositions().size());
        assertTrue(b.getCrossedPositions().get(2).equals(new Grids(2, 1)));
        assertTrue(b.getCrossedPositions().get(1).equals(new Grids(1, 0)));
        assertTrue(b.getCrossedPositions().get(0).equals(new Grids(0, 1)));
        assertFalse(b.getCrossedPositions().get(0).equals(new Grids(0, 0)));
    }

    @Test
    public void test_getCrossedNeighbours_Nodices() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 0, 5],"
                + "[0, 3, 1],"
                + "[5, 0, 2]"
                + "],"
                + "\"planet\": {\"x\" : 1, \"y\": 1}"
                + "}";
        Board b = new Board(new StringReader(jString1));
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(3);
        dices.add(5);
        assertEquals(5, b.getCrossedNeighbours(true, dices).size());
    }

    @Test
    public void test_getCrossedNeighbours_key_keyHole() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 0, 5],"
                + "[0, 3, 1],"
                + "[5, 0, 2]"
                + "],"
                + "\"keys\": ["
                + "{"
                + "\"position\" : {\"x\" : 2, \"y\": 1},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 0}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(jString1));
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(3);
        dices.add(5);
        assertEquals(6, b.getCrossedNeighbours(true, dices).size());
        assertFalse(b.getCrossedNeighbours(true, dices).contains(new Grids(0, 0)));
    }

    @Test
    public void test_getCrossedNeighbours_key_keyHole_dice_placed() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 0, 5],"
                + "[2, 3, 1],"
                + "[5, 0, 2]"
                + "],"
                + "\"keys\": ["
                + "{"
                + "\"position\" : {\"x\" : 0, \"y\": 1},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 0}"
                + "]"
                + "}"
                + "]"
                + "}";
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 0));
        Board b = new Board(new StringReader(jString1), dicePlaced);
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(3);
        dices.add(5);
        assertEquals(2, b.getCrossedNeighbours(false, dices).size());
        assertFalse(b.getCrossedNeighbours(false, dices).contains(new Grids(0, 0)));
    }

    @Test
    public void test_getCrossedNeighbours_key_keyHole_key_collected() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 0, 5],"
                + "[0, 3, 1],"
                + "[5, 0, 2]"
                + "],"
                + "\"keys\": ["
                + "{"
                + "\"position\" : {\"x\" : 0, \"y\": 1},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 0}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(jString1));
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(3);
        dices.add(5);
        assertEquals(8, b.getCrossedNeighbours(true, dices).size());
        assertTrue(b.getCrossedNeighbours(false, dices).contains(new Grids(0, 0)));
    }

    @Test
    public void test_two_key_hole() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 0, 5],"
                + "[0, 3, 1],"
                + "[5, 0, 2]"
                + "],"
                + "\"keys\": ["
                + "{"
                + "\"position\" : {\"x\" : 0, \"y\": 1},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 0}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(jString1));
        Cell c = b.getIndividualCell(1, 0);
        assertEquals(1, ((Key) c).getHolePositions().size());
    }

    @Test
    public void test_two_key_hole_two() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 0, 5],"
                + "[0, 3, 1],"
                + "[5, 0, 2]"
                + "],"
                + "\"keys\": ["
                + "{"
                + "\"position\" : {\"x\" : 0, \"y\": 1},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 0},"
                + "{\"x\" : 0, \"y\": 2}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(jString1));
        Cell c = b.getIndividualCell(1, 0);
        assertEquals(2, ((Key) c).getHolePositions().size());
        assertEquals(new Grids(0, 0), ((Key) c).getHolePositions().get(0));
        assertEquals(new Grids(0, 2), ((Key) c).getHolePositions().get(1));
        HashMap<Integer, Integer> keys = b.getKeyAndHolePositions();
        assertEquals(3, keys.size());
        assertTrue(keys.containsKey(10));
        assertTrue(keys.containsKey(00));
        assertTrue(keys.containsKey(20));
        Integer value = 1;
        assertEquals(value, keys.get(10));
        assertEquals(value, keys.get(00));
        assertEquals(value, keys.get(20));
    }

    @Test
    public void test_multiple_keys() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 0, 5],"
                + "[0, 3, 1],"
                + "[5, 0, 2]"
                + "],"
                + "\"keys\": ["
                + "{"
                + "\"position\" : {\"x\" : 0, \"y\": 1},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 2}"
                + "]"
                + "},"
                + "{"
                + "\"position\" : {\"x\" : 1, \"y\": 0},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 0}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(jString1));
        Cell c1 = b.getIndividualCell(1, 0);
        Cell c2 = b.getIndividualCell(0, 1);
        assertEquals(1, ((Key) c1).getHolePositions().size());
        assertEquals(new Grids(0, 2), ((Key) c1).getHolePositions().get(0));
        assertEquals(1, ((Key) c2).getHolePositions().size());
        assertEquals(new Grids(0, 0), ((Key) c2).getHolePositions().get(0));
        HashMap<Integer, Integer> keys = b.getKeyAndHolePositions();
        assertEquals(4, keys.size());
        assertTrue(keys.containsKey(10));
        assertTrue(keys.containsKey(20));
        assertTrue(keys.containsKey(01));
        assertTrue(keys.containsKey(00));
        Integer value = 1;
        assertEquals(value, keys.get(10));
        assertEquals(value, keys.get(20));
        value = 2;
        assertEquals(value, keys.get(01));
        assertEquals(value, keys.get(00));
    }

    @Test
    public void test_multiple_keys_no_dice_placed() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 2, 5],"
                + "[0, 3, 1],"
                + "[5, 0, 2]"
                + "],"
                + "\"keys\": ["
                + "{"
                + "\"position\" : {\"x\" : 0, \"y\": 1},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 2}"
                + "]"
                + "},"
                + "{"
                + "\"position\" : {\"x\" : 1, \"y\": 0},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 0}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(jString1));
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(3);
        dices.add(5);
        dices.add(2);
        List<Grids> neighbours = b.getCrossedNeighbours(true, dices);
        assertEquals(5, neighbours.size());
        assertTrue(neighbours.contains(new Grids(2, 0)));
        assertFalse(neighbours.contains(new Grids(0, 0)));
    }

    @Test
    public void test_multiple_keys_jumpCross() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 2, 5],"
                + "[2, 3, 0],"
                + "[5, 0, 2]"
                + "],"
                + "\"keys\": ["
                + "{"
                + "\"position\" : {\"x\" : 0, \"y\": 1},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 2}"
                + "]"
                + "},"
                + "{"
                + "\"position\" : {\"x\" : 1, \"y\": 0},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 0}"
                + "]"
                + "}"
                + "]"
                + "}";
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(2);
        dices.add(3);
        dices.add(5);
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(2, 2));
        Board b = new Board(new StringReader(jString1), dicePlaced);
        List<Grids> neighbours = b.getCrossedNeighbours(false, dices);
        assertFalse(neighbours.isEmpty());
        assertEquals(3, neighbours.size());
        assertFalse(neighbours.contains(new Grids(2, 0)));
    }

    @Test
    public void test_multiple_keys_jumpCross_with_key_open() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 2, 5],"
                + "[0, 3, 0],"
                + "[5, 0, 2]"
                + "],"
                + "\"keys\": ["
                + "{"
                + "\"position\" : {\"x\" : 0, \"y\": 1},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 2}"
                + "]"
                + "},"
                + "{"
                + "\"position\" : {\"x\" : 1, \"y\": 0},"
                + "\"holes\" : ["
                + "{\"x\" : 0, \"y\": 0}"
                + "]"
                + "}"
                + "]"
                + "}";
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(2);
        dices.add(3);
        dices.add(5);
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(2, 2));
        Board b = new Board(new StringReader(jString1), dicePlaced);
        List<Grids> neighbours = b.getCrossedNeighbours(false, dices);
        assertFalse(neighbours.isEmpty());
        assertEquals(6, neighbours.size());
        assertTrue(neighbours.contains(new Grids(2, 0)));
        assertFalse(neighbours.contains(new Grids(0, 0)));
    }

    @Test
    public void test_getValue() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        assertEquals(new Integer(1), b.getCellValue(0, 0));
        assertNull(b.getCellValue(3, 1));
    }

    @Test
    public void test_updateValues() throws InvalidJSONFileException {
        String jString1 = "{"
                + "\"field\": ["
                + "[1, 0, 5],"
                + "[0, 3, 1],"
                + "[5, 0, 1]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(jString1));
        assertEquals(new Integer(5), b.getCellValue(0, 2));
        b.updateValues(0, 2, 3);
        assertEquals(new Integer(3), b.getCellValue(0, 2));
        assertEquals(new Integer(0), b.getCellValue(2, 1));
        b.updateValues(0, 2, null);
        assertNull(b.getCellValue(0, 2));
    }

    @Test
    public void test_checkPreference_true() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        assertTrue(b.checkPreference(new Grids(0, 2), SpecialFields.JEWEL));
        assertFalse(b.checkPreference(new Grids(2, 0), SpecialFields.JEWEL));
        assertTrue(b.checkPreference(new Grids(2, 0), SpecialFields.BOMB));
        assertTrue(b.checkPreference(new Grids(1, 1), SpecialFields.BOMB));
        assertTrue(b.checkPreference(new Grids(0, 1), SpecialFields.PUZZLE));
        assertFalse(b.checkPreference(new Grids(3, 1), SpecialFields.PUZZLE));
    }

    @Test(expected = OutOfRangeException.class)
    public void test_fieldValueErrors() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 8],"
                + "[4, 3]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
    }

    @Test(expected = NullObjectException.class)
    public void test_initialiseJewels() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2],"
                + "[4, 3]"
                + "],"
                + "\"jewels\": ["
                + "{"
                + "\"points\"    : 2,"
                + "\"positions\" : ["
                + "{\"x\" : 2, \"y\": 0}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
    }

    @Test(expected = NullObjectException.class)
    public void test_initialiseBomb() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3],"
                + "[4, 3, null],"
                + "[0, 7, 3]"
                + "],"
                + "\"bombs\": {"
                + "\"points\"    : 2,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 3},"
                + "{\"x\" : 3, \"y\": 1}"
                + "]"
                + "}"
                + "}";
        Board b = new Board(new StringReader(testString));
    }

//    @Test
//    public void test_copyCells() throws InvalidJSONFileException {
//        String testString = "{"
//                + "\"field\": ["
//                + "[1, 2],"
//                + "[4, 3]"
//                + "]"
//                + "}";
//        Board b = new Board(new StringReader(testString));
//        assertEquals(1, b.getIndividualCell(0, 0).getfieldValue().intValue());
//        assertEquals(1, b.getReferenceCell(0, 0));
//        b.updateValues(0, 0, 0);
//        assertEquals(0, b.getCellValue(0, 0).intValue());
//        assertEquals(1, b.getReferenceCell(0, 0));
//    }
    @Test
    public void test_preference_multipleDices() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 6],"
                + "[4, 3, 4, 5],"
                + "[0, 6, 3, 1],"
                + "[1, 2, 3, 4]"
                + "]"
                + "}";
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 2));
        dicePlaced.add(new Grids(1, 1));
        Board b = new Board(new StringReader(testString), dicePlaced);
        assertEquals(2, b.getDicePlacedPositions().size());
//        assertEquals(new Grids(1, 1), b.getDicePlacedPositions().get(0));
//        assertEquals(new Grids(1, 2), b.getDicePlacedPositions().get(1));
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(5);
        dices.add(6);
        List<Grids> neighbours = b.getCrossedNeighbours(false, dices);
        assertEquals(6, neighbours.size());
        assertEquals(new Grids(0, 1), neighbours.get(0));
    }

//    @Test
//    public void test_dicePlacedSorting() throws InvalidJSONFileException {
//        String testString = "{"
//                + "\"field\": ["
//                + "[1, 2, 3, 6],"
//                + "[4, 3, 4, 5],"
//                + "[0, 6, 3, 1],"
//                + "[1, 2, 3, 4]"
//                + "]"
//                + "}";
//        List<Grids> dicePlaced = new ArrayList<>();
//        dicePlaced.add(new Grids(1, 2));
//        dicePlaced.add(new Grids(1, 1));
//        Board b = new Board(new StringReader(testString), dicePlaced);
//        assertEquals(2, b.getDicePlacedPositions().size());
//        assertEquals(new Grids(1, 1), b.getDicePlacedPositions().get(0));
//        assertEquals(new Grids(1, 2), b.getDicePlacedPositions().get(1));
//        b.placeDiceOnBoard(new int[]{0, 2});
//        assertEquals(3, b.getDicePlacedPositions().size());
//        assertEquals(new Grids(0, 2), b.getDicePlacedPositions().get(0));
//        assertEquals(new Grids(1, 1), b.getDicePlacedPositions().get(1));
//        assertEquals(new Grids(1, 2), b.getDicePlacedPositions().get(2));
//        b.placeDiceOnBoard(new int[]{0, 0});
//        assertEquals(4, b.getDicePlacedPositions().size());
//        assertEquals(new Grids(0, 0), b.getDicePlacedPositions().get(0));
//        assertEquals(new Grids(0, 2), b.getDicePlacedPositions().get(1));
//        assertEquals(new Grids(1, 1), b.getDicePlacedPositions().get(2));
//        assertEquals(new Grids(1, 2), b.getDicePlacedPositions().get(3));
//    }

    @Test
    public void test_samePreferenceSelection() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3],"
                + "[4, 3, 6],"
                + "[5, 0, 6]"
                + "],"
                + "\"jewels\": ["
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 0}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 1, \"y\": 0}"
                + "]"
                + "}"
                + "]"
                + "}";
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 1));
        dicePlaced.add(new Grids(1, 0));
        List<Integer> arrayOfDices = new ArrayList<>();
        arrayOfDices.add(1);
        arrayOfDices.add(2);
        arrayOfDices.add(5);
        arrayOfDices.add(6);
        Board b = new Board(new StringReader(testString), dicePlaced);
        List<Grids> values = b.getCrossedNeighbours(false, arrayOfDices);
        assertEquals(4, values.size());
        int[] chosenCell = b.chooseAICell(values);
        assertArrayEquals(chosenCell, new int[]{0, 0});
    }

    @Test
    public void test_replaceDices() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 6],"
                + "[4, 3, 4, 5],"
                + "[0, 6, 3, 1],"
                + "[1, 2, 3, 4]"
                + "]"
                + "}";
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 2));
        dicePlaced.add(new Grids(1, 1));
        Board b = new Board(new StringReader(testString), dicePlaced);
        assertEquals(4, b.getCellValue(1, 2).intValue());
        assertEquals(3, b.getCellValue(1, 1).intValue());
        b.replaceDices();
        assertEquals(0, b.getCellValue(1, 2).intValue());
        assertEquals(0, b.getCellValue(1, 1).intValue());
    }

    @Test
    public void test_SatelliteLanding() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 6],"
                + "[4, 3, 4, 5],"
                + "[0, 6, 3, 1],"
                + "[1, 7, 3, 4]"
                + "],"
                + "\"rocket\": {\"x\" : 0, \"y\": 1},"
                + "\"planet\": {\"x\" : 1, \"y\": 3}"
                + "}";
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 2));
        dicePlaced.add(new Grids(1, 0));
        Board b = new Board(new StringReader(testString), dicePlaced);
        assertTrue(b.checkSatelliteLanding(1, 0));
        assertEquals(new Grids(3, 1), b.getPlanetPosition());
        assertEquals(7, b.getCellValue(3, 1).intValue());
        assertEquals(4, b.getCellValue(1, 0).intValue());
        b.replaceDices();
        assertEquals(0, b.getCellValue(3, 1).intValue());
        assertEquals(0, b.getCellValue(1, 0).intValue());
    }

    @Test
    public void test_Rocket_Planet_null() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2],"
                + "[4, 3]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
        assertNull(b.getPlanetPosition());
        assertFalse(b.checkSatelliteLanding(1, 0));
        b.replaceDices();
    }

    @Test
    public void test_clear_dices_emptyDices() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2],"
                + "[4, 3]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
        assertEquals(0, b.getDicePlacedPositions().size());
        assertEquals(0, b.getCrossedPositions().size());
        b.clearDices();
        assertEquals(0, b.getDicePlacedPositions().size());
        assertEquals(0, b.getCrossedPositions().size());
    }

    @Test
    public void test_clear_dices_crossedPositions() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2],"
                + "[4, 3]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
        b.placeDiceOnBoard(new int[]{0, 1});
        b.placeDiceOnBoard(new int[]{1, 0});
        assertEquals(2, b.getDicePlacedPositions().size());
        assertEquals(0, b.getCrossedPositions().size());
        b.replaceDices();
        b.clearDices();
        assertEquals(0, b.getDicePlacedPositions().size());
        assertEquals(2, b.getCrossedPositions().size());
    }

    @Test
    public void test_explodeBombs_empty() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2],"
                + "[4, 3]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
        assertEquals(0, b.getBombPositions().size());
    }

    @Test
    public void test_explodeBomb_noDice() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3],"
                + "[4, 3, null],"
                + "[0, 7, 3]"
                + "],"
                + "\"bombs\": {"
                + "\"points\"    : 2,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 2},"
                + "{\"x\" : 1, \"y\": 1}"
                + "]"
                + "}"
                + "}";
        Board b = new Board(new StringReader(testString));
        assertEquals(0, b.getBombPositions().size());
    }

    @Test
    public void test_explodeBomb_withDice() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3],"
                + "[4, 3, null],"
                + "[0, 7, 3]"
                + "],"
                + "\"bombs\": {"
                + "\"points\"    : 2,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 2},"
                + "{\"x\" : 1, \"y\": 1}"
                + "]"
                + "}"
                + "}";
        Board b = new Board(new StringReader(testString));
        assertEquals(0, b.getBombPositions().size());
        b.placeDiceOnBoard(new int[]{2, 0});
        b.placeDiceOnBoard(new int[]{1, 1});
        assertEquals(2, b.getBombPositions().size());
    }

    @Test
    public void test_FlagPoints_empty() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2],"
                + "[4, 3]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
        assertEquals(0, b.getFlagPoints());
    }

    @Test
    public void test_FlagPoints() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2],"
                + "[4, 3]"
                + "],"
                + "\"flag\": {"
                + "\"points\"   : [10, 6, 3, 1],"
                + "\"position\" : {\"x\" : 1, \"y\": 0}"
                + "}"
                + "}";
        Board b = new Board(new StringReader(testString));
        assertEquals(10, b.getFlagPoints());
        b.removeFlagPoints(1);
        assertEquals(6, b.getFlagPoints());
        assertEquals(3, b.getFlagPointList().size());
        b.removeFlagPoints(2);
        assertEquals(1, b.getFlagPoints());
        assertEquals(1, b.getFlagPointList().size());
        b.removeFlagPoints(2);
    }

    @Test
    public void test_FlagPoints_1() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2],"
                + "[4, 3]"
                + "],"
                + "\"flag\": {"
                + "\"points\"   : [10, 6, 3, 1],"
                + "\"position\" : {\"x\" : 1, \"y\": 0}"
                + "}"
                + "}";
        Board b = new Board(new StringReader(testString));
        assertEquals(10, b.getFlagPoints());
        b.removeFlagPoints(10);
        assertEquals(0, b.getFlagPoints());
        assertEquals(0, b.getFlagPointList().size());
        b.removeFlagPoints(10);
        assertEquals(0, b.getFlagPoints());
        assertEquals(0, b.getFlagPointList().size());
    }

    @Test
    public void test_removeDices() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 6],"
                + "[4, 3, 4, 5],"
                + "[0, 6, 3, 1],"
                + "[1, 7, 3, 4]"
                + "],"
                + "\"rocket\": {\"x\" : 0, \"y\": 1},"
                + "\"planet\": {\"x\" : 1, \"y\": 3}"
                + "}";
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 2));
        dicePlaced.add(new Grids(1, 0));
        Board b = new Board(new StringReader(testString), dicePlaced);
        assertEquals(2, b.getDicePlacedPositions().size());
        b.removeDice(new int[]{1, 2});
        assertEquals(1, b.getDicePlacedPositions().size());
        b.removeDice(new int[]{1, 1});
        assertEquals(1, b.getDicePlacedPositions().size());
        b.removeDice(new int[]{1, 0});
        assertEquals(0, b.getDicePlacedPositions().size());
    }

    @Test
    public void test_totalScore_1() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 6],"
                + "[4, 3, 4, 5],"
                + "[0, 6, 3, 1],"
                + "[1, 7, 3, 4]"
                + "]"
                + "}";
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 2));
        dicePlaced.add(new Grids(1, 0));
        Board b = new Board(new StringReader(testString), dicePlaced);
        b.placeDiceOnBoard(new int[]{2, 3});
        b.replaceDices();
        b.updateCrossPositions();
        assertEquals(0, b.getTotalScore());
    }

    @Test
    public void test_totalScore_2() throws InvalidJSONFileException {
        List<Grids> dice = new ArrayList<>();
        dice.add(new Grids(0, 1));
        dice.add(new Grids(0, 2));
        dice.add(new Grids(1, 0));
        dice.add(new Grids(1, 1));
        dice.add(new Grids(2, 0));
        dice.add(new Grids(0, 0));
        dice.add(new Grids(0, 3));
        Board b = new Board(new StringReader(jsonString), dice);
//        b.placeDiceOnBoard(new int[]{0, 0});
//        b.placeDiceOnBoard(new int[]{0, 3});
        b.replaceDices();
        b.updateCrossPositions();
        assertEquals(11, b.getTotalScore());
    }

    @Test
    public void test_getPlayer() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 6],"
                + "[4, 3, 4, 5],"
                + "[0, 6, 3, 1],"
                + "[1, 7, 3, 4]"
                + "]"
                + "}";
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 2));
        dicePlaced.add(new Grids(1, 0));
        Board b = new Board(new StringReader(testString), dicePlaced);
        b.updateCrossPositions();
        Players player = b.getPlayer(true);
        assertEquals(2, player.getDicePlacedCells().size());
        assertEquals(1, player.getCheckedCells().size());
        assertEquals(0, player.getExplodedCells().size());
        assertTrue(player.getStatus());
    }

    @Test
    public void test_updateData() throws InvalidJSONFileException {
        List<Grids> crossed = new ArrayList<>();
        crossed.add(new Grids(0, 0));
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(0, 1));
        List<Grids> exploded = new ArrayList<>();
        exploded.add(new Grids(0, 1));
        String testString = "{"
                + "\"field\": ["
                + "[1, 2],"
                + "[4, 3]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
        assertEquals(0, b.getDicePlacedPositions().size());
        assertEquals(0, b.getCrossedPositions().size());
        assertEquals(0, b.getExplodedBombsList().size());
        b.updateData(crossed, dicePlaced, exploded, 1);
        assertEquals(1, b.getCrossedPositions().size());
        assertEquals(1, b.getDicePlacedPositions().size());
        assertEquals(1, b.getExplodedBombsList().size());
    }

    @Test
    public void test_Lines_same_preference() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 5],"
                + "[4, 1, 6, 1],"
                + "[5, 3, 6, 1],"
                + "[5, 2, 5, 3]"
                + "],"
                + "\"vertical-lines\": ["
                + "{"
                + "\"points\"    : 5,"
                + "\"positions\" : ["
                + "{\"x\" : 2, \"y\": 0},"
                + "{\"x\" : 2, \"y\": 3}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 10,"
                + "\"positions\" : ["
                + "{\"x\" : 3, \"y\": 0},"
                + "{\"x\" : 3, \"y\": 3}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
        List<Integer> arrayOfDices = new ArrayList<>();
        arrayOfDices.add(5);
        arrayOfDices.add(3);
        b.addCrossPositions(1, 2);
        b.addCrossPositions(3, 2);
        b.addCrossPositions(2, 2);
        b.addCrossPositions(1, 3);
        b.addCrossPositions(2, 3);
        b.addCrossPositions(3, 3);
        List<Grids> values = b.getCrossedNeighbours(true, arrayOfDices);
        assertEquals(3, values.size());
        int[] chosenCell = new int[]{0, 3};
        assertArrayEquals(chosenCell, b.chooseAICell(values));
    }

    @Test
    public void test_horizontalLastCordinate_false() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 5],"
                + "[4, 1, 6, 1],"
                + "[5, 3, 6, 1],"
                + "[5, 2, 5, 3]"
                + "],"
                + "\"horizontal-lines\": ["
                + "{"
                + "\"points\"    : 5,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 0},"
                + "{\"x\" : 3, \"y\": 0}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 10,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 2},"
                + "{\"x\" : 3, \"y\": 2}"
                + "]"
                + "}"
                + "]"
                + "}";

        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(0, 1));
        dicePlaced.add(new Grids(0, 2));
        Board b = new Board(new StringReader(testString), dicePlaced);
        List<Integer> arrayOfDices = new ArrayList<>();
        arrayOfDices.add(4);
        arrayOfDices.add(1);
        arrayOfDices.add(6);
        arrayOfDices.add(5);
        List<Grids> n = b.getCrossedNeighbours(false, arrayOfDices);
        assertEquals(4, n.size());
        assertEquals(0, b.lineCompleted(n).length);
    }

    @Test
    public void key_and_hole_pair() throws InvalidJSONFileException {
        Board b = new Board(new StringReader(jsonString));
        HashMap<Integer, Integer> keys = b.getKeyAndHolePositions();
        assertEquals(2, keys.size());
        assertTrue(keys.containsKey(21));
        assertTrue(keys.containsKey(00));
        Integer value = 1;
        assertEquals(value, keys.get(21));
        assertEquals(value, keys.get(00));
    }

    @Test
    public void test_random_cell() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 5],"
                + "[0, 0, 6, 1],"
                + "[5, 3, 6, 1],"
                + "[5, 2, 5, 3]"
                + "],"
                + "\"horizontal-lines\": ["
                + "{"
                + "\"points\"    : 5,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 0},"
                + "{\"x\" : 3, \"y\": 0}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 10,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 2},"
                + "{\"x\" : 3, \"y\": 2}"
                + "]"
                + "}"
                + "]"
                + "}";
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(0, 2));
        List<Integer> arrayOfDices = new ArrayList<>();
        arrayOfDices.add(4);
        arrayOfDices.add(6);
        arrayOfDices.add(5);
        Board b = new Board(new StringReader(testString), dicePlaced);
        List<Grids> neighbours = b.getCrossedNeighbours(false, arrayOfDices);
        b.chooseAICell(neighbours);
    }

    @Test
    public void test_sameJewel() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 2],"
                + "[4, 3, 6, 1],"
                + "[5, 3, 6, 5]"
                + "],"
                + "\"jewels\": ["
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 0}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 1}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 3, \"y\": 1}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 1, \"y\": 2}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 2, \"y\": 2}"
                + "]"
                + "}"
                + "]"
                + "}";
        List<Integer> arrayOfDices = new ArrayList<>();
        arrayOfDices.add(1);
        arrayOfDices.add(2);
        arrayOfDices.add(3);
        arrayOfDices.add(4);
        arrayOfDices.add(5);
        arrayOfDices.add(6);
        Board b = new Board(new StringReader(testString));
        b.addCrossPositions(1, 1);
        b.addCrossPositions(1, 2);
        List<Grids> values = b.getCrossedNeighbours(true, arrayOfDices);
        assertEquals(6, values.size());
        int[] chosenCell = b.chooseAICell(values);
        assertArrayEquals(chosenCell, new int[]{1, 0});
    }

    @Test
    public void test_exhaust_fields() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 6],"
                + "[4, 3, 4, 5],"
                + "[0, 6, 3, 1],"
                + "[1, 7, 3, 4]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
        Integer value = 1;
        assertEquals(value, b.getCellValue(0, 0));
        assertFalse(b.getDicePlacedPositions().contains(new Grids(0,0)));
        assertFalse(b.getDicePlacedPositions().contains(new Grids(2,1)));
        b.exhaustFields();
        assertTrue(b.getDicePlacedPositions().contains(new Grids(0,0)));
        assertTrue(b.getDicePlacedPositions().contains(new Grids(2,1)));
        List<Integer> arrayOfDices = new ArrayList<>();
        arrayOfDices.add(1);
        arrayOfDices.add(2);
        arrayOfDices.add(3);
        arrayOfDices.add(4);
        arrayOfDices.add(5);
        arrayOfDices.add(6);
        List<Grids> neighbours = b.getCrossedNeighbours(true, arrayOfDices);
        assertTrue(neighbours.isEmpty());
    }

    @Test
    public void test_jewel_same_preference() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3],"
                + "[4, 3, 6],"
                + "[5, 4, 6]"
                + "],"
                + "\"jewels\": ["
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 2, \"y\": 1}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 2}"
                + "]"
                + "}"
                + "]"
                + "}";
        List<Grids> dicePlaced = new ArrayList<>();
        dicePlaced.add(new Grids(1, 0));
        dicePlaced.add(new Grids(1, 1));
        List<Integer> arrayOfDices = new ArrayList<>();
        arrayOfDices.add(6);
        arrayOfDices.add(5);
        Board b = new Board(new StringReader(testString), dicePlaced);
        List<Grids> neighbours = b.getCrossedNeighbours(false, arrayOfDices);
        int[] chosenCell = b.chooseAICell(neighbours);
        assertArrayEquals(chosenCell, new int[]{1, 2});
    }

    @Test
    public void test_jewel_same_preference_two() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 2],"
                + "[4, 3, 6, 1],"
                + "[4, 3, 5, 1],"
                + "[5, 4, 6, 2]"
                + "],"
                + "\"jewels\": ["
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 3, \"y\": 1}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 0, \"y\": 3}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 3, \"y\": 3}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
        b.addCrossPositions(0, 0);
        b.addCrossPositions(0, 1);
        b.addCrossPositions(1, 1);
        b.addCrossPositions(1, 0);
        b.addCrossPositions(1, 2);
        b.addCrossPositions(2, 0);
        b.addCrossPositions(2, 1);
        b.addCrossPositions(2, 3);
        List<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(2);
        dices.add(5);
        List<Grids> neighbours = b.getCrossedNeighbours(true, dices);
        int[] chosenCell = b.chooseAICell(neighbours);
        assertArrayEquals(chosenCell, new int[]{1, 3});
    }
    
    @Test
    public void test_jewel_same_preference_three() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 2],"
                + "[4, 3, 6, 1],"
                + "[4, 3, 5, 1],"
                + "[5, 4, 6, 2]"
                + "],"
                + "\"jewels\": ["
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 3, \"y\": 2}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 1, \"y\": 3}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 3, \"y\": 3}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
        b.addCrossPositions(0, 0);
        b.addCrossPositions(1, 3);
        b.addCrossPositions(1, 1);
        b.addCrossPositions(1, 0);
        b.addCrossPositions(1, 2);
        b.addCrossPositions(2, 0);
        b.addCrossPositions(2, 1);
        b.addCrossPositions(2, 2);
        b.addCrossPositions(3, 2);
        List<Integer> dices = new ArrayList<>();
//        dices.add(1);
        dices.add(2);
        dices.add(4);
        List<Grids> neighbours = b.getCrossedNeighbours(true, dices);
        int[] chosenCell = b.chooseAICell(neighbours);
        assertArrayEquals(chosenCell, new int[]{3, 1});
    }
    
    @Test
    public void test_jewel_same_preference_four() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 2],"
                + "[4, 3, 6, 1],"
                + "[4, 3, 5, 1],"
                + "[5, 4, 6, 2]"
                + "],"
                + "\"jewels\": ["
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 1, \"y\": 2}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 3, \"y\": 2}"
                + "]"
                + "},"
                + "{"
                + "\"points\"    : 3,"
                + "\"positions\" : ["
                + "{\"x\" : 2, \"y\": 3}"
                + "]"
                + "}"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString));
        b.addCrossPositions(0, 0);
        b.addCrossPositions(0, 1);
        b.addCrossPositions(1, 1);
        b.addCrossPositions(0, 2);
        b.addCrossPositions(1, 2);
        b.addCrossPositions(0, 3);
        b.addCrossPositions(2, 2);
        List<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(6);
        dices.add(3);
        List<Grids> neighbours = b.getCrossedNeighbours(true, dices);
        int[] chosenCell = b.chooseAICell(neighbours);
        assertArrayEquals(chosenCell, new int[]{2, 1});
    }
    
    @Test
    public void test_sort_list() throws InvalidJSONFileException {
       Board b = new Board(new StringReader(jsonString)); 
       List<Grids> positions = new ArrayList<>();
       positions.add(new Grids(3,0));
       positions.add(new Grids(2,3));
       positions.add(new Grids(0,0));
       positions.add(new Grids(0,4));
       positions.add(new Grids(1,0));
       positions.add(new Grids(1,3));
       
       
       assertEquals(new Grids(3,0), positions.get(0));
       assertEquals(new Grids(2,3), positions.get(1));
       assertEquals(new Grids(0,0), positions.get(2));
       assertEquals(new Grids(0,4), positions.get(3));
       assertEquals(new Grids(1,0), positions.get(4));
       assertEquals(new Grids(1,3), positions.get(5));
       b.sortList(positions);
       assertEquals(new Grids(0,0), positions.get(0));
       assertEquals(new Grids(0,4), positions.get(1));
       assertEquals(new Grids(1,0), positions.get(2));
       assertEquals(new Grids(1,3), positions.get(3));
       assertEquals(new Grids(2,3), positions.get(4));
       assertEquals(new Grids(3,0), positions.get(5));
    }
    
    @Test
    public void test_random_choice_AI() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 2],"
                + "[4, 3, 6, 1],"
                + "[4, 3, 5, 1],"
                + "[5, 4, 6, 2]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString)); 
        b.placeDiceOnBoard(new int[]{1,2});
         List<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(5);
        dices.add(6);
       List<Grids> values =  b.getCrossedNeighbours(false, dices);
        assertEquals(4, values.size());
        int[] chosen = b.chooseAICell(values);
        assertArrayEquals(chosen, new int[]{0,2});
    }
    
     @Test
    public void test_random_choice_AI_multiple() throws InvalidJSONFileException {
        String testString = "{"
                + "\"field\": ["
                + "[1, 2, 3, 2],"
                + "[4, 3, 6, 1],"
                + "[4, 3, 5, 1],"
                + "[5, 4, 6, 2]"
                + "]"
                + "}";
        Board b = new Board(new StringReader(testString)); 
        b.placeDiceOnBoard(new int[]{1,2});
        b.placeDiceOnBoard(new int[]{1,1});
         List<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(5);
        dices.add(6);
       List<Grids> values =  b.getCrossedNeighbours(false, dices);
        assertEquals(6, values.size());
        int[] chosen = b.chooseAICell(values);
        assertArrayEquals(chosen, new int[]{0,1});
    }
}
