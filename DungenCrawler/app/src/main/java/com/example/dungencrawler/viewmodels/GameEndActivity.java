package com.example.dungencrawler.viewmodels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dungencrawler.R;
import com.example.dungencrawler.model.Leaderboard;
import com.example.dungencrawler.model.Player;

import java.util.ArrayList;
import java.util.Date;

public class GameEndActivity extends AppCompatActivity {
    private Button newGamebutton;
    private TextView lb;

    private TextView lbvals;

    private TextView recentScore;

    private Player player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end_view);
        recentScore = findViewById(R.id.recentScore);
        Bundle extras = getIntent().getExtras();
        int score = 0;
        if (extras != null && extras.getString("name") != null) {
            String userName = extras.getString("name");
            score = extras.getInt("score");
            updateLeaderboard(userName, score);
            recentScore.setText("Last Attempt By: " + userName + ", " + score);
            recentScore.setVisibility(View.VISIBLE);
            player = new Player(userName, 1, 0, 0);
            player.setPlayerWinResult(score);
            if (player.getPlayerWinResult()) {
                showWinScreen();
            } else {
                showLoseScreen();
            }
        }
        newGamebutton = findViewById(R.id.newGameButton);
        lb = findViewById(R.id.leaderboard);
        lbvals = findViewById(R.id.lbvals);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        this.setNewGameButtonSize(width, height);
        this.setLbTitleSize(width, height);
        this.setLbValsUI(width, height);
        this.setRecentScore(width, height);
        newGamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }

        });
    }

    /**
     * Sprint 3 implementation to show winning screen, after user wins
     * System pause for 5 seconds, before disabling win screen to show leaderboard/scores
     */
    private void showWinScreen() {
        View win_icon = findViewById(R.id.JL_winIcon);
        win_icon.setVisibility(View.VISIBLE);
        View lose_icon = findViewById(R.id.JL_loseIcon);
        lose_icon.setVisibility(View.INVISIBLE);
    }

    /**
     * Sprint 3 implementation to show loss screen, after user loses
     * System pause for 5 seconds, before disabling win screen to show leaderboard/scores
     */
    private void showLoseScreen() {
        View win_icon = findViewById(R.id.JL_winIcon);
        win_icon.setVisibility(View.INVISIBLE);
        View lose_icon = findViewById(R.id.JL_loseIcon);
        lose_icon.setVisibility(View.VISIBLE);
    }


    private void setRecentScore(int width, int height) {
        recentScore.setTextSize((float) (recentScore.getTextSize()
                * ((width * height) / (2280 * 1014)) / 2.3));
        recentScore.setX(0);
        recentScore.setY((float) (height * 0.8) - 72 * (height / 1014));
    }

    private void setNewGameButtonSize(int width, int height) {
        newGamebutton.setWidth(240 * (width / 2280));
        newGamebutton.setHeight(72 * (height / 1014));
        newGamebutton.setX(((float) width) / 2 - 270 * (width / 2280));
        newGamebutton.setY((float) (height * 0.8) - 72 * (height / 1014));
        newGamebutton.setTextSize((float) (newGamebutton.getTextSize() * (width / 2280) / 2.75));
    }

    private void setLbTitleSize(int width, int height) {
        lb.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        lb.setTextSize((float) (lb.getTextSize() * ((width * height) / (2280 * 1014)) / 2.3));
    }

    private void setLbValsUI(int width, int height) {
        lbvals.setTextSize((float) (lbvals.getTextSize()
                * ((width * height) / (2280 * 1014)) / 2.3));
        String currentDateTimeString =
                java.text.DateFormat.getDateTimeInstance().format(new Date());
        ArrayList<String> arr = Leaderboard.getLeaderboard();
        String lbString = "";
        for (int i = 0; i < arr.size(); i++) {
            lbString = lbString + arr.get(i) + " " + currentDateTimeString + "\n";
        }
        lbvals.setGravity(Gravity.CENTER_HORIZONTAL);
        lbvals.setY((float) (height / 4.8));
        lbvals.setText(lbString);
    }

    private void restartGame() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    private void updateLeaderboard(String name, int score) {
        Leaderboard.updateLeaderboard(name, score);
    }

    private void generateSampleValues() {
        Leaderboard.updateLeaderboard("Jess", 556);
        Leaderboard.updateLeaderboard("Edison", 843);
        Leaderboard.updateLeaderboard("Jonathan", 332);
        Leaderboard.updateLeaderboard("Justin", 284);
        Leaderboard.updateLeaderboard("Achyutan", 844);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}