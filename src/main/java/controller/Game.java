package controller;

import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.Table;
import logic.table.TableClass;

import java.util.List;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game {
    private List<Table>  tables;
    private Table activeTable;
    private Bonus extraBallBonus;
    private Bonus dropTargetBonus;
    private Bonus jackPotBonus;
    private int balls;

    public void Game(){
        extraBallBonus = new ExtraBallBonus();
        dropTargetBonus = new DropTargetBonus();
        jackPotBonus = new JackPotBonus();
        balls = 0;
    }

    public void newPlayableTableWithNoTargets(String name, int numberOfBumpers, double prob){
        tables.add(new TableClass(name, numberOfBumpers, prob, 0, 0));
    }

    public void newFullPlayableTable(String name, int numberOfBumpers, double prob, int numberOfSpotTargets, int numberOfDropTargets){
        tables.add(new TableClass(name, numberOfBumpers, prob, numberOfSpotTargets, numberOfDropTargets));
    }

    public List<Bumper> getBumpers(){
        return activeTable.getBumpers();
    }

    public List<Target> getTargets(){
        return activeTable.getTargets();
    }

    public String getTableName(){
        return activeTable.getTableName();
    }

    public int getAvailableBalls(){
        return balls;
    }

    public Bonus getExtraBallBonus(){
        return extraBallBonus;
    }

    public Table getCurrentTable(){
        return activeTable;
    }

    public void setGameTable(Table newTable){
        activeTable = newTable;
    }

    public int dropBall(){
        balls --;
        return getAvailableBalls();
    }

    public boolean gameOver(){
        return getAvailableBalls() == 0;
    }

    public Bonus getDropTargetBonus(){
        return dropTargetBonus;
    }

    public Bonus getJackPotBonus(){
        return jackPotBonus;
    }



}
