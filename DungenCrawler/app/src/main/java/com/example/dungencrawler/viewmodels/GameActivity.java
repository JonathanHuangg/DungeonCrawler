package com.example.dungencrawler.viewmodels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.*;
import android.widget.RelativeLayout;

import com.example.dungencrawler.R;
import com.example.dungencrawler.model.Difficulty;
import com.example.dungencrawler.model.GameConfig;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PlayerMovementLeft;
import com.example.dungencrawler.model.PlayerMovementRight;
import com.example.dungencrawler.model.PlayerMovementUp;
import com.example.dungencrawler.model.PlayerMovementDown;


public class GameActivity extends AppCompatActivity {
    private Player player;
    private PlayerView playerView;
    private GameConfig game;
    private CountDownTimer timer;
    RelativeLayout gameLayout;

    DisplayMetrics displayMetrics = new DisplayMetrics();
    int screenWidth = displayMetrics.widthPixels;
    int screenHeight = displayMetrics.heightPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        Intent i = getIntent();

        TextView playerName = findViewById(R.id.textView_playerName);
        TextView chosenDifficulty = findViewById(R.id.textView_ChosenDifficulty);
        TextView startingHealth = findViewById(R.id.textView_startingHealth);
        Button goToEndScreenButton = findViewById(R.id.goToEndScreenButton);
        gameLayout = findViewById(R.id.gameLayOut);

        String username = i.getStringExtra("username");
        playerName.setText(username);
        Difficulty difficulty = (Difficulty) i.getSerializableExtra("difficulty");
        int character = i.getIntExtra("character", 1);
        int charId;
        if (character == 1) {
            charId = R.id.dc1;
        } else if (character == 2) {
            charId = R.id.dc2;
        } else {
            charId = R.id.dc3;
        }

        //Initialize game objects (player, enemies, etc)
        player = new Player(username, 200, (float) screenWidth / 2, (float) screenHeight / 2);

        playerView = new PlayerView(this, player, charId);
        gameLayout.addView(playerView);

        //Initialize game configurations
        game = new GameConfig(Difficulty.easy, 30);

        // Set the Character
//        View dC1 = findViewById(R.id.dc1);
//        View dC2 = findViewById(R.id.dc2);
//        View dC3 = findViewById(R.id.dc3);
//        if (character == 1) {
//            dC1.setVisibility(View.VISIBLE);
//        } else if (character == 2) {
//            dC2.setVisibility(View.VISIBLE);
//        } else {
//            dC3.setVisibility(View.VISIBLE);
//        }

        // Set the Difficulty Text
        if (difficulty == Difficulty.medium) {
            chosenDifficulty.setText("Medium Difficulty");
            game.setDifficulty(Difficulty.medium);
            startingHealth.setText("Health: 100");
            player.setHealth(100);
        } else if (difficulty == Difficulty.hard) {
            chosenDifficulty.setText("Hard Difficulty");
            game.setDifficulty(Difficulty.hard);
            startingHealth.setText("Health: 50");
            player.setHealth(50);
        } else {
            chosenDifficulty.setText("Easy Difficulty");
            game.setDifficulty(Difficulty.easy);
            startingHealth.setText("Health: 200");
            player.setHealth(200);
        }

        goToEndScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButton(goToEndScreenButton);
                navigateToEndScreen(username, game.getScore());
            }

        });

        //score timer system
        TextView countdownTimer = findViewById(R.id.countdownTimer);
        timer = new CountDownTimer(game.getCountdownTime() * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) millisUntilFinished / 1000;
                countdownTimer.setText("Score: " + secondsLeft);
                game.setScore(secondsLeft);
            }

            public void onFinish() {
                navigateToEndScreen(player.getName(), 0);
            }

        }.start();
    }

    private void toggleButton(View v) {
        v.setEnabled(false);
    }
    public void navigateToEndScreen(String name, int score) {
        if (timer != null) {
            timer.cancel();
        }
        Intent i = new Intent(GameActivity.this, GameEndActivity.class);
        i.putExtra("name", name);
        i.putExtra("score", score);
        startActivity(i);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                player.setEntityStrategy(new PlayerMovementLeft());
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                player.setEntityStrategy(new PlayerMovementRight());
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                player.setEntityStrategy(new PlayerMovementUp());
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                player.setEntityStrategy(new PlayerMovementDown());
                break;
        }
        // playerView.updatePlayerPosition(playerX, playerY);
        // checkCollisions();
        return true;
    }

}