package com.example.dungencrawler.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import com.example.dungencrawler.model.PowerUpSlashAndDash;
public class PowerUpView extends View {
    private PowerUpSlashAndDash powerUp; //I am doing this one first
    private Bitmap powerUpBitmap;

    public PowerUpView(Context context, PowerUpSlashAndDash powerUp, int powerUpResourceId) {
        super(context);
        this.powerUp = powerUp;
        this.powerUpBitmap = BitmapFactory.decodeResource(getResources(), powerUpResourceId);
        int width = 100;
        int height = 100;
        this.powerUpBitmap = Bitmap.createScaledBitmap(powerUpBitmap, width, height, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (powerUpBitmap != null) {
            // Assuming PowerUpSlashAndDash has getX() and getY() methods
            canvas.drawBitmap(powerUpBitmap, powerUp.getX(), powerUp.getY(), null);
        }
    }
}
