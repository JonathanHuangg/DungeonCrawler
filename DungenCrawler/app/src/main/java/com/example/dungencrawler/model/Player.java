package com.example.dungencrawler.model;
public class Player {
    //more to be added as game develops
    private String name;
    private int health;
    public Player(String name, int health) {
        this.name = name;
        this.health = health;
    }

    //getters
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setHealth(int health) {
        this.health = health;
    }

}
