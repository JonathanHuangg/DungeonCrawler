package com.example.dungencrawler.viewmodels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.*;
import android.widget.*;

import com.example.dungencrawler.R;
import com.example.dungencrawler.model.Difficulty;
import com.example.dungencrawler.model.GameConfig;
import com.example.dungencrawler.model.Player;


public class GameActivity extends AppCompatActivity {
    private Player player;
    private GameConfig game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        Intent i = getIntent();

        TextView playerName = findViewById(R.id.textView_playerName);
        TextView chosenDifficulty = findViewById(R.id.textView_ChosenDifficulty);
        TextView startingHealth = findViewById(R.id.textView_startingHealth);

        String username = i.getStringExtra("username");
        playerName.setText(username);
        Difficulty difficulty = (Difficulty) i.getSerializableExtra("difficulty");
        int character = i.getIntExtra("character", 1);

        //Initialize game objects (player, enemies, etc)
        player = new Player(username, 200);

        //Initialize game configurations
        game = new GameConfig(Difficulty.easy);

        // Set the Character
        View dC1 = findViewById(R.id.dc1);
        View dC2 = findViewById(R.id.dc2);
        View dC3 = findViewById(R.id.dc3);
        if (character == 1) {
            dC1.setVisibility(View.VISIBLE);
        } else if (character == 2) {
            dC2.setVisibility(View.VISIBLE);
        } else {
            dC3.setVisibility(View.VISIBLE);
        }

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
    }

    protected void toggle(View v) {
        v.setEnabled(false);
        Log.d("success", "button should be disabled");
    }
    public void navigateToEndScreen(View v) {
        Intent i = new Intent(GameActivity.this, GameEndActivity.class);
        startActivity(i);
    }

}