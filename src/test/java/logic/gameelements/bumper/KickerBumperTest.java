package logic.gameelements.bumper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KickerBumperTest {
    private KickerBumper bumperTest;

    @Before
    public void setup(){
        bumperTest = new KickerBumper();
    }

    @Test
    public void upgradeAndDowngradeTest() {
        assertEquals(500, bumperTest.getScore());
        bumperTest.upgrade();
        assertTrue(bumperTest.isUpgraded());
        assertEquals(1000, bumperTest.getScore());
        bumperTest.downgrade();
        assertFalse(bumperTest.isUpgraded());
        assertEquals(500, bumperTest.getScore());
    }

    @Test
    public void hitsTest() {
        assertEquals(5, bumperTest.remainingHitsToUpgrade());
        bumperTest.setRemainingHits(4);
        assertEquals(4, bumperTest.remainingHitsToUpgrade());
        bumperTest.hit();
        assertEquals(3, bumperTest.remainingHitsToUpgrade());
    }


    @Test
    public void setScoreTest() {
        assertEquals(500, bumperTest.getScore());
        bumperTest.setScore(700);
        assertEquals(700, bumperTest.getScore());
    }
}