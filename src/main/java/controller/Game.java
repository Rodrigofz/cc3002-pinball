package controller;

import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;
import logic.table.Table;
import logic.table.TableClass;
import visitor.Visitor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */

public class Game implements Observer{

    private Table table;
    private Bonus extraBallBonus;
    private Bonus dropTargetBonus;
    private Bonus jackPotBonus;
    private int balls;
    private int score;

    /**
     * Constructor of the class, creates the bonuses and set the number of balls in 3
     */
    public Game(){
        table = new TableClass("Mesa inicial", 0, 1, 0,0);
        extraBallBonus = new ExtraBallBonus();
        dropTargetBonus = new DropTargetBonus();
        jackPotBonus = new JackPotBonus();
        balls = 3;
    }

    /**
     * Gets the list of bumpers of the currently active table
     *
     * @return bumpers variable of table
     */
    public List<Bumper> getBumpers(){
        return table.getBumpers();
    }

    /**
     * Gets the list of targets of the currently active table
     *
     * @return dropTargets and spotTargets of the currently active table combined
     */
    public List<Target> getTargets(){
        return table.getTargets();
    }

    /**
     * Gets the name of the currently active table
     *
     * @return name variable of table
     */
    public String getTableName(){
        return table.getTableName();
    }

    /**
     * Gets the number of the currently available balls in the game
     *
     * @return variable balls
     */
    public int getAvailableBalls(){
        return balls;
    }

    /**
     * Drops the number of avaliable balls in one, and returns the new avaliable balls
     *
     * @return variable balls
     */
    public int dropBall(){
        balls --;
        getCurrentTable().updatePlayable(balls);
        return getAvailableBalls();
    }

    /**
     * Adds one ball to the currently available balls
     */
    public void addBall(){
        balls ++;
    }

    /**
     * Gets the current score
     *
     * @return variable score
     */
    public int getCurrentScore(){
        return score;
    }

    /**
     * Adds a certain value to the current score
     *
     * @param value value added to the current score
     */
    public void addScore(int value){
        this.score += value;
    }

    /**
     * Gets the instance of ExtraBallBonus currently in the game
     *
     * @return ExtraBallBonus instance
     */
    public Bonus getExtraBallBonus(){
        return extraBallBonus;
    }

    /**
     * Gets the instance of DropTargetBonus currently in the game
     *
     * @return DropTargetBonus instance
     */
    public Bonus getDropTargetBonus(){
        return dropTargetBonus;
    }

    /**
     * Gets the instance of JackPotBonus currently in the game
     *
     * @return JackPotBonus instance
     */
    public Bonus getJackPotBonus(){
        return jackPotBonus;
    }

    /**
     * Gets the instance of Table currently active in the game
     *
     * @return Table instance
     */
    public Table getCurrentTable(){
        return table;
    }

    /**
     * Sets a certain table as the currently active in the game
     *
     * @param newTable   Table to be set as active
     */
    public void setGameTable(Table newTable){
        table = newTable;
        ((TableClass) table).addObserver(this);
    }

    /**
     * Gets wether the game if over or not, it depends on the number of avaliable balls
     *
     * @return true if there are not avaliable balls, false if there are
     */
    public boolean gameOver(){
        return getAvailableBalls() == 0;
    }

    /**
     * Triggers a visitor to visit the game
     * @param o     Observable that is notifying the game
     * @param arg   Object argument, always used as a visitor that visits the game
     */
    @Override
    public void update(Observable o, Object arg) {
        ((Visitor) arg).visitGame(this, (Table) o);
    }
}
