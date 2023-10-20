package com.example.dungencrawler;
import static org.junit.Assert.assertEquals;

import com.example.dungencrawler.model.Player;

import org.junit.Test;
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
}
