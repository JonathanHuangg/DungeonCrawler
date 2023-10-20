package com.example.dungencrawler.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Player {
    //more to be added as game develops
    private String name;
    private int health;
    private float x, y;
    private static Player player;
    private Bitmap icon;
    private EntityStrategy entityStrategy;
    private List<Subscriber> subscribers = new ArrayList<>();
    public Player(String name, int health, float x, float y) {
        if (validateName(name)) {
            setName(name);
        }
        setHealth(health);
        setPlayerX(x);
        setPlayerY(y);

    }
    public static Player getPlayer() {
        if (player == null) {
            player = new Player("name", 200, 0, 0);
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
    public float getPlayerX() { return x; }
    public float getPlayerY() { return y; }
    public EntityStrategy getEntityStrategy() {
        return entityStrategy;
    }

    //setters
    public void setEntityStrategy(EntityStrategy entityStrategy) {
        this.entityStrategy = entityStrategy;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHealth(int health) {
        if (health > 0) {
            this.health = health;
        }
    }
    public void setPlayerX(float x) {
        this.x = x;
        notifySubscribers();
    }

    public void setPlayerY(float y) {
        this.y = y;
        notifySubscribers();
    }

    //check username
    public static boolean validateName(String name) {
        return (name != null && !name.isEmpty() && !name.trim().isEmpty());
    }

    public void executeEntityStrategy(Player player, String direction) {
        entityStrategy.execute(player);
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
    protected void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(this);
        }
    }

}
