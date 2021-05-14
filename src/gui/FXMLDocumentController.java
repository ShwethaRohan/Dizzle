package gui;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.Game;
import logic.exception.InvalidJSONFileException;

/**
 * FXML Controller class. Reaction to user inputs
 *
 * @author SHWETHA
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button dcRoll;
    /**
     * Stack pane corresponding to each cell of the human player. Our design
     * implements 7X9 board. To extend the design for boards with higher
     * dimensions, one stack pane element must be added for every additional
     * dimension/cell
     */
    @FXML
    private StackPane plyrOne00;
    @FXML
    private StackPane plyrOne10;
    @FXML
    private StackPane plyrOne20;
    @FXML
    private StackPane plyrOne30;
    @FXML
    private StackPane plyrOne40;
    @FXML
    private StackPane plyrOne50;
    @FXML
    private StackPane plyrOne60;
    @FXML
    private StackPane plyrOne70;
    @FXML
    private StackPane plyrOne80;
    @FXML
    private StackPane plyrOne01;
    @FXML
    private StackPane plyrOne11;
    @FXML
    private StackPane plyrOne21;
    @FXML
    private StackPane plyrOne31;
    @FXML
    private StackPane plyrOne41;
    @FXML
    private StackPane plyrOne51;
    @FXML
    private StackPane plyrOne61;
    @FXML
    private StackPane plyrOne71;
    @FXML
    private StackPane plyrOne81;
    @FXML
    private StackPane plyrOne02;
    @FXML
    private StackPane plyrOne12;
    @FXML
    private StackPane plyrOne22;
    @FXML
    private StackPane plyrOne32;
    @FXML
    private StackPane plyrOne42;
    @FXML
    private StackPane plyrOne52;
    @FXML
    private StackPane plyrOne62;
    @FXML
    private StackPane plyrOne72;
    @FXML
    private StackPane plyrOne82;
    @FXML
    private StackPane plyrOne03;
    @FXML
    private StackPane plyrOne13;
    @FXML
    private StackPane plyrOne23;
    @FXML
    private StackPane plyrOne33;
    @FXML
    private StackPane plyrOne43;
    @FXML
    private StackPane plyrOne53;
    @FXML
    private StackPane plyrOne63;
    @FXML
    private StackPane plyrOne73;
    @FXML
    private StackPane plyrOne83;
    @FXML
    private StackPane plyrOne04;
    @FXML
    private StackPane plyrOne14;
    @FXML
    private StackPane plyrOne24;
    @FXML
    private StackPane plyrOne34;
    @FXML
    private StackPane plyrOne44;
    @FXML
    private StackPane plyrOne54;
    @FXML
    private StackPane plyrOne64;
    @FXML
    private StackPane plyrOne74;
    @FXML
    private StackPane plyrOne84;
    @FXML
    private StackPane plyrOne05;
    @FXML
    private StackPane plyrOne15;
    @FXML
    private StackPane plyrOne25;
    @FXML
    private StackPane plyrOne35;
    @FXML
    private StackPane plyrOne45;
    @FXML
    private StackPane plyrOne55;
    @FXML
    private StackPane plyrOne65;
    @FXML
    private StackPane plyrOne75;
    @FXML
    private StackPane plyrOne85;
    @FXML
    private StackPane plyrOne06;
    @FXML
    private StackPane plyrOne16;
    @FXML
    private StackPane plyrOne26;
    @FXML
    private StackPane plyrOne36;
    @FXML
    private StackPane plyrOne46;
    @FXML
    private StackPane plyrOne56;
    @FXML
    private StackPane plyrOne66;
    @FXML
    private StackPane plyrOne76;
    @FXML
    private StackPane plyrOne86;
    @FXML
    private GridPane scndPlayerGrid;
    @FXML
    private GridPane thrdPlayerGrid;
    @FXML
    private GridPane frthPlayerGrid;
    @FXML
    private TextArea lgMoves;
    @FXML
    private BorderPane plyrOneBorder;
    @FXML
    private BorderPane plyrTwoBorder;
    @FXML
    private BorderPane plyrThreeBorder;
    @FXML
    private BorderPane plyrFourBorder;

    /* Dice on the table */
    @FXML
    private Pane dc0;
    @FXML
    private Pane dc1;
    @FXML
    private Pane dc2;
    @FXML
    private Pane dc3;
    @FXML
    private Pane dc4;
    @FXML
    private Pane dc6;
    @FXML
    private Pane dc5;
    @FXML
    private Pane dc7;
    @FXML
    private Pane dc8;
    @FXML
    private Pane dc9;
    @FXML
    private Pane dc10;
    @FXML
    private Pane dc11;
    @FXML
    private Pane dc12;
    @FXML
    private Label rndLabel;
    @FXML
    private Label trnLabel;

    /**
     * Grid panes to mark the start and end of special horizontal and vertical
     * lines on each players board
     */
    @FXML
    private GridPane hrzntlLines00;
    @FXML
    private GridPane vrtclLines00;
    @FXML
    private GridPane hrzntlLinesRight00;
    @FXML
    private GridPane hrzntlLines01;
    @FXML
    private GridPane vrtclLines01;
    @FXML
    private GridPane hrzntlLinesRight01;
    @FXML
    private GridPane hrzntlLines02;
    @FXML
    private GridPane vrtclLines02;
    @FXML
    private GridPane hrzntlLinesRight02;
    @FXML
    private GridPane hrzntlLines03;
    @FXML
    private GridPane vrtclLines03;
    @FXML
    private GridPane hrzntlLinesRight03;
    @FXML
    private GridPane plyrsGrid;
    @FXML
    private AnchorPane anchrPane;
    @FXML
    private MenuItem svGame;
    @FXML
    private MenuItem ldGame;
    @FXML
    private Label wrngCellWarning;
    @FXML
    private Button drpTurn;

    private JavaFXGUI gui;

    private GridPane[] horizontalLinesArray;

    private GridPane[] verticalLinesArray;

    private GridPane[] horizontalLinesRightArray;

    private BorderPane[] playerBorders;

    private boolean gameAborted;

    private StackPane[][] stackPaneArray;

    private Game game;

    private int numberPlayers;

    private int level;

    private Button strtGame;

    private Pane[] diceArray;

    /**
     * Constant representing the number 0
     */
    private final int ZERO = 0;
    /**
     * Constant representing the number 1
     */
    private final int ONE = 1;
    /**
     * Constant representing the number 1
     */
    private final int TWO = 2;

    /*
    array representing the possible levels in the game
     */
    private final int[] levelNumber = new int[]{1, 2, 3};

    /*
    array representing the possible number of players who can play the game
     */
    private final int[] playersCount = new int[]{2, 3, 4};

    /*
    Constant representing length of each coordinate of a cell on the board
     */
    private final int GRID_DIMENSION = 2;

    /**
     * Initialises the controller class.
     *
     * @param url URL
     * @param rb RB
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * Initialise the human player's grid. Our design implements 7X9 board.
         * Hence 63 stack pane elements are added to the human player board. To
         * implement boards with higher dimensions , additional stack pane
         * elements must be added and included in this array
         *
         */
        stackPaneArray = new StackPane[][]{
            {plyrOne00, plyrOne10, plyrOne20, plyrOne30, plyrOne40, plyrOne50, plyrOne60, plyrOne70, plyrOne80},
            {plyrOne01, plyrOne11, plyrOne21, plyrOne31, plyrOne41, plyrOne51, plyrOne61, plyrOne71, plyrOne81},
            {plyrOne02, plyrOne12, plyrOne22, plyrOne32, plyrOne42, plyrOne52, plyrOne62, plyrOne72, plyrOne82},
            {plyrOne03, plyrOne13, plyrOne23, plyrOne33, plyrOne43, plyrOne53, plyrOne63, plyrOne73, plyrOne83},
            {plyrOne04, plyrOne14, plyrOne24, plyrOne34, plyrOne44, plyrOne54, plyrOne64, plyrOne74, plyrOne84},
            {plyrOne05, plyrOne15, plyrOne25, plyrOne35, plyrOne45, plyrOne55, plyrOne65, plyrOne75, plyrOne85},
            {plyrOne06, plyrOne16, plyrOne26, plyrOne36, plyrOne46, plyrOne56, plyrOne66, plyrOne76, plyrOne86}
        };
        // Initializes the dice array
        diceArray = new Pane[]{
            dc0, dc1, dc2, dc3, dc4, dc5, dc6, dc7, dc8, dc9, dc10, dc11, dc12
        };

        horizontalLinesArray = new GridPane[]{
            hrzntlLines00, hrzntlLines01, hrzntlLines02, hrzntlLines03
        };

        verticalLinesArray = new GridPane[]{
            vrtclLines00, vrtclLines01, vrtclLines02, vrtclLines03
        };

        horizontalLinesRightArray = new GridPane[]{
            hrzntlLinesRight00, hrzntlLinesRight01, hrzntlLinesRight02, hrzntlLinesRight03
        };

        playerBorders = new BorderPane[]{
            plyrOneBorder, plyrTwoBorder, plyrThreeBorder, plyrFourBorder
        };
        // logging area must not be ediatble
        lgMoves.setEditable(false);
        loadNewGame(true);
        startNewGame();
    }

    /**
     * Handles the drop out operation of the game. By dropping out of the turn
     * the player remains inactive during the turn
     *
     * @param event the event which is triggered when the drop out button is
     * clicked
     *
     */
    @FXML
    private void hndlDropOut(ActionEvent event) {
        try {
            game.dropOutTurn();
        } catch (IOException ex) {
            gui.logFileErrors(ex);
        }
    }

    /**
     * Handles the roll operation of the game. This rolls the dice when it is a
     * players turn or player wishes to roll again
     *
     * @param event the event which is triggered when the roll button is clicked
     *
     */
    @FXML
    private void hndlRoll(ActionEvent event) {
        try {
            game.determineDiceValues();
            game.handleRoll();
        } catch (IOException ex) {
            gui.logFileErrors(ex);
        }

    }

    /**
     * Handles the save operation of the game. This saves the current ongoing
     * game in a JSON file format
     *
     * @param event the event which is triggered when the save button is clicked
     *
     */
    @FXML
    private void hndlGameSave(ActionEvent event) {
        try {
            File currDir = new File(FXMLDocumentController.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());
            FileChooser fileChooser = new FileChooser();
            //Ensure the dialog opens in the correct directory 
            fileChooser.setInitialDirectory(currDir.getParentFile());
            // Restrict the file format to JSON
            FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extension);
            File file = fileChooser.showSaveDialog(anchrPane.getScene().getWindow());
            game.saveGame(file);
        } catch (IOException | URISyntaxException ex) {
            gui.displayError(ex);
        }
    }

    /**
     * Handles the load operation of the game. This loads a previously saved
     * game
     *
     * @param event the event which is triggered when the load button is clicked
     *
     */
    @FXML
    private void hndlGameLoad(ActionEvent event) {

        try {
            File currDir = new File(FXMLDocumentController.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());

            FileChooser fileChooser = new FileChooser();

            //ensure the dialog opens in the correct directory 
            fileChooser.setInitialDirectory(currDir.getParentFile());
            File file = fileChooser.showOpenDialog(anchrPane.getScene().getWindow());
            if (file != null) {
                plyrsGrid.setDisable(false);
                game.loadGame(file, true);

            }
        } catch (URISyntaxException | InvalidJSONFileException | IOException ex) {
            gui.displayError(ex);
        }
    }

    /**
     * Handles the click on the human player grid.
     *
     * @param event the event which is triggered when there is a mouse click on
     * the human player grid
     *
     */
    @FXML
    private void hndlClick(MouseEvent event
    ) {
        StackPane click = (StackPane) event.getSource();
        try {
            game.playerTurn(getCoordinate(click));
        } catch (IOException ex) {
            gui.logFileErrors(ex);
        }
    }

    /**
     * Handles the operation of displaying the special symbols corresponding to
     * the specific level. This displays the special symbols present in the
     * current level along with the points
     *
     * @param event the event which is triggered when the show symbols button in
     * the help tab is clicked
     *
     */
    @FXML
    private void hndlShowFields(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("Special fields");
        VBox sFields = new VBox();
        gui.computeSpecialFields(sFields);
        sFields.setAlignment(Pos.CENTER);
        Scene scene = new Scene(sFields, 350, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Handles the load new game operation. This loads a new game
     *
     * @param event the event which is triggered when the new game button in the
     * file tab is clicked
     *
     */
    @FXML
    private void hndlNewGame(ActionEvent event) {
        gameAborted = false;
        this.plyrsGrid.setDisable(false);
        gui.clearLog();
        // user chooses to start a new game while a game is going on
        loadNewGame(false);
        if (!gameAborted) {
            try {
                /**
                 * validate if the new game can be started. If not return to the
                 * current ongoing game
                 */
                game.validateNewGameStart(level);
                startNewGame();
            } catch (InvalidJSONFileException ex) {
                gui.displayError(ex);
            }
        }
    }

    /**
     * Loads a new game pop-up. At the start of every new game, there is a pop
     * up window where the user must select the number of players and the level
     *
     * @param initialLoad true - no game ongoing and is a fresh load of the
     * game. False - There is an ongoing game and user opts to open a new game
     * scenario
     * @throws IOException Any input/output operation errors
     */
    private void loadNewGame(boolean initialLoad) {
        Stage stage = new Stage();
        stage.setTitle("Dizzle");
        stage.initModality(Modality.APPLICATION_MODAL);

        ChoiceBox<Integer> plyrsCB = new ChoiceBox<>();
        ChoiceBox<Integer> lvlCB = new ChoiceBox<>();

        Label plyrsLabel = new Label("Number of Players");
        Label lvlLabel = new Label("Level");

        plyrsCB.getItems().addAll(playersCount[ZERO], playersCount[ONE], playersCount[TWO]);
        lvlCB.getItems().addAll(levelNumber[ZERO], levelNumber[ONE], levelNumber[TWO]);

        HBox plyrs = new HBox(plyrsLabel, plyrsCB);
        plyrs.setSpacing(35);
        HBox lvls = new HBox(lvlLabel, lvlCB);
        lvls.setSpacing(105);

        strtGame = new Button("Start");
        plyrsCB.setValue(2);
        lvlCB.setValue(1);
        /* 
        Actions taken when start button is clicked
         */
        strtGame.setOnAction((ActionEvent event) -> {
            if (plyrsCB.getValue() == null) {
                // default value for number of players is 2
                plyrsCB.getSelectionModel().selectFirst();

            }
            if (lvlCB.getValue() == null) {
                // default value for level is 1
                lvlCB.getSelectionModel().selectFirst();
            }
            numberPlayers = plyrsCB.getValue();
            level = lvlCB.getValue();
            stage.close();

        });

        stage.setOnCloseRequest((WindowEvent event) -> {
            /*
            If there is a game ongoing and user wishes to start a new game by 
             clicking new game option. The user then changes mind to continue with 
             the current game by closing the new game pop up. In this scenario the 
            user is redirected to the old ongoing game         
             */
            if (!initialLoad) {
                gameAborted = true;
                stage.close();
            } /*
            User closes the pop up window
             */ else {
                numberPlayers = ZERO;
                level = ZERO;
            }
        });

        VBox initialWindow = new VBox(plyrs, lvls, strtGame);
        VBox.setMargin(plyrs, new Insets(30, 0, 0, 50));
        VBox.setMargin(lvls, new Insets(30, 0, 0, 50));
        VBox.setMargin(strtGame, new Insets(30, 50, 0, 50));

        Scene scene = new Scene(initialWindow, 300, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Clears the previous entries on the player grids and starts a fresh game
     */
    private void startNewGame() {
        try {
            if (numberPlayers != ZERO && level != ZERO) {
                this.gui = new JavaFXGUI(numberPlayers, stackPaneArray,
                        scndPlayerGrid, thrdPlayerGrid, frthPlayerGrid, diceArray, rndLabel,
                        trnLabel, dcRoll, drpTurn, verticalLinesArray, horizontalLinesArray,
                        horizontalLinesRightArray, playerBorders, lgMoves, plyrsGrid, svGame, ldGame, wrngCellWarning);
                game = new Game(level, numberPlayers, gui, true);
                /*
                default colour of all player boards
                 */
                plyrOneBorder.setStyle("-fx-background-color: red");
                plyrTwoBorder.setStyle("-fx-background-color: red");
                plyrThreeBorder.setStyle("-fx-background-color: red");
                plyrFourBorder.setStyle("-fx-background-color: red");
            } else {
                System.exit(0);
            }
        } catch (InvalidJSONFileException ex) {
            gui.displayError(ex);
            returnToMainWindow();
        } catch (IOException | URISyntaxException ex) {
            gui.logFileErrors(ex);
            returnToMainWindow();
        }

    }

    /**
     * Returns the control back to the main window where a new game is started
     */
    private void returnToMainWindow() {
        loadNewGame(true);
        startNewGame();
    }

    /**
     * Determines the clicked coordinates of the human player grid
     *
     * @param clickedButton the stackPane which was clicked
     * @return the coordinates of the human player grid
     */
    private int[] getCoordinate(StackPane clickedButton) {
        int[] buttonCoord = new int[GRID_DIMENSION];
        boolean found = false;

        for (int i = ZERO; !found && (i < stackPaneArray.length); i++) {
            for (int j = ZERO; !found && (j < stackPaneArray[ZERO].length); j++) {
                if (stackPaneArray[i][j] == clickedButton) {
                    found = true;
                    buttonCoord[ZERO] = i;
                    buttonCoord[ONE] = j;
                }
            }
        }
        return buttonCoord;
    }
}
