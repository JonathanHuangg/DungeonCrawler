package com.example.dungencrawler.model;

import android.graphics.Bitmap;

public class Player {
    //more to be added as game develops
    private String name;
    private int health;
    private static Player player;
    private Bitmap icon;
    public Player(String name, int health) {
        if (validateName(name)) {
            setName(name);
        }
        setHealth(health);
    }
    public static Player getPlayer() {
        if (player == null) {
            player = new Player("name", 200);
        }
        return player;
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
        if (health > 0) {
            this.health = health;
        }
    }

    //check username
    public static boolean validateName(String name) {
        return (name != null && !name.isEmpty() && !name.trim().isEmpty());
    }

}
