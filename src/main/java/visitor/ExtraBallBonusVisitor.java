package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

public class ExtraBallBonusVisitor implements Visitor{
    @Override
    public void visitTable(Table table, Hittable hittable) {
        table.bumperUpgradeBonus();
    }

    @Override
    public void visitGame(Game game, Table table) {
        game.getExtraBallBonus().trigger(game);
    }
}
