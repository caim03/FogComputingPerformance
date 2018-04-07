package utilities;

public class Clock {
    private static Clock clock = null;
    private double currTime;
    private double lastArrival;

    private Clock(){
        this.currTime = 0;
        this.lastArrival = 0;
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

    public double getLastArrival() {
        return lastArrival;
    }

    public void setLastArrival(double lastArrival) {
        this.lastArrival = lastArrival;
    }

    @Override
    public String toString() {
        return "Clock{" +
                "currTime=" + currTime +
                '}';
    }
}
