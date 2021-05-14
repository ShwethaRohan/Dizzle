package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

/**
 * Logs every move of the players into a log file. This is also displayed in the
 * GUI for reference
 *
 * @author SHWETHA
 */
public class LogData {

    private final FileWriter fileWriter;

    protected String latestData;

    /**
     * Constructor to create a log file. A log file is created at the beginning
     * of every new game.
     *
     * @param level level of the game
     * @param numberPlayers number of players playing the game
     * @throws IOException Any errors that occur whenever input/output operation
     * is failed
     * @throws URISyntaxException Any errors that occur when parsing a string as
     * a URI reference
     */
    protected LogData(int level, int numberPlayers) throws IOException, URISyntaxException {
        String fileSeparator = System.getProperty("file.separator");
        String logFilePath = getParentPath() + fileSeparator + "gameLog.log";
        File f = new File(logFilePath);
        f.delete();
        fileWriter = new FileWriter(new File(logFilePath));
        try {
            String log = initialiseLogData(level, numberPlayers);
            fileWriter.write(log);
            latestData = log;
            fileWriter.flush();
        } finally {

        }
    }

    /**
     * Retrieves the location at which the jar file is present
     *
     * @return location of the jar file
     * @throws UnsupportedEncodingException This exception is thrown when the
     * character encoding is not supported
     */
    private String getParentPath() throws UnsupportedEncodingException {
        URL url = LogData.class.getProtectionDomain().getCodeSource().getLocation();
        String jarFile = URLDecoder.decode(url.getFile(), "UTF-8");
        return new File(jarFile).getParent();
    }

    /**
     * Initialises the log file with the level number and number of players
     * playing the game. Every log file contains the corresponding level number
     * and number of players.
     *
     * @param level level of the game
     * @param numberPlayers number of players playing the game
     * @return
     */
    private String initialiseLogData(int level, int numberPlayers) {
        return "level :" + String.valueOf(level) + ", players :"
                + String.valueOf(numberPlayers);
    }

    /**
     * Formulates a fixed phrase corresponding to the move of the player and
     * appends it to the log file
     *
     * @param playerName name of the player who carried out the move
     * @param pos cell coordinates where the dice was placed
     * @param jump true - player dropped out of the turn, else false
     * @param rollAgain true - player has rolled the dice for the second time,
     * else false
     * @param newTurn true - new turn begins, else false
     * @param dices list of dices on the table
     * @param flagPoints points gained if a player reached a flag
     * @param cell cell where the dice was placed
     * @param newRound true - new round begins, else false
     * @param winnerName name of the winner if there is any
     * @throws IOException Any errors that occur whenever input/output operation
     * is failed
     */
    protected void logMoves(String playerName, int[] pos,
            boolean jump, boolean rollAgain, boolean newTurn, List<Integer> dices,
            int flagPoints, Cell cell, boolean newRound, List<String> winnerName) throws IOException {
        String data = "";
        // human player rolls the dice for second time
        if (rollAgain) {
            data = playerName + " rolls again, new dice " + Arrays.toString(dices.toArray());
        } // new turn begins
        else if (newTurn) {
            data = playerName + " starts a new turn and rolls the dice :" + Arrays.toString(dices.toArray());
        } // player drops out
        else if (jump) {
            data = playerName + " dropping out";
        } // new round begins
        else if (newRound) {
            data = "New round begins";
        } // game ends
        else if (winnerName != null && !winnerName.isEmpty()) {
            data = "Game over. Winner(s) : ";
            for (int i = 0; i < winnerName.size(); i++) {
                data = data + winnerName.get(i) + " ";
            }
        } // player places a dice on the board
        else if (pos != null && cell != null) {
            data = playerName + " places dice " + String.valueOf(cell.getfieldValue()) + " at ["
                    + String.valueOf(pos[0]) + "," + String.valueOf(pos[1]) + "]";
            // cell has a special field. Log it
            if (cell.hasSpecialField()) {
                String specialData = addSpecialFieldData(cell.getSpecialField(), flagPoints, cell, pos);
                data = data + "-------" + specialData;
            }
        }
        try {
            if (!data.isEmpty()) {
                fileWriter.write(System.getProperty("line.separator"));
                fileWriter.write(data);
                latestData = data;
                fileWriter.flush();
            }
        } finally {
            if (winnerName != null) {
                fileWriter.close();
            }
        }
    }

    /**
     * Formulates the phrase when human player returns a die from his/her board
     * and appends it to the log file
     *
     * @param pos position from which the die is returned
     * @param dices list of dices on the list
     * @param fieldValue integer field value of the corresponding cell
     * @throws IOException Any errors that occur whenever input/output operation
     * is failed
     */
    protected void humanPlayerDiceReturnLog(int[] pos, List<Integer> dices, int fieldValue) throws IOException {
        fileWriter.write(System.getProperty("line.separator"));
        String dataOne = "Human Player returns dice " + fieldValue + " placed at ["
                + String.valueOf(pos[0]) + "," + String.valueOf(pos[1]) + "]";
        fileWriter.write(dataOne);
        fileWriter.flush();
        fileWriter.write(System.getProperty("line.separator"));
        String dataTwo = "Dices : " + Arrays.toString(dices.toArray());
        fileWriter.write(dataTwo);
        latestData = dataOne + "\n" + dataTwo;
        fileWriter.flush();
    }

    /**
     * Formulates the phrase corresponding to the special field collected by a
     * player from the given cell.
     *
     * @param specialField special field collected
     * @param flagPoints flag points gained
     * @param cell Cell on which the die was placed
     * @param pos coordinates of the given cell
     * @return phrase to be appended in the log file
     */
    private String addSpecialFieldData(SpecialFields specialField, int flagPoints, Cell cell, int[] pos) {
        String data = "";
        switch (specialField) {
            case PUZZLE:
                data = "puzzle collected";
                break;
            case JEWEL:
                data = "jewel collected";
                break;
            case BOMB:
                data = "bomb ignited";
                break;
            case KEY:
                if (((Key) cell).isKey(pos[1], pos[0])) {
                    data = "key collected";
                } else {
                    data = "lock opened";
                }
                break;
            case ROCKET:
                data = "rocket launched";
                break;
            case FLAG:
                data = "reached flag with " + flagPoints + " points";
        }
        return data;
    }

    /**
     * Retrieves the latest line from the log file
     *
     * @return line from the log file
     */
    protected String getLatestLog() {
        return latestData;
    }
}
