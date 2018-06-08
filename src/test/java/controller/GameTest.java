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
        tableTest = new TableClass("TableTest", 3, 0.5, 2, 2, 0.5);
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
        //With the seed 0.5, there will be 1 kicker bumper and 2 pop bumpers
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
        assertEquals(2, gameTest.getDropTargetBonus().timesTriggered());

        //2 spot targets -> 200000
        //2 drop targets -> 200
        //all drop targets -> 2000000
        assertEquals(2200200, gameTest.getCurrentScore());
    }



}