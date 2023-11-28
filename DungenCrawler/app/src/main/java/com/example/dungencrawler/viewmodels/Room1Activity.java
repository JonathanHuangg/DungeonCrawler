package com.example.dungencrawler.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.dungencrawler.model.PowerUp;
import com.example.dungencrawler.model.PowerUpSlashAndDash;
import com.example.dungencrawler.model.Sword;
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
    private PlayerView playerView;
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
        obs.setPlayer(player);
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
        //Add enemies
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
        gameLayout.addView(swordView, params);
        // Add SlashAndDashPowerUp
        float powerUpX = 100;
        float powerUpY = 100;
        PowerUpSlashAndDash powerUpSlash = new PowerUpSlashAndDash(player, powerUpX, powerUpY);

        // Initialize the PowerUpView for PowerUpSlashAndDash
        PowerUpView powerUpSlashView = new PowerUpView(this,
                powerUpSlash, R.drawable.powerupslashdash,
                null, 0,  // Other powerups don't exist here
                null, 0);

        // Add the PowerUpView to your game layout
        RelativeLayout.LayoutParams powerUpParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        powerUpParams.leftMargin = (int) powerUpX;
        powerUpParams.topMargin = (int) powerUpY;
        gameLayout.addView(powerUpSlashView, powerUpParams);

        createBoard();

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
                if (!enemy1.isDead() && playerEnemyCollideAttack(enemy1, player)) {
                    gameLayout.removeView(enemy1View);
                    enemy1.kill();
                    score+=100;
                }
                if (!enemy2.isDead() && playerEnemyCollideAttack(enemy2, player)) {
                    gameLayout.removeView(enemy2View);
                    enemy2.kill();
                    score+=100;
                }
                if (playerPowerUpCollide(player, powerUpSlash, 100, 100, 100, 100)) {
                    // Start the dash if not already dashing
                    powerUpSlash.startDash();
                    gameLayout.removeView(powerUpSlashView);
                }

                // Update the dash effect
                powerUpSlash.updateDash();

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
                    endGame(username, 0);
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
        startActivity(i);
    }
    private void setRandomEnemyDirection(Enemy enemy) {
        float angle = (float) (Math.random() * 2 * Math.PI);
        enemy.setEnemyDx((float) Math.cos(angle) * enemyMovementSpeed);
        enemy.setEnemyDy((float) Math.sin(angle) * enemyMovementSpeed);
    }

    private boolean playerEnemyCollideAttack(Enemy enemy, Player player) {
        if (Math.abs(enemy.getEnemyY() - player.getPlayerY()) < 100
            && Math.abs(enemy.getEnemyX() - player.getPlayerX())
                < 100 && player.getAttackStatus() == 1) {
            return true;
        }
        return false;
    }

    private boolean playerPowerUpCollide(Player player, PowerUp powerUp, int playerWidth,
                                         int playerHeight, int powerUpWidth, int powerUpHeight) {
        // Calculate the center positions of the player and the power-up
        float playerCenterX = player.getPlayerX() + playerWidth / 2.0f;
        float playerCenterY = player.getPlayerY() + playerHeight / 2.0f;
        float powerUpCenterX = powerUp.getX() + powerUpWidth / 2.0f;
        float powerUpCenterY = powerUp.getY() + powerUpHeight / 2.0f;

        // Define a threshold for collision, adjust as necessary
        final int collisionThreshold = 25;

        if (Math.abs(powerUpCenterY - playerCenterY) < collisionThreshold
                && Math.abs(powerUpCenterX - playerCenterX) < collisionThreshold) {
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
            break;
        default:
            break;
        }
        player.getEntityStrategy().execute(player, heightOfScreen, widthOfScreen);
        playerView.updatePlayerPosition(player.getPlayerX(), player.getPlayerY());
        if (playerView.getPlayerPosition() > 2100) {
            navigateToEndScreen(username, score + additionalScore, character);
        }
        return true;
    }
}