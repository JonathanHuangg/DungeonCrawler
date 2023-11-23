package com.example.dungencrawler.Sprint5Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.dungencrawler.model.Enemy;
import com.example.dungencrawler.model.Enemy1Creator;
import com.example.dungencrawler.model.EnemyCreator;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PlayerMovementRight;
import org.junit.Test;

public class PlayerAttackTest {
    @Test
    public void testPlayerAttack() {
        Player player1 = new Player("Thomas", 1000, 0, 0);
        player1.setAttackStatus(1);
        assertEquals(1, player1.getAttackStatus(), 1);
    }
    public void testPlayerEnemyCollideAttack() {
        EnemyCreator enemy1Creator = new Enemy1Creator();
        Player player1 = new Player("Edison", 1000, 0, 0);
        Enemy enemy1 = enemy1Creator.createEnemy(0, 0, 50);
        player1.setAttackStatus(1);
        assertTrue(playerEnemyCollideAttack(enemy1, player1));
    }

    private boolean playerEnemyCollideAttack(Enemy enemy, Player player) {
        if (Math.abs(enemy.getEnemyY() - player.getPlayerY()) < 100
                && Math.abs(enemy.getEnemyX() - player.getPlayerX()) < 100 && player.getAttackStatus() == 1) {
            return true;
        }
        return false;
    }
}
