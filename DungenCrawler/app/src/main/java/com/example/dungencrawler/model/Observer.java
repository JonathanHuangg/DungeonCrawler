package com.example.dungencrawler.model;

import java.util.ArrayList;

public class Observer implements EnemySubscriber{

    private static Observer obs;
    private Player player;
    private Observer() {

    }

    public static Observer getObserver() {
        if (obs == null) {
            synchronized (Observer.class) {
                if (obs == null) {
                    obs = new Observer();
                }
            }
        }
        return obs;
    }

    @Override
    public void enemyUpdate(Enemy subscriber) {
        if(Math.abs(subscriber.getEnemyY() - player.getPlayerY()) < 100
                && Math.abs(subscriber.getEnemyX() - player.getPlayerX()) < 100) {
            player.setHealth((int) (player.getHealth() - subscriber.getAttackDamage()));
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
