package logic.gameelements.bumper;
import visitor.ScoreVisitor;
import visitor.ExtraBallBonusVisitor;

import java.util.Observable;
import java.util.Random;

/**
 * Abstract class that represent bumpers, there can be two types of these: KickerBumper or PopBumper
 */
public abstract class AbstractBumper extends Observable implements Bumper  {
    private int remainingHits;
    private int score;
    private boolean upgraded;

    /**
     * Constructor of the class, sets the upgraded variable as false
     */
    public AbstractBumper(){
        this.upgraded = false;
    }

    /**
     * Sets the remaining hits as a value received
     *
     * @param value     Value to be setted as remainingHits
     */
    public void setRemainingHits(int value){
        this.remainingHits = value;
    }

    /**
     * Gets wether the Bumper is upgraded or not
     *
     * @return boolean variable upgraded
     */
    public boolean isUpgraded(){
        return upgraded;
    }

    /**
     * Upgrades the bumper, and uses a random number to see if its neccesary to trigger an ExtraBallBonus
     */
    public void upgrade(){
        this.upgraded = true;
        Random randGen = new Random(-2100);
        double randNum = randGen.nextDouble();
        if (randNum < 0.1) {
            this.setChanged();
            notifyObservers(new ExtraBallBonusVisitor());
        }
    }

    /**
     * Downgrades the bumper
     */
    public void downgrade(){
        this.upgraded = false;
    }

    /**
     * Gets the number of remaining hits to upgrade that the bumper has
     *
     * @return variable remainingHits
     */
    public int remainingHitsToUpgrade(){
        return remainingHits;
    }

    /**
     * Hits the bumper, checks if its necessary to upgrade it, and notifies the observers to update the score. Finally, returns the score of the bumper
     *
     * @return score of the bumper
     */
    public int hit(){
        this.remainingHits--;
        if (remainingHitsToUpgrade() == 0){
            upgrade();
        }
        this.setChanged();
        notifyObservers(new ScoreVisitor());
        return getScore();
    }

    /**
     * Gets the score that the bumper gives when is hitted
     *
     * @return score of the bumper
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the score as a new value
     *
     * @param newScore Value to be setted as the score of the bumper
     */
    public void setScore(int newScore){
        this.score = newScore;
    }

}
