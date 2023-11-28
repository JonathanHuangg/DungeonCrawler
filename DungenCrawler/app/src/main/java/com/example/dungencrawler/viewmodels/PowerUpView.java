package com.example.dungencrawler.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import com.example.dungencrawler.model.PowerUpHealth;
import com.example.dungencrawler.model.PowerUpInstaWin;
import com.example.dungencrawler.model.PowerUpSlashAndDash;

public class PowerUpView extends View {
    private final PowerUpSlashAndDash powerUpSlash;
    private final PowerUpInstaWin powerUpInsta;
    private final PowerUpHealth powerUpHealth;

    private Bitmap powerUpSlashBitmap;
    private Bitmap powerUpInstaBitmap;
    private Bitmap powerUpHealthBitmap;

    public PowerUpView(Context context, PowerUpSlashAndDash powerUpSlash, int powerUpSlashResourceId,
                       PowerUpInstaWin powerUpInsta, int powerUpInstaResourceId,
                       PowerUpHealth powerUpHealth, int powerUpHealthResourceId) {
        super(context);
        this.powerUpSlash = powerUpSlash;
        this.powerUpInsta = powerUpInsta;
        this.powerUpHealth = powerUpHealth;

        if (powerUpSlash != null) {
            this.powerUpSlashBitmap = BitmapFactory.decodeResource(getResources(), powerUpSlashResourceId);
        }
        if (powerUpInsta != null) {
            this.powerUpInstaBitmap = BitmapFactory.decodeResource(getResources(), powerUpInstaResourceId);
        }
        if (powerUpHealth != null) {
            this.powerUpHealthBitmap = BitmapFactory.decodeResource(getResources(), powerUpHealthResourceId);
        }

        resizeBitmaps();
    }

    private void resizeBitmaps() {
        int width = 100;
        int height = 100;
        if (powerUpSlashBitmap != null) {
            powerUpSlashBitmap = Bitmap.createScaledBitmap(powerUpSlashBitmap, width, height, true);
        }
        if (powerUpInstaBitmap != null) {
            powerUpInstaBitmap = Bitmap.createScaledBitmap(powerUpInstaBitmap, width, height, true);
        }
        if (powerUpHealthBitmap != null) {
            powerUpHealthBitmap = Bitmap.createScaledBitmap(powerUpHealthBitmap, width, height, true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (powerUpSlash != null && powerUpSlashBitmap != null) {
            canvas.drawBitmap(powerUpSlashBitmap, powerUpSlash.getX(), powerUpSlash.getY(), null);
        }
        if (powerUpInsta != null && powerUpInstaBitmap != null) {
            canvas.drawBitmap(powerUpInstaBitmap, powerUpInsta.getX(), powerUpInsta.getY(), null);
        }
        if (powerUpHealth != null && powerUpHealthBitmap != null) {
            canvas.drawBitmap(powerUpHealthBitmap, powerUpHealth.getX(), powerUpHealth.getY(), null);
        }
    }
}
