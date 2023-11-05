package com.example.dungencrawler.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungencrawler.R;
import com.example.dungencrawler.model.Difficulty;
import com.example.dungencrawler.model.Enemy;
import com.example.dungencrawler.model.Enemy2Creator;
import com.example.dungencrawler.model.Enemy4Creator;
import com.example.dungencrawler.model.EnemyCreator;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PlayerMovementDown;
import com.example.dungencrawler.model.PlayerMovementLeft;
import com.example.dungencrawler.model.PlayerMovementRight;
import com.example.dungencrawler.model.PlayerMovementUp;

public class Room3Activity extends AppCompatActivity {

    private int[] blocks = {
        R.drawable.cobblestonetexture,
        R.drawable.goldtexture,
    };
    private CountDownTimer timer;
    private int widthOfBlock;
    private int noOfBlocks = 50;
    private int widthOfScreen;
    private int heightOfScreen;
    private String username;
    private int score;
    private int additionalScore = 0;
    private int time;
    private int character;
    private PlayerView playerView;
    private Player player;
    private Enemy enemy2;
    private EnemyCreator enemy2Creator;
    private EnemyView enemy2View;
    private Enemy enemy4;
    private EnemyCreator enemy4Creator;
    private EnemyView enemy4View;
    private RelativeLayout gameLayout;
    private Difficulty difficulty;
    private int enemyAttackDamage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1_view); // Set the layout here

        Intent i = getIntent();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen = displayMetrics.widthPixels;
        heightOfScreen = displayMetrics.heightPixels;
        gameLayout = findViewById(R.id.gameLayOut);

        widthOfBlock = widthOfScreen / noOfBlocks;

        ImageView character1 = findViewById(R.id.character1);
        ImageView character2 = findViewById(R.id.character2);
        ImageView character3 = findViewById(R.id.character3);

        character1.setVisibility(View.INVISIBLE);
        character2.setVisibility(View.INVISIBLE);
        character3.setVisibility(View.INVISIBLE);

        character = i.getIntExtra("character", 1);
        int charId;
        if (character == 1) {
            charId = R.drawable.amongus1;
        } else if (character == 2) {
            charId = R.drawable.amongus2;
        } else {
            charId = R.drawable.amongus3;
        }

        //Initialize game objects (player, enemies, etc)
        player = new Player(username, 200, 0, 0);
        playerView = new PlayerView(this, player, charId);

        enemy2Creator = new Enemy2Creator();
        enemy2 = enemy2Creator.createEnemy(0, 0, enemyAttackDamage);
        enemy2View = new EnemyView(this, enemy2, R.drawable.enemy2);
        enemy4Creator = new Enemy4Creator();
        enemy4 = enemy4Creator.createEnemy(0, 50, enemyAttackDamage);
        enemy4View = new EnemyView(this, enemy4, R.drawable.enemy4);



        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = (int) player.getPlayerX();
        params.topMargin = (int) player.getPlayerY();
        gameLayout.addView(playerView, params);
        gameLayout.addView(enemy2View, params);
        gameLayout.addView(enemy4View, params);
        difficulty = (Difficulty) i.getSerializableExtra("difficulty");

        // set enemy attack value based on difficulty
        if (difficulty == Difficulty.easy) {
            enemyAttackDamage = 30;
        } else if (difficulty == Difficulty.medium) {
            enemyAttackDamage = 40;
        } else {
            // hard
            enemyAttackDamage = 50;
        }

        createBoard();

        // tilemap.setSomeProperty(value);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getString("name") != null) {
            username = extras.getString("name");
            score = extras.getInt("score");
            time = extras.getInt("time");
        }
        additionalScore = time;
        TextView countdownTimer = findViewById(R.id.countdownTimer);
        countdownTimer.setX(widthOfScreen / 2);
        countdownTimer.setY(heightOfScreen / 10);
        timer = new CountDownTimer(time * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) millisUntilFinished / 1000;
                countdownTimer.setText("Score: " + secondsLeft);
                additionalScore = secondsLeft;
            }
            String text = "you lose!";
            public void onFinish() {
                navigateToEndScreen(username, score + 0, text);
            }

        }.start();

    }

    private void createBoard() {
        GridLayout gridLayout = findViewById(R.id.board);

        gridLayout.setRowCount(noOfBlocks * heightOfScreen / widthOfScreen);
        gridLayout.setColumnCount(noOfBlocks);

        gridLayout.getLayoutParams().width = widthOfScreen;
        gridLayout.getLayoutParams().height = heightOfScreen;

        for (int i = 0; i < gridLayout.getRowCount() * gridLayout.getColumnCount(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(widthOfBlock, widthOfBlock));
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

    public void navigateToEndScreen(String name, int score, String text) {
        if (timer != null) {
            timer.cancel();
        }
        Intent i = new Intent(Room3Activity.this, GameEndActivity.class);
        i.putExtra("name", name);
        i.putExtra("score", score);
        i.putExtra("text", text);
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
        default:
            break;
        }
        player.getEntityStrategy().execute(player, heightOfScreen, widthOfScreen);
        playerView.updatePlayerPosition(player.getPlayerX(), player.getPlayerY());
        // checkCollisions();
        String text = "you win!";
        if (playerView.getPlayerPosition() > 2180) {
            navigateToEndScreen(username, score + additionalScore, text);
        }

        return true;
    }
}