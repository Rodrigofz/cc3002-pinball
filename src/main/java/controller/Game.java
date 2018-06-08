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


    public Game(){
        extraBallBonus = new ExtraBallBonus();
        dropTargetBonus = new DropTargetBonus();
        jackPotBonus = new JackPotBonus();
        balls = 3;
    }

    public List<Bumper> getBumpers(){
        return table.getBumpers();
    }

    public List<Target> getTargets(){
        return table.getTargets();
    }

    public String getTableName(){
        return table.getTableName();
    }

    public int getAvailableBalls(){
        return balls;
    }

    public int dropBall(){
        balls --;
        getCurrentTable().updatePlayable(balls);
        return getAvailableBalls();
    }

    public void addBall(){
        balls ++;
    }

    public int getCurrentScore(){
        return score;
    }

    public void addScore(int value){
        this.score += value;
    }

    public Bonus getExtraBallBonus(){
        return extraBallBonus;
    }

    public Bonus getDropTargetBonus(){
        return dropTargetBonus;
    }

    public Bonus getJackPotBonus(){
        return jackPotBonus;
    }

    public Table getCurrentTable(){
        return table;
    }

    public void setGameTable(Table newTable){
        table = newTable;
        ((TableClass) table).addObserver(this);
    }

    public boolean gameOver(){
        return getAvailableBalls() == 0;
    }

    @Override
    public void update(Observable o, Object arg) {
        ((Visitor) arg).visitGame(this, (Table) o);
    }
}
