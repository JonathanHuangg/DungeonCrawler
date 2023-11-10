package com.example.dungencrawler.model;

public class GameConfig {
    //more to be added as game develops
    private Difficulty difficulty;
    private int countdownTime;
    private int score;

    public GameConfig(Difficulty difficulty, int time) {
        this.difficulty = difficulty;
        this.countdownTime = time;
        this.score = 0;
    }

    public GameConfig(Difficulty difficulty) {
        this(difficulty, 30);
    }

    //getters
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getCountdownTime() {
        return countdownTime;
    }

    public void setCountdownTime(int time) {
        this.countdownTime = time;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    //setters
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
