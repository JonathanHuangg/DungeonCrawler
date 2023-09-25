package com.example.dungencrawler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameEndActivity extends AppCompatActivity {
    private Button newGamebutton;
    private TextView lb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        newGamebutton = findViewById(R.id.newGameButton);
        lb = findViewById(R.id.leaderboard);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        this.setNewGameButtonSize(width, height);
        lb.setX(width / 2 - 400 * (width / 2280));
        lb.setY(10);
        lb.setTextSize((float) (lb.getTextSize() * (width / 2280) / 2.3));
        newGamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }

        });
    }

    private void setNewGameButtonSize(int width, int height) {
        newGamebutton.setWidth(240 * (width / 2280));
        newGamebutton.setHeight(72 * (height / 1014));
        newGamebutton.setX(((float) width) / 2 - 270 * (width / 2280));
        newGamebutton.setY((float) (height * 0.8) - 72 * (height / 1014));
        newGamebutton.setTextSize((float) (newGamebutton.getTextSize() * (width / 2280) / 2.75));
    }

    private void restartGame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}