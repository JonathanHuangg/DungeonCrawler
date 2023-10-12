package com.example.dungencrawler.model;

import java.util.ArrayList;
//Keep this ready: PrintWriter wr =
// new PrintWriter("../../../../../res/leaderboard/leaderboard.txt");
public class Leaderboard {
    private static ArrayList<String> leaderboard;
    public static ArrayList<String> getLeaderboard() {
        if (leaderboard == null) {
            synchronized (Leaderboard.class) {
                if (leaderboard == null) {
                    leaderboard = new ArrayList<String>();
                }
            }
        }
        return leaderboard;
    }
    public static void updateLeaderboard(String name, int score) {
        ArrayList<String> lb = getLeaderboard();
        if (lb.size() == 0) {
            lb.add(0, name + " " + Integer.toString(score));
            return;
        }
        for (int i = 0; i < lb.size(); i++) {
            int sc = Integer.parseInt(lb.get(i).split(" ")[1]);
            if (score > sc) {
                lb.add(i, name + " " + score);
                if (lb.size() > 5) {
                    lb.remove(lb.size() - 1);
                }
                return;
            }
        }
        if (lb.size() < 5) {
            lb.add(lb.size(), name + " " + Integer.toString(score));
        }

    }

}
