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
import com.example.dungencrawler.model.Enemy1Creator;
import com.example.dungencrawler.model.Enemy2Creator;
import com.example.dungencrawler.model.EnemyCreator;
import com.example.dungencrawler.model.Observer;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PlayerMovementDown;
import com.example.dungencrawler.model.PlayerMovementLeft;
import com.example.dungencrawler.model.PlayerMovementRight;
import com.example.dungencrawler.model.PlayerMovementUp;

import java.util.Random;

public class Room1Activity extends AppCompatActivity {

    private int[] blocks = {
        R.drawable.lavatexture,
        R.drawable.cobblestonetexture,
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
    private Player player;
    private  PlayerView playerView;
    private Enemy enemy1;
    private EnemyCreator enemy1Creator;
    private EnemyView enemy1View;
    private Enemy enemy2;
    private EnemyCreator enemy2Creator;
    private EnemyView enemy2View;
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
        obs.setPlayer(player);

        player.setPlayerX((float) (widthOfScreen * 0.05));
        player.setPlayerY((float) (heightOfScreen * 0.05));

        // Want enemies appear randomly on the right half of the screen
        int randX1 = widthOfScreen / 4 + random.nextInt(widthOfScreen / 2);
        int randY1 = random.nextInt(heightOfScreen);
        int randX2 = widthOfScreen / 6 + random.nextInt(widthOfScreen / 2);
        int randY2 = random.nextInt(heightOfScreen);

        enemy1Creator = new Enemy1Creator();
        enemy1 = enemy1Creator.createEnemy(randX1, randY1, enemyAttackDamage);
        setRandomEnemyDirection(enemy1);
        enemy1View = new EnemyView(this, enemy1, R.drawable.enemy1);

        enemy2Creator = new Enemy2Creator();
        enemy2 = enemy2Creator.createEnemy(randX2, randY2, enemyAttackDamage);
        setRandomEnemyDirection(enemy2);
        enemy2View = new EnemyView(this, enemy2, R.drawable.enemy2);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = (int) player.getPlayerX();
        params.topMargin = (int) player.getPlayerY();
        gameLayout.addView(playerView, params);
        gameLayout.addView(enemy1View, params);
        gameLayout.addView(enemy2View, params);

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

                enemy1.enemyMove(difficulty, widthOfScreen, heightOfScreen);
                enemy2.enemyMove(difficulty, widthOfScreen, heightOfScreen);
                enemy1View.updateEnemyPosition(enemy1.getEnemyX(), enemy1.getEnemyY());
                enemy2View.updateEnemyPosition(enemy2.getEnemyX(), enemy2.getEnemyY());

                obs.enemyUpdate(enemy1);
                obs.enemyUpdate(enemy2);
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

    public void navigateToEndScreen(String name, int score, int character) {
        if (timer != null) {
            timer.cancel();
        }
        Intent i = new Intent(Room1Activity.this, Room2Activity.class);
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
        Intent i = new Intent(Room1Activity.this, GameEndActivity.class);
        i.putExtra("name", name);
        i.putExtra("score", score);
        i.putExtra("text", "you lose!");
        i.putExtra("difficulty", difficulty);
        System.out.println("This triggered! Game lost.");
        startActivity(i);
    }
    private void setRandomEnemyDirection(Enemy enemy) {
        float angle = (float) (Math.random() * 2 * Math.PI);
        enemy.setEnemyDx((float) Math.cos(angle) * enemyMovementSpeed);
        enemy.setEnemyDy((float) Math.sin(angle) * enemyMovementSpeed);
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