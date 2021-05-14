package gui;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.GUIConnector;
import logic.PlayerScore;
import logic.exception.InvalidPlayerRecords;
import logic.exception.IllegalPointsException;
import logic.exception.JsonInvalidException;
import logic.exception.NonUniqueCoordinates;
import logic.exception.NullObjectException;
import logic.exception.OutOfRangeException;

/**
 * Connects Logic and UI. Actual implementation of visualisation
 *
 * @author SHWETHA
 */
public class JavaFXGUI implements GUIConnector {

    private final StackPane[][] playerOne;

    private final GridPane playerTwo;

    private final GridPane playerThree;

    private final GridPane playerFour;

    private final Pane[] dices;

    private final Label rndLabel;

    private final Label trnLabel;

    private final Button roll;

    private final Button dropOut;

    private final GridPane[] vrtclLines;

    private final GridPane[] hrzntlLines;

    private final GridPane[] hrzntlLinesRight;

    private final TextArea lgMoves;

    private final GridPane plyrsGrid;

    private boolean flag;

    private final HashMap<Integer, String> hlines;

    private final HashMap<Integer, String> vlines;

    private final HashMap<Integer, String> jewels;

    private final List<Integer> puzzlePoints;

    private final List<String> puzzleColour;

    private Integer[] flagPoints;

    private int bombPoints;
    /**
     * Prefix image path for the images
     */
    private final String imagePath = "/gui/images/";

    /**
     * Extention of the image files
     */
    private final String imageExtension = ".png";

    /**
     * Constant string that are commonly used in various scenarios
     */
    private final String FLAG = "flagBlue";

    private final String ROCKET = "rocket";

    private final String PLANET = "planet";

    private final String BOMB = "bomb";

    private final String EXPLODED_BOMB = "exploded";

    private int players;

    private final BorderPane[] playerBorders;

    private final MenuItem saveGameBtn;

    private final MenuItem loadGameBtn;

    private final Label wrongCellWarning;

    /**
     * Enum to denote the type of image
     */
    private enum imageType {
        FIELD_VALUE,
        SPECIAL_SYMBOL,
        HLINE,
        VLINE
    };
    /**
     * Constants to represent the points associated with the completion of
     * special horizontal/vertical lines
     */
    private final int LINE_THREE = 3;
    private final int LINE_FIVE = 5;
    private final int LINE_TEN = 10;
    private final int LINE_FIFTEEN = 15;

    /**
     * Constant to denote the number 1, 2 ,3, 4. This is used in several
     * scenarios
     */
    private final int ZERO = 0;
    private final int ONE = 1;
    private final int TWO = 2;
    private final int THREE = 3;
    private final int FOUR = 4;
    private final int FIVE = 5;
    private final int SIX = 6;
    private final int SEVEN = 7;

    /**
     * Constructor for creating JavaFXGUI
     *
     * @param numberPlayers number of players
     * @param playerOne human player
     * @param playerTwo computer player two
     * @param playerThree computer player three
     * @param playerFour computer player four
     * @param dices the pane in which the dices are present
     * @param rndLabel the label denoting the round number
     * @param trnLabel the label denoting the turn number
     * @param roll the button to roll dice
     * @param dropOut the button to drop out from the turn
     * @param vrtclLines grids to indicate the special vertical lines
     * @param hrzntlLines grids to indicate the special horizontal lines
     * @param hrzntlLinesRight grids to indicate the special horizontal lines
     * from the right
     * @param playerBorders border pane of each player
     * @param lgMoves text area to log the moves
     * @param plyrsGrid
     * @param svGame
     * @param ldGame
     * @param wrongCellWarning
     */
    public JavaFXGUI(int numberPlayers, StackPane[][] playerOne, GridPane playerTwo,
            GridPane playerThree, GridPane playerFour, Pane[] dices, Label rndLabel,
            Label trnLabel, Button roll, Button dropOut, GridPane[] vrtclLines,
            GridPane[] hrzntlLines, GridPane[] hrzntlLinesRight,
            BorderPane[] playerBorders, TextArea lgMoves,
            GridPane plyrsGrid, MenuItem svGame, MenuItem ldGame, Label wrongCellWarning) {

        this.players = numberPlayers;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerThree = playerThree;
        this.playerFour = playerFour;
        this.dices = dices;
        this.rndLabel = rndLabel;
        this.trnLabel = trnLabel;
        this.roll = roll;
        this.dropOut = dropOut;
        this.vrtclLines = vrtclLines;
        this.hrzntlLines = hrzntlLines;
        this.hrzntlLinesRight = hrzntlLinesRight;
        this.playerBorders = playerBorders;
        this.lgMoves = lgMoves;
        this.plyrsGrid = plyrsGrid;
        this.flag = false;
        this.saveGameBtn = svGame;
        this.loadGameBtn = ldGame;
        this.wrongCellWarning = wrongCellWarning;
        hlines = new HashMap<>();
        vlines = new HashMap<>();
        jewels = new HashMap<>();
        puzzlePoints = new ArrayList<>();
        puzzleColour = new ArrayList<>();
        for (Pane dice : dices) {
            dice.setVisible(false);
        }
        dropOut.setVisible(false);
    }

    /**
     * Visually enables the player's board corresponding to the number of
     * players
     *
     * @param numberPlayers number of players
     */
    @Override
    public void setPlayerBoards(int numberPlayers) {
        this.players = numberPlayers;
        playerBorders[ZERO].setVisible(true);
        switch (numberPlayers) {
            case TWO:
                playerBorders[ONE].setVisible(true);
                break;
            case THREE:
                playerBorders[ONE].setVisible(true);
                playerBorders[TWO].setVisible(true);
                break;
            case FOUR:
                playerBorders[ONE].setVisible(true);
                playerBorders[TWO].setVisible(true);
                playerBorders[THREE].setVisible(true);
                break;
            default:
                break;
        }
    }

