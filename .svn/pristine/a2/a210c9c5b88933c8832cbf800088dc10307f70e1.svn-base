/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SHWETHA
 */
public class PlayerScoreTest {

    @Test
    public void test_constructor() {
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getBombCount());
        assertEquals(0, sc.getFlagPoints());
        assertEquals(0, sc.getHLinePoints().size());
        assertEquals(0, sc.getJewelsList().size());
        assertEquals(0, sc.getVLinePoints().size());
    }
    
    @Test
    public void test_addBomb() {
        PlayerScore sc = new PlayerScore();
        sc.addBomb();
        assertEquals(1, sc.getBombCount());
    }
    
    @Test
    public void test_addJewelPoints_empty() {
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getJewelsList().size());
        Cell c = new Cell(5);
        sc.addJewelPoints(c);
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getJewelsList().size());      
    }
    @Test
    public void test_addJewelPoints() {
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getJewelsList().size());
        Cell c = new Jewel(5, 3);
        sc.addJewelPoints(c);
        assertEquals(3, sc.getScore());
        assertEquals(1, sc.getJewelsList().size());      
    }
    
    @Test
    public void test_addJewelPoints_multiple() {
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getJewelsList().size());
        Cell c = new Jewel(5, 3);
        sc.addJewelPoints(c);
        c = new Jewel(4, 2);
        sc.addJewelPoints(c);
        c = new Jewel(6, 3);
        sc.addJewelPoints(c);
        assertEquals(8, sc.getScore());
        assertEquals(3, sc.getJewelsList().size());      
    }
    
    @Test
    public void test_addHLines_noHlines() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> hLines = new HashMap<>();
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getHLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialHLines(cells, hLines);
        assertEquals(0, sc.getHLinePoints().size());
        assertEquals(0, sc.getScore());
    }
    
    @Test
    public void test_addHLines_noCrossedCells() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> hLines = new HashMap<>();
        List<Integer[]> positions = new ArrayList<>();
        positions.add(new Integer[]{0,0,2,0});
        hLines.put(3, positions);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getHLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialHLines(cells, hLines);
        assertEquals(0, sc.getHLinePoints().size());
        assertEquals(0, sc.getScore());
    }
    
    @Test
    public void test_addHLines_withCrossedCells() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> hLines = new HashMap<>();
        List<Integer[]> positions = new ArrayList<>();
        positions.add(new Integer[]{0,0,2,0});
        hLines.put(3, positions);
        cells[0][0].setfieldValue(0);
        cells[0][1].setfieldValue(0);
        cells[0][2].setfieldValue(0);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getHLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialHLines(cells, hLines);
        assertEquals(1, sc.getHLinePoints().size());
        assertEquals(3, sc.getScore());
    }
    
    @Test
    public void test_addHLines_oneCellMissing() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> hLines = new HashMap<>();
        List<Integer[]> positions = new ArrayList<>();
        positions.add(new Integer[]{0,0,2,0});
        hLines.put(3, positions);
        cells[0][0].setfieldValue(0);
        cells[0][2].setfieldValue(0);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getHLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialHLines(cells, hLines);
        assertEquals(0, sc.getHLinePoints().size());
        assertEquals(0, sc.getScore());
    }
    
    @Test
    public void test_addHLines_twoHLines_samePoints_1() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> hLines = new HashMap<>();
        List<Integer[]> positions = new ArrayList<>();
        positions.add(new Integer[]{0,0,2,0});
        positions.add(new Integer[]{0,2,2,2});
        hLines.put(3, positions);
        cells[0][0].setfieldValue(0);
        cells[0][1].setfieldValue(0);
        cells[0][2].setfieldValue(0);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getHLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialHLines(cells, hLines);
        assertEquals(1, sc.getHLinePoints().size());
        assertEquals(3, sc.getScore());
    }
    
    @Test
    public void test_addHLines_twoHLines_samePoints_2() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> hLines = new HashMap<>();
        List<Integer[]> positions = new ArrayList<>();
        positions.add(new Integer[]{0,0,2,0});
        positions.add(new Integer[]{0,2,2,2});
        hLines.put(3, positions);
        cells[0][0].setfieldValue(0);
        cells[0][1].setfieldValue(0);
        cells[0][2].setfieldValue(0);
        cells[2][0].setfieldValue(0);
        cells[2][1].setfieldValue(0);
        cells[2][2].setfieldValue(0);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getHLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialHLines(cells, hLines);
        assertEquals(2, sc.getHLinePoints().size());
        assertEquals(6, sc.getScore());
    }
    
    @Test
    public void test_addHLines_twoHLines_differentPoints() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> hLines = new HashMap<>();
        List<Integer[]> positions1 = new ArrayList<>();
        positions1.add(new Integer[]{0,0,2,0});
        List<Integer[]> positions2 = new ArrayList<>();
        positions2.add(new Integer[]{0,2,2,2});
        hLines.put(3, positions1);
        hLines.put(5, positions2);
        cells[0][0].setfieldValue(0);
        cells[0][1].setfieldValue(0);
        cells[0][2].setfieldValue(0);
        cells[2][0].setfieldValue(0);
        cells[2][1].setfieldValue(0);
        cells[2][2].setfieldValue(0);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getHLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialHLines(cells, hLines);
        assertEquals(2, sc.getHLinePoints().size());
        assertEquals(8, sc.getScore());
    }
    
    @Test
    public void test_addVLines_noHlines() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> vLines = new HashMap<>();
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getVLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialHLines(cells, vLines);
        assertEquals(0, sc.getVLinePoints().size());
        assertEquals(0, sc.getScore());
    }
    
    @Test
    public void test_addVLines_noCrossedCells() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> vLines = new HashMap<>();
        List<Integer[]> positions = new ArrayList<>();
        positions.add(new Integer[]{1,0,1,2});
        vLines.put(3, positions);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getVLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialVLines(cells, vLines);
        assertEquals(0, sc.getVLinePoints().size());
        assertEquals(0, sc.getScore());
    }
    
    @Test
    public void test_addVLines_withCrossedCells() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> vLines = new HashMap<>();
        List<Integer[]> positions = new ArrayList<>();
        positions.add(new Integer[]{1,0,1,2});
        vLines.put(3, positions);
        cells[0][1].setfieldValue(0);
        cells[1][1].setfieldValue(0);
        cells[2][1].setfieldValue(0);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getVLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialVLines(cells, vLines);
        assertEquals(1, sc.getVLinePoints().size());
        assertEquals(3, sc.getScore());
    }
    
    @Test
    public void test_addVLines_oneCellMissing() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> vLines = new HashMap<>();
        List<Integer[]> positions = new ArrayList<>();
        positions.add(new Integer[]{1,0,1,2});
        vLines.put(3, positions);
        cells[0][1].setfieldValue(0);
        cells[2][1].setfieldValue(0);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getVLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialVLines(cells, vLines);
        assertEquals(0, sc.getVLinePoints().size());
        assertEquals(0, sc.getScore());
    }
    
    @Test
    public void test_addVLines_twoVLines_samePoints_1() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> vLines = new HashMap<>();
        List<Integer[]> positions = new ArrayList<>();
        positions.add(new Integer[]{1,0,1,2});
        positions.add(new Integer[]{2,0,2,2});
        vLines.put(3, positions);
        cells[0][1].setfieldValue(0);
        cells[1][1].setfieldValue(0);
        cells[2][1].setfieldValue(0);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getVLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialVLines(cells, vLines);
        assertEquals(1, sc.getVLinePoints().size());
        assertEquals(3, sc.getScore());
    }
    
    @Test
    public void test_addVLines_twoVLines_samePoints_2() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> vLines = new HashMap<>();
        List<Integer[]> positions = new ArrayList<>();
        positions.add(new Integer[]{1,0,1,2});
        positions.add(new Integer[]{2,0,2,2});
        vLines.put(3, positions);
        cells[0][1].setfieldValue(0);
        cells[1][1].setfieldValue(0);
        cells[2][1].setfieldValue(0);
        cells[0][2].setfieldValue(0);
        cells[1][2].setfieldValue(0);
        cells[2][2].setfieldValue(0);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getVLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialVLines(cells, vLines);
        assertEquals(2, sc.getVLinePoints().size());
        assertEquals(6, sc.getScore());
    }
    
    @Test
    public void test_addVLines_twoVLines_differentPoints() {
        Cell[][] cells = {
            {new Cell(1), new Cell(3), new Cell(4)},
            {new Cell(3), new Cell(2), new Cell(1)},
            {new Cell(1), new Cell(5), new Cell(6)}
        };
        HashMap<Integer, List<Integer[]>> vLines = new HashMap<>();
        List<Integer[]> positions1 = new ArrayList<>();
        positions1.add(new Integer[]{1,0,1,2});
        List<Integer[]> positions2 = new ArrayList<>();
        positions2.add(new Integer[]{2,0,2,2});
        vLines.put(3, positions1);
        vLines.put(5, positions2);
        cells[0][1].setfieldValue(0);
        cells[1][1].setfieldValue(0);
        cells[2][1].setfieldValue(0);
        cells[0][2].setfieldValue(0);
        cells[1][2].setfieldValue(0);
        cells[2][2].setfieldValue(0);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getVLinePoints().size());
        assertEquals(0, sc.getScore());
        sc.addSpecialVLines(cells, vLines);
        assertEquals(2, sc.getVLinePoints().size());
        assertEquals(8, sc.getScore());
    }
    
    @Test
    public void test_addPuzzle_noPairCompletion() {
        Cell[][] cells = new Cell[2][2];
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(0,0));
        positions.add(new Grids(1,1));
        cells[0][0] = new Puzzle(5, 5, positions);
        cells[0][1] = new Cell(2);
        cells[1][0] = new Cell(3);
        cells[1][1] = new Puzzle(4, 5, positions);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getPuzzleCount());
        cells[0][0].setfieldValue(0);
        sc.checkForPuzzleCompletion(cells, new HashMap<>());
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getPuzzleCount()); 
    }
    
    @Test
    public void test_addPuzzle_PairCompletion() {
        Cell[][] cells = new Cell[2][2];
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(0,0));
        positions.add(new Grids(1,1));
        cells[0][0] = new Puzzle(5, 5, positions);
        cells[0][1] = new Cell(2);
        cells[1][0] = new Cell(3);
        cells[1][1] = new Puzzle(4, 5, positions);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getPuzzleCount());
        cells[0][0].setfieldValue(0);
        cells[1][1].setfieldValue(0);
        sc.checkForPuzzleCompletion(cells, new HashMap<>());
        assertEquals(5, sc.getScore());
        assertEquals(1, sc.getPuzzleCount()); 
    }
    
    @Test
    public void test_addPuzzle_3Puzzles_notCompleted() {
        Cell[][] cells = new Cell[2][2];
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(0,0));
        positions.add(new Grids(1,1));
        positions.add(new Grids(1,0));
        cells[0][0] = new Puzzle(5, 5, positions);
        cells[0][1] = new Cell(2);
        cells[1][0] = new Puzzle(3, 5, positions);
        cells[1][1] = new Puzzle(4, 5, positions);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getPuzzleCount());
        cells[0][0].setfieldValue(0);
        cells[1][1].setfieldValue(0);
        sc.checkForPuzzleCompletion(cells, new HashMap<>());
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getPuzzleCount()); 
    }
    
    @Test
    public void test_addPuzzle_3Puzzles_Completed() {
        Cell[][] cells = new Cell[2][2];
        List<Grids> positions = new ArrayList<>();
        positions.add(new Grids(0,0));
        positions.add(new Grids(1,1));
        positions.add(new Grids(0,1));
        cells[0][0] = new Puzzle(5, 5, positions);
        cells[0][1] = new Cell(2);
        cells[1][0] = new Puzzle(3, 5, positions);
        cells[1][1] = new Puzzle(4, 5, positions);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getPuzzleCount());
        cells[0][0].setfieldValue(0);
        cells[1][1].setfieldValue(0);
        cells[1][0].setfieldValue(0);
        sc.checkForPuzzleCompletion(cells, new HashMap<>());
        assertEquals(5, sc.getScore());
        assertEquals(1, sc.getPuzzleCount()); 
    }
    
    @Test
    public void test_addPuzzle_2Puzzlespairs_NotCompleted() {
        Cell[][] cells = new Cell[2][2];
        List<Grids> positions1 = new ArrayList<>();
        positions1.add(new Grids(0,0));
        positions1.add(new Grids(1,0));
        
        List<Grids> positions2 = new ArrayList<>();
        positions2.add(new Grids(0,1));
        positions2.add(new Grids(1,1));
        
        cells[0][0] = new Puzzle(5, 5, positions1);
        Cell c = cells[0][0];
        System.out.println(((Puzzle)c).getPositions().get(0).getXPos());
        System.out.println(((Puzzle)c).getPositions().get(0).getYPos());
        System.out.println(((Puzzle)c).getPositions().get(1).getXPos());
        System.out.println(((Puzzle)c).getPositions().get(1).getYPos());
        
        
        cells[0][1] = new Puzzle(2, 5, positions1);
        cells[1][0] = new Puzzle(3, 5, positions2);
        cells[1][1] = new Puzzle(4, 5, positions2);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getPuzzleCount());
        cells[0][0].setfieldValue(0);
        cells[1][0].setfieldValue(0);
        cells[1][1].setfieldValue(0);
        
        sc.checkForPuzzleCompletion(cells, new HashMap<>());
        assertEquals(5, sc.getScore());
        assertEquals(1, sc.getPuzzleCount()); 
    }
    
    @Test
    public void test_addPuzzle_2Puzzlespairs_Completed() {
        Cell[][] cells = new Cell[2][2];
        List<Grids> positions1 = new ArrayList<>();
        positions1.add(new Grids(0,0));
        positions1.add(new Grids(1,0));
        
        List<Grids> positions2 = new ArrayList<>();
        positions2.add(new Grids(0,1));
        positions2.add(new Grids(1,1));
        
        cells[0][0] = new Puzzle(5, 5, positions1);
        cells[0][1] = new Puzzle(2, 5, positions1);
        cells[1][0] = new Puzzle(3, 5, positions2);
        cells[1][1] = new Puzzle(4, 5, positions2);
        PlayerScore sc = new PlayerScore();
        assertEquals(0, sc.getScore());
        assertEquals(0, sc.getPuzzleCount());
        cells[0][0].setfieldValue(0);
        cells[0][1].setfieldValue(0);
        cells[1][0].setfieldValue(0);
        cells[1][1].setfieldValue(0);
        
        sc.checkForPuzzleCompletion(cells, new HashMap<>());
        assertEquals(10, sc.getScore());
        assertEquals(2, sc.getPuzzleCount()); 
    }
    
}
