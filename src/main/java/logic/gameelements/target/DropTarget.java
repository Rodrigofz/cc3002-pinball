package logic.gameelements.target;

import visitor.DropTargetHittedVisitor;
import visitor.DropTargetResetVisitor;
import visitor.ExtraBallBonusVisitor;

import java.util.Random;

/**
 * One of the two types of targets
 */
public class DropTarget extends AbstractTarget {
    /**
     * Constructor of the class: Calls the constructor of the super class and sets the score to 100
     */
    public DropTarget(){
        super();
        setScore(100);
    }

    /**
     * Hits the target: Uses a random number to see if it has to trigger an ExtraBallBonus, then notifies the observers
     * to update the score, cals the hit method of the super class, and finally calls returns the score of the target
     *
     * @return score of the target
     */
    public int hit(){
        Random randGen = new Random(-2300);
        double randNum = randGen.nextDouble();
        if (randNum < 0.3){
            this.setChanged();
            notifyObservers(new ExtraBallBonusVisitor());
        }
        this.setChanged();
        notifyObservers(new DropTargetHittedVisitor());
        super.hit();
        return getScore();
    }

    public void reset(){
        this.setChanged();
        notifyObservers(new DropTargetResetVisitor());
        super.reset();
    }

}
