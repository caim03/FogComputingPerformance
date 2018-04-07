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
