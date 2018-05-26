package logic.gameelements.target;

import java.util.Observable;

public abstract class AbstractTarget extends Observable implements Target{
    private boolean active;
    private int score;

    public AbstractTarget(){
        active = true;
    }

    public void setScore(int value){
        this.score = value;
    }

    public void setInactive(){
        this.active = false;
    }

    public int hit() {
        this.setInactive();
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
