package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Interface that represents the basics of a table to be played on.
 *
 * @author Juan-Pablo Silva
 */
public interface Table {
    /**
     * Gets the table name.
     *
     * @return the table's name
     */
    String getTableName();

    /**
     * Gets the number of {@link logic.gameelements.target.DropTarget} in the table.
     *
     * @return the number of DropTargets in the table
     */
    int getNumberOfDropTargets();

    /**
     * Gets the number of {@link logic.gameelements.target.DropTarget} that are currently dropped or inactive.
     *
     * @return the number of DropTargets that are currently inactive
     */
    int getCurrentlyDroppedDropTargets();

    /**
     * Gets the {@link List} of {@link Bumper}s in the table.
     *
     * @return the bumpers in the table
     */
    List<Bumper> getBumpers();

    /**
     * Gets the {@link List} of {@link Target}s in the table.
     *
     * @return the targets in the table
     */
    List<Target> getTargets();

    /**
     * Resets all {@link logic.gameelements.target.DropTarget} in the table. Make them active.
     */
    void resetDropTargets();

    /**
     * Upgrade all {@link Bumper}s in the table.
     */
    void upgradeAllBumpers();

    /**
     * Gets whether the table is playable or not.
     *
     * @return true if the table is playable, false otherwise
     */
    boolean isPlayableTable();

    /**
     * Sets the score as a new one
     *
     * @param dif   New score to be setted
     */
    void changeScore(int dif);

    /**
     * Gets the current score of the table
     *
     * @return score variable
     */
    int getScore();

    /**
     * Notify the observers that an ExtraBallBonus must be triggered
     */
    void extraBallBonusNotify();

    /**
     * Notify the observers that a SpotTarget has been hitted
     */
    void notifySpotTarget();

    /**
     * Notify the observers that a DropTarget has been hitted
     */
    void notifyDropTarget();

    /**
     * Update the playable state of the table, given an amount of balls
     *
     * @param numberOfBalls     If the amount is 0, the table is not playable
     */
    void updatePlayable(int numberOfBalls);

    /**
     * Changes the number of currently dropped DropTargets for a new value
     *
     * @param newValue      Value to be setted as the currently dropped DropTargets
     */
    public void setCurrentlyDroppedDropTargets(int newValue);

}
