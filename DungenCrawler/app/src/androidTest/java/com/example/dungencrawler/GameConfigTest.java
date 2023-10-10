package com.example.dungencrawler;
import static org.junit.Assert.assertEquals;

import com.example.dungencrawler.model.Difficulty;
import com.example.dungencrawler.model.GameConfig;

import org.junit.Test;

public class GameConfigTest {

    @Test
    public void testGameConfigInit() {
        GameConfig conf = new GameConfig(Difficulty.hard);
        assertEquals(conf.getDifficulty(), Difficulty.hard);
    }
    @Test
    public void testGameConfigSet() {
        GameConfig conf = new GameConfig(Difficulty.hard);
        assertEquals(conf.getDifficulty(), Difficulty.hard);
        conf.setDifficulty(Difficulty.easy);
        assertEquals(conf.getDifficulty(), Difficulty.easy);
        conf.setDifficulty(Difficulty.medium);
        assertEquals(conf.getDifficulty(), Difficulty.medium);
    }
}
