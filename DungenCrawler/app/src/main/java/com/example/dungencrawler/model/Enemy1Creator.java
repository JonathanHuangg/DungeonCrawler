package com.example.dungencrawler.model;

public class Enemy1Creator extends EnemyCreator {
    @Override
    public Enemy createEnemy(float x, float y, float attackDamage) {
        return new Enemy1(x, y, attackDamage);
    }
}
