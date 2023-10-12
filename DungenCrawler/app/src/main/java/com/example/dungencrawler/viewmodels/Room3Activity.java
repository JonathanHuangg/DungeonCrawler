package com.example.dungencrawler.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungencrawler.R;
import com.example.dungencrawler.map.MapLayout;
import com.example.dungencrawler.map.Tilemap;
import com.example.dungencrawler.model.GameConfig;

public class Room3Activity extends AppCompatActivity {

    int [] blocks = {
            R.drawable.lavatexture,
            R.drawable.cobblestonetexture,
            R.drawable.leaftexture,
            R.drawable.goldtexture,
            R.drawable.grasstexture
    };
    private CountDownTimer timer;
    int widthOfBlock, noOfBlocks = 50, widthOfScreen, heightOfScreen;
    String username;
    int score;

    int additionalScore = 0;

    int time;
    private Tilemap tilemap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3_view); // Set the layout here
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen = displayMetrics.widthPixels;
        heightOfScreen = displayMetrics.heightPixels;

        widthOfBlock = widthOfScreen / noOfBlocks;
        createBoard();

        // Initialize the tilemap from the layout
        tilemap = findViewById(R.id.tilemap);

        // If you need to set some properties or listeners on tilemap, you can do it here. For example:
        // tilemap.setSomeProperty(value);
        Bundle extras = getIntent().getExtras();
        Button goToEndScreenButton = findViewById(R.id.goToEndScreenButton);
        if (extras != null && extras.getString("name") != null) {
            username = extras.getString("name");
            score = extras.getInt("score");
            time = extras.getInt("time");
        }
        additionalScore = time;
        goToEndScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButton(goToEndScreenButton);
                navigateToEndScreen(username, score + additionalScore);
            }

        });
        TextView countdownTimer = findViewById(R.id.countdownTimer);
        countdownTimer.setX(widthOfScreen/2);
        countdownTimer.setY(heightOfScreen/10);
        timer = new CountDownTimer(time * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) millisUntilFinished / 1000;
                countdownTimer.setText("Score: " + secondsLeft);
                additionalScore = secondsLeft;
            }

            public void onFinish() {
                navigateToEndScreen(username, score + 0);
            }

        }.start();

    }

    private void createBoard() {
        GridLayout gridLayout = findViewById(R.id.board);

        gridLayout.setRowCount(noOfBlocks*heightOfScreen/widthOfScreen);
        gridLayout.setColumnCount(noOfBlocks);

        gridLayout.getLayoutParams().width = widthOfScreen;
        gridLayout.getLayoutParams().height = heightOfScreen;

        for (int i = 0; i < gridLayout.getRowCount() * gridLayout.getColumnCount(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(widthOfBlock,widthOfBlock));
            imageView.setMaxHeight(widthOfBlock);
            imageView.setMaxWidth(widthOfBlock);
            int randomBlock = (int) Math.floor(Math.random() * blocks.length);
            imageView.setImageResource(blocks[randomBlock]);
            gridLayout.addView(imageView);
        }
    }
    private void toggleButton(View v) {
        v.setEnabled(false);
    }

    public void navigateToEndScreen(String name, int score) {
        if (timer != null) {
            timer.cancel();
        }
        Intent i = new Intent(Room3Activity.this, GameEndActivity.class);
        i.putExtra("name", name);
        i.putExtra("score", score);
        startActivity(i);
    }
}