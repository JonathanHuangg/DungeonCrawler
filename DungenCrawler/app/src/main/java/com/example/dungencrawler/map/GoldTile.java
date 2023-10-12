package com.example.dungencrawler.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungencrawler.R;

public class GoldTile extends Tile {

    private Bitmap goldTile;

    public GoldTile(Context context, Rect mapLocationRect) {
        super(mapLocationRect);

        int textureResourceId = R.drawable.goldtexture;
        goldTile = BitmapFactory.decodeResource(context.getResources(), textureResourceId);
    }

    @Override
    public void draw(Canvas canvas) {
        if (goldTile != null) {
            canvas.drawBitmap(goldTile, null, getMapLocationRect(), null);
        }
    }
}




