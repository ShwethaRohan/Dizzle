/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SHWETHA
 */
public class StatusTest {

    @Test
    public void testConstructorAndGetter() {      
        Status s = new Status(3, 1, 0, new ArrayList<>(), new ArrayList<>());
        assertEquals(3, s.getLevel());
        assertEquals(1, s.getRoundNumber());
        assertEquals(0, s.getTurnNumber());
        assertTrue(s.getDices().isEmpty());
        assertTrue(s.getPlayers().isEmpty());
    }
    
    @Test
    public void testConstructorAndGetterWithPlayers() {
        //player 1
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
        Players p1 = new Players(status, checked, diceOn, exploded, flagPoints);
        //player 2
        status = true;
        checked = new ArrayList<>();
        checked.add(new Grids(1,2));
        checked.add(new Grids(0,3));
        diceOn = new ArrayList<>();
        diceOn.add(new Grids(2,2));
        diceOn.add(new Grids(1,3));
        exploded = new ArrayList<>();
        exploded.add(new Grids(3,2));
        exploded.add(new Grids(3,0));
        flagPoints = 0;
        Players p2 = new Players(status, checked, diceOn, exploded, flagPoints);
        
        //player 3
        Players p3 = new Players(false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 6);
        
        List<Integer> diceOnTable = new ArrayList<>();
        diceOnTable.add(2);
        diceOnTable.add(6);
        
        List<Players> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        
        Status s = new Status(3, 1, 0, diceOnTable, players);
        
        assertEquals(3, s.getLevel());
        assertEquals(1, s.getRoundNumber());
        assertEquals(0, s.getTurnNumber());
        assertEquals(2, s.getDices().size());
        assertEquals(3, s.getPlayers().size());
        
        List<Players> p = s.getPlayers();
        assertFalse(p.get(0).getStatus());
        assertTrue(p.get(1).getStatus());
        assertFalse(p.get(2).getStatus());
        
        
    }
    
    
}
