package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

public class DropTargetResetVisitor implements Visitor {
    /**
     * Drops 1 of the number of currently dropped Dgid ropTargets
     *
     * @param table         Table to be visited
     * @param hittable      Hittable that is triggering the visit
     */
    @Override
    public void visitTable(Table table, Hittable hittable) {
        table.setCurrentlyDroppedDropTargets(table.getCurrentlyDroppedDropTargets() - 1);
    }

    @Override
    public void visitGame(Game game, Table table) {
    }

}
