package com.example.dungencrawler.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;


public abstract class Tile {

    private final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public abstract void draw(Canvas canvas);

    public enum TileType {
        COBBLE_TILE,
        LAVA_TILE,
        GRASS_TILE,
        LEAVES_TILE,
        NETHERACK_TILE,
        GOLD_TILE
    }

    public static Tile getTile(Context context, int idxTileType, Rect mapLocationRect) {
        switch (TileType.values()[idxTileType]) {
            case COBBLE_TILE:
                return new CobbleTile(context, mapLocationRect);

            case LAVA_TILE:
                return new LavaTile(context, mapLocationRect);

            case GRASS_TILE:
                return new GrassTile(context, mapLocationRect);

            case LEAVES_TILE:
                return new LeavesTile(context, mapLocationRect);

            case NETHERACK_TILE:
                return new NetherackTile(context, mapLocationRect);

            case GOLD_TILE:
                return new GoldTile(context, mapLocationRect);


            default:
                return null;
        }
    }

    public Rect getMapLocationRect() {
        return mapLocationRect;
    }
}