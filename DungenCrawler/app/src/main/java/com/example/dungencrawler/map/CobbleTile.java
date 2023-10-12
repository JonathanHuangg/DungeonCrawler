package com.example.dungencrawler.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungencrawler.R;

public class CobbleTile extends Tile {

    private Bitmap cobblestoneTexture;

    public CobbleTile(Context context, Rect mapLocationRect) {
        super(mapLocationRect);

        int textureResourceId = R.drawable.cobblestonetexture;
        cobblestoneTexture = BitmapFactory.decodeResource(context.getResources(), textureResourceId);
    }


    @Override
    public void draw(Canvas canvas) {
        if (cobblestoneTexture != null) {
            canvas.drawBitmap(cobblestoneTexture, null, getMapLocationRect(), null);
            System.out.println("HELLO");
        }
    }
}




