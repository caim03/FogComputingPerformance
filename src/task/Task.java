package task;

public abstract class Task {
    protected double arrivalTime;
    protected double completionTime;
    protected double serviceTime;

    public Task(double arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(double completionTime) {
        this.completionTime = completionTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "arrivalTime=" + arrivalTime +
                ", completionTime=" + completionTime +
                ", serviceTime=" + serviceTime +
                '}';
    }
}
