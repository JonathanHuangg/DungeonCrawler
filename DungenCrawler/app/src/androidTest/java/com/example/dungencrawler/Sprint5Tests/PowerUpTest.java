package com.example.dungencrawler.Sprint5Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.dungencrawler.model.Enemy;
import com.example.dungencrawler.model.Enemy1Creator;
import com.example.dungencrawler.model.EnemyCreator;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PowerUpHealth;
import com.example.dungencrawler.model.PowerUpInstaWin;
import com.example.dungencrawler.model.PowerUpSlashAndDash;

import org.junit.Test;

public class PowerUpTest {
    //Test PowerUp at correct location
    @Test
    public void powerUpHealthTest() {
        Player player = new Player("test", 100, 100, 100);
        float powerUpX = 100;
        float powerUpY = 100;
        PowerUpHealth powerUpHealth = new PowerUpHealth(player, powerUpX, powerUpY);
        powerUpHealth.setHealth(100);
        assertEquals(200, player.getHealth());
    }
    //Test PowerUp at incorrect Location
    @Test
    public void powerUpWinTest() {
        Player player = new Player("test", 100, 50, 50);
        float powerUpX = 100;
        float powerUpY = 100;
        PowerUpInstaWin powerUpHealth = new PowerUpInstaWin(player, powerUpX, powerUpY);
        powerUpHealth.setPlayerWinResult(100);
        assertEquals(true, player.getPlayerWinResult());
    }
}
