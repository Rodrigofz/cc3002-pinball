package logic.gameelements.target;

import visitor.DropTargetHittedVisitor;
import visitor.DropTargetResetVisitor;
import visitor.ExtraBallBonusVisitor;

import java.util.Random;

public class DropTarget extends AbstractTarget {

    public DropTarget(){
        super();
        setScore(100);
    }

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
