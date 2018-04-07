package task;

public class Task2 extends Task{
    private boolean preemptive;
    private double preemptionTime; // Time instant of preemption
    private double setupTime;

    public Task2(double arrivalTime) {

        super(arrivalTime);
        this.preemptive = false;
        this.preemptionTime = 0;
        this.setupTime = 0;
    }

    public boolean isPreemptive() {
        return preemptive;
    }

    public void setPreemptive(boolean preemptive) {
        this.preemptive = preemptive;
    }

    public double getPreemptionTime() {
        return preemptionTime;
    }

    public void setPreemptionTime(double preemptionTime) {
        this.preemptionTime = preemptionTime;
    }

    public double getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(double setupTime) {
        this.setupTime = setupTime;
    }
}
