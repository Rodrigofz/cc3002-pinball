package logic.gameelements.bumper;

public class KickerBumper extends AbstractBumper {

    public KickerBumper(){
        super();
        super.setScore(500);
        super.setRemainingHits(5);
    }

    @Override
    public void upgrade() {
        super.setScore(1000);
        super.upgrade();
    }

    @Override
    public void downgrade(){
        super.setScore(500);
        super.downgrade();
    }

}
