package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

/**
 * Interface that represent the visitors of the project
 *
 * @author Rodrigo Fuentes
 */
public interface Visitor {
    /**
     * Visits a Table instance
     *
     * @param table         Table to be visited
     * @param hittable      Hittable that is triggering the visit
     */
    void visitTable(Table table, Hittable hittable);

    /**
     * Visits a Game instance
     *
     * @param game          Game to be visited
     * @param table         Table that is triggering the visit
     */
    void visitGame(Game game, Table table);

}