    /**
     * Loads the appropriate jewel to the player board at given coordinates.
     * This is used in two scenarios. 1. Loading a new game 2. Human player
     * return a die and the die is replaced with original jewel cell
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param points points for the jewel
     * @param initialize true - loads jewel to the boards of all the players.
     * False - loads the jewel only to the human player board
     */
    @Override
    public void loadJewel(int xPos, int yPos, int points, boolean initialize) {
        String jewelType = getJewelType(points);
        jewels.put(points, jewelType);
        playerOne[xPos][yPos].getChildren().addAll(getImage(jewelType, imageType.SPECIAL_SYMBOL));
        if (initialize) {
            loadspecialFieldsForAI(xPos, yPos, jewelType, imageType.SPECIAL_SYMBOL);
        }
    }

    /**
     * Selects the jewel name based on the points
     *
     * @param points jewel points
     * @return jewel name
     */
    private String getJewelType(int points) {
        String jewelType = "";
        switch (points) {
            case THREE:
                jewelType = "jewelRed";
                break;
            case TWO:
                jewelType = "jewelYellow";
                break;
            case ONE:
                jewelType = "jewelBlue";
                break;
        }
        return jewelType;
    }

    /**
     * Loads bomb to the player board at given coordinates. This is used in two
     * scenarios. 1. Loading a new game 2. Human player return a die and the die
     * is replaced with original bomb cell
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param points points associated with the bomb
     * @param initialize true - loads bomb to the boards of all the players.
     * False - loads the bomb only to the human player board
     */
    @Override
    public void loadBomb(int xPos, int yPos, int points, boolean initialize) {
        playerOne[xPos][yPos].getChildren().addAll(getImage(BOMB, imageType.SPECIAL_SYMBOL));
        bombPoints = points;
        if (initialize) {
            loadspecialFieldsForAI(xPos, yPos, BOMB, imageType.SPECIAL_SYMBOL);
        }
    }

    /**
     * Loads flag to the player board at given coordinates. This is used in two
     * scenarios. 1. Loading a new game 2. Human player return a die and the die
     * is replaced with original flag cell
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param points list of points that can be gained on reaching the flag
     * @param initialize true - loads flag to the boards of all the players.
     * False - loads the flag only to the human player board
     */
    @Override
    public void loadFlag(int xPos, int yPos, List<Integer> points, boolean initialize) {
        flagPoints = new Integer[points.size()];
        playerOne[xPos][yPos].getChildren().addAll(getImage(FLAG, imageType.SPECIAL_SYMBOL));
        flagPoints = points.toArray(flagPoints);
        if (initialize) {
            loadspecialFieldsForAI(xPos, yPos, FLAG, imageType.SPECIAL_SYMBOL);
        }
        flag = true;
    }

    /**
     * Loads key to the player board at given coordinates. This is used in two
     * scenarios. 1. Loading a new game 2. Human player return a die and the die
     * is replaced with original key cell
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param count the count of the key and keyhole pairs being added
     * @param initialize true - loads key to the boards of all the players.
     * False - loads the key only to the human player board
     */
    @Override
    public void loadKey(int xPos, int yPos, int count, boolean initialize) {
        String keyType = "";
        switch (count) {
            case ONE:
                keyType = "keyBlue";
                break;
            case TWO:
                keyType = "keyYellow";
                break;
            default:
                keyType = "keyBlue";
                break;
        }
        playerOne[xPos][yPos].getChildren().addAll(getImage(keyType, imageType.SPECIAL_SYMBOL));
        if (initialize) {
            loadspecialFieldsForAI(xPos, yPos, keyType, imageType.SPECIAL_SYMBOL);
        }
    }

    /**
     * Loads keyhole to the player board at given coordinates, This is used in
     * two scenarios. 1. Loading a new game 2. Human player return a die and the
     * die is replaced with original keyhole cell
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param count the count of the key and keyhole pairs being added
     * @param initialize true - loads keyhole to the boards of all the players.
     * False - loads the keyhole only to the human player board
     */
    @Override
    public void loadKeyHole(int xPos, int yPos, int count, boolean initialize) {
        String holeType = "";
        switch (count) {
            case ONE:
                holeType = "keyholeBlue";
                break;
            case TWO:
                holeType = "keyholeYellow";
                break;
            default:
                holeType = "keyholeBlue";
                break;
        }
        playerOne[xPos][yPos].getChildren().addAll(getImage(holeType, imageType.SPECIAL_SYMBOL));
        if (initialize) {
            loadspecialFieldsForAI(xPos, yPos, holeType, imageType.SPECIAL_SYMBOL);
        }
    }

    /**
     * Loads puzzle to the player board at given coordinates. This is used in
     * two scenarios. 1. Loading a new game 2. Human player return a die and the
     * die is replaced with original puzzle cell
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param count the count of the puzzle being added
     * @param points points assigned to the puzzle
     * @param initialize true - loads puzzle to the boards of all the players.
     * False - loads the puzzle only to the human player board
     */
    @Override
    public void loadPuzzle(int xPos, int yPos, int count, int points, boolean initialize) {
        String puzzleType = getPuzzleType(count);
        if (!puzzlePoints.contains(points) || !puzzleColour.contains(puzzleType)) {
            puzzlePoints.add(points);
            puzzleColour.add(puzzleType);
        }
        playerOne[xPos][yPos].getChildren().addAll(getImage(puzzleType, imageType.SPECIAL_SYMBOL));
        if (initialize) {
            loadspecialFieldsForAI(xPos, yPos, puzzleType, imageType.SPECIAL_SYMBOL);
        }
    }

