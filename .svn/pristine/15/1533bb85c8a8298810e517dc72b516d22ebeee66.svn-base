package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Points gained/lost by a player is recorded here. This class also records a
 * players score statistics
 *
 * @author SHWETHA
 */
public class PlayerScore {

    private int totalScore;
    private final ArrayList<Integer> jewelPoints;
    private final ArrayList<Integer> hLinePoints;
    private final ArrayList<Integer> vLinePoints;
    private final List<Integer> puzzleType;
    private final List<Integer> puzzlePoints;
    private int flagPoint;
    private int bombCount;
    private int puzzleCount;

    /**
     * Constructor for the playerScore
     */
    public PlayerScore() {
        jewelPoints = new ArrayList<>();
        hLinePoints = new ArrayList<>();
        vLinePoints = new ArrayList<>();
        puzzleType = new ArrayList<>();
        puzzlePoints = new ArrayList<>();
        flagPoint = 0;
        bombCount = 0;
        puzzleCount = 0;
    }

    /**
     * Getter for the bomb explosion count
     *
     * @return number of bombs exploded
     */
    public int getBombCount() {
        return bombCount;
    }

    /**
     * Getter for the jewels points collected by the player during the course of
     * the game
     *
     * @return list of jewels collected
     */
    public List<Integer> getJewelsList() {
        return jewelPoints;
    }

    /**
     * Getter for the points obtained on completing special horizontal lines by
     * the player during the course of the game
     *
     * @return list of horizontal lines completion points
     */
    public List<Integer> getHLinePoints() {
        return hLinePoints;
    }

    /**
     * Getter for the points obtained on completing special vertical lines by
     * the player during the course of the game
     *
     * @return list of vertical lines completion points
     */
    public List<Integer> getVLinePoints() {
        return vLinePoints;
    }

    /**
     * Getter for the flag points collected by the player during the course of
     * the game
     *
     * @return flag point collected
     */
    public int getFlagPoints() {
        return flagPoint;
    }

    /**
     * Getter for the number of puzzles collected by the player
     *
     * @return puzzles collected
     */
    public int getPuzzleCount() {
        return puzzleCount;
    }

    /**
     * Getter for the total score of a player
     *
     * @return total score of the player
     */
    public int getScore() {
        return totalScore;
    }

    /**
     * Getter for the type of the puzzle collected during the course of the
     * game.
     *
     * @return points associated with the puzzle
     */
    public List<Integer> getPuzzleType() {
        return puzzleType;
    }

    /**
     * Getter for the points gained on completing the jigsaw puzzle during the
     * course of the game
     *
     * @return points gained by completing jigsaw
     */
    public List<Integer> getPuzzlePoints() {
        return puzzlePoints;
    }

    /**
     * Keeps the count of number of bombs exploded at the end of each turn.
     * Points corresponding to the number of bombs exploded are subtracted from
     * the total score at the end of the game
     */
    protected void addBomb() {
        bombCount++;
    }

    /**
     * If a flag cell has been reached, the corresponding points are added to
     * the players score.
     *
     * @param flagScore the points obtained on reaching the flag
     */
    protected void addFlagScore(int flagScore) {
        flagPoint = flagScore;
    }

