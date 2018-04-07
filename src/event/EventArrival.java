package event;
import task.Task;

public class EventArrival extends Event {
    public EventArrival(Task task) {
        super(task, task.getArrivalTime());
    }
}
