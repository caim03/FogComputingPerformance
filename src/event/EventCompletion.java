package event;
import task.Task;

public class EventCompletion extends Event{
    public EventCompletion(Task task) {
        super(task, task.getCompletionTime());
    }
}
