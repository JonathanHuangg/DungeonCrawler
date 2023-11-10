package com.example.dungencrawler.Sprint4Tests;
import static org.junit.Assert.assertEquals;

import com.example.dungencrawler.model.Enemy;
import com.example.dungencrawler.model.Enemy1;
import com.example.dungencrawler.model.Observer;
import com.example.dungencrawler.model.Player;
import org.junit.Test;

import java.lang.reflect.Field;


public class ObserverTest {
    @Test
    public void testInnit() throws NoSuchFieldException, IllegalAccessException {
        //innit?
        Player player = Player.getPlayer();
        Observer obs = Observer.getObserver();
        player.setPlayerX(45);
        obs.setPlayer(player);
        Field field = obs.getClass().getDeclaredField("player");
        field.setAccessible(true);
        Player pl = (Player) field.get(obs);
        assertEquals(pl, player);
    }
    public void testCollision() {
        Player player = Player.getPlayer();
        Observer obs = Observer.getObserver();
        obs.setPlayer(player);
        player.setPlayerX(45);
        player.setPlayerY(45);
        player.setHealth(50);
        Enemy enemy = new Enemy1(45, 45, 10);
        obs.enemyUpdate(enemy);
        assertEquals(40, player.getHealth());
    }
}
