package com.example.dungencrawler.viewmodels;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import com.example.dungencrawler.model.Enemy;

public class EnemyView extends View {
    private Enemy enemy;
    private Bitmap enemyBitmap;
    public EnemyView(Context context, Enemy enemy, int enemyId) {
        super(context);
        this.enemy = enemy;
        this.enemyBitmap = BitmapFactory.decodeResource(getResources(), enemyId);
        int width = 100;
        int height = 100;
        this.enemyBitmap = Bitmap.createScaledBitmap(enemyBitmap, width, height, true);
    }

    public void updateEnemyPosition(float newX, float newY) {
        enemy.setEnemyX(newX);
        enemy.setEnemyY(newY);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (enemyBitmap != null) {
            canvas.drawBitmap(enemyBitmap, enemy.getEnemyX(), enemy.getEnemyY(), null);
        }
    }
}
