package logic.gameelements.target;

import visitor.SpotTargetHittedVisitor;

public class SpotTarget extends AbstractTarget {

    public SpotTarget(){
        super();
        setScore(0);
    }

    public int hit(){
        this.setChanged();
        notifyObservers(new SpotTargetHittedVisitor());
        super.hit();
        return getScore();
    }
}
