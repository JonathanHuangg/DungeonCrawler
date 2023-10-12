package com.example.dungencrawler.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dungencrawler.R;

public class NetherackTile extends Tile {

    private Bitmap netherackTile;

    public NetherackTile(Context context, Rect mapLocationRect) {
        super(mapLocationRect);

        int textureResourceId = R.drawable.netheracktexture;
        netherackTile = BitmapFactory.decodeResource(context.getResources(), textureResourceId);
    }

    @Override
    public void draw(Canvas canvas) {
        if (netherackTile != null) {
            canvas.drawBitmap(netherackTile, null, getMapLocationRect(), null);
        }
    }
}




