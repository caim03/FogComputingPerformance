package fogComputing;

import event.Event;
import event.EventArrival;
import event.EventCompletion;
import event.EventList;
import statistics.BatchController;
import task.Task1;
import task.Task2;
import utilities.*;

import java.util.ArrayList;


public class Cloudlet {
    private static Cloudlet cloudlet = null;

    private int n1;
    private int n2;
    private int n1CletComplete;
    private int n2CletComplete;
    private int n2Interrupt;

    private EventList eventList;
    private ArrayList<EventCompletion> eventCompletionList;
    private Clock clock;
    private BatchController batchController;


    private Cloudlet(){
        this.n1 = 0;
        this.n2 = 0;
        this.n1CletComplete = 0;
        this.n2CletComplete = 0;
        this.n2Interrupt = 0;

        this.eventList = EventList.getInstance();
        this.clock = Clock.getIstance();

        this.eventCompletionList = new ArrayList<>();
        this.batchController = BatchController.getInstance();
    }

    public static Cloudlet getInstance(){
        if(cloudlet == null){
            cloudlet = new Cloudlet();
        }
        return cloudlet;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int getN1CletComplete() {
        return n1CletComplete;
    }

    public void setN1CletComplete(int n1CletComplete) {
        this.n1CletComplete = n1CletComplete;
    }

    public int getN2CletComplete() {
        return n2CletComplete;
    }

    public void setN2CletComplete(int n2CletComplete) {
        this.n2CletComplete = n2CletComplete;
    }

    public int getN2Interrupt() {
        return n2Interrupt;
    }

    public void setN2Interrupt(int n2Interrupt) {
        this.n2Interrupt = n2Interrupt;
    }

    public void handleEvent(Event event){
        /* Event is arrival */
        if(event instanceof EventArrival){
            /* Set service time and completion time of a task1 */
            if(event.getTask() instanceof Task1){
                RvgsWrapper.getInstance().selectStream(2);
                event.getTask().setServiceTime(RvgsWrapper.getInstance().exponential(1/Configuration.mu1Clet));
                event.getTask().setCompletionTime(event.getTask().getArrivalTime() + event.getTask().getServiceTime());
                /* Update population */
                this.n1++;
            }
            /* Set service time and completion time of a task2 */
            else{
                RvgsWrapper.getInstance().selectStream(3);
                event.getTask().setServiceTime(RvgsWrapper.getInstance().exponential(1/Configuration.mu2Clet));
                event.getTask().setCompletionTime(event.getTask().getArrivalTime() + event.getTask().getServiceTime());
                /* Update population */
                this.n2++;
            }

            EventCompletion completionEvent = new EventCompletion(event.getTask());
            completionEvent.setTime(event.getTask().getCompletionTime());
            eventList.pushEvent(completionEvent);
            if (completionEvent.getTask() instanceof Task2){
                this.eventCompletionList.add(completionEvent);
            }
        }
        else{
            if(event.getTask() instanceof Task1){
                this.n1--;
                this.n1CletComplete++;
            }
            else{
                this.n2--;
                this.n2CletComplete++;
                eventCompletionList.remove(event);
            }
        }

        computeStatistics(event);
    }

    public void handlePreemption(EventArrival event){
        this.n2Interrupt++;
        this.n2--;

        EventCompletion preempEvent;
        EventArrival newEvent;
        Task2 task;

        RvgsWrapper.getInstance().selectStream(7);
        long i = RvgsWrapper.getInstance().equilikely(0, eventCompletionList.size()-1);

        preempEvent = eventCompletionList.get((int)i);
        eventCompletionList.remove(preempEvent);
        eventList.removeEvent(preempEvent);

        task = (Task2)preempEvent.getTask();

        task.setPreemptive(true);
        task.setPreemptionTime(event.getTime());

        newEvent = new EventArrival(task);
        newEvent.setTime(event.getTime());
        newEvent.setCloudlet(false);
        eventList.pushEvent(newEvent);

        //computeStatistics(event);
    }

    private void computeStatistics(Event event){
        /* Update Population Statistics */
        this.batchController.getCletPopulationTask1().batchMeansAlgorithm(this.n1);
        this.batchController.getCletPopulationTask2().batchMeansAlgorithm(this.n2);

        /* Update Throughput Statistics */
        this.batchController.getCletThroughput().
                batchMeansAlgorithm((this.n1CletComplete + this.n2CletComplete)/this.clock.getCurrTime());
        this.batchController.getCletThroughputTask1().
                batchMeansAlgorithm(this.n1CletComplete/this.clock.getCurrTime());
        this.batchController.getCletThroughputTask2()
                .batchMeansAlgorithm(this.n2CletComplete/this.clock.getCurrTime());

        this.batchController.getSystemThroughput().
                batchMeansAlgorithm(((this.n1CletComplete + this.n2CletComplete) +
                        (Cloud.getInstance().getN1CloudComplete() + Cloud.getInstance().getN2CloudComplete()))/clock.getCurrTime());

        this.batchController.getSystemThroughputTask1().
                batchMeansAlgorithm((this.n1CletComplete + Cloud.getInstance().getN1CloudComplete())/clock.getCurrTime());

        this.batchController.getSystemThroughputTask2().
                batchMeansAlgorithm((this.n2CletComplete + Cloud.getInstance().getN2CloudComplete())/clock.getCurrTime());

        if (event instanceof EventCompletion){
            this.batchController.getSystemResponseTime().
                    batchMeansAlgorithm(event.getTask().getServiceTime());
            if(event.getTask() instanceof Task1){
                this.batchController.getSystemResponseTimeTask1().batchMeansAlgorithm(event.getTask().getServiceTime());
                this.batchController.getCletResponseTimeTask1().batchMeansAlgorithm(event.getTask().getServiceTime());
            }
            else{
                this.batchController.getSystemResponseTimeTask2().batchMeansAlgorithm(event.getTask().getServiceTime());
                this.batchController.getCletResponseTimeTask2().batchMeansAlgorithm(event.getTask().getServiceTime());
            }
        }
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------------------------------------------\n" +
                "Cloudlet information: \n" +
                "n1 = " + n1 + "\n" +
                "n2 = " + n2 + "\n" +
                "n1CletComplete = " + n1CletComplete + "\n" +
                "n2CletComplete = " + n2CletComplete + "\n" +
                "n2Interrupt = " + n2Interrupt + "\n" +
                "--------------------------------------------------------------------------------";
    }
}
