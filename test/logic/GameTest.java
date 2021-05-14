package logic;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import logic.exception.InvalidJSONFileException;
import logic.exception.InvalidPlayerRecords;
import logic.exception.NullObjectException;
import logic.exception.OutOfRangeException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SHWETHA
 */
public class GameTest {

    /**
     * Test cases to check if the correct number of dices are determined for the
     * game
     */
    @Test
    public void determineDice_TwoPlayers() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(1, 2, new FakeGUI(), false);
        assertEquals(7, g.getDiceNumber(g.getNoPlyrs()));
    }

    @Test
    public void determineDice_ThreePlayers() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(1, 3, new FakeGUI(), false);
        assertEquals(10, g.getDiceNumber(g.getNoPlyrs()));
    }

    @Test
    public void determineDice_FourPlayers() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(2, 4, new FakeGUI(), false);
        assertEquals(13, g.getDiceNumber(g.getNoPlyrs()));
    }

    /**
     * Test cases to check if the correct rounds are determined for the game
     */
    @Test
    public void determineRounds_TwoPlayers() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(1, 2, new FakeGUI(), false);
        assertEquals(6, g.getRounds(2));
    }

    @Test
    public void determineRounds_ThreePlayers() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(1, 3, new FakeGUI(), false);
        assertEquals(4, g.getRounds(3));
    }

    @Test
    public void determineRounds_FourPlayers() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(2, 4, new FakeGUI(), false);
        assertEquals(3, g.getRounds(4));
    }

    /**
     * Test case to check if the correct player index is computed
     */
    @Test
    public void compute_nextPlayer_Index_1() throws InvalidJSONFileException, IOException, URISyntaxException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(6);
        dices.add(5);
        dices.add(2);
        Game g = new Game(3, 4, new FakeGUI(), dices);
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(2, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(3, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getPlayerIdx());
    }

    @Test
    public void compute_nextPlayer_Index_2() throws InvalidJSONFileException, IOException, URISyntaxException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(6);
        dices.add(5);
        dices.add(2);
        Game g = new Game(3, 3, new FakeGUI(), dices);
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(2, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(2, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getPlayerIdx());
    }

    /**
     * Test case to check if the dice is removed after being placed
     */
    @Test
    public void dice_removal_1() throws InvalidJSONFileException, IOException, URISyntaxException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(6);
        dices.add(5);
        dices.add(2);
        Game g = new Game(3, 4, new FakeGUI(), dices);
        assertEquals(6, g.getArrayOfDices().size());
        assertTrue(Objects.equals(2, g.getArrayOfDices().get(0)));
        assertTrue(Objects.equals(2, g.getArrayOfDices().get(5)));
        g.handleDicePlacement(2);
        assertEquals(5, g.getArrayOfDices().size());
        assertFalse(Objects.equals(2, g.getArrayOfDices().get(0)));
        assertTrue(Objects.equals(2, g.getArrayOfDices().get(4)));
        g.handleDicePlacement(4);
        assertEquals(4, g.getArrayOfDices().size());
        assertTrue(Objects.equals(6, g.getArrayOfDices().get(1)));
    }

    @Test
    public void dice_removal_2() throws InvalidJSONFileException, IOException, URISyntaxException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(6);
        Game g = new Game(3, 4, new FakeGUI(), dices);
        assertEquals(4, g.getArrayOfDices().size());
        assertTrue(Objects.equals(2, g.getArrayOfDices().get(0)));
        assertTrue(Objects.equals(3, g.getArrayOfDices().get(1)));
        assertTrue(Objects.equals(4, g.getArrayOfDices().get(2)));
        assertTrue(Objects.equals(6, g.getArrayOfDices().get(3)));
        g.handleDicePlacement(2);
        assertEquals(3, g.getArrayOfDices().size());
        assertTrue(Objects.equals(3, g.getArrayOfDices().get(0)));
        assertTrue(Objects.equals(4, g.getArrayOfDices().get(1)));
        assertTrue(Objects.equals(6, g.getArrayOfDices().get(2)));
        g.handleDicePlacement(6);
        assertEquals(2, g.getArrayOfDices().size());
        assertTrue(Objects.equals(3, g.getArrayOfDices().get(0)));
        assertTrue(Objects.equals(4, g.getArrayOfDices().get(1)));
        g.handleDicePlacement(3);
        assertEquals(1, g.getArrayOfDices().size());
        assertTrue(Objects.equals(4, g.getArrayOfDices().get(0)));
        g.handleDicePlacement(4);
        assertTrue(g.getArrayOfDices().isEmpty());
    }

    @Test(expected = NullObjectException.class)
    public void checkLoadedPosition_checked() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        List<Grids> checkedCells = new ArrayList<>();
        checkedCells.add(new Grids(7, 3));

        Players player1 = new Players(true, checkedCells, new ArrayList<>(), new ArrayList<>(), 0);
        Players player2 = new Players(true, checkedCells, new ArrayList<>(), new ArrayList<>(), 0);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        plyrs.add(player2);
        g.validateLoadedFileData(new Status(3, 1, 2, new ArrayList<>(), plyrs));
    }

    @Test(expected = InvalidPlayerRecords.class)
    public void checkLoadedPosition_dice() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        List<Grids> dice = new ArrayList<>();
        dice.add(new Grids(3, 9));
        List<Integer> diceOnTable = new ArrayList<>();
        diceOnTable.add(1);

        Players player1 = new Players(true, new ArrayList<>(), dice, new ArrayList<>(), 0);
        Players player2 = new Players(true, new ArrayList<>(), dice, new ArrayList<>(), 0);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        plyrs.add(player2);
        g.validateLoadedFileData(new Status(3, 1, 2, new ArrayList<>(), plyrs));
    }

    @Test(expected = NullObjectException.class)
    public void checkLoadedPosition_exploded() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        List<Grids> bomb = new ArrayList<>();
        bomb.add(new Grids(-1, 0));

        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), bomb, 0);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), bomb, 0);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        plyrs.add(player2);
        g.validateLoadedFileData(new Status(3, 1, 2, new ArrayList<>(), plyrs));
    }

    @Test(expected = OutOfRangeException.class)
    public void checkLoadedPosition_invalid_level() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);

        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        plyrs.add(player2);
        g.validateLoadedFileData(new Status(4, 1, 2, new ArrayList<>(), plyrs));
    }

    @Test(expected = OutOfRangeException.class)
    public void checkLoadedPosition_invalid_players() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);

        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        g.validateLoadedFileData(new Status(3, 1, 2, new ArrayList<>(), plyrs));
    }

    @Test(expected = OutOfRangeException.class)
    public void checkLoadedPosition_invalid_turn_number() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);

        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        Players player3 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        plyrs.add(player2);
        plyrs.add(player3);
        g.validateLoadedFileData(new Status(3, 1, 4, new ArrayList<>(), plyrs));
    }

    @Test(expected = OutOfRangeException.class)
    public void checkLoadedPosition_invalid_round_number() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);

        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        Players player3 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        plyrs.add(player2);
        plyrs.add(player3);
        g.validateLoadedFileData(new Status(3, 5, 3, new ArrayList<>(), plyrs));
    }

    @Test(expected = OutOfRangeException.class)
    public void checkLoadedPosition_invalid_dice_number() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        List<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(1);

        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        plyrs.add(player2);
        g.validateLoadedFileData(new Status(3, 1, 2, dices, plyrs));
    }

    @Test(expected = OutOfRangeException.class)
    public void checkLoadedPosition_invalid_dice_value() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        List<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(8);
        dices.add(1);
        dices.add(1);

        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        plyrs.add(player2);
        g.validateLoadedFileData(new Status(3, 1, 2, dices, plyrs));
    }

    @Test(expected = OutOfRangeException.class)
    public void checkLoadedPosition_invalid_flag_value() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        List<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(3);
        dices.add(1);
        dices.add(1);

        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 10);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 6);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        plyrs.add(player2);
        g.validateLoadedFileData(new Status(3, 1, 2, dices, plyrs));
    }

    @Test(expected = InvalidPlayerRecords.class)
    public void checkLoadedPosition_valid_flag_value_invalid() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        List<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(3);
        dices.add(1);
        dices.add(1);

        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        plyrs.add(player2);
        g.validateLoadedFileData(new Status(3, 1, 2, dices, plyrs));
    }
    
     @Test
    public void checkLoadedPosition_valid_flag_value() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        List<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(1);
        dices.add(3);
        dices.add(1);
        dices.add(1);

        List<Grids> checked = new ArrayList<>();
        checked.add(new Grids(0,8));
        Players player1 = new Players(true, checked, new ArrayList<>(), new ArrayList<>(), 1);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        List<Players> plyrs = new ArrayList<>();
        plyrs.add(player1);
        plyrs.add(player2);
        g.validateLoadedFileData(new Status(3, 1, 2, dices, plyrs));
    }

    @Test(expected = OutOfRangeException.class)
    public void checkLoadedPosition_invalid_flag_value_missing() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 4);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1);
        Players player3 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 3);
        Players player4 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1);
        List<Players> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        g.validateLoadedFileData(new Status(3, 1, 2, new ArrayList<>(), players));
    }

    @Test
    public void test_winnerName() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        int[] scores = new int[]{120, 123, 156, 176};
        List<String> winner = g.getWinnerName(scores);
        assertEquals(1, winner.size());
        assertEquals("AI Player 3", winner.get(0));
    }

    @Test
    public void test_compare_checked_cells() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        int[] scores = new int[]{15, 13, 12, 15};
        //List<String> winner = g.getWinnerName(scores);
        assertEquals(1, g.compareCheckedCells(scores).size());
        Integer result = 2;
        assertEquals(result, g.compareCheckedCells(scores).get(0));
    }

    @Test
    public void test_constructor() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        assertEquals(3, g.getLevel());
        assertEquals(4, g.getNoPlyrs());
        assertEquals(1, g.getRoundNumber());
        assertEquals(1, g.getTurnNumber());
        assertEquals(true, g.getPlyrStatus(0));
        assertEquals(true, g.getPlyrStatus(1));
        assertEquals(true, g.getPlyrStatus(2));
        assertTrue(g.getArrayOfDices().isEmpty());
    }

    @Test
    public void test_flags() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(1, 2, new FakeGUI(), false);
        assertTrue(g.getPlayer(0).getFlagPointList().isEmpty());
        assertTrue(g.getPlayer(1).getFlagPointList().isEmpty());
        g = new Game(2, 2, new FakeGUI(), false);
        assertTrue(g.getPlayer(0).getFlagPointList().isEmpty());
        assertTrue(g.getPlayer(1).getFlagPointList().isEmpty());
        g = new Game(3, 2, new FakeGUI(), false);
        assertFalse(g.getPlayer(0).getFlagPointList().isEmpty());
        assertFalse(g.getPlayer(1).getFlagPointList().isEmpty());
    }

    @Test
    public void test_loadFile_2Players() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        assertEquals(true, g.getPlyrStatus(0));
        assertEquals(true, g.getPlyrStatus(1));
        assertEquals(true, g.getPlyrStatus(2));
        assertEquals(true, g.getPlyrStatus(3));
        ArrayList<Integer> dices = new ArrayList<>();

        List<Grids> checkedCells = new ArrayList<>();
        checkedCells.add(new Grids(2, 3));
        List<Grids> dice = new ArrayList<>();
        dice.add(new Grids(3, 2));
        List<Grids> bomb = new ArrayList<>();
        bomb.add(new Grids(1, 0));
        Players player1 = new Players(false, checkedCells, dice, bomb, 0);

        checkedCells = new ArrayList<>();
        checkedCells.add(new Grids(3, 3));
        dice = new ArrayList<>();
        dice.add(new Grids(4, 2));
        bomb = new ArrayList<>();
        bomb.add(new Grids(0, 3));
        Players player2 = new Players(true, checkedCells, dice, bomb, 0);

        List<Players> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        Status status = new Status(2, 3, 0, dices, players);

        g.loadGameFromFile(status, false);
        assertEquals(2, g.getLevel());
        assertEquals(2, g.getNoPlyrs());
        assertFalse(g.getPlyrStatus(0));
        assertTrue(g.getPlyrStatus(1));

        assertEquals(1, g.getPlayer(0).getCrossedPositions().size());
        assertEquals(1, g.getPlayer(0).getDicePlacedPositions().size());
        assertEquals(1, g.getPlayer(0).getExplodedBombsList().size());
        assertEquals(new Grids(2, 3), g.getPlayer(0).getCrossedPositions().get(0));
        assertEquals(new Grids(3, 2), g.getPlayer(0).getDicePlacedPositions().get(0));
        assertEquals(new Grids(1, 0), g.getPlayer(0).getExplodedBombsList().get(0));

        assertEquals(1, g.getPlayer(1).getCrossedPositions().size());
        assertEquals(1, g.getPlayer(1).getDicePlacedPositions().size());
        assertEquals(1, g.getPlayer(1).getExplodedBombsList().size());
        assertEquals(new Grids(3, 3), g.getPlayer(1).getCrossedPositions().get(0));
        assertEquals(new Grids(4, 2), g.getPlayer(1).getDicePlacedPositions().get(0));
        assertEquals(new Grids(0, 3), g.getPlayer(1).getExplodedBombsList().get(0));
    }

    @Test
    public void test_loadFile_wrongLevel_withFlags() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 10);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 6);
        List<Players> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Status status = new Status(2, 3, 0, new ArrayList<>(), players);
        g.loadGameFromFile(status, false);
        assertEquals(0, g.getPlayer(0).getFlagPoint());
        assertEquals(0, g.getPlayer(1).getFlagPoint());

    }

    @Test
    public void test_loadFile_rightLevel_withFlags() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        Players player3 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1);
        List<Players> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Status status = new Status(3, 3, 0, new ArrayList<>(), players);
        g.loadGameFromFile(status, false);
        assertEquals(10, g.getPlayer(0).getFlagPoint());
        assertEquals(0, g.getPlayer(1).getFlagPoint());
        assertEquals(10, g.getPlayer(2).getFlagPoint());
        Integer value = 3;
        assertEquals(value, g.getPlayer(1).getFlagPointList().get(0));
    }

    @Test
    public void test_loadFile_Flags() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 4);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 2);
        Players player3 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 3);
        Players player4 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1);
        List<Players> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        Status status = new Status(3, 3, 0, new ArrayList<>(), players);
        g.loadGameFromFile(status, false);
        assertEquals(1, g.getPlayer(0).getFlagPoint());
        assertEquals(6, g.getPlayer(1).getFlagPoint());
        assertEquals(3, g.getPlayer(2).getFlagPoint());
        assertEquals(10, g.getPlayer(3).getFlagPoint());

        assertEquals(0, g.getPlayer(0).getFlagPointList().size());
        assertEquals(0, g.getPlayer(1).getFlagPointList().size());
        assertEquals(0, g.getPlayer(2).getFlagPointList().size());
        assertEquals(0, g.getPlayer(3).getFlagPointList().size());
    }

    @Test
    public void test_loadFile_Flags_two() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        Players player1 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1);
        Players player2 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 2);
        Players player3 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
        Players player4 = new Players(true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 1);
        List<Players> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        Status status = new Status(3, 3, 0, new ArrayList<>(), players);
        g.loadGameFromFile(status, false);
        assertEquals(10, g.getPlayer(0).getFlagPoint());
        assertEquals(3, g.getPlayer(1).getFlagPoint());
        assertEquals(0, g.getPlayer(2).getFlagPoint());
        assertEquals(10, g.getPlayer(3).getFlagPoint());

        assertEquals(1, g.getPlayer(0).getFlagPointList().size());
        assertEquals(1, g.getPlayer(1).getFlagPointList().size());
        assertEquals(1, g.getPlayer(2).getFlagPointList().size());
        assertEquals(1, g.getPlayer(3).getFlagPointList().size());
    }

    @Test
    public void players_field_empty() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        assertFalse(g.playersFieldEmpty());
        assertFalse(g.checkGameEnd());
        g.getPlayer(0).exhaustFields();
        assertTrue(g.playersFieldEmpty());
        assertTrue(g.checkGameEnd());
    }

    @Test
    public void test_turn_and_round_number() throws InvalidJSONFileException, IOException, URISyntaxException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(6);
        Game g = new Game(3, 3, new FakeGUI(), dices);
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(2, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getTurnNumber());
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(2, g.getTurnNumber());
        assertEquals(1, g.getRoundNumber());

        dices = new ArrayList<>();
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(6);
        g.addDices(dices);
        assertEquals(1, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(2, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(2, g.getTurnNumber());
        assertEquals(1, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(3, g.getTurnNumber());
        assertEquals(1, g.getRoundNumber());

        dices = new ArrayList<>();
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(6);
        g.addDices(dices);
        assertEquals(2, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(3, g.getTurnNumber());
        assertEquals(2, g.getPlayerIdx());
        g.removeDice(0);
        g.handleMove();
        assertEquals(1, g.getTurnNumber());
        assertEquals(2, g.getRoundNumber());
    }

    @Test
    public void test_last_active_player() throws InvalidJSONFileException, IOException, URISyntaxException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(6);
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(6);
        Game g = new Game(3, 4, new FakeGUI(), dices);
        assertTrue(g.getPlyrStatus(0));
        assertTrue(g.getPlyrStatus(1));
        assertTrue(g.getPlyrStatus(2));
        assertTrue(g.getPlyrStatus(3));

        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(2, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getTurnNumber());
        assertEquals(3, g.getPlayerIdx());
        g.setStatus(0, false);
        g.setStatus(1, false);
        g.setStatus(2, false);
        g.determinePlayerIndex();
        assertEquals(2, g.getTurnNumber());
        assertEquals(1, g.getPlayerIdx());
    }

    @Test
    public void test_players_field_exhausted() throws InvalidJSONFileException, IOException, URISyntaxException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(6);
        Game g = new Game(3, 3, new FakeGUI(), dices);
        assertEquals(1, g.getRoundNumber());
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(2, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getTurnNumber());
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        assertFalse(g.checkGameEnd());
        g.getPlayer(0).exhaustFields();
        g.handleMove();
        assertTrue(g.checkGameEnd());
        assertEquals(1, g.getRoundNumber());
    }

    @Test
    public void test_players_field_exhausted_inbetween() throws InvalidJSONFileException, IOException, URISyntaxException {
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(2);
        dices.add(3);
        dices.add(4);
        dices.add(6);
        Game g = new Game(3, 3, new FakeGUI(), dices);
        assertEquals(1, g.getRoundNumber());
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(1, g.getPlayerIdx());
        g.removeDice(0);
        g.determinePlayerIndex();
        assertEquals(2, g.getPlayerIdx());
        g.removeDice(0);
        g.getPlayer(0).exhaustFields();
        g.determinePlayerIndex();
        assertEquals(1, g.getTurnNumber());
        assertEquals(0, g.getPlayerIdx());
        g.removeDice(0);
        g.handleMove();
        assertTrue(g.checkGameEnd());
        assertEquals(1, g.getRoundNumber());
        assertEquals(2, g.getTurnNumber());
    }

    @Test
    public void test_tie_breaker() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 3, new FakeGUI(), false);
        List<Integer> playerIndices = new ArrayList<>();
        playerIndices.add(0);
        playerIndices.add(2);
        List<Integer> winners = g.tieBreaker(playerIndices);
        assertEquals(2, winners.size());
        assertTrue(winners.contains(0));
        assertTrue(winners.contains(2));
    }

    @Test
    public void test_winnername_two() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        int[] score = new int[]{12, 15, 12, 15};
        List<String> winners = g.getWinnerName(score);
        assertEquals(2, winners.size());
        assertTrue(winners.contains("AI Player 1"));
        assertTrue(winners.contains("AI Player 3"));
    }

    @Test
    public void test_winnername_two_unequal_crossed_cells() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        int[] score = new int[]{12, 15, 12, 15};
        g.getPlayer(1).addCrossPositions(0, 0);
        g.getPlayer(1).addCrossPositions(0, 1);
        List<String> winners = g.getWinnerName(score);
        assertEquals(1, winners.size());
        assertTrue(winners.contains("AI Player 3"));
    }

    @Test
    public void test_winnername_two_equal_crossed_cell() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        int[] score = new int[]{12, 15, 12, 15};
        g.getPlayer(1).addCrossPositions(0, 0);
        g.getPlayer(1).addCrossPositions(0, 1);
        g.getPlayer(1).addCrossPositions(0, 2);
        g.getPlayer(3).addCrossPositions(1, 0);
        g.getPlayer(3).addCrossPositions(2, 0);
        g.getPlayer(3).addCrossPositions(3, 0);
        List<String> winners = g.getWinnerName(score);
        assertEquals(2, winners.size());
        assertTrue(winners.contains("AI Player 1"));
        assertTrue(winners.contains("AI Player 3"));
    }

    @Test
    public void test_winnername() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        int[] score = new int[]{12, 15, 35, 25};
        g.getPlayer(1).addCrossPositions(0, 0);
        g.getPlayer(1).addCrossPositions(0, 1);
        g.getPlayer(1).addCrossPositions(0, 2);
        g.getPlayer(3).addCrossPositions(1, 0);
        g.getPlayer(3).addCrossPositions(2, 0);
        g.getPlayer(3).addCrossPositions(3, 0);
        List<String> winners = g.getWinnerName(score);
        assertEquals(1, winners.size());
        assertTrue(winners.contains("AI Player 2"));
    }

    @Test
    public void test_winnername_equal_points_unequal_crossed_positions() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        int[] score = new int[]{12, 35, 35, 25};
        g.getPlayer(1).addCrossPositions(0, 0);
        g.getPlayer(1).addCrossPositions(0, 1);
        g.getPlayer(1).addCrossPositions(0, 2);
        g.getPlayer(2).addCrossPositions(1, 0);
        g.getPlayer(2).addCrossPositions(2, 0);
        g.getPlayer(2).addCrossPositions(3, 0);
        g.getPlayer(2).addCrossPositions(1, 1);
        List<String> winners = g.getWinnerName(score);
        assertEquals(1, winners.size());
        assertTrue(winners.contains("AI Player 1"));
    }

    @Test
    public void test_winnername_equal_points_equal_crossed_positions() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        int[] score = new int[]{12, 35, 35, 25};
        g.getPlayer(1).addCrossPositions(0, 0);
        g.getPlayer(1).addCrossPositions(0, 1);
        g.getPlayer(1).addCrossPositions(0, 2);
        g.getPlayer(2).addCrossPositions(1, 0);
        g.getPlayer(2).addCrossPositions(2, 0);
        g.getPlayer(2).addCrossPositions(3, 0);
        List<String> winners = g.getWinnerName(score);
        assertEquals(2, winners.size());
        assertTrue(winners.contains("AI Player 2"));
        assertTrue(winners.contains("AI Player 1"));
    }

    @Test
    public void test_key_lock() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        ArrayList<Integer> dices = new ArrayList<>();
        dices.add(1);
        dices.add(4);
        dices.add(2);
        g.getPlayer(1).addCrossPositions(6, 4);
        g.getPlayer(1).addCrossPositions(1, 3);
        assertTrue(g.getPlayer(1).getCrossedNeighbours(true, dices).contains(new Grids(1, 4)));

        g.getPlayer(3).addCrossPositions(6, 4);
        g.getPlayer(3).addCrossPositions(1, 3);
        assertTrue(g.getPlayer(3).getCrossedNeighbours(true, dices).contains(new Grids(1, 4)));
    }

    @Test
    public void no_dice_to_return() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        assertTrue(g.getPlyrStatus(0));
        assertTrue(g.getPlyrStatus(1));
        assertTrue(g.getPlyrStatus(2));
        assertTrue(g.getPlyrStatus(3));
        List<Integer> dices = new ArrayList<>();
        dices.add(2);
        g.addDices(dices);
        g.handleRoll();
        assertTrue(g.getPlyrStatus(0));
        assertTrue(g.getPlyrStatus(1));
        assertTrue(g.getPlyrStatus(2));
        assertTrue(g.getPlyrStatus(3));
        g.enableRollAgain();
        assertTrue(g.getRollAgain());
        g.handleRoll();
        assertFalse(g.getRollAgain());
    }

    @Test
    public void test_flag_reached_before_save() throws InvalidJSONFileException, IOException, URISyntaxException {
        Game g = new Game(3, 4, new FakeGUI(), false);
        assertEquals(0, g.getPlayer(0).getFlagReachedPosition());
        assertEquals(0, g.getPlayer(1).getFlagReachedPosition());
        assertEquals(0, g.getPlayer(2).getFlagReachedPosition());
        assertEquals(0, g.getPlayer(3).getFlagReachedPosition());
        g.getPlayer(0).placeDiceOnBoard(new int[]{0, 8});
        g.getPlayer(1).placeDiceOnBoard(new int[]{0, 8});
        g.checkFlagPoints();
        assertEquals(2, g.getPlayerToReachFlag());
        assertEquals(1, g.getPlayer(0).getFlagReachedPosition());
        assertEquals(1, g.getPlayer(1).getFlagReachedPosition());
        assertEquals(0, g.getPlayer(2).getFlagReachedPosition());
        assertEquals(0, g.getPlayer(3).getFlagReachedPosition());
        g.getPlayer(0).replaceDices();
        g.getPlayer(1).replaceDices();
        g.getPlayer(0).clearDices();
        g.getPlayer(1).clearDices();
        assertTrue(g.getPlayer(0).getDicePlacedPositions().isEmpty());
        g.getPlayer(2).placeDiceOnBoard(new int[]{0, 8});
        g.checkFlagPoints();
        assertEquals(3, g.getPlayerToReachFlag());
        assertEquals(2, g.getPlayer(2).getFlagReachedPosition());
        assertEquals(1, g.getPlayer(0).getFlagReachedPosition());
        assertEquals(1, g.getPlayer(1).getFlagReachedPosition());
        assertEquals(0, g.getPlayer(3).getFlagReachedPosition());
        g.getPlayer(2).replaceDices();
        g.getPlayer(2).clearDices();
        g.getPlayer(3).placeDiceOnBoard(new int[]{0, 8});
        g.checkFlagPoints();
        assertEquals(4, g.getPlayerToReachFlag());
        assertEquals(2, g.getPlayer(2).getFlagReachedPosition());
        assertEquals(1, g.getPlayer(0).getFlagReachedPosition());
        assertEquals(1, g.getPlayer(1).getFlagReachedPosition());
        assertEquals(3, g.getPlayer(3).getFlagReachedPosition());

    }
}
