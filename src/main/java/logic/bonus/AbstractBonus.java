package logic.bonus;

public abstract class AbstractBonus implements Bonus {
    private int timesTriggered;

    public AbstractBonus(){
        timesTriggered = 0;
    }

    public void setTimesTriggered(int timesTriggered) {
        this.timesTriggered = timesTriggered;
    }

    public int timesTriggered(){
        return timesTriggered;
    }

    public void trigger(){
        timesTriggered ++;
    }

}