    /**
     * Selects the name for the puzzle depending on the count of the puzzle.
     *
     * @param count puzzle count
     * @return puzzle name
     */
    private String getPuzzleType(int count) {
        String puzzleType = "";
        switch (count) {
            case ONE:
                puzzleType = "puzzleGreen";
                break;
            case TWO:
                puzzleType = "puzzleBlue";
                break;
            default:
                puzzleType = "puzzleGreen";
                break;
        }
        return puzzleType;
    }

    /**
     * Loads rocket to the player board at given coordinates. This is used in
     * two scenarios. 1. Loading a new game 2. Human player return a die and the
     * die is replaced with original rocket cell
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param initialize true - loads rocket to the boards of all the players.
     * False - loads the rocket only to the human player board
     */
    @Override
    public void loadRocket(int xPos, int yPos, boolean initialize) {
        playerOne[xPos][yPos].getChildren().addAll(getImage(ROCKET, imageType.SPECIAL_SYMBOL));
        if (initialize) {
            loadspecialFieldsForAI(xPos, yPos, ROCKET, imageType.SPECIAL_SYMBOL);
        }
    }

    /**
     * Loads planet to the player board at given coordinates
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param initialize true - loads planet to the boards of all the players.
     * False - loads the planet only to the human player board
     */
    @Override
    public void loadPlanet(int xPos, int yPos, boolean initialize) {
        playerOne[xPos][yPos].getChildren().addAll(getImage(PLANET, imageType.SPECIAL_SYMBOL));
        if (initialize) {
            loadspecialFieldsForAI(xPos, yPos, PLANET, imageType.SPECIAL_SYMBOL);
        }
    }

    /**
     * Loads the pip value for player board at given coordinates. Die matching
     * with this pip value can be placed on this cell
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param value integer pip value for the cell
     * @param initialize true - loads values to the boards of all the players.
     * False - loads values only to the human player board
     */
    @Override
    public void loadFields(int xPos, int yPos, int value, boolean initialize) {
        playerOne[xPos][yPos].getChildren().addAll(getImage(getImageName(value),
                imageType.FIELD_VALUE));
        if (initialize) {
            loadspecialFieldsForAI(xPos, yPos, getImageName(value), imageType.FIELD_VALUE);
        }
    }

    /**
     * Loads the specified field images to the cell at the given coordinates on
     * the AI players boards
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param symbol name of the image
     * @param type represents the type of image. A pip value or special symbol
     */
    private void loadspecialFieldsForAI(int xPos, int yPos, String symbol, imageType type) {
        playerTwo.add(getImage(symbol, type), yPos, xPos);
        if (players == THREE || players == FOUR) {
            playerThree.add(getImage(symbol, type), yPos, xPos);
            if (players == FOUR) {
                playerFour.add(getImage(symbol, type), yPos, xPos);
            }
        }
    }

    /**
     * Loads the images to indicate the start and end of special lines specified
     * for the AI players board
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param lineType image name of the special line type
     * @param image type of image
     * @param gridPane grids of the special lines
     */
    private void loadSpecialLines(int xPos, int yPos, String lineType,
            imageType image, GridPane[] gridPane) {
        gridPane[ONE].add(getImage(lineType, image), xPos, yPos);
        if (players == THREE || players == FOUR) {
            gridPane[TWO].add(getImage(lineType, image), xPos, yPos);
            if (players == FOUR) {
                gridPane[THREE].add(getImage(lineType, image), xPos, yPos);
            }
        }
    }

    /**
     * Loads the starting/ending position of the special horizontal lines
     *
     * @param horizontalLines the start and end of horizontal lines
     */
    @Override
    public void loadHorizontalLines(HashMap<Integer, List<Integer[]>> horizontalLines) {
        for (Map.Entry<Integer, List<Integer[]>> entry : horizontalLines.entrySet()) {
            int points = entry.getKey();
            String lineType = horizontalImageType(points);
            List<Integer[]> Positions = entry.getValue();
            for (int i = 0; i < Positions.size(); i++) {
                switch (points) {
                    case LINE_TEN:
                    case LINE_FIFTEEN:
                        hrzntlLines[ZERO].add(getImage(lineType, imageType.HLINE),
                                Positions.get(i)[ZERO], Positions.get(i)[ONE]);
                        loadSpecialLines(Positions.get(i)[ZERO], Positions.get(i)[ONE],
                                lineType, imageType.HLINE, hrzntlLines);
                        break;
                    case LINE_FIVE:
                        playerOne[Positions.get(i)[ONE]][Positions.get(i)[TWO] + ONE].
                                getChildren().addAll(getImage(lineType, imageType.HLINE));
                        loadspecialFieldsForAI(Positions.get(i)[ONE],
                                Positions.get(i)[TWO] + ONE, lineType, imageType.HLINE);
                        break;
                    default:
                        hrzntlLinesRight[ZERO].add(getImage(lineType, imageType.HLINE),
                                ZERO, Positions.get(i)[THREE]);
                        loadSpecialLines(ZERO, Positions.get(i)[THREE], lineType,
                                imageType.HLINE, hrzntlLinesRight);
                        break;
                }
                hlines.put(points, lineType);
            }
        }
    }

    /**
     * Loads the ending position of the special vertical lines
     *
     * @param verticalLines the start and end of vertical lines
     */
    @Override
    public void loadVerticalLines(HashMap<Integer, List<Integer[]>> verticalLines) {
        for (Map.Entry<Integer, List<Integer[]>> entry : verticalLines.entrySet()) {
            int points = entry.getKey();
            String lineType = verticalImageType(points);
            List<Integer[]> Positions = entry.getValue();
            for (int i = 0; i < Positions.size(); i++) {
                vrtclLines[ZERO].add(getImage(lineType, imageType.VLINE),
                        Positions.get(i)[ZERO], Positions.get(i)[ONE]);
                loadSpecialLines(Positions.get(i)[ZERO], Positions.get(i)[ONE],
                        lineType, imageType.VLINE, vrtclLines);
                vlines.put(points, lineType);
            }
        }
    }

