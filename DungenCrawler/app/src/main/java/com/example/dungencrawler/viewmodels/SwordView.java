package com.example.dungencrawler.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import com.example.dungencrawler.R;
import com.example.dungencrawler.model.Sword;

public class SwordView extends View {
    private Sword sword;
    private Bitmap swordBitmap;
    public SwordView(Context context, Sword sword, int charId) {
        super(context);
        this.sword = sword;
        this.swordBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sword);
        int width = 100;
        int height = 100;
        this.swordBitmap = Bitmap.createScaledBitmap(swordBitmap, width, height, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (swordBitmap != null) {
            canvas.drawBitmap(swordBitmap, sword.getSwordX(), sword.getSwordY(), null);
        }
    }
}
