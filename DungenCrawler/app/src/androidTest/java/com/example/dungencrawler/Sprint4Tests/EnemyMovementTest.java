package com.example.dungencrawler.Sprint4Tests;
import static org.junit.Assert.assertNotEquals;
import com.example.dungencrawler.model.Enemy;
import com.example.dungencrawler.model.Enemy1;
import com.example.dungencrawler.model.Enemy2;
import com.example.dungencrawler.model.Difficulty;
import org.junit.Test;
import java.util.Random;

public class EnemyMovementTest {
    @Test
    public void testEnemy1Movement() {
        Enemy enemy1 = new Enemy1(0, 0, 50);
        setRandomEnemyDirection(enemy1);
        enemy1.enemyMove(Difficulty.hard, 50, 50);
        assertNotEquals((float) 0, enemy1.getEnemyX(), 0.000001);
        assertNotEquals((float) 0, enemy1.getEnemyY(), 0.000001);
    }

    @Test
    public void testEnemy2Movement() {
        Enemy enemy2 = new Enemy2(0, 0, 50);
        setRandomEnemyDirection(enemy2);
        enemy2.enemyMove(Difficulty.hard, 50, 50);
        assertNotEquals((float) 0, enemy2.getEnemyX(), 0.000001);
        assertNotEquals((float) 0, enemy2.getEnemyY(), 0.000001);
    }

    private void setRandomEnemyDirection(Enemy enemy) {
        final Random random = new Random(99999999);
        float angle = (float)(random.nextFloat() * 2 * Math.PI);
        enemy.setEnemyDx((float)Math.cos(angle) * 50);
        enemy.setEnemyDy((float)Math.sin(angle) * 50);
    }
}
