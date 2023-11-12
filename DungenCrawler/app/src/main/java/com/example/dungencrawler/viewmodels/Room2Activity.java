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
import com.example.dungencrawler.model.Enemy3Creator;
import com.example.dungencrawler.model.Enemy4Creator;
import com.example.dungencrawler.model.EnemyCreator;
import com.example.dungencrawler.model.Observer;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PlayerMovementDown;
import com.example.dungencrawler.model.PlayerMovementLeft;
import com.example.dungencrawler.model.PlayerMovementRight;
import com.example.dungencrawler.model.PlayerMovementUp;

import java.util.Random;


public class Room2Activity extends AppCompatActivity {

    private int[] blocks = {
        R.drawable.leaftexture,
        R.drawable.grasstexture
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
    private RelativeLayout gameLayout;
    private int character;
    private PlayerView playerView;
    private Player player;
    private Enemy enemy3;
    private EnemyCreator enemy3Creator;
    private EnemyView enemy3View;
    private Enemy enemy4;
    private EnemyCreator enemy4Creator;
    private EnemyView enemy4View;
    private Difficulty difficulty;
    private float enemyAttackDamage;
    private float enemyMovementSpeed;
    private Random random = new Random();


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
        difficulty = (Difficulty) i.getSerializableExtra("difficulty");
        Observer obs = Observer.getObserver();


        // set enemy attack value and movement speed based on difficulty
        if (difficulty == Difficulty.easy) {
            enemyAttackDamage = 1;
            enemyMovementSpeed = 20;
        } else if (difficulty == Difficulty.medium) {
            enemyAttackDamage = 2;
            enemyMovementSpeed = 30;
        } else {
            // hard
            enemyAttackDamage = 5;
            enemyMovementSpeed = 50;
        }

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
        player = Player.getPlayer();
        playerView = new PlayerView(this, player, charId);

        player.setPlayerX((float) (widthOfScreen * 0.05));
        player.setPlayerY((float) (heightOfScreen * 0.05));

        // Want enemies appear randomly on the right half of the screen
        int randX1 = widthOfScreen / 2 + random.nextInt(widthOfScreen / 2);
        int randY1 = random.nextInt(heightOfScreen);
        int randX2 = widthOfScreen / 2 + random.nextInt(widthOfScreen / 2);
        int randY2 = random.nextInt(heightOfScreen);

        enemy3Creator = new Enemy3Creator();
        enemy3 = enemy3Creator.createEnemy(randX1, randY1, enemyAttackDamage);
        setRandomEnemyDirection(enemy3);
        enemy3View = new EnemyView(this, enemy3, R.drawable.enemy3);

        enemy4Creator = new Enemy4Creator();
        enemy4 = enemy4Creator.createEnemy(randX2, randY2, enemyAttackDamage);
        setRandomEnemyDirection(enemy4);
        enemy4View = new EnemyView(this, enemy4, R.drawable.enemy4);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = (int) player.getPlayerX();
        params.topMargin = (int) player.getPlayerY();
        gameLayout.addView(playerView, params);
        gameLayout.addView(enemy3View, params);
        gameLayout.addView(enemy4View, params);

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
        timer = new CountDownTimer(time * 1000, 10) {
            public void onTick(long millisUntilFinished) {

                enemy3.enemyMove(difficulty, widthOfScreen, heightOfScreen);
                enemy4.enemyMove(difficulty, widthOfScreen, heightOfScreen);
                enemy3View.updateEnemyPosition(enemy3.getEnemyX(), enemy3.getEnemyY());
                enemy4View.updateEnemyPosition(enemy4.getEnemyX(), enemy4.getEnemyY());

                obs.enemyUpdate(enemy3);
                obs.enemyUpdate(enemy4);
                if (player.getHealth() == 0) {
                    endGame(username, 0);
                }
                int secondsLeft = (int) millisUntilFinished / 1000;
                countdownTimer.setText("Score: " + secondsLeft + "\nPlayer Location:"
                        + playerView.getPlayerPosition() + "\nPlayer Health:"
                        + player.getHealth());
                additionalScore = secondsLeft;
            }

            public void onFinish() {
                if (player.getHealth() > 0) {
                    navigateToEndScreen(username, score + 0, character);
                }
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
    private void setRandomEnemyDirection(Enemy enemy) {
        float angle = (float) (Math.random() * 2 * Math.PI);
        enemy.setEnemyDx((float) Math.cos(angle) * enemyMovementSpeed);
        enemy.setEnemyDy((float) Math.sin(angle) * enemyMovementSpeed);
    }
    public void navigateToEndScreen(String name, int score, int character) {
        if (timer != null) {
            timer.cancel();
        }
        Intent i = new Intent(Room2Activity.this, Room3Activity.class);
        i.putExtra("name", name);
        i.putExtra("score", score);
        i.putExtra("time", time);
        i.putExtra("character", character);
        i.putExtra("difficulty", difficulty);
        startActivity(i);
    }
    public void endGame(String name, int score) {
        if (timer != null) {
            timer.cancel();
        }
        Intent i = new Intent(Room2Activity.this, GameEndActivity.class);
        i.putExtra("name", name);
        i.putExtra("score", score);
        i.putExtra("text", "you lose!");
        i.putExtra("difficulty", difficulty);
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
        if (playerView.getPlayerPosition() > 2180) {
            navigateToEndScreen(username, score + additionalScore, character);
        }

        return true;
    }
}