package controller;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.Table;
import logic.table.TableClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private Game gameTest;
    private Table tableTest;

    @Before
    public void setup() {
        gameTest = new Game();
        tableTest = new TableClass("TableTest", 3, 0.5, 2, 2, 3);
        gameTest.setGameTable(tableTest);
    }

    @Test
    public void getBumpersTest() {
        assertEquals(3, gameTest.getBumpers().size());
    }

    @Test
    public void getTargetsTest() {
        assertEquals(4, gameTest.getTargets().size());
    }

    @Test
    public void getTableNameTest() {
        assertEquals("TableTest", gameTest.getTableName());
    }

    @Test
    public void ballsTest() {
        assertEquals(3, gameTest.getAvailableBalls());
        gameTest.dropBall();
        assertEquals(2, gameTest.getAvailableBalls());
        gameTest.addBall();
        assertEquals(3, gameTest.getAvailableBalls());
    }

    @Test
    public void addScoreTest() {
        assertEquals(0, gameTest.getCurrentScore());
        gameTest.addScore(1000);
        assertEquals(1000, gameTest.getCurrentScore());
    }

    @Test
    public void extraBallBonusTest(){
        assertEquals(3, gameTest.getAvailableBalls());
        gameTest.getExtraBallBonus().trigger(gameTest);
        assertEquals(4, gameTest.getAvailableBalls());
    }

    @Test
    public void getTableTest(){
        assertEquals(tableTest, gameTest.getCurrentTable());
    }

    @Test
    public void gameOverTest(){
        assertFalse(gameTest.gameOver());
        gameTest.dropBall();
        gameTest.dropBall();
        gameTest.dropBall();
        assertTrue(gameTest.gameOver());
    }

    //Checking differents hittables being hitted
    @Test
    public void hitBumpersTest(){
        assertEquals(0, gameTest.getCurrentScore());
        for (Bumper b: gameTest.getCurrentTable().getBumpers())
            b.hit();
        //With the seed used, there will be 1 kicker bumper and 2 pop bumpers (500 + 100 + 100)
        assertEquals(700, gameTest.getCurrentScore());
    }

    /*
    Checks that:
    -the targets give correct score
    -spot targets triggers jackpot bonus correctly
    -drop targets triggers droptarget bonus correctly
     */
    @Test
    public void hitTargetsTest(){
        assertEquals(0, gameTest.getJackPotBonus().timesTriggered());
        assertEquals(0, gameTest.getDropTargetBonus().timesTriggered());

        assertEquals(0, gameTest.getCurrentScore());

        for (Target t: gameTest.getCurrentTable().getTargets())
            t.hit();

        assertEquals(2, gameTest.getJackPotBonus().timesTriggered());
        assertEquals(1, gameTest.getDropTargetBonus().timesTriggered());

        //2 spot targets -> 200000
        //2 drop targets -> 200
        //all drop targets -> 1000000
        assertEquals(1200200, gameTest.getCurrentScore());
    }

    //Pseudo random tested
    public void upgradeBumperBonusTest(){
        assertEquals(0, gameTest.getExtraBallBonus().timesTriggered());
        /*
        Thanks to the seed in the bumper, we know that the first one is a PopBumper
        we also know that it should trigger the bonus the first time it upgrades
         */

        gameTest.getCurrentTable().getBumpers().get(0).hit();
        gameTest.getCurrentTable().getBumpers().get(0).hit();
        gameTest.getCurrentTable().getBumpers().get(0).hit();

        assertEquals(1, gameTest.getExtraBallBonus().timesTriggered());
    }

    public void dropTargetHitBonusTest(){
        assertEquals(0, gameTest.getExtraBallBonus().timesTriggered());
        /*
        We know that the first target is a dropTarget. Thanks to the seed in the
        target class, we know that the first time it should trigger the bonus
         */
        gameTest.getCurrentTable().getTargets().get(0).hit();

        assertEquals(1, gameTest.getExtraBallBonus().timesTriggered());
    }


}