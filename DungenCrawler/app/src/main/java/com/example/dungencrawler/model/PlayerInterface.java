package com.example.dungencrawler.model;

import java.util.List;

public interface PlayerInterface {
    // Getters from player class
    String getName();
    int getHealth();
    float getPlayerX();
    float getPlayerY();
    EntityStrategy getEntityStrategy();
    List<Subscriber> getSubscribers();
    int getAttackStatus();
    boolean getPlayerWinResult();

    // Setters
    void setEntityStrategy(EntityStrategy entityStrategy);
    void setName(String name);
    void setAttackStatus(int status);
    void setHealth(int health);
    void setPlayerX(float x);
    void setPlayerY(float y);
    void setPlayerWinResult(int score);

    // Other methods
    void executeEntityStrategy(Player player, int screenHeight, int screenWidth);
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);


}
