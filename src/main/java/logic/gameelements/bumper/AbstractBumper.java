package logic.gameelements.bumper;
import visitor.ScoreVisitor;
import visitor.UpgradeVisitor;

import java.util.Observable;

public abstract class AbstractBumper extends Observable implements Bumper  {
    private int remainingHits;
    private int score;
    private boolean upgraded;

    //Constructor
    public AbstractBumper(){
        this.upgraded = false;
    }

    public void setRemainingHits(int value){
        this.remainingHits = value;
    }

    public boolean isUpgraded(){
        return upgraded;
    }

    public void upgrade(){
        this.upgraded = true;
        this.setChanged();
        notifyObservers(new UpgradeVisitor());
    }

    public void downgrade(){
        this.upgraded = false;
    }

    public int remainingHitsToUpgrade(){
        return remainingHits;
    }

    public int hit(){
        this.remainingHits--;
        if (remainingHitsToUpgrade() == 0){
            upgrade();
        }
        this.setChanged();
        notifyObservers(new ScoreVisitor());
        return getScore();
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int newScore){
        this.score = newScore;
    }

}
