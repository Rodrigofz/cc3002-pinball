package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

public interface Visitor {
    void visitTable(Table table, Hittable hittable);

    void visitGame(Game game, Table table);

}
