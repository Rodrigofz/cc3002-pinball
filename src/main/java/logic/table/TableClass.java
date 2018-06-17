package logic.table;

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

/**
 * Class that implements the Table interfaace, represents the element that has all the hittables of the game
 */
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

    /**
     * Constructor that doesnt receive a seed to the random numbers, uses 100 as a seed, calling the other constructor
     *
     * @param name                      Name of the table
     * @param numberOfBumpers           Total number of bumpers in the table
     * @param prob                      Probability of having a PopBumper over a KickerBumper
     * @param numberOfSpotTargets       Total number of SpotTargets in the table
     * @param numberOfDropTargets       Total number of DropTargets in the table
     */
    public TableClass(String name, int numberOfBumpers, double prob, int numberOfSpotTargets, int numberOfDropTargets){
        this(name, numberOfBumpers, prob, numberOfSpotTargets, numberOfDropTargets, 100);
    }

    /**
     * Constructor that receives a seed to the random numbers
     *
     * @param name                      Name of the table
     * @param numberOfBumpers           Total number of bumpers in the table
     * @param prob                      Probability of having a PopBumper over a KickerBumper
     * @param numberOfSpotTargets       Total number of SpotTargets in the table
     * @param numberOfDropTargets       Total number of DropTargets in the table
     * @param seed                      Seed used in the random generator
     */
    public TableClass(String name, int numberOfBumpers, double prob, int numberOfSpotTargets, int numberOfDropTargets, int seed){
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

        Random randGen = new Random(seed);
        for (int i=0; i<numberOfBumpers; i++){
            double randDob = randGen.nextDouble();
            bumpers.add((randDob<prob) ? new PopBumper(): new KickerBumper());
        }

        this.observateObservers();
    }

    /**
     * Sets the table as an observer to all the hittables that it has
     */
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

    /**
     * Gets the table name
     *
     * @return variable name
     */
    @Override
    public String getTableName() {
        return name;
    }

    /**
     * Gets the number of DropTargets
     *
     * @return variable numberOfDropTargets
     */
    @Override
    public int getNumberOfDropTargets() {
        return numberOfDropTargets;
    }

    /**
     * Gets the currently dropped DropTargets
     *
     * @return variable currentlyDroppedDropTargets
     */
    @Override
    public int getCurrentlyDroppedDropTargets() {
        return currentlyDroppedDropTargets;
    }

    /**
     * Changes the number of currently dropped DropTargets for a new value
     *
     * @param newValue      Value to be setted as the currently dropped DropTargets
     */
    public void setCurrentlyDroppedDropTargets(int newValue){
        currentlyDroppedDropTargets = newValue;
    }

    /**
     * Gets the list of Bumpers in the table
     *
     * @return variable bumpers
     */
    @Override
    public ArrayList<Bumper> getBumpers() {
        return bumpers;
    }

    /**
     * Gets the list of Targets in the table
     *
     * @return variables dropTargets and spotTargets combined into one list
     */
    @Override
    public ArrayList<Target> getTargets() {
        ArrayList<Target> returnList = new ArrayList<Target>(dropTargets);
        returnList.addAll(spotTargets);
        return returnList;
    }

    /**
     * Gets the list of DropTargets
     *
     * @return variable dropTargets
     */
    public ArrayList<DropTarget> getDropTargets(){
        return dropTargets;
    }

    /**
     * Resets all the DropTargets in the table
     */
    @Override
    public void resetDropTargets() {
        for (Target t : dropTargets)
            t.reset();
    }

    /**
     * Upgrades all the bumpers in the table
     */
    @Override
    public void upgradeAllBumpers() {
        for (Bumper b : bumpers)
            b.upgrade();
    }

    /**
     * Gets wether the table if playable or not, represented as an instance variable
     *
     * @return variable playable
     */
    @Override
    public boolean isPlayableTable() {
        return playable;
    }

    /**
     * Update the playable state of the table, given an amount of balls
     *
     * @param numberOfBalls     If the amount is 0, the table is not playable
     */
    public void updatePlayable(int numberOfBalls){
        playable = numberOfBalls > 0;
    }

    /**
     * Changes the current score for a new one
     *
     * @param newScore      Value to be setted as the new current score of the table
     */
    public void changeScore(int newScore){
        score = newScore;
        this.setChanged();
        notifyObservers(new ScoreVisitor());
    }

    /**
     * Notifies the observers that a SpotTarget has been hitted
     */
    public void notifySpotTarget(){
        this.setChanged();
        notifyObservers(new SpotTargetHittedVisitor());
    }

    /**
     * Notifies the observers that a DropTarget has been hitted
     */
    public void notifyDropTarget(){
        currentlyDroppedDropTargets--;
        this.setChanged();
        notifyObservers(new DropTargetHittedVisitor());
    }

    /**
     * Gets the current score of the table
     *
     * @return variable score
     */
    public int getScore(){
        return score;
    }

    /**
     * Notifies the observers that an ExtraBallBonus must be triggered
     */
    public void extraBallBonusNotify(){
        this.setChanged();
        notifyObservers(new ExtraBallBonusVisitor());
    }

    /**
     * Triggers a visitor to visit the table
     *
     * @param o         Observable that is notifying the table
     * @param arg       Object argument, always used as a visitor that visits the table
     */
    @Override
    public void update(Observable o, Object arg) {
        ((Visitor)arg).visitTable(this, (Hittable) o);
    }


}
