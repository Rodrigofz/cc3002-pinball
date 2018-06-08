package logic.gameelements.target;

import visitor.ScoreVisitor;

import java.util.Observable;
import java.util.Observer;

public abstract class AbstractTarget extends Observable implements Target{
    private boolean active;
    private int score;

    public AbstractTarget(){
        active = true;
    }

    public void setScore(int value) {
        this.score = value;
    }

    public void setInactive(){
        this.active = false;
    }

    public int hit() {
        this.setInactive();
        this.setChanged();
        notifyObservers(new ScoreVisitor());
        return score;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void reset() {
        this.active = true;
    }

    public int getScore(){
        return score;
    }

}
