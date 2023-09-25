package com.example.dungencrawler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.*;
import android.widget.*;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent i = getIntent();
        TextView playerName = findViewById(R.id.textView_playerName);
        TextView chosenDifficulty = findViewById(R.id.textView_ChosenDifficulty);
        TextView startingHealth = findViewById(R.id.textView_startingHealth);

        String userName = i.getStringExtra("userName");
        playerName.setText(userName);
        float difficulty = i.getFloatExtra("difficulty", 0.5f);
        int character = i.getIntExtra("character", 1);

        View DC1 = findViewById(R.id.dc1);
        View DC2 = findViewById(R.id.dc2);
        View DC3 = findViewById(R.id.dc3);
        if (character == 1) {
            DC1.setVisibility(View.VISIBLE);
        } else if (character == 2) {
            DC2.setVisibility(View.VISIBLE);
        } else {
            DC3.setVisibility(View.VISIBLE);
        }

        if (difficulty == 0.75f) {
            chosenDifficulty.setText("Medium Difficulty");
            startingHealth.setText("Health: 100");
        } else if (difficulty == 1f) {
            chosenDifficulty.setText("Hard Difficulty");
            startingHealth.setText("Health: 50");

        } else {
            chosenDifficulty.setText("Easy Difficulty");
            startingHealth.setText("Health: 200");
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