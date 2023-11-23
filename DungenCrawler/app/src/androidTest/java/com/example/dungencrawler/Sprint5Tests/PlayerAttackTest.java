package com.example.dungencrawler.Sprint5Tests;
import static org.junit.Assert.assertEquals;
import com.example.dungencrawler.model.Player;
import com.example.dungencrawler.model.PlayerMovementRight;
import org.junit.Test;

public class PlayerAttackTest {
    @Test
    public void testPlayerAttack() {
        Player player1 = new Player("Thomas", 1000, 0, 0);
        player1.setAttackStatus(1);
        assertEquals(1, player1.getAttackStatus(), 1);
    }
}
