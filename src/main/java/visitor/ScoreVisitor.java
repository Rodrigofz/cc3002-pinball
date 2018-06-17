package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;
import logic.table.TableClass;

public class ScoreVisitor implements Visitor {
    /**
     * Sets the table current score as the score of the hittable
     *
     * @param table         Table to be visited
     * @param hittable      Hittable that is triggering the visit
     */
    @Override
    public void visitTable(Table table, Hittable hittable) {
        table.changeScore(hittable.getScore());
    }

    /**
     * Adds the current score of the table to the current score of the game
     * @param game          Game to be visited
     * @param table         Table that is triggering the visit
     */
    @Override
    public void visitGame(Game game, Table table) {
        game.addScore(((TableClass) table).getScore());
    }


}
