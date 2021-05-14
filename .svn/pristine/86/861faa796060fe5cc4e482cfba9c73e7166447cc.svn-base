package logic;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javafx.scene.layout.VBox;

/**
 * Possible events that occur in the logic and need to be reflected in the UI.
 *
 * @author SHWETHA
 */
public interface GUIConnector {

    public void loadDices(List<Integer> diceArray);

    public void higlightField(int x, int y);

    public void resetFields();

    public void updateRound(int roundNumber);

    public void updateTurn(int turnNumber);

    public void enableRoll(boolean visible);

    public void placeDiceOnBoard(int value, int[] pos, int playerIndex);

    public void replaceDicesWithCross(int xPos, int yPos, int playerIndex);

    public void enableDropOut(boolean visible);

    public void loadHorizontalLines(HashMap<Integer, List<Integer[]>> horizontalLines);

    public void computeSpecialFields(VBox specialFields);

    public void loadFields(int xPos, int yPos, int value, boolean initialize);

    public void loadJewel(int xPos, int yPos, int points, boolean initialize);

    public void loadBomb(int xPos, int yPos, int points, boolean initialize);

    public void loadFlag(int xPos, int yPos, List<Integer> points, boolean initialize);

    public void loadKey(int xPos, int yPos, int count, boolean initialize);

    public void loadKeyHole(int xPos, int yPos, int count, boolean initialize);

    public void loadPuzzle(int xPos, int yPos, int count, int points, boolean initialize);

    public void loadRocket(int xPos, int yPos, boolean initialize);

    public void loadPlanet(int xPos, int yPos, boolean initialize);

    public void loadVerticalLines(HashMap<Integer, List<Integer[]>> verticalLines);

    public void gameEnd(LinkedHashMap<String, PlayerScore> playerStats,
            List<String> winners);

    public void droppedOutPlayerUpdate(int playerIndex);

    public void resetJump();

    public void explodeBomb(int playerIndex, int xPos, int yPos);

    public void resetHumanPlayerCell(int xPos, int yPos);

    public void logMoves(String data);

    public void displayError(Exception exception);

    public void logFileErrors(Exception ex);

    public void disableSaveOption(boolean status);

    public void disableLoadOption(boolean status);

    public void setPlayerBoards(int numberPlayers);

    public void showWarning(boolean status);

    public void resetBoards();

    public void clearLog();

}
