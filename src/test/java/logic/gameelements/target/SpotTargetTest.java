package logic.gameelements.target;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpotTargetTest {

    private SpotTarget targetTest;

    @Before
    public void setup(){
        targetTest = new SpotTarget();
    }

    @Test
    public void scoreTest() {
        assertEquals(0, targetTest.getScore());
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