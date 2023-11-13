package com.example.dungencrawler.Sprint4Tests;
import static org.junit.Assert.assertEquals;
import com.example.dungencrawler.model.PlayerMovementRight;
import com.example.dungencrawler.model.Player;
import org.junit.Test;

public class CharacterMovementTest {
    @Test
    public void testCharMovementRight() {
        Player player1 = new Player("Thomas", 1000, 0, 0);
        player1.setEntityStrategy(new PlayerMovementRight());
        player1.getEntityStrategy().execute(player1, 0, 0);
        assertEquals((float) 15.0, player1.getPlayerX(), 0.000001);
    }

    @Test
    public void testCharMovementDown() {
        Player player2 = new Player("Edison", 2000, 0, 0);
        player2.setEntityStrategy(new PlayerMovementRight());
        player2.getEntityStrategy().execute(player2, 0, 0);
        assertEquals((float) 15, player2.getPlayerY(), 0.000001);
    }
}