    /**
     * Selects the image name for the special vertical line based on the points
     *
     * @param points points associated with the vertical line
     * @return image name
     */
    private String verticalImageType(int points) {
        String lineType = "";
        switch (points) {
            case LINE_FIVE:
                lineType = "vFive";
                break;
            case LINE_TEN:
                lineType = "vTen";
                break;
        }
        return lineType;
    }

    /**
     * Selects the image name for the special horizontal line based on the
     * points
     *
     * @param points points associated with the horizontal line
     * @return image name
     */
    private String horizontalImageType(int points) {
        String lineType = "";
        switch (points) {
            case LINE_FIVE:
                lineType = "hFiveLeft";
                break;
            case LINE_TEN:
                lineType = "hTenRight";
                break;
            case LINE_FIFTEEN:
                lineType = "hFifteenRight";
                break;
            case LINE_THREE:
                lineType = "hThreeLeft";
                break;
        }
        return lineType;
    }

    /**
     * Retrieves the image corresponding to the symbol name
     *
     * @param symbol name of the special field
     * @return image view with the image
     */
    private ImageView getImage(String symbol, imageType type) {
        String fileName = imagePath + symbol + imageExtension;
        ImageView iv = new ImageView(new Image(fileName));
        switch (type) {
            case FIELD_VALUE:
                iv.fitHeightProperty().bind(playerOne[ZERO][ZERO].heightProperty());
                iv.fitWidthProperty().bind(playerOne[ZERO][ZERO].widthProperty());
                break;
            case SPECIAL_SYMBOL:
                iv.fitHeightProperty().bind(playerOne[ZERO][ZERO].heightProperty().divide(1.3));
                iv.fitWidthProperty().bind(playerOne[ZERO][ZERO].widthProperty().divide(1.3));
                iv.setOpacity(0.7);
                break;
            case HLINE:
                iv.setFitHeight(15);
                iv.fitHeightProperty().bind(hrzntlLines[ZERO].heightProperty());
                iv.fitWidthProperty().bind(hrzntlLines[ZERO].widthProperty());
                iv.setOpacity(0.8);
                break;
            case VLINE:
                iv.fitHeightProperty().bind(vrtclLines[ZERO].heightProperty().divide(1.2));
                iv.fitWidthProperty().bind(vrtclLines[ZERO].widthProperty().divide(2));
                iv.setOpacity(0.8);
                break;
            default:
                break;
        }
        iv.setPreserveRatio(true);
        return iv;
    }

    /**
     * Selects the image name for the cell pip image based on the value
     *
     * @param value integer value of the cell
     * @return image name
     */
    private String getImageName(int value) {
        String imName = "";
        switch (value) {
            case ZERO:
                imName = "crossed";
                break;
            case ONE:
                imName = "bOne";
                break;
            case TWO:
                imName = "bTwo";
                break;
            case THREE:
                imName = "bThree";
                break;
            case FOUR:
                imName = "bFour";
                break;
            case FIVE:
                imName = "bFive";
                break;
            case SIX:
                imName = "bSix";
                break;
            case SEVEN:
                imName = "planet";
                break;
            default:
                break;
        }
        return imName;
    }

    /**
     * Retrieves the dice image corresponding to the integer value and position
     *
     * @param value of the dice
     * @param position where the dice has to be placed. Either in the dice array
     * or the player grid
     * @return image view with dice image
     */
    private ImageView getDiceImage(int value, String position) {
        String imName = "";
        switch (value) {
            case ONE:
                imName = "/gui/images/dOne.png";
                break;
            case TWO:
                imName = "/gui/images/dTwo.png";
                break;
            case THREE:
                imName = "/gui/images/dThree.png";
                break;
            case FOUR:
                imName = "/gui/images/dFour.png";
                break;
            case FIVE:
                imName = "/gui/images/dFive.png";
                break;
            case SIX:
                imName = "/gui/images/dSix.png";
                break;
            default:
                break;
        }
        ImageView iv = new ImageView(new Image(imName));
        // dice on the table
        if (position.equals("diceGrid")) {
            iv.fitHeightProperty().bind(dices[ZERO].heightProperty());
            iv.fitWidthProperty().bind(dices[ZERO].widthProperty());
        } // dice on the board 
        else if (position.equals("board")) {
            iv.fitHeightProperty().bind(playerOne[ONE][ZERO].heightProperty().divide(1.3));
            iv.fitWidthProperty().bind(playerOne[ONE][ZERO].widthProperty());
        }
        iv.setOpacity(500);
        iv.setPreserveRatio(true);
        return iv;
    }

    /**
     * Loads the dices in the dice array
     *
     * @param diceArray array with dices
     */
    @Override
    public void loadDices(List<Integer> diceArray) {
        for (Pane dice : dices) {
            dice.setVisible(false);
        }
        for (int i = 0; i < diceArray.size(); i++) {
            dices[i].getChildren().add(getDiceImage(diceArray.get(i), "diceGrid"));
            dices[i].setVisible(true);
        }

    }

    /**
     * Visually highlights a cell of the human player board
     *
     * @param x coordinate of the player grid
     * @param y coordinate of the player grid
     */
    @Override
    public void higlightField(int x, int y) {
        playerOne[x][y].setStyle("-fx-background-color: white; -fx-border-color: green; -fx-border-width: 2px;");
    }

