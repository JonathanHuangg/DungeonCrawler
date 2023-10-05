package com.example.dungencrawler.model;

public class GameConfig {
    //more to be added as game develops
    private Difficulty difficulty;

    public GameConfig(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    //getters
    public Difficulty getDifficulty() {
        return difficulty;
    }

    //setters
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
