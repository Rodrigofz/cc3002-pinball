package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.*;

public class TableClass implements Table, Observer {
    private String name;

    private List<Bumper> bumpers;
    private int numberOfBumpers;

    private List<Target> dropTargets;
    private List<Target> spotTargets;
    private int numberOfDropTargets;
    private int numberOfSpotTargets;
    private int currentlyDroppedDropTargets;

    private double prob;

    private boolean playable;

    public TableClass(String name, int numberOfBumpers, double prob, int numberOfSpotTargets, int numberOfDropTargets){
        this.name = name;
        this.numberOfBumpers =  numberOfBumpers;
        this.prob = prob;
        this.numberOfSpotTargets = numberOfSpotTargets;
        this.numberOfDropTargets = numberOfDropTargets;

        for (int i=0; i<numberOfSpotTargets; i++)
            spotTargets.add(new SpotTarget());

        for (int i=0; i<numberOfDropTargets; i++)
            dropTargets.add(new DropTarget());

        for (int i=0; i<numberOfBumpers; i++){
            double randNum = Math.random();
            bumpers.add((randNum<prob) ? new PopBumper(): new KickerBumper());
        }

    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public int getNumberOfDropTargets() {
        return numberOfDropTargets;
    }

    @Override
    public int getCurrentlyDroppedDropTargets() {
        return currentlyDroppedDropTargets;
    }

    @Override
    public List<Bumper> getBumpers() {
        return bumpers;
    }

    @Override
    public List<Target> getTargets() {
        List<Target> returnList = new ArrayList<Target>(dropTargets);
        returnList.addAll(spotTargets);
        return returnList;
    }

    @Override
    public void resetDropTargets() {
        for (Target t : dropTargets)
            t.reset();
    }

    @Override
    public void upgradeAllBumpers() {
        for (Bumper b : bumpers)
            b.upgrade();
    }

    @Override
    public boolean isPlayableTable() {
        return playable;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof  Bumper && ((Bumper) o).remainingHitsToUpgrade() == 0) {
            upgradeAllBumpers();
        }
    }
}
