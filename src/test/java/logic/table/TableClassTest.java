package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.Target;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TableClassTest {
    private TableClass tableTest1;
    private TableClass tableTest2;

    @Before
    public void setup(){
        tableTest1 = new TableClass("TableTest", 1, 1, 1, 0);
        tableTest2 = new TableClass("TableTest", 4,0.4,3,3);
    }


    @Test
    public void scoreTest(){
        assertEquals(0, tableTest1.getScore());
        assertEquals(100, tableTest1.getBumpers().get(0).getScore());
        tableTest1.getBumpers().get(0).hit();
        assertEquals(100, tableTest1.getScore());
    }

    @Test
    public void getNameTest(){
        assertEquals("TableTest", tableTest1.getTableName());
    }

    @Test
    public void numberOfDropTargetsTest(){
        assertEquals(0, tableTest1.getNumberOfDropTargets());
        assertEquals(3, tableTest2.getNumberOfDropTargets());
    }

    @Test
    public void currentlyDroppedDropTargetsTest(){
        assertEquals(0, tableTest1.getCurrentlyDroppedDropTargets());
        tableTest1.setCurrentlyDroppedDropTargets(1);
        assertEquals(1, tableTest1.getCurrentlyDroppedDropTargets());
        tableTest1.setCurrentlyDroppedDropTargets(tableTest1.getCurrentlyDroppedDropTargets() + 2);
        assertEquals(3, tableTest1.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void getBumpersAndTargetsTest(){
        assertEquals(4, tableTest2.getBumpers().size());
        assertEquals(6, tableTest2.getTargets().size());
    }

    @Test
    public void resetDropTargetsTest(){
        for (Target t: tableTest2.getDropTargets()){
            assertTrue(t.isActive());
            t.hit();
            assertFalse(t.isActive());
        }
        tableTest2.resetDropTargets();
        for (Target t: tableTest2.getTargets()){
            assertTrue(t.isActive());
        }
    }

    @Test
    public void playableTest(){
        assertTrue(tableTest1.isPlayableTable());
        tableTest1.updatePlayable(0);
        assertFalse(tableTest1.isPlayableTable());
    }

    //Used to test the notifySpotTarget method
    @Test
    public void hitSpotTargetTest(){
        tableTest1.getTargets().get(0).hit();
    }

    @Test
    public void upgradeAllBumpersTest(){
        for (Bumper b: tableTest2.getBumpers())
            assertFalse(b.isUpgraded());
        tableTest2.upgradeAllBumpers();
        for (Bumper b: tableTest2.getBumpers())
            assertTrue(b.isUpgraded());
    }

}