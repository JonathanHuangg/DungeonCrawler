package com.example.dungencrawler.model;
public class Sword {
    private float x;
    private float y;
    public Sword(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSwordX(float x) {
        this.x = x;
    }
    public void setSwordY(float y) {
        this.y = y;
    }
    public float getSwordX() {
        return x;
    }
    public float getSwordY() {
        return y;
    }
}
