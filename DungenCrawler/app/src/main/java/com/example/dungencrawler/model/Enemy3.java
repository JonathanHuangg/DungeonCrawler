package com.example.dungencrawler.model;

public class Enemy3 extends Enemy implements EnemyInterface {
    public Enemy3(float x, float y, float attackDamage) {
        super(x, y, attackDamage);
    }
    @Override
    public void enemyMove(Difficulty difficultyLevel, float screenWidth, float screenHeight) {

        this.setEnemyX(this.getEnemyX() + this.getEnemyDx());
        this.setEnemyY(this.getEnemyY() + this.getEnemyDy());

        // Bounce off when collide with the boundary
        if (this.getEnemyX() < 0 || this.getEnemyX() > screenWidth - 200) {
            this.setEnemyDx(-this.getEnemyDx());
            this.setEnemyX(Math.max(0, Math.min(this.getEnemyX(), screenWidth)));
        }
        if (this.getEnemyY() < 0 || this.getEnemyY() > screenHeight - 200) {
            this.setEnemyDy(-this.getEnemyDy());
            this.setEnemyY(Math.max(0, Math.min(this.getEnemyY(), screenWidth)));
        }
    }
}
