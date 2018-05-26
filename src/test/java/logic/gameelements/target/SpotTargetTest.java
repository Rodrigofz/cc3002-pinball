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


}