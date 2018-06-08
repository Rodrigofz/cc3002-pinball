package logic.table;

import javafx.scene.effect.Light;
import logic.gameelements.Hittable;
import logic.gameelements.bumper.AbstractBumper;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import visitor.*;

import java.util.*;

import static jdk.nashorn.internal.objects.NativeMath.random;

public class TableClass extends Observable implements Table, Observer {
    private String name;

    private ArrayList<Bumper> bumpers;
    private int numberOfBumpers;

    private ArrayList<DropTarget> dropTargets;
    private ArrayList<SpotTarget> spotTargets;
    private int numberOfDropTargets;
    private int numberOfSpotTargets;
    private int currentlyDroppedDropTargets;
    private int score;

    private double prob;

    private boolean playable;

    public TableClass(String name, int numberOfBumpers, double prob, int numberOfSpotTargets, int numberOfDropTargets){
        this.name = name;
        this.numberOfBumpers =  numberOfBumpers;
        this.bumpers = new ArrayList<Bumper>();
        this.dropTargets = new ArrayList<DropTarget>();
        this.spotTargets = new ArrayList<SpotTarget>();
        this.prob = prob;
        this.numberOfSpotTargets = numberOfSpotTargets;
        this.numberOfDropTargets = numberOfDropTargets;
        this.currentlyDroppedDropTargets = 0;
        this.score = 0;
        this.playable = true;

        for (int i=0; i<numberOfSpotTargets; i++)
            spotTargets.add(new SpotTarget());

        for (int i=0; i<numberOfDropTargets; i++)
            dropTargets.add(new DropTarget());

        for (int i=0; i<numberOfBumpers; i++){
            double randNum = Math.random();
            bumpers.add((randNum<prob) ? new PopBumper(): new KickerBumper());
        }

        this.observateObservers();
    }

    public TableClass(String name, int numberOfBumpers, double prob, int numberOfSpotTargets, int numberOfDropTargets, double seed){
        this.name = name;
        this.numberOfBumpers =  numberOfBumpers;
        this.bumpers = new ArrayList<Bumper>();
        this.dropTargets = new ArrayList<DropTarget>();
        this.spotTargets = new ArrayList<SpotTarget>();
        this.prob = prob;
        this.numberOfSpotTargets = numberOfSpotTargets;
        this.numberOfDropTargets = numberOfDropTargets;
        this.currentlyDroppedDropTargets = 0;
        this.score = 0;
        this.playable = true;

        for (int i=0; i<numberOfSpotTargets; i++)
            spotTargets.add(new SpotTarget());

        for (int i=0; i<numberOfDropTargets; i++)
            dropTargets.add(new DropTarget());

        for (int i=0; i<numberOfBumpers; i++){
            double randNum = random(seed);
            bumpers.add((randNum<prob) ? new PopBumper(): new KickerBumper());
        }

        this.observateObservers();
    }

    public void observateObservers(){
        for (Bumper b: bumpers){
            ((AbstractBumper) b).addObserver(this);
        }
        for (DropTarget t: dropTargets){
            t.addObserver(this);
        }
        for(SpotTarget t: spotTargets){
            t.addObserver(this);
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

    public void setCurrentlyDroppedDropTargets(int newValue){
        currentlyDroppedDropTargets = newValue;
    }

    @Override
    public ArrayList<Bumper> getBumpers() {
        return bumpers;
    }

    @Override
    public ArrayList<Target> getTargets() {
        ArrayList<Target> returnList = new ArrayList<Target>(dropTargets);
        returnList.addAll(spotTargets);
        return returnList;
    }

    public ArrayList<DropTarget> getDropTargets(){
        return dropTargets;
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

    public void updatePlayable(int numberOfBalls){
        playable = numberOfBalls > 0;
    }

    public void changeScore(int newScore){
        score = newScore;
        this.setChanged();
        notifyObservers(new ScoreVisitor());
    }

    public void notifySpotTarget(){
        this.setChanged();
        notifyObservers(new SpotTargetHittedVisitor());
    }

    public void notifyDropTarget(){
        currentlyDroppedDropTargets--;
        this.setChanged();
        notifyObservers(new DropTargetHittedVisitor());
    }

    public int getScore(){
        return score;
    }

    public void bumperUpgraded(){
        this.setChanged();
        notifyObservers(new UpgradeVisitor());
    }

    @Override
    public void update(Observable o, Object arg) {
        ((Visitor)arg).visitTable(this, (Hittable) o);
    }


}
