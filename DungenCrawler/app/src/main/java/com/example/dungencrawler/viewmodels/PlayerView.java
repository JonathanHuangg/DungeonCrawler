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

    public PlayerView(Context context, Player player, int characterNum) {
        super(context);
        this.player = player;
        //this.character = character;
        this.characterBitmap = BitmapFactory.decodeResource(getResources(), characterNum);
    }

//    public void updatePlayerPosition(float newX, float newY) {
//        player.setPlayerX(newX);
//        player.setPlayerY(newY);
//        invalidate();
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(characterBitmap, player.getPlayerX(), player.getPlayerY(), null);
    }
}
