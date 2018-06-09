package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

public class DropTargetHittedVisitor implements Visitor {
    @Override
    public void visitTable(Table table, Hittable hittable) {
        table.setCurrentlyDroppedDropTargets(table.getNumberOfDropTargets() + 1);
        table.notifyDropTarget();
    }

    @Override
    public void visitGame(Game game, Table table) {
        if (table.getCurrentlyDroppedDropTargets() == table.getNumberOfDropTargets())
            game.getDropTargetBonus().trigger(game);
    }
}
