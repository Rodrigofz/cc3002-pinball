package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

public class DropTargetHittedVisitor implements Visitor {
    /**
     * Adds 1 to the number of dropped DropTargets and triggers the notification to the Game instance
     *
     * @param table         Table to be visited
     * @param hittable      Hittable that is triggering the visit
     */
    @Override
    public void visitTable(Table table, Hittable hittable) {
        table.setCurrentlyDroppedDropTargets(table.getNumberOfDropTargets() + 1);
        table.notifyDropTarget();
    }

    /**
     * Triggers the DropTargetBonus in the Game instance
     *
     * @param game          Game to be visited
     * @param table         Table that is triggering the visit
     */
    @Override
    public void visitGame(Game game, Table table) {
        if (table.getCurrentlyDroppedDropTargets() == table.getNumberOfDropTargets())
            game.getDropTargetBonus().trigger(game);
    }
}
