package com.example.dungencrawler.model;
import java.util.ArrayList;
import java.util.List;

public class Player implements  PlayerInterface {
    //more to be added as game develops
    private int attackStatus;
    private String name;
    private int health;
    private float x;
    private float y;
    private static Player player;
    private EntityStrategy entityStrategy;
    private List<Subscriber> subscribers = new ArrayList<>();
    private boolean winResult;

    public Player(String name, int health, float x, float y) {
        if (validateName(name)) {
            setName(name);
        }
        setHealth(health);
        setPlayerX(x);
        setPlayerY(y);
        setAttackStatus(0); // default to not attacking
    }
    public static Player getPlayer() {
        if (player == null) {
            synchronized (Observer.class) {
                if (player == null) {
                    player = new Player("name", 200, 0, 0);
                }
            }
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
    public float getPlayerX() {
        return x; }
    public float getPlayerY() {
        return y; }
    public EntityStrategy getEntityStrategy() {
        return entityStrategy;
    }
    public List<Subscriber> getSubscribers() {
        return subscribers;
    }
    public int getAttackStatus() {
        return attackStatus;
    }

    //setters
    public void setEntityStrategy(EntityStrategy entityStrategy) {
        this.entityStrategy = entityStrategy;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAttackStatus(int status) {
        this.attackStatus = status;
    }
    public void setHealth(int health) {
        if (health > 0) {
            this.health = health;
        } else {
            this.health = 0;
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

    public void setPlayerWinResult(int score) {
        if (score > 40) {
            this.winResult = true;
        } else {
            this.winResult = false;
        }
    }

    public boolean getPlayerWinResult() {
        return this.winResult;
    }

    //check username
    public static boolean validateName(String name) {
        return (name != null && !name.isEmpty() && !name.trim().isEmpty());
    }

    public void executeEntityStrategy(Player player, int screenHeight, int screenWidth) {
        entityStrategy.execute(player, screenHeight, screenWidth);
    }

    //observer architecture
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
    protected void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(this, 100, 100);
        }
    }
}
