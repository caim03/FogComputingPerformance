package event;

import java.util.PriorityQueue;

public class EventList {
    private static EventList eventList = null;
    private PriorityQueue<Event> priorityQueue;

    private EventList(){
        this.priorityQueue = new PriorityQueue<>();
    }

    public static EventList getInstance(){
        if(eventList == null){
            eventList = new EventList();
        }
        return eventList;
    }

    public void pushEvent(Event event){
        this.priorityQueue.add(event);
    }

    public Event popEvent(){
        Event event =  this.priorityQueue.poll();
        this.priorityQueue.remove(event);
        return event;
    }

    public void removeEvent(Event event){
        this.priorityQueue.remove(event);
    }

    public int getSize(){
        return this.priorityQueue.size();
    }
}