    /**
     * Resets the cells of human player
     */
    @Override
    public void resetFields() {
        for (StackPane[] playerOne1 : playerOne) {
            for (StackPane playerOne11 : playerOne1) {
                playerOne11.setStyle("-fx-background-color: red; -fx-border-color: transparent; -fx-border-width: 2px;");
            }
        }
    }

    /**
     * Updates the round number in the GUI
     *
     * @param roundNumber current round number
     */
    @Override
    public void updateRound(int roundNumber) {
        this.rndLabel.setText(String.valueOf(roundNumber));
    }

    /**
     * Updates the turn number in the GUI
     *
     * @param turnNumber current turn number
     */
    @Override
    public void updateTurn(int turnNumber) {
        this.trnLabel.setText(String.valueOf(turnNumber));
    }

    /**
     * Enables the roll button on the GUI
     *
     * @param visible status of visibility
     */
    @Override
    public void enableRoll(boolean visible) {
        this.roll.setVisible(visible);
    }

    /**
     * Places a dice on the player board at a given position
     *
     * @param value of the dice
     * @param pos at which the dice has to be placed
     * @param playerIndex player index
     */
    @Override
    public void placeDiceOnBoard(int value, int[] pos, int playerIndex) {
        resetFields();
        if (playerIndex == ZERO) {
            resetHumanPlayerCell(pos[ZERO], pos[ONE]);
            playerOne[pos[ZERO]][pos[ONE]].getChildren().add(getDiceImage(value, "board"));
        } else {
            GridPane playerAI = getPlayerGrid(playerIndex);
            clearAIPlayersField(pos[ZERO], pos[ONE], playerIndex);
            playerAI.add(getDiceImage(value, "board"), pos[ONE], pos[ZERO]);
        }
    }

    /**
     * Replaces the dice with cross symbol at given coordinates
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param playerIndex player index
     */
    @Override
    public void replaceDicesWithCross(int xPos, int yPos, int playerIndex) {
        if (playerIndex == ZERO) {
            resetHumanPlayerCell(xPos, yPos);
            playerOne[xPos][yPos].getChildren().add(getImage(getImageName(ZERO),
                    imageType.FIELD_VALUE));
        } else {
            replaceDicesAIPlayers(xPos, yPos, playerIndex);
        }
    }

    /**
     * Enables/disables the drop out button on the GUI
     *
     * @param visible true - enables the button. False- disables the button
     */
    @Override
    public void enableDropOut(boolean visible) {
        this.dropOut.setVisible(visible);
    }

    /**
     * Displays the special fields and the points associated with them
     *
     * @param specialFields vertical box in which the special fields are to be
     * displayed
     */
    @Override
    public void computeSpecialFields(VBox specialFields) {
        for (Map.Entry<Integer, String> entry : jewels.entrySet()) {
            addSpecialFields(entry.getKey(), entry.getValue(), specialFields);
        }
        // Display the puzzles if any
        for (int i = 0; i < puzzlePoints.size(); i++) {
            addSpecialFields(puzzlePoints.get(i), puzzleColour.get(i), specialFields);
        }
        // Display special horizontal lines
        for (Map.Entry<Integer, String> entry : hlines.entrySet()) {
            addSpecialFields(entry.getKey(), entry.getValue(), specialFields);
        }
        // Display special vertical lines
        for (Map.Entry<Integer, String> entry : vlines.entrySet()) {
            addSpecialFields(entry.getKey(), entry.getValue(), specialFields);
        }

        // Display the flag
        if (flag) {
            HBox flagField = new HBox();
            flagField.getChildren().add(getImage(FLAG, imageType.SPECIAL_SYMBOL));
            Label flagPointsLabel = new Label();
            String flagGain = "";
            for (int i = 0; i < flagPoints.length; i++) {
                flagGain = flagGain + flagPoints[i];
                if (i < flagPoints.length - ONE) {
                    flagGain = flagGain + " - ";
                }
            }
            if (!flagGain.isEmpty()) {
                flagPointsLabel.setText(flagGain);
                flagField.getChildren().add(flagPointsLabel);
                flagField.setSpacing(15);
                specialFields.getChildren().add(flagField);
                flagField.setAlignment(Pos.CENTER);
                VBox.setMargin(flagField, new Insets(30, 0, 0, 0));
            }
        }
        // display the bomb explosion
        HBox bomb = new HBox();
        bomb.getChildren().add(getImage(EXPLODED_BOMB, imageType.SPECIAL_SYMBOL));
        Label bPoints = new Label();
        bPoints.setText(Integer.toString(-bombPoints));
        bomb.getChildren().add(bPoints);
        bomb.setSpacing(15);
        specialFields.getChildren().add(bomb);
        bomb.setAlignment(Pos.CENTER);
        VBox.setMargin(bomb, new Insets(30, 0, 0, 0));
    }

    /**
     * Adds the image of the specified special symbol with the points associated
     * with them to the vertical box given
     *
     * @param value points associated with the special symbol
     * @param imageName image name of the special symbol
     * @param specialFields the vertical box to which the entries are to be
     * added
     */
    private void addSpecialFields(Integer value, String imageName, VBox specialFields) {
        Label points = new Label();
        points.setText(Integer.toString(value));
        HBox jewel = new HBox();
        jewel.getChildren().add(getImage(imageName, imageType.SPECIAL_SYMBOL));
        jewel.getChildren().add(points);
        jewel.setSpacing(15);
        specialFields.getChildren().add(jewel);
        jewel.setAlignment(Pos.CENTER);
        VBox.setMargin(jewel, new Insets(30, 0, 0, 0));
    }

