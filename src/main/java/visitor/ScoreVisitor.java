package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;
import logic.table.TableClass;

public class ScoreVisitor implements Visitor {

    @Override
    public void visitTable(Table table, Hittable hittable) {
        table.changeScore(hittable.getScore());
    }

    @Override
    public void visitGame(Game game, Table table) {
        game.addScore(((TableClass) table).getScore());
    }


}
