package com.example.dungencrawler;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.containsString;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.dungencrawler.model.Leaderboard;
import com.example.dungencrawler.viewmodels.GameEndActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

public class LeaderboardUITest {
    @Rule
    public ActivityScenarioRule<GameEndActivity> activityScenarioRule
            = new ActivityScenarioRule<>(GameEndActivity.class);

    @Test
    public void testUIUpdate() {
        ArrayList<String> arr = Leaderboard.getLeaderboard();
        Leaderboard.updateLeaderboard("Jess", 556);
        Leaderboard.updateLeaderboard("Edison", 843);
        Leaderboard.updateLeaderboard("Jonathan", 332);
        Leaderboard.updateLeaderboard("Justin", 284);
        Leaderboard.updateLeaderboard("Achyutan", 844);
        String lbString = "";
        for (int i = 0; i < arr.size(); i++) {
            lbString = lbString + arr.get(i) + "\n";
        }
        // Check that the text was changed.
        onView(withId(R.id.lbvals)).check(matches(withText(containsString(lbString))));
    }
}