    /**
     * Displays the winner/s names at the end of the game and also displays the
     * detailed score statistics of every player
     *
     * @param playerStats map of player names with their scores
     * @param winners list of winners
     */
    @Override
    public void gameEnd(LinkedHashMap<String, PlayerScore> playerStats,
            List<String> winners) {
        plyrsGrid.setDisable(true);
        Stage stage = new Stage();
        stage.setTitle("Game Over");
        VBox deatiledScore = new VBox(20);

        // display the winner names
        VBox winnerNames = new VBox();
        Label title = new Label();
        title.setText("WINNER(S) :");
        winnerNames.getChildren().add(title);
        for (int i = 0; i < winners.size(); i++) {
            Label plyrName = new Label();
            plyrName.setText(winners.get(i));
            winnerNames.getChildren().add(plyrName);
        }
        deatiledScore.getChildren().add(winnerNames);
        winnerNames.setAlignment(Pos.CENTER);

        HBox plyrStats = new HBox();
        // individual player score statistics
        for (Map.Entry<String, PlayerScore> mapElement : playerStats.entrySet()) {
            String name = mapElement.getKey();
            mapIndividualScore(name, mapElement.getValue(),
                    plyrStats);
        }
        plyrStats.setAlignment(Pos.CENTER);
        plyrStats.setPadding(new Insets(15, 12, 15, 12));
        plyrStats.setSpacing(50);
        plyrStats.setStyle("-fx-border-color: black;");

        deatiledScore.getChildren().add(plyrStats);
        deatiledScore.setStyle("-fx-padding: 20;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: green;");

        // Close button on the screen
        Button close = new Button("Close");
        close.setOnAction((ActionEvent event) -> {
            stage.close();
        });

        deatiledScore.getChildren().add(close);
        close.setAlignment(Pos.CENTER);
        deatiledScore.setAlignment(Pos.CENTER);
        Scene scene = new Scene(deatiledScore, 1100, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Determines the individual statistics of each player and sorts them
     *
     * @param plyrName name of the player
     * @param score player statistics
     * @param hbox the horizontal boundary
     */
    private void mapIndividualScore(String plyrName, PlayerScore score, HBox hbox) {
        // set the player name
        VBox vbox = new VBox();
        Label name = new Label();
        name.setText(plyrName);
        name.setAlignment(Pos.CENTER);
        vbox.getChildren().add(name);
        GridPane gp = new GridPane();
        ColumnConstraints col = new ColumnConstraints(65);
        col.setHalignment(HPos.CENTER);
        gp.getColumnConstraints().add(col);
        gp.getColumnConstraints().add(col);
        gp.getColumnConstraints().add(col);
        int rowIndex = ZERO;

        // add special symbols collected by the player
        List<Integer> jewelsCollected = score.getJewelsList();
        List<Integer> hLinesCompleted = score.getHLinePoints();
        List<Integer> vLinesCompleted = score.getVLinePoints();
        List<Integer> puzzlePointsGained = score.getPuzzlePoints();

        // calculate the number of each special field calculated by the players
        rowIndex = calculateFrequency(jewelsCollected, "Jewel", gp, rowIndex);
        rowIndex = calculateFrequency(hLinesCompleted, "hLine", gp, rowIndex);
        rowIndex = calculateFrequency(vLinesCompleted, "vLine", gp, rowIndex);

        // include bomb explosion symbol if a bomb has exploded on the players board
        int bomb = score.getBombCount();
        if (bomb > ZERO) {
            addExplodedeBomb(bomb, gp, rowIndex);
        }
        rowIndex++;
        // include puzzle symbol if the player has completed the jigsaw
        int puzzleCount = score.getPuzzleCount();
        if (puzzleCount > ZERO) {
            addPuzzle(gp, rowIndex, score.getPuzzleType(), puzzlePointsGained);
        }
        rowIndex++;
        // include flag symbol if the player has reached the flag
        int flagPoint = score.getFlagPoints();
        if (flagPoint > ZERO) {
            addFlag(gp, rowIndex, flagPoint);
        }
        rowIndex++;
        // sum up the points and display
        Label total = new Label();
        total.setText("Total :");
        Label totalScore = new Label();
        totalScore.setText(String.valueOf(score.getScore()));
        gp.add(total, ZERO, rowIndex);
        gp.add(totalScore, TWO, rowIndex);
        gp.setStyle("-fx-grid-lines-visible: true");

        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10);
        vbox.getChildren().add(gp);
        hbox.getChildren().add(vbox);
    }

    /**
     * Determines how many of the mentioned special field types were collected
     * during the game
     *
     * @param specialFields list of the special fields collected
     * @param filedType the type of the special field
     * @param gp grid pane where the scores must be added
     * @param rowIndex the row number where the scores must be added
     * @return the last row index where an item was added
     */
    private int calculateFrequency(List<Integer> specialFields,
            String filedType, GridPane gp, int rowIndex) {
        List<Integer> completed = new ArrayList<>();
        for (int i = 0; i < specialFields.size(); i++) {
            if (!completed.contains(specialFields.get(i))) {
                completed.add(specialFields.get(i));
                int count = Collections.frequency(specialFields, specialFields.get(i));
                String imageName = mapSpecialFieldToImage(filedType, specialFields.get(i));
                Label number = new Label();
                number.setText("x" + String.valueOf(count));
                Label points = new Label();
                points.setText(String.valueOf(count * specialFields.get(i)));
                gp.add(getImage(imageName, imageType.SPECIAL_SYMBOL), ZERO, rowIndex);
                gp.add(number, ONE, rowIndex);
                gp.add(points, TWO, rowIndex);
                rowIndex++;
            }
        }
        return rowIndex;
    }

    /**
     * Adds the bomb and their count to the grid pane
     *
     * @param bombCount the number of bombs exploded
     * @param gp grid pane
     * @param rowIndex the row number where the scores must be added
     */
    private void addExplodedeBomb(int bombCount, GridPane gp, int rowIndex) {
        Label number = new Label();
        number.setText("x" + String.valueOf(bombCount));
        Label points = new Label();
        points.setText(String.valueOf(bombCount * -bombPoints));
        gp.add(getImage(EXPLODED_BOMB, imageType.SPECIAL_SYMBOL), ZERO, rowIndex);
        gp.add(number, ONE, rowIndex);
        gp.add(points, TWO, rowIndex);
    }

    /**
     * Adds the puzzles and their points to the grid pane
     *
     * @param gp grid pane
     * @param rowIndex the row number where the scores must be added
     * @param puzzleValues the value of each puzzle piece
     * @param puzzlepointsGained points gained on completing the jigsaw
     */
    private void addPuzzle(GridPane gp, int rowIndex, List<Integer> puzzleValues,
            List<Integer> puzzlePointsGained) {

        for (int i = 0; i < puzzleValues.size(); i++) {
            gp.add(getImage(getPuzzleType(puzzleValues.get(i)),
                    imageType.SPECIAL_SYMBOL), ZERO, rowIndex);
            Label lbl = new Label();
            lbl.setText("completed");
            Label points = new Label();
            points.setText(String.valueOf(puzzlePointsGained.get(i)));
            gp.add(lbl, ONE, rowIndex);
            gp.add(points, TWO, rowIndex);
            rowIndex++;
        }
    }

    /**
     * Adds the flag and the points to the grid pane
     *
     * @param gp grid pane
     * @param rowIndex the row number where the scores must be added
     * @param flagPoint points gained on reaching the flag
     */
    private void addFlag(GridPane gp, int rowIndex, int flagPoint) {
        Label lbl = new Label();
        lbl.setText(String.valueOf(flagPoint));
        Label mark = new Label();
        mark.setText("Reached");
        gp.add(getImage(FLAG, imageType.SPECIAL_SYMBOL), ZERO, rowIndex);
        gp.add(mark, ONE, rowIndex);
        gp.add(lbl, TWO, rowIndex);
    }

    /**
     * Gets the name of the image corresponding the field type and points given
     *
     * @param fieldType special field type
     * @param points points associated with the special field
     * @return image name
     */
    private String mapSpecialFieldToImage(String fieldType, int points) {
        String imageName = "";
        switch (fieldType) {
            case "Jewel":
                imageName = getJewelType(points);
                break;
            case "hLine":
                imageName = horizontalImageType(points);
                break;
            case "vLine":
                imageName = verticalImageType(points);
                break;
        }
        return imageName;
    }

    /**
     * Retrieves the player grid corresponding to the player index
     *
     * @param playerIndex player index
     * @return grid pane of the player
     */
    private GridPane getPlayerGrid(int playerIndex) {
        switch (playerIndex) {
            case ONE:
                return playerTwo;
            case TWO:
                return playerThree;
            case THREE:
                return playerFour;
            default:
                return playerTwo;
        }
    }

    /**
     * Replaces dices with cross on the AI player board at given coordinates
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param playerIndex player index of the AI player
     */
    private void replaceDicesAIPlayers(int xPos, int yPos, int playerIndex) {
        GridPane playerAI = getPlayerGrid(playerIndex);
        clearAIPlayersField(xPos, yPos, playerIndex);
        playerAI.add(getImage(getImageName(0), imageType.FIELD_VALUE), yPos, xPos);
    }

    /**
     * Clears the cells at the given coordinates of the AI players board
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     * @param playerIndex player index
     */
    private void clearAIPlayersField(int xPos, int yPos, int playerIndex) {
        GridPane playerAI = getPlayerGrid(playerIndex);
        ObservableList<Node> children = playerAI.getChildren();
        List<Node> toBeRemoved = new ArrayList<>();
        for (Node node : children) {
            if (node != null && GridPane.getRowIndex(node) != null
                    && GridPane.getColumnIndex(node) != null) {
                if (GridPane.getRowIndex(node) == xPos
                        && GridPane.getColumnIndex(node) == yPos) {
                    toBeRemoved.add(node);
                }
            }
        }
        playerAI.getChildren().removeAll(toBeRemoved);
    }

    /**
     * Changes the background colour of the player board indicating the player
     * has skipped the turn
     *
     * @param playerIndex player index who has skipped the turn
     */
    @Override
    public void droppedOutPlayerUpdate(int playerIndex) {
        playerBorders[playerIndex].setStyle("-fx-background-color: green");
    }

    /**
     * Resets the boards to default colour after a new turn starts
     */
    @Override
    public void resetJump() {
        for (BorderPane playerBorder : playerBorders) {
            playerBorder.setStyle("-fx-background-color: red");
        }
    }

    /**
     * Adds bomb exploded image to the board of specified player at specified
     * coordinates
     *
     * @param playerIndex index of the player where the bomb has exploded
     * @param xPos x coordinate
     * @param yPos y coordinate
     */
    @Override
    public void explodeBomb(int playerIndex, int xPos, int yPos) {
        switch (playerIndex) {
            case 0:
                resetHumanPlayerCell(xPos, yPos);
                playerOne[xPos][yPos].getChildren().addAll(getImage(EXPLODED_BOMB, imageType.SPECIAL_SYMBOL));
                break;
            case 1:
                clearAIPlayersField(xPos, yPos, ONE);
                playerTwo.add(getImage(EXPLODED_BOMB, imageType.SPECIAL_SYMBOL), yPos, xPos);
                break;
            case 2:
                clearAIPlayersField(xPos, yPos, TWO);
                playerThree.add(getImage(EXPLODED_BOMB, imageType.SPECIAL_SYMBOL), yPos, xPos);
                break;
            case 3:
                clearAIPlayersField(xPos, yPos, THREE);
                playerFour.add(getImage(EXPLODED_BOMB, imageType.SPECIAL_SYMBOL), yPos, xPos);
                break;
        }
    }

    /**
     * Removes all the children nodes of the human player board cell at given
     * coordinates
     *
     * @param xPos x coordinate
     * @param yPos y coordinate
     */
    @Override
    public void resetHumanPlayerCell(int xPos, int yPos) {
        ObservableList<Node> children = playerOne[xPos][yPos].getChildren();
        List<Node> toBeRemoved = new ArrayList<>();
        for (Node node : children) {
            if (node instanceof ImageView) {
                toBeRemoved.add(node);
            }
        }
        playerOne[xPos][yPos].getChildren().removeAll(toBeRemoved);
    }

    /**
     * Appends data to the log file
     *
     * @param data string to be appended
     */
    @Override
    public void logMoves(String data) {
        lgMoves.appendText(data);
        lgMoves.appendText("\n");
    }

    /**
     * Displays errors that occurs when a user starts a new game and there are
     * errors in the corresponding chosen level file. Errors might also occur
     * when a user loads a previously saved game file which has errors. There
     * cannot be errors while saving the game, but the saved game file can be
     * edited and loaded. This might contain errors. Errors are displayed in
     * accordance with the exception caught
     *
     * @param exception exception caught
     */
    @Override
    public void displayError(Exception exception) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error loading the JSON file");
        alert.setHeaderText("An error occured parsing the level file");
        // null object exception
        if (exception instanceof NullObjectException) {
            alert.setContentText(((NullObjectException) exception).getErrorMessage());
        } // parameters in the file are out of pre defined range 
        else if (exception instanceof OutOfRangeException) {
            alert.setContentText(((OutOfRangeException) exception).getErrorMessage());
        } // Sytax error in the loaded file 
        else if (exception instanceof JsonInvalidException) {
            alert.setContentText("Syntax error occured " + ((JsonInvalidException) exception).getEx());
        } // errors in input/output operations 
        else if (exception instanceof IOException) {
            alert.setContentText("IO Exception while saving file");
        } // invalid points assigned to special fields
        else if (exception instanceof IllegalPointsException) {
            alert.setContentText(((IllegalPointsException) exception).getErrorMessage());
        } // Inavalid player parameters in the previously saved game file 
        else if (exception instanceof InvalidPlayerRecords) {
            alert.setContentText(((InvalidPlayerRecords) exception).getErrorMessage());
        } // coordinates in the saved file not unique 
        else if (exception instanceof NonUniqueCoordinates) {
            alert.setContentText(((NonUniqueCoordinates) exception).getErrorMessage());
        }
        alert.showAndWait();
    }

