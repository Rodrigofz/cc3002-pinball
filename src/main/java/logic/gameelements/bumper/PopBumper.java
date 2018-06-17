package logic.gameelements.bumper;

import logic.table.TableClass;

/**
 * One of the two types of bumpers
 */
public class PopBumper extends AbstractBumper {
    /**
     * Constructor of the class. Calls the constructor of the super class, then sets the score as 100 and the remaining hits as 3
     */
    public PopBumper(){
        super();
        super.setScore(100);
        super.setRemainingHits(3);
    }

    /**
     * Upgrades the bumper: Sets the score as 300, and then calls the upgrade method of the super class
     */
    @Override
    public void upgrade() {
        super.setScore(300);
        super.upgrade();
    }

    /**
     * Downgrades the bumper, sets the score as 100, and then calls the downgrade method from the superc class
     */
    @Override
    public void downgrade(){
        super.setScore(100);
        super.downgrade();
    }

}
