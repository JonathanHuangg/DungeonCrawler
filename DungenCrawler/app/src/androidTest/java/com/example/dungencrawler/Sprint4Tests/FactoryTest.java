package com.example.dungencrawler.Sprint4Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.example.dungencrawler.model.Enemy;
import com.example.dungencrawler.model.Enemy1;
import com.example.dungencrawler.model.Enemy1Creator;
import com.example.dungencrawler.model.Enemy2;
import com.example.dungencrawler.model.Enemy2Creator;
import com.example.dungencrawler.model.Enemy3;
import com.example.dungencrawler.model.Enemy3Creator;
import com.example.dungencrawler.model.Enemy4;
import com.example.dungencrawler.model.Enemy4Creator;
import com.example.dungencrawler.model.EnemyCreator;

import org.junit.Test;
public class FactoryTest {

    //Tests the creation of all Enemies and makes sure they match expected types
    @Test
    public void enemyCreatorTests() {
        EnemyCreator enemyTest1 = new Enemy1Creator();
        Enemy enemy1 = enemyTest1.createEnemy(0,0,0);
        assertNotNull(enemy1);
        assertTrue(enemy1 instanceof Enemy1);

        EnemyCreator enemyTest2 = new Enemy2Creator();
        Enemy enemy2 = enemyTest2.createEnemy(0,0,0);
        assertNotNull(enemy1);
        assertTrue(enemy2 instanceof Enemy2);

        EnemyCreator enemyTest3 = new Enemy3Creator();
        Enemy enemy3 = enemyTest3.createEnemy(0,0,0);
        assertNotNull(enemy1);
        assertTrue(enemy3 instanceof Enemy3);

        EnemyCreator enemyTest4 = new Enemy4Creator();
        Enemy enemy4 = enemyTest4.createEnemy(0,0,0);
        assertNotNull(enemy1);
        assertTrue(enemy4 instanceof Enemy4);
    }

    //Test to make sure you cannot create an invalid enemy
    // makes sure the factory constructor follows enemy constructor logic
    @Test
    public void enemyCreatorTestInvalid() {
        EnemyCreator enemyTest5 = new Enemy1Creator();
        Enemy enemy5 = enemyTest5.createEnemy(0,0,-5);
        assertEquals(1, enemy5.getAttackDamage(), 0);

        EnemyCreator enemyTest6 = new Enemy2Creator();
        Enemy enemy6 = enemyTest6.createEnemy(0,0,-5);
        assertEquals(1, enemy6.getAttackDamage(), 0);

        EnemyCreator enemyTest7 = new Enemy2Creator();
        Enemy enemy7 = enemyTest7.createEnemy(0,0,-5);
        assertEquals(1, enemy7.getAttackDamage(), 0);

        EnemyCreator enemyTest8 = new Enemy2Creator();
        Enemy enemy8 = enemyTest8.createEnemy(0,0,-5);
        assertEquals(1, enemy8.getAttackDamage(), 0);


    }

}
