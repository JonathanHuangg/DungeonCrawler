package com.example.dungencrawler.viewmodels;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import com.example.dungencrawler.model.Player;
import android.content.Context;
import android.graphics.Canvas;

public class PlayerView extends View {
    private Player player;
    //private int character;
    private Bitmap characterBitmap;

    public PlayerView(Context context, Player player, int charId) {
        super(context);
        this.player = player;
        this.characterBitmap = BitmapFactory.decodeResource(getResources(), charId);
        int width = 100;
        int height = 100;
        this.characterBitmap = Bitmap.createScaledBitmap(characterBitmap, width, height, true);
    }

    public void updatePlayerPosition(float newX, float newY) {
        player.setPlayerX(newX);
        player.setPlayerY(newY);
        invalidate();
    }

    public float getPlayerPosition() {
        return player.getPlayerX();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (characterBitmap != null) {
            canvas.drawBitmap(characterBitmap, player.getPlayerX(), player.getPlayerY(), null);
        }
    }
}
