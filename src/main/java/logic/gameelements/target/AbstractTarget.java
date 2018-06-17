package logic.gameelements.target;

import visitor.ScoreVisitor;

import java.util.Observable;
import java.util.Observer;

/**
 * Abstract class that represents the targets. There can be two types of them, DropTarget or SpotTarget
 */
public abstract class AbstractTarget extends Observable implements Target{
    private boolean active;
    private int score;

    /**
     * Constructor of the targets. Sets the active variable as true
     */
    public AbstractTarget(){
        active = true;
    }

    /**
     * Sets the score of the target as a received value
     *
     * @param value     Value to be setted as the score of the target
     */
    public void setScore(int value) {
        this.score = value;
    }

    /**
     * Sets the target as inactive
     */
    public void setInactive(){
        this.active = false;
    }

    /**
     * Hits the target: Set it as inactive and notifies the hit to the observers. Finally returns the score of the target
     *
     * @return variable score
     */
    public int hit() {
        this.setInactive();
        this.setChanged();
        notifyObservers(new ScoreVisitor());
        return score;
    }

    /**
     * Gets wether the target is active or not
     *
     * @return boolean variable active
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * Resets the target, this is, setting the target as active
     */
    @Override
    public void reset() {
        this.active = true;
    }

    /**
     * Gets the score of the target
     *
     * @return variable score
     */
    public int getScore(){
        return score;
    }

}
