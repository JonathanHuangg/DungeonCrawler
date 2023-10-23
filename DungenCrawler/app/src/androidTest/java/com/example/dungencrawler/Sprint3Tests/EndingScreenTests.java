package com.example.dungencrawler.Sprint3Tests;

import static org.junit.Assert.assertEquals;

import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PlayerMovementRight;
import com.example.dungencrawler.viewmodels.Room3Activity;

import org.junit.Test;

public class EndingScreenTests {
    /**
     * pass a win condition boolean to ending screen, verfiy win screen is shown
     */
    @Test
    public void testWinCondition() {
        Player player = new Player("winConTest", 79, 50, 50);
        player.setPlayerWinResult(61);
        assertEquals(player.getPlayerWinResult(), true);
    }

    /**
     * pass a lose condition boolean to ending screen, verify loss screen is shown
     */
    @Test
    public void testLoseCondition() {
        Player player = new Player("winConTest", 79, 50, 50);
        player.setPlayerWinResult(59);
        assertEquals(player.getPlayerWinResult(), false);
    }
}
