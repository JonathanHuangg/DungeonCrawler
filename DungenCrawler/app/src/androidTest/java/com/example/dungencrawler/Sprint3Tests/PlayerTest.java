package com.example.dungencrawler.Sprint3Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.example.dungencrawler.model.Difficulty;
import com.example.dungencrawler.model.GameConfig;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PlayerMovementDown;
import com.example.dungencrawler.model.Subscriber;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest {

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
}
