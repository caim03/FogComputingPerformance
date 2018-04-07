package event;
import task.Task;

public abstract class Event implements Comparable<Event>{
    protected Task task;
    protected double time;
    protected boolean isCloudlet;

    public Event(Task task, double time){
        this.task = task;
        this.time = time;
        this.isCloudlet = true;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public boolean isCloudlet() {
        return isCloudlet;
    }

    public void setCloudlet(boolean cloudlet) {
        isCloudlet = cloudlet;
    }

    @Override
    public int compareTo(Event event){
        if(this.time < event.time){
            return -1;
        }
        else if (this.time == event.time){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "task=" + task +
                ", time=" + time +
                ", isCloudlet=" + isCloudlet +
                '}';
    }
}
