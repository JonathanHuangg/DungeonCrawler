package com.example.dungencrawler.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungencrawler.R;

public class LeavesTile extends Tile {

    private Bitmap leavesTile;

    public LeavesTile(Context context, Rect mapLocationRect) {
        super(mapLocationRect);

        int textureResourceId = R.drawable.leaftexture;
        leavesTile = BitmapFactory.decodeResource(context.getResources(), textureResourceId);
    }

    @Override
    public void draw(Canvas canvas) {
        if (leavesTile != null) {
            canvas.drawBitmap(leavesTile, null, getMapLocationRect(), null);
        }
    }
}




