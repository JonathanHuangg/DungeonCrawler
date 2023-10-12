package com.example.dungencrawler.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungencrawler.R;

public class GrassTile extends Tile {

    private Bitmap grassTile;

    public GrassTile(Context context, Rect mapLocationRect) {
        super(mapLocationRect);

        int textureResourceId = R.drawable.grasstexture;
        grassTile = BitmapFactory.decodeResource(context.getResources(), textureResourceId);
    }

    @Override
    public void draw(Canvas canvas) {
        if (grassTile != null) {
            canvas.drawBitmap(grassTile, null, getMapLocationRect(), null);
        }
    }
}




