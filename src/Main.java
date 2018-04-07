import event.Event;
import event.EventArrival;
import event.EventList;
import fogComputing.Cloud;
import fogComputing.Cloudlet;
import fogComputing.ControllerCloudlet;
import statistics.BatchController;
import task.Task;
import task.Task1;
import task.Task2;
import utilities.*;



public class Main {

    public static void main(String[] args) {
        /* --- Initialization --- */
        long begin, end;

        begin = System.nanoTime();

        System.out.println("\n---------- START SIMULATION ----------\n");

        Clock clock = Clock.getIstance();
        EventList eventList = EventList.getInstance();
        Cloudlet cloudlet = Cloudlet.getInstance();
        Cloud cloud = Cloud.getInstance();
        ControllerCloudlet controller = ControllerCloudlet.getInstance();
        RvgsWrapper rvgsWrapper = RvgsWrapper.getInstance();

        rvgsWrapper.setSeed(Configuration.seed);

        /* --- End Initialization --- */

        while (clock.getLastArrival() < Configuration.stop || eventList.getSize() > 0){

            /* If arrivals are already allowed */
            if(clock.getLastArrival() < Configuration.stop){
                generateArrival();
            }

            Event event = eventList.popEvent();
            clock.setCurrTime(event.getTime());


            if(event instanceof EventArrival){
                if(!event.isCloudlet()){
                    cloud.handleEvent(event);
                }
                else{
                    controller.dispatchArrival((EventArrival) event);
                }
            }
            else{
                if(event.isCloudlet()){
                    cloudlet.handleEvent(event);
                }
                else{
                    cloud.handleEvent(event);
                }
            }
        }

        /*
        System.out.println(controller.toString());
        System.out.println(cloudlet.toString());
        System.out.println(cloud.toString());
        */

        end = System.nanoTime();
        System.out.println("\n" + (end - begin)/1000000000.0 + " seconds");

        System.out.println(BatchController.getInstance().toString());

        System.out.println("\n---------- END SIMULATION ----------\n");
    }

    private static void generateArrival(){
        EventArrival event;
        Task task;
        double p;

        RvgsWrapper.getInstance().selectStream(0);
        p = RvgsWrapper.getInstance().uniform(0,1);

        /* Arrivals are generated in monotonic way */

        if(p <= Configuration.lambda1/(Configuration.lambda1 + Configuration.lambda2)){
            task = new Task1(Clock.getIstance().getLastArrival() + RvgsWrapper.getInstance().exponential(1/(Configuration.lambda1 + Configuration.lambda2)));
        }
        else{
            task = new Task2(Clock.getIstance().getLastArrival() + RvgsWrapper.getInstance().exponential(1/(Configuration.lambda1 + Configuration.lambda2)));
        }

        event = new EventArrival(task);
        event.setTime(task.getArrivalTime());
        event.setCloudlet(true);
        Clock.getIstance().setLastArrival(task.getArrivalTime());
        EventList.getInstance().pushEvent(event);
    }
}
