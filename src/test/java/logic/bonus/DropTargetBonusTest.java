package logic.bonus;

import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.table.Table;
import logic.table.TableClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DropTargetBonusTest {
    /*A game instance is used to test the trigger
     method of each bonus */
    Game gameTest;
    /*A table is needed to check if the bumpers are
    geting upgraded */
    Table tableTest;

    @Before
    public void setup(){
        gameTest = new Game();
        tableTest = new TableClass("test", 2, 1, 0, 0);
        gameTest.setGameTable(tableTest);
    }

    @Test
    public void triggerTest(){
        assertEquals(0, gameTest.getCurrentScore());
        assertEquals(0 ,gameTest.getDropTargetBonus().timesTriggered());
        for (Bumper b:gameTest.getCurrentTable().getBumpers())
            assertFalse(b.isUpgraded());

        gameTest.getDropTargetBonus().trigger(gameTest);

        assertEquals(1000000, gameTest.getCurrentScore());
        assertEquals(1, gameTest.getDropTargetBonus().timesTriggered());
        for (Bumper b:gameTest.getCurrentTable().getBumpers())
            assertTrue(b.isUpgraded());

    }

}