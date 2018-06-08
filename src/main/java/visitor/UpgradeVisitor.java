package visitor;

import controller.Game;
import logic.gameelements.Hittable;
import logic.table.Table;

public class UpgradeVisitor implements Visitor{
    @Override
    public void visitTable(Table table, Hittable hittable) {
        table.bumperUpgraded();
    }

    @Override
    public void visitGame(Game game, Table table) {
        double randNum = Math.random();
        if (randNum < 0.1)
            game.getExtraBallBonus().trigger(game);
    }
}
