package com.example.dungencrawler.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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
import com.example.dungencrawler.model.Observer;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PlayerMovementDown;
import com.example.dungencrawler.model.PlayerMovementLeft;
import com.example.dungencrawler.model.PlayerMovementRight;
import com.example.dungencrawler.model.PlayerMovementUp;
import com.example.dungencrawler.model.PowerUp;
import com.example.dungencrawler.model.PowerUpHealth;
import com.example.dungencrawler.model.PowerUpInstaWin;
import com.example.dungencrawler.model.Sword;

import java.util.Random;

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
    private float enemyMovementSpeed;
    private Random random = new Random();
    private SwordView swordView;
    private Sword sword;

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

        player.setPlayerX((float) (widthOfScreen * 0.03));
        player.setPlayerY((float) (heightOfScreen * 0.03));
        sword = new Sword(0, 0);
        swordView = new SwordView(this, sword, R.drawable.sword);
        swordView.setVisibility(View.INVISIBLE);

        // Want enemies appear randomly on the right half of the screen
        int randX1 = widthOfScreen / 4 + random.nextInt(widthOfScreen / 2);
        int randY1 = random.nextInt(heightOfScreen);
        int randX2 = widthOfScreen / 6 + random.nextInt(widthOfScreen / 2);
        int randY2 = random.nextInt(heightOfScreen);

        enemy2Creator = new Enemy2Creator();
        enemy2 = enemy2Creator.createEnemy(randX1, randY1, enemyAttackDamage);
        setRandomEnemyDirection(enemy2);
        enemy2View = new EnemyView(this, enemy2, R.drawable.enemy2);

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
        gameLayout.addView(enemy2View, params);
        gameLayout.addView(enemy4View, params);
        gameLayout.addView(swordView, params);

        // Add Health PowerUp

        float powerUpX = 500;
        float powerUpY = 100;
        PowerUpHealth powerUpHealth = new PowerUpHealth(player, powerUpX, powerUpY);

        // Initialize the PowerUpView for Health
        PowerUpView powerUpHealthView = new PowerUpView(this,
                null, 0,
                null, 0,  // Other powerups don't exist here
                powerUpHealth, R.drawable.poweruphealth);

        // Add the PowerUpView to your game layout
        RelativeLayout.LayoutParams powerUpParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        powerUpParams.leftMargin = (int) powerUpX;
        powerUpParams.topMargin = (int) powerUpY;
        gameLayout.addView(powerUpHealthView, powerUpParams);

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

                enemy2.enemyMove(difficulty, widthOfScreen, heightOfScreen);
                enemy4.enemyMove(difficulty, widthOfScreen, heightOfScreen);
                enemy2View.updateEnemyPosition(enemy2.getEnemyX(), enemy2.getEnemyY());
                enemy4View.updateEnemyPosition(enemy4.getEnemyX(), enemy4.getEnemyY());
                if (playerEnemyCollideAttack(enemy2, player)) {
                    gameLayout.removeView(enemy2View);
                }
                if (playerEnemyCollideAttack(enemy4, player)) {
                    gameLayout.removeView(enemy4View);
                }

                if (playerPowerUpCollide(player, powerUpHealth, 100, 100, 100, 100)) {
                    // redo health
                    System.out.println("collide");
                    powerUpHealth.setHealth(300);
                }

                obs.enemyUpdate(enemy2);
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
            private String text = "you lose!";
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
    private void setRandomEnemyDirection(Enemy enemy) {
        float angle = (float) (Math.random() * 2 * Math.PI);
        enemy.setEnemyDx((float) Math.cos(angle) * enemyMovementSpeed);
        enemy.setEnemyDy((float) Math.sin(angle) * enemyMovementSpeed);
    }
    public void navigateToEndScreen(String name, int score, String text) {
        if (timer != null) {
            timer.cancel();
        }
        Intent i = new Intent(Room3Activity.this, GameEndActivity.class);
        i.putExtra("name", name);
        i.putExtra("score", score);
        i.putExtra("text", text);
        i.putExtra("difficulty", difficulty);
        startActivity(i);
    }

    private boolean playerEnemyCollideAttack(Enemy enemy, Player player) {
        if (Math.abs(enemy.getEnemyY() - player.getPlayerY()) < 100
                && Math.abs(enemy.getEnemyX() - player.getPlayerX()) < 100 && player.getAttackStatus() == 1) {
            return true;
        }
        return false;
    }

    private boolean playerPowerUpCollide(Player player, PowerUp powerUp, int playerWidth, int playerHeight, int powerUpWidth, int powerUpHeight) {
        // Calculate the center positions of the player and the power-up
        float playerCenterX = player.getPlayerX() + playerWidth / 2.0f;
        float playerCenterY = player.getPlayerY() + playerHeight / 2.0f;
        float powerUpCenterX = powerUp.getX() + powerUpWidth / 2.0f;
        float powerUpCenterY = powerUp.getY() + powerUpHeight / 2.0f;

        // Define a threshold for collision, adjust as necessary
        final int COLLISION_THRESHOLD = 25;

        if (Math.abs(powerUpCenterY - playerCenterY) < COLLISION_THRESHOLD
                && Math.abs(powerUpCenterX - playerCenterX) < COLLISION_THRESHOLD) {
            return true;
        }
        return false;
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
        case KeyEvent.KEYCODE_SPACE:
            swordView.setX(player.getPlayerX() + 120);
            swordView.setY(player.getPlayerY() + 50);
            swordView.setVisibility(View.VISIBLE);
            player.setAttackStatus(1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    swordView.setVisibility(View.INVISIBLE);
                    player.setAttackStatus(0);
                }
            }, 100);
        default:
            break;
        }
        player.getEntityStrategy().execute(player, heightOfScreen, widthOfScreen);
        playerView.updatePlayerPosition(player.getPlayerX(), player.getPlayerY());
        // checkCollisions();
        String text = "you win!";
        if (playerView.getPlayerPosition() > 2100) {
            navigateToEndScreen(username, score + additionalScore, text);
        }

        return true;
    }
    public void endGame(String name, int score) {
        if (timer != null) {
            timer.cancel();
        }
        Intent i = new Intent(Room3Activity.this, GameEndActivity.class);
        i.putExtra("name", name);
        i.putExtra("score", score);
        i.putExtra("text", "you lose!");
        i.putExtra("difficulty", difficulty);
        startActivity(i);
    }
}