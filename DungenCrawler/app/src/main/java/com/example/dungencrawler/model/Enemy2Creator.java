package com.example.dungencrawler.model;

public class Enemy2Creator extends EnemyCreator {
    @Override
    public Enemy createEnemy(float x, float y, float attackDamage) {
        return new Enemy2(x, y, attackDamage);
    }
}
