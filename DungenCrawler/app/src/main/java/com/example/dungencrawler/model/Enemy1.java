package com.example.dungencrawler.model;

public class Enemy1 extends Enemy implements EnemyInterface {
    public Enemy1(float x, float y, float attackDamage) {
        super(x, y, attackDamage);
    }
    @Override
    public void enemyMove(String difficultyLevel, String direction) {

    }
}
