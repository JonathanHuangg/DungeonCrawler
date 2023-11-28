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

        this.powerUpSlashBitmap = BitmapFactory.decodeResource(getResources(), powerUpSlashResourceId);
        this.powerUpInstaBitmap = BitmapFactory.decodeResource(getResources(), powerUpInstaResourceId);
        this.powerUpHealthBitmap = BitmapFactory.decodeResource(getResources(), powerUpHealthResourceId);

        int width = 100;
        int height = 100;
        this.powerUpSlashBitmap = Bitmap.createScaledBitmap(powerUpSlashBitmap, width, height, true);
        this.powerUpInstaBitmap = Bitmap.createScaledBitmap(powerUpInstaBitmap, width, height, true);
        this.powerUpHealthBitmap = Bitmap.createScaledBitmap(powerUpHealthBitmap, width, height, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPowerUpSlash(canvas, powerUpSlash, powerUpSlashBitmap);
        drawPowerUpInsta(canvas, powerUpInsta, powerUpInstaBitmap);
        drawPowerUpHealth(canvas, powerUpHealth, powerUpHealthBitmap);
    }

    private void drawPowerUpHealth(Canvas canvas, PowerUpHealth powerUp, Bitmap bitmap) {
        if (powerUp != null && bitmap != null) {
            canvas.drawBitmap(bitmap, powerUp.getX(), powerUp.getY(), null);
        }
    }

    private void drawPowerUpSlash(Canvas canvas, PowerUpSlashAndDash powerUp, Bitmap bitmap) {
        if (powerUp != null && bitmap != null) {
            canvas.drawBitmap(bitmap, powerUp.getX(), powerUp.getY(), null);
        }
    }

    private void drawPowerUpInsta(Canvas canvas, PowerUpInstaWin powerUp, Bitmap bitmap) {
        if (powerUp != null && bitmap != null) {
            canvas.drawBitmap(bitmap, powerUp.getX(), powerUp.getY(), null);
        }
    }
}
