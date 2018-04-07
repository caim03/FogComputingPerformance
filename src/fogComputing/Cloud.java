package fogComputing;

import event.Event;
import event.EventArrival;
import event.EventCompletion;
import event.EventList;
import statistics.BatchController;
import task.Task1;
import task.Task2;
import utilities.Clock;
import utilities.Configuration;
import utilities.RvgsWrapper;

public class Cloud {
    private static Cloud cloud = null;

    private int n1;
    private int n2;
    private int n1CloudComplete;
    private int n2CloudComplete;

    private EventList eventList;
    private Clock clock;
    private BatchController batchController;

    private Cloud(){
        this.n1 = 0;
        this.n2 = 0;

        this.n1CloudComplete = 0;
        this.n2CloudComplete = 0;

        this.eventList = EventList.getInstance();
        this.clock = Clock.getIstance();
        this.batchController = BatchController.getInstance();
    }

    public static Cloud getInstance(){
        if(cloud == null){
            cloud = new Cloud();
        }
        return cloud;
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

    public int getN1CloudComplete() {
        return n1CloudComplete;
    }

    public void setN1CloudComplete(int n1CloudComplete) {
        this.n1CloudComplete = n1CloudComplete;
    }

    public int getN2CloudComplete() {
        return n2CloudComplete;
    }

    public void setN2CloudComplete(int n2CloudComplete) {
        this.n2CloudComplete = n2CloudComplete;
    }

    public void handleEvent(Event event){
        /* Event is arrival */
        if(event instanceof EventArrival){
            /* Set service time and completion time of a task1 */
            RvgsWrapper.getInstance().selectStream(4);
            if(event.getTask() instanceof Task1){
                event.getTask().setServiceTime(RvgsWrapper.getInstance().exponential(1/Configuration.mu1Cloud));
                event.getTask().setCompletionTime(event.getTask().getArrivalTime() + event.getTask().getServiceTime());
                /* Update population */
                this.n1++;
            }
            /* Set service time and completion time of a task2, but it considers preemption case */
            else{
                RvgsWrapper.getInstance().selectStream(5);
                event.getTask().setServiceTime(RvgsWrapper.getInstance().exponential(1/Configuration.mu2Cloud));

                /* Preemption case */
                if(((Task2)event.getTask()).isPreemptive()){
                    RvgsWrapper.getInstance().selectStream(6);
                    ((Task2)event.getTask()).setSetupTime(RvgsWrapper.getInstance().exponential(Configuration.setup));

                    event.getTask().setCompletionTime(((Task2)event.getTask()).getPreemptionTime() +
                            event.getTask().getServiceTime() + ((Task2)event.getTask()).getSetupTime());
                }
                /* No preemption */
                else{
                    event.getTask().setCompletionTime(event.getTask().getArrivalTime() + event.getTask().getServiceTime());
                }
                this.n2++;
            }

            EventCompletion completionEvent = new EventCompletion(event.getTask());
            completionEvent.setTime(event.getTask().getCompletionTime());
            completionEvent.setCloudlet(false);
            eventList.pushEvent(completionEvent);
        }

        /* Event is completion */
        else{
            if(event.getTask() instanceof Task1){
                this.n1--;
                this.n1CloudComplete++;
            }
            else{
                this.n2--;
                this.n2CloudComplete++;

            }
        }

        computeStatistics(event);
    }

    private void computeStatistics(Event event){
        /* Update Population Statistics */
        this.batchController.getCloudPopulationTask1().batchMeansAlgorithm(this.n1);
        this.batchController.getCloudPopulationTask2().batchMeansAlgorithm(this.n2);

        /* Update Throughput Statistics */
        this.batchController.getSystemThroughput().
                batchMeansAlgorithm(((Cloudlet.getInstance().getN1CletComplete() + Cloudlet.getInstance().getN2CletComplete()) +
                        (this.n1CloudComplete + this.n2CloudComplete))/clock.getCurrTime());

        this.batchController.getSystemThroughputTask1().
                batchMeansAlgorithm((this.n1CloudComplete + Cloudlet.getInstance().getN1CletComplete())/clock.getCurrTime());

        this.batchController.getSystemThroughputTask2().
                batchMeansAlgorithm((this.n2CloudComplete + Cloudlet.getInstance().getN2CletComplete())/clock.getCurrTime());

        if (event instanceof EventCompletion){
            this.batchController.getSystemResponseTime().
                    batchMeansAlgorithm(event.getTask().getServiceTime());
            if(event.getTask() instanceof Task1){
                this.batchController.getSystemResponseTimeTask1().batchMeansAlgorithm(event.getTask().getServiceTime());
                this.batchController.getCloudResponseTimeTask1().batchMeansAlgorithm(event.getTask().getServiceTime());
            }
            else{
                this.batchController.getSystemResponseTimeTask2().batchMeansAlgorithm(event.getTask().getServiceTime());
                this.batchController.getCloudResponseTimeTask2().batchMeansAlgorithm(event.getTask().getServiceTime());
                if(((Task2)event.getTask()).isPreemptive()){
                    this.batchController.getSystemResponseTimeInterrupted().
                            batchMeansAlgorithm(event.getTask().getCompletionTime() - event.getTask().getArrivalTime());
                }
            }
        }
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------------------------------------------\n" +
                "Cloud information: \n" +
                "n1 = " + n1 + "\n" +
                "n2 = " + n2 + "\n" +
                "n1CloudComplete = " + n1CloudComplete + "\n" +
                "n2CloudComplete = " + n2CloudComplete + "\n" +
                "--------------------------------------------------------------------------------";
    }
}
