package com.example.dungencrawler.viewmodels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dungencrawler.R;

public class WelcomeActivity extends AppCompatActivity {
    private Button button;
    private Button extButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_view);

        button = (Button) findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConfigurationScreen();
            }
        });

        extButton = (Button) findViewById(R.id.exitButton);

        extButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void openConfigurationScreen() {
        //this may need to change based on screen
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}