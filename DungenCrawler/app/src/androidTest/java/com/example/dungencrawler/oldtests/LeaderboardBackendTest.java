package com.example.dungencrawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.dungencrawler.model.Leaderboard;

import org.junit.Test;

import java.util.ArrayList;

public class LeaderboardBackendTest {
    @Test
    public void testLeaderboardInit() {
        ArrayList<String> arr = Leaderboard.getLeaderboard();
        assertNotEquals(null, arr);
    }

    @Test
    public void testLeaderboard() {
        ArrayList<String> arr = Leaderboard.getLeaderboard();
        assertNotEquals(null, arr);
        Leaderboard.updateLeaderboard("Jess", 556);
        assertEquals("Jess 556", arr.get(0));
        Leaderboard.updateLeaderboard("Edison", 843);
        assertEquals("Edison 843", arr.get(0));
        assertEquals("Jess 556", arr.get(1));
        Leaderboard.updateLeaderboard("Jonathan", 332);
        Leaderboard.updateLeaderboard("Justin", 284);
        Leaderboard.updateLeaderboard("Achyutan", 844);
        assertEquals("Achyutan 844", arr.get(0));
        assertEquals("Edison 843", arr.get(1));
        assertEquals("Jess 556", arr.get(2));
        assertEquals("Jonathan 332", arr.get(3));
        assertEquals("Justin 284", arr.get(4));
        Leaderboard.updateLeaderboard("Pedro", 896);
        assertEquals("Pedro 896", arr.get(0));
        assertEquals("Achyutan 844", arr.get(1));
        assertEquals("Edison 843", arr.get(2));
        assertEquals("Jess 556", arr.get(3));
        assertEquals("Jonathan 332", arr.get(4));
        Leaderboard.updateLeaderboard("John", 579);
        assertEquals("Pedro 896", arr.get(0));
        assertEquals("Achyutan 844", arr.get(1));
        assertEquals("Edison 843", arr.get(2));
        assertEquals("John 579", arr.get(3));
        assertEquals("Jess 556", arr.get(4));
        Leaderboard.updateLeaderboard("M", 1);
        assertEquals("Pedro 896", arr.get(0));
        assertEquals("Achyutan 844", arr.get(1));
        assertEquals("Edison 843", arr.get(2));
        assertEquals("John 579", arr.get(3));
        assertEquals("Jess 556", arr.get(4));
        Leaderboard.getLeaderboard().clear();
        Leaderboard.updateLeaderboard("M", 1);
        Leaderboard.updateLeaderboard("H", 9);
        Leaderboard.updateLeaderboard("J", 8);
        Leaderboard.updateLeaderboard("K", 4);
        Leaderboard.updateLeaderboard("L", 6);
        assertEquals("H 9", arr.get(0));
        assertEquals("J 8", arr.get(1));
        assertEquals("L 6", arr.get(2));
        assertEquals("K 4", arr.get(3));
        assertEquals("M 1", arr.get(4));
    }
}
