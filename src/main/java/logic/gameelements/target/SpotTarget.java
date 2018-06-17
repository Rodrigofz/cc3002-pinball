package logic.gameelements.target;

import visitor.SpotTargetHittedVisitor;

/**
 * One of the two types of targets
 */
public class SpotTarget extends AbstractTarget {
    /**
     * Constructor of the class: Calls the constructor of the super class and sets the score to 0
     */
    public SpotTarget(){
        super();
        setScore(0);
    }

    /**
     * Hits the target: Notify the observers to update the score, calls the super class hit method, and finally
     * returns the score of the table
     *
     * @return score of the target
     */
    public int hit(){
        this.setChanged();
        notifyObservers(new SpotTargetHittedVisitor());
        super.hit();
        return getScore();
    }
}
