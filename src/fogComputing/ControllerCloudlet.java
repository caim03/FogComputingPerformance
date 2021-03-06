package fogComputing;

import event.EventArrival;
import task.Task1;
import utilities.Configuration;

public class ControllerCloudlet {
    private static ControllerCloudlet controllerCloudlet = null;
    private Cloudlet cloudlet;
    private Cloud cloud;

    private int n1Arrival;
    private int n2Arrival;
    private int n1Reject;
    private int n2Reject;

    private ControllerCloudlet(){
        cloudlet = Cloudlet.getInstance();
        cloud = Cloud.getInstance();

        this.n1Arrival = 0;
        this.n2Arrival = 0;
        this.n1Reject = 0;
        this.n2Reject = 0;
    }

    public static ControllerCloudlet getInstance(){
        if(controllerCloudlet == null){
            controllerCloudlet = new ControllerCloudlet();
        }
        return controllerCloudlet;
    }

    public void dispatchArrival(EventArrival event){
        if(event.getTask() instanceof Task1){
            this.n1Arrival++;
            /* Reject class 1 tasks */
            if(this.cloudlet.getN1() == Configuration.N){
                this.n1Reject++;
                cloud.handleEvent(event);
            }
            /* Anyway accept class 1 tasks */
            else if(this.cloudlet.getN1() + this.cloudlet.getN2() < Configuration.S){
                cloudlet.handleEvent(event);
            }
            else if(this.cloudlet.getN2() > 0){
                cloudlet.handlePreemption(event);
                cloudlet.handleEvent(event);
            }
            else{
                cloudlet.handleEvent(event);
            }
        }
        else{
            this.n2Arrival++;

            /* Reject class 2 tasks */
            if(this.cloudlet.getN1() + this.cloudlet.getN2() >= Configuration.S){
                this.n2Reject++;
                cloud.handleEvent(event);
            }
            /* Accept class 2 tasks */
            else{
                cloudlet.handleEvent(event);
            }
        }

        cloudlet.computeStatistics(event);
    }

    public int getN1Arrival() {
        return n1Arrival;
    }

    public void setN1Arrival(int n1Arrival) {
        this.n1Arrival = n1Arrival;
    }

    public int getN2Arrival() {
        return n2Arrival;
    }

    public void setN2Arrival(int n2Arrival) {
        this.n2Arrival = n2Arrival;
    }

    public int getN1Reject() {
        return n1Reject;
    }

    public void setN1Reject(int n1Reject) {
        this.n1Reject = n1Reject;
    }

    public int getN2Reject() {
        return n2Reject;
    }

    public void setN2Reject(int n2Reject) {
        this.n2Reject = n2Reject;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------------------------------------------\n" +
                "Controller information: \n" +
                "n1Arrival = " + n1Arrival + "\n" +
                "n2Arrival = " + n2Arrival + "\n" +
                "n1Reject = " + n1Reject + "\n" +
                "n2Reject = " + n2Reject + "\n" +
                "--------------------------------------------------------------------------------";
    }
}
