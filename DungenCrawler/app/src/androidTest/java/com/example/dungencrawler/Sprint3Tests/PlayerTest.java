package com.example.dungencrawler.Sprint3Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.util.DisplayMetrics;

import com.example.dungencrawler.model.Difficulty;
import com.example.dungencrawler.model.GameConfig;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PlayerMovementDown;
import com.example.dungencrawler.model.Subscriber;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

public class PlayerTest {
    @Test
    public void initTest() throws NoSuchFieldException, IllegalAccessException {
        Player player = new Player("test", 79, 43, (float)92.4);
        Field field = player.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String name = (String) field.get(player);
        assertEquals("test", name);
        field = player.getClass().getDeclaredField("health");
        field.setAccessible(true);
        assertEquals(79, field.getInt(player));
        field = player.getClass().getDeclaredField("x");
        field.setAccessible(true);
        assertEquals(43, field.getFloat(player), 0.00001);
        field = player.getClass().getDeclaredField("y");
        field.setAccessible(true);
        assertEquals(92.4, field.getFloat(player), 0.00001);
    }
    @Test
    public void getterTest() {
        Player player = new Player("test", 79, 43, (float)92.4);
        assertEquals("test", player.getName());
        assertEquals(79, player.getHealth());
        assertEquals(43, player.getPlayerX(), 0.00001);
        assertEquals(92.4, player.getPlayerY(), 0.00001);
    }

    //testing subscribe and unsubscribe of observer architecture
    @Test
    public void observerArchitectureTest() {
        Player player = new Player("j", 50, 10, 10);
        PlayerMovementDown movementDown = new PlayerMovementDown();
        player.subscribe(movementDown);

        player.unsubscribe(movementDown);
        List<Subscriber> subscriberList = new ArrayList<>();
        assertEquals(subscriberList, player.getSubscribers());
    }

    //test notify() in playerX and playerY
    @Test
    public void observerArchitectureNotifyTest() {
        Player player = new Player("j", 50, 10, 10);
        player.setPlayerY(50);
        player.setPlayerX(50);
        List<Subscriber> subscriberList = new ArrayList<>();
        assertEquals(subscriberList, player.getSubscribers());
        assertEquals(50, player.getPlayerX(), 0);
        assertEquals(50, player.getPlayerY(), 0);
    }

    @Test
    public void playerTopBoundaryTest() {
        // Initialize player at (0, 0)
        Player player = new Player("test", 100, 0, 0);

        // Define screen boundaries
        final float SCREEN_MIN_Y = 0;


        player.setPlayerX(SCREEN_MIN_Y - 10);
        assertEquals(SCREEN_MIN_Y, player.getPlayerY(), 0.00001);
    }
    @Test
    public void playerBottomBoundaryTest() {
        // Initialize player at (0, 0)
        Player player = new Player("test", 100, 0, 0);

        // Define screen boundaries
        final float SCREEN_MAX_Y = 400;


        player.setPlayerX(SCREEN_MAX_Y + 99999);
        assertEquals(SCREEN_MAX_Y, player.getPlayerY(), 0.00001);
    }
}