    /**
     * Calculates the total points gained by the player
     *
     * @param cells array of the cells from the players board
     * @param hLines the special horizontal line positions with corresponding
     * points
     * @param vLines the special vertical lines positions with corresponding
     * points
     * @param puzzlePositions positions of the puzzle pieces
     * @return the total points gained by the player
     */
    protected int calculatePoints(Cell[][] cells,
            HashMap<Integer, List<Integer[]>> hLines,
            HashMap<Integer, List<Integer[]>> vLines,
            HashMap<Integer, Integer> puzzlePositions) {
        //Parse every cell of the board
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] != null
                        && Objects.equals(cells[i][j].getfieldValue(), 0)) {
                    // check if the cell has a jewel and if it is collected
                    addJewelPoints(cells[i][j]);
                }
            }
        }
        // check if any horizontal lines are completed
        if (hLines != null) {
            addSpecialHLines(cells, hLines);
        }
        // check if any vertical lines are completed
        if (vLines != null) {
            addSpecialVLines(cells, vLines);
        }
        // check if the puzzles are completed
        checkForPuzzleCompletion(cells, puzzlePositions);

        // subtract points corresponding to the exploded bombs
        subtractBombs();

        // add flag points if any
        if (flagPoint > 0) {
            totalScore = totalScore + flagPoint;
        }
        return totalScore;
    }

    /**
     * Adds the jewel points to the total score if the input cell has any jewels
     *
     * @param cell input cell which has to checked for the presence of the jewel
     */
    protected void addJewelPoints(Cell cell) {
        if (cell.hasSpecialField()
                && cell.getSpecialField() == SpecialFields.JEWEL) {
            jewelPoints.add(((Jewel) cell).getPoints());
            totalScore = totalScore + ((Jewel) cell).getPoints();
        }
    }

    /**
     * Checks if any special horizontal line is completed and adds the
     * corresponding points to the total score
     *
     * @param cells array of the cells from the players board
     * @param hLines the list of special horizontal lines with corresponding
     * points
     */
    protected void addSpecialHLines(Cell[][] cells,
            HashMap<Integer, List<Integer[]>> hLines) {

        for (Map.Entry<Integer, List<Integer[]>> entry : hLines.entrySet()) {
            List<Integer[]> specialLine = entry.getValue();
            for (int i = 0; i < specialLine.size(); i++) {
                Integer[] line = specialLine.get(i);
                boolean complete = true;
                int xPos = line[1];
                for (int j = line[0]; j <= line[2] && complete; j++) {
                    if (cells[xPos][j] != null
                            && !Objects.equals(cells[xPos][j].getfieldValue(), 0)) {
                        complete = false;
                    }
                }
                if (complete) {
                    hLinePoints.add(entry.getKey());
                    totalScore = totalScore + entry.getKey();
                }
            }
        }
    }

    /**
     * Checks if any special vertical line is completed and adds the
     * corresponding points to the total score
     *
     * @param cells array of the cells from the players board
     * @param vLines the list of special vertical lines with corresponding
     * points
     */
    protected void addSpecialVLines(Cell[][] cells,
            HashMap<Integer, List<Integer[]>> vLines) {

        for (Map.Entry<Integer, List<Integer[]>> entry : vLines.entrySet()) {
            List<Integer[]> specialLine = entry.getValue();
            for (int i = 0; i < specialLine.size(); i++) {
                Integer[] line = specialLine.get(i);
                boolean complete = true;
                int yPos = line[0];
                for (int j = line[1]; j <= line[3] && complete; j++) {
                    if (cells[j][yPos] != null && !Objects.equals(cells[j][yPos].getfieldValue(), 0)) {
                        complete = false;
                    }
                }
                if (complete) {
                    vLinePoints.add(entry.getKey());
                    totalScore = totalScore + entry.getKey();
                }
            }
        }
    }

    /**
     * Checks if all the puzzles of the jigsaw are collected and adds the
     * corresponding points to the total score
     *
     * @param cells array of the cells from the players board
     * @param puzzle
     */
    protected void checkForPuzzleCompletion(Cell[][] cells,
            HashMap<Integer, Integer> puzzle) {
        List<Grids> puzzleSolved = new ArrayList<>();
        //Parse all the cells
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] != null && cells[i][j].hasSpecialField()
                        && cells[i][j].getSpecialField() == SpecialFields.PUZZLE) {
                    // store all the other pieces of the jigsaw
                    List<Grids> puzzlePositions = ((Puzzle) cells[i][j]).getPositions();
                    // check if this jigsaw puzzle is already counted
                    if (Collections.disjoint(puzzlePositions, puzzleSolved)) {
                        /*
                        * add all the pices of the jigsaw to inspected list. 
                        * The other pieces of this jigsaw need not be checked again
                         */
                        puzzleSolved.addAll(puzzlePositions);
                        boolean complete = true;
                        for (int k = 0; k < puzzlePositions.size() && complete; k++) {
                            int xPos = puzzlePositions.get(k).getYPos();
                            int yPos = puzzlePositions.get(k).getXPos();
                            if (!Objects.equals(cells[xPos][yPos].getfieldValue(), 0)) {
                                complete = false;
                            }
                        }
                        // all the pieces of the jigsaw are collected
                        if (complete) {
                            totalScore = totalScore + ((Puzzle) cells[i][j]).getPoints();
                            puzzleCount++;
                            if (puzzle != null && !puzzle.isEmpty()) {
                                int count = puzzle.get(Integer.parseInt(i + "" + j));
                                puzzleType.add(count);
                                puzzlePoints.add(((Puzzle) cells[i][j]).getPoints());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Subtracts the points corresponding to the exploded bombs from the total
     * score
     */
    protected void subtractBombs() {
        for (int i = 1; i <= bombCount; i++) {
            totalScore = totalScore - 2;
        }
    }
}