    /**
     * Displays errors that occur during logging the moves
     *
     * @param ex exception caught while logging the moves
     */
    @Override
    public void logFileErrors(Exception ex) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Log file error");
        alert.setContentText("An error occured while logging the moves");
        if (ex instanceof IOException) {
            alert.setHeaderText("IO exception");
        } else if (ex instanceof URISyntaxException) {
            alert.setContentText("URI syntax error");
        }
    }

    /**
     * Disables the save game option in the file tab
     *
     * @param status true - disable. False - enable
     */
    @Override
    public void disableSaveOption(boolean status) {
        this.saveGameBtn.setDisable(status);
    }

    /**
     * Disables the load game option in the file tab
     *
     * @param status true - disable. False - enable
     */
    @Override
    public void disableLoadOption(boolean status) {
        this.loadGameBtn.setDisable(status);
    }

    /**
     * Clears the previous logs from the text area
     */
    @Override
    public void clearLog() {
        lgMoves.clear();
    }

    /**
     * Displays a warning when wrong cell in the human player board is selected
     *
     * @param status true - warning displayed. False - warning not displayed
     */
    @Override
    public void showWarning(boolean status) {
        this.wrongCellWarning.setVisible(status);
    }

    /**
     * Resets the boards of all the players to the default state
     */
    @Override
    public void resetBoards() {
        ObservableList<Node> children = playerTwo.getChildren();
        List<Node> toBeRemoved = new ArrayList<>();
        for (Node node : children) {
            if (node instanceof ImageView) {
                toBeRemoved.add(node);
            }
        }
        playerTwo.getChildren().removeAll(toBeRemoved);

        children = playerThree.getChildren();
        toBeRemoved = new ArrayList<>();
        for (Node node : children) {
            if (node instanceof ImageView) {
                toBeRemoved.add(node);
            }
        }
        playerThree.getChildren().removeAll(toBeRemoved);

        children = playerFour.getChildren();
        toBeRemoved = new ArrayList<>();
        for (Node node : children) {
            if (node instanceof ImageView) {
                toBeRemoved.add(node);
            }
        }
        playerFour.getChildren().removeAll(toBeRemoved);

        for (StackPane[] stackPaneArray1 : playerOne) {
            for (StackPane stackPaneArray11 : stackPaneArray1) {
                stackPaneArray11.getChildren().clear();
            }
        }

        for (GridPane horizontalLinesArray1 : hrzntlLines) {
            horizontalLinesArray1.getChildren().clear();
        }
        for (GridPane verticalLinesArray1 : vrtclLines) {
            verticalLinesArray1.getChildren().clear();
        }
        for (GridPane horizontalLinesRightArray1 : hrzntlLinesRight) {
            horizontalLinesRightArray1.getChildren().clear();
        }

        playerBorders[ZERO].setVisible(false);
        playerBorders[ONE].setVisible(false);
        playerBorders[TWO].setVisible(false);
        playerBorders[THREE].setVisible(false);

        playerBorders[ZERO].setStyle("-fx-background-color: red");
        playerBorders[ONE].setStyle("-fx-background-color: red");
        playerBorders[TWO].setStyle("-fx-background-color: red");
        playerBorders[THREE].setStyle("-fx-background-color: red");
    }
}
