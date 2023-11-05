package com.example.dungencrawler.model;

public class Enemy3Creator extends EnemyCreator {
    @Override
    public Enemy createEnemy(float x, float y, float attackDamage) {
        return new Enemy3(x, y, attackDamage);
    }
}
