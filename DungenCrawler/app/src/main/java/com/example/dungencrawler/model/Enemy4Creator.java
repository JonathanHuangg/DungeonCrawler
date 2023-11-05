package com.example.dungencrawler.model;

public class Enemy4Creator extends EnemyCreator {
    @Override
    public Enemy createEnemy(float x, float y, float attackDamage) {
        return new Enemy4(x, y, attackDamage);
    }
}
