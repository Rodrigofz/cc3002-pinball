package logic.gameelements.bumper;
import logic.table.Table;
import logic.table.TableClass;

import java.util.Observable;
import java.util.Observer;

public abstract class AbstractBumper extends Observable implements Bumper  {
    private int remainingHits;
    private int score;
    private boolean upgraded;

    //Constructor
    public AbstractBumper(TableClass table){
        this.upgraded = false;
        addObserver(table);
    }

    public void setRemainingHits(int value){
        this.remainingHits = value;
    }

    public boolean isUpgraded(){
        return upgraded;
    }

    public void upgrade(){
        this.upgraded = true;
    }

    public void downgrade(){
        this.upgraded = false;
    }

    public int remainingHitsToUpgrade(){
        return remainingHits;
    }

    public int hit(){
        this.remainingHits--;
        notifyObservers();
        return getScore();
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int newScore){
        this.score = newScore;
    }

}
