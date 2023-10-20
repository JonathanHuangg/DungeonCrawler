package com.example.dungencrawler.oldtests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.example.dungencrawler.model.Difficulty;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.viewmodels.MainActivity;

import org.junit.Test;

public class PlayerTest {

    //test the constructor to make sure that it initializes correctly
    /*
    @Test
    public void testPlayerConst() {
        Player player = new Player("name", 50);
        assertEquals("name", player.getName());
        assertEquals(50, player.getHealth());
        Player player2 = new Player("name2", 60);
        assertEquals("name2", player2.getName());
        assertEquals(60, player2.getHealth());
        Player player3 = new Player(null, 200 );
        assertEquals(null, player3.getName());
        assertEquals(200, player3.getHealth());
        Player player4 = new Player("jessie", -5 );
        assertEquals("jessie", player4.getName());
        assertEquals(0, player4.getHealth());
    }

    //test the usernames to make sure they cannot be null, whitespace, etc.
    @Test
    public void testPlayerName() {
        Player player1 = new Player("", 100);
        assertEquals(false, Player.validateName(player1.getName()));
        player1.setName(null);
        assertEquals(false, Player.validateName(player1.getName()));
        player1.setName("jess");
        assertEquals(true, Player.validateName(player1.getName()));
        player1.setName("123");
        assertEquals(true, Player.validateName(player1.getName()));
        player1.setName("   ");
        assertEquals(false, Player.validateName(player1.getName()));
        player1.setName("jess rigs");
        assertEquals(true, Player.validateName(player1.getName()));
    }

    @Test
    public void validateUserInputNull() {
        Player player = new Player(null, 1000);
        player.setName(null);
        assertFalse(Player.validateName(player.getName()));
    }

    @Test
    public void validateUserInputSpace() {
        Player player = new Player(null, 1000);
        player.setName("    ");
        assertFalse(Player.validateName(player.getName()));
    }
    */


}
