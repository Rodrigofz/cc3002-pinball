package logic.gameelements.target;

import visitor.DropTargetHittedVisitor;
import visitor.DropTargetResetVisitor;

public class DropTarget extends AbstractTarget {

    public DropTarget(){
        super();
        setScore(100);
    }

    public int hit(){
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
