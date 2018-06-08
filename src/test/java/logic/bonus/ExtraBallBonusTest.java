package logic.bonus;

import controller.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExtraBallBonusTest {
    /*A game instance is used to test the trigger
     method of each bonus */
    Game gameTest;

    @Before
    public void setup(){
        gameTest = new Game();
    }

    @Test
    public void triggerTest(){
        assertEquals(3, gameTest.getAvailableBalls());
        assertEquals(0, gameTest.getExtraBallBonus().timesTriggered());
        gameTest.getExtraBallBonus().trigger(gameTest);
        assertEquals(4, gameTest.getAvailableBalls());
        assertEquals(1, gameTest.getExtraBallBonus().timesTriggered());
    }

}