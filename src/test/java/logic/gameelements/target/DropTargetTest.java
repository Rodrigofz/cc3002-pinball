package logic.gameelements.target;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DropTargetTest {
    private DropTarget targetTest;

    @Before
    public void setup(){
        targetTest = new DropTarget();
    }

    @Test
    public void setScoreTest() {
        assertEquals(100, targetTest.getScore());
        targetTest.setScore(1000);
        assertEquals(1000, targetTest.getScore());
    }

    @Test
    public void setInactiveTest() {
        assertTrue(targetTest.isActive());
        targetTest.setInactive();
        assertFalse(targetTest.isActive());
    }

    @Test
    public void hitAndResetTest() {
        assertTrue(targetTest.isActive());
        targetTest.hit();
        assertFalse(targetTest.isActive());
        targetTest.reset();
        assertTrue(targetTest.isActive());
    }

}