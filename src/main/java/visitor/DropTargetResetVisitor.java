package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

public class DropTargetResetVisitor implements Visitor {
    @Override
    public void visitTable(Table table, Hittable hittable) {
        table.setCurrentlyDroppedDropTargets(table.getCurrentlyDroppedDropTargets() - 1);
    }

    @Override
    public void visitGame(Game game, Table table) {
    }

}
