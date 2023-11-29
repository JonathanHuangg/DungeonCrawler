package com.example.dungencrawler.Sprint5Tests;
import static org.junit.Assert.assertEquals;

import com.example.dungencrawler.model.Enemy;
import com.example.dungencrawler.model.Enemy1Creator;
import com.example.dungencrawler.model.EnemyCreator;
import com.example.dungencrawler.model.Observer;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PlayerMovementRight;
import org.junit.Test;
public class EnemyKillTest {
    @Test
    public void testEnemyKill() {
        EnemyCreator enemy1Creator = new Enemy1Creator();
        Enemy enemy1 = enemy1Creator.createEnemy(0, 0, 50);
        assertEquals(false, enemy1.isDead());
        enemy1.kill();
        assertEquals(true, enemy1.isDead());
    }

    @Test
    public void testDeadEnemyDamage() {
        EnemyCreator enemy1Creator = new Enemy1Creator();
        Enemy enemy1 = enemy1Creator.createEnemy(0, 0, 50);
        Player player = new Player("Edison", 1000, 0, 0);
        enemy1.kill();
        Observer obs = Observer.getObserver();
        obs.setPlayer(player);
        obs.enemyUpdate(enemy1);
        assertEquals(1000, player.getHealth());
    }
}
