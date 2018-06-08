package logic.bonus;

import controller.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JackPotBonusTest {
    /*A game instance is used to test the trigger
     method of each bonus */
    Game gameTest;

    @Before
    public void setup(){
        gameTest = new Game();
    }

    @Test
    public void triggerTest(){
        assertEquals(0, gameTest.getCurrentScore());
        assertEquals(0 ,gameTest.getJackPotBonus().timesTriggered());
        gameTest.getJackPotBonus().trigger(gameTest);
        assertEquals(100000, gameTest.getCurrentScore());
        assertEquals(1, gameTest.getJackPotBonus().timesTriggered());

    }

}