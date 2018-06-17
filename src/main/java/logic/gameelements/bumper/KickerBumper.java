package logic.gameelements.bumper;

import logic.table.TableClass;

/**
 * One of the two types of bumpers
 */
public class KickerBumper extends AbstractBumper {
    /**
     * Constructor of the class. Calls the constructor of the super class, then sets the score as 500 and the remaining hits as 5
     */
    public KickerBumper(){
        super();
        super.setScore(500);
        super.setRemainingHits(5);
    }

    /**
     * Upgrades the bumper: Sets the score as 1000, and then calls the upgrade method of the super class
     */
    @Override
    public void upgrade() {
        super.setScore(1000);
        super.upgrade();
    }

    /**
     * Downgrades the bumper, sets the score as 500, and then calls the downgrade method from the superc class
     */
    @Override
    public void downgrade(){
        super.setScore(500);
        super.downgrade();
    }

}
