package utilities;

public class Clock {
    private static Clock clock = null;
    private double currTime;
    private double lastTransient;

    private Clock(){
        this.currTime = 0;
        this.lastTransient = 0;
    }

    public static Clock getIstance(){
        if(clock == null){
            clock = new Clock();
        }
        return clock;
    }

    public double getCurrTime() {
        return currTime;
    }

    public void setCurrTime(double currTime) {
        this.currTime = currTime;
    }

    public double getLastTransient() {
        return lastTransient;
    }

    public void setLastTransient(double lastTransient) {
        this.lastTransient = lastTransient;
    }

    @Override
    public String toString() {
        return "Clock{" +
                "currTime=" + currTime +
                '}';
    }
}
