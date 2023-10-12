// File: Tilemap.java
package com.example.dungencrawler.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class Tilemap extends View {

    private final int rows;
    private final int cols;
    private Tile[][] tiles;

    public Tilemap(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Do not initialize rows and cols here; they'll be populated when setup is called.
        this.rows = 0;
        this.cols = 0;
    }

    public void setupTilemap(int[][] layout) {
        int newRows = layout.length;
        int newCols = layout[0].length;
        this.tiles = new Tile[newRows][newCols];

        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newCols; j++) {
                Rect tileRect = new Rect(
                        j * MapLayout.TILE_WIDTH_PIXELS,
                        i * MapLayout.TILE_HEIGHT_PIXELS,
                        (j + 1) * MapLayout.TILE_WIDTH_PIXELS,
                        (i + 1) * MapLayout.TILE_HEIGHT_PIXELS
                );
                this.tiles[i][j] = Tile.getTile(getContext(), layout[i][j], tileRect);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (tiles == null) return;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles[i][j].draw(canvas);
            }
        }
    }

}