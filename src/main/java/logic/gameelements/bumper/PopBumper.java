package logic.gameelements.bumper;

public class PopBumper extends AbstractBumper {
    public PopBumper(){
        super();
        super.setScore(100);
        super.setRemainingHits(3);
    }

    @Override
    public void upgrade() {
        super.setScore(300);
        super.upgrade();
    }

    @Override
    public void downgrade(){
        super.setScore(100);
        super.downgrade();
    }

}
