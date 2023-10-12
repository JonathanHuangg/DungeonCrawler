package com.example.dungencrawler.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungencrawler.R;

public class LavaTile extends Tile {

    private Bitmap lavaTexture;

    public LavaTile(Context context, Rect mapLocationRect) {
        super(mapLocationRect);

        int textureResourceId = R.drawable.lavatexture;
        lavaTexture = BitmapFactory.decodeResource(context.getResources(), textureResourceId);
    }

    @Override
    public void draw(Canvas canvas) {
        if (lavaTexture != null) {
            canvas.drawBitmap(lavaTexture, null, getMapLocationRect(), null);
        }
    }
}




