package logic.gameelements.bumper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PopBumperTest {
    private PopBumper bumperTest;

    @Before
    public void setup(){
        bumperTest = new PopBumper();
    }
    @Test
    public void upgradeAndDowngradeTest() {
        assertEquals(100, bumperTest.getScore());
        bumperTest.upgrade();
        assertTrue(bumperTest.isUpgraded());
        assertEquals(300, bumperTest.getScore());
        bumperTest.downgrade();
        assertFalse(bumperTest.isUpgraded());
        assertEquals(100, bumperTest.getScore());
    }

}