package fogComputing;

import statistics.Welford;
import utilities.Clock;
import utilities.Configuration;

import java.util.ArrayList;

public class TransientController {

    private static TransientController transientController = null;

    private ArrayList<Double> transientMean;
    private ArrayList<Double> transientMeanTask1;
    private ArrayList<Double> transientMeanTask2;

    private Welford welford;
    private Welford welfordTask1;
    private Welford welfordTask2;

    private int batchPointer;

    private TransientController(){
        this.transientMean = new ArrayList<>();
        this.transientMeanTask1 = new ArrayList<>();
        this.transientMeanTask2 = new ArrayList<>();

        this.welford = new Welford();
        this.welfordTask1 = new Welford();
        this.welfordTask2 = new Welford();

        this.batchPointer = 1;
    }

    public static TransientController getInstance(){
        if(transientController == null){
            transientController = new TransientController();
        }
        return transientController;
    }

    public ArrayList<Double> getTransientMean() {
        return transientMean;
    }

    public void setTransientMean(ArrayList<Double> transientMean) {
        this.transientMean = transientMean;
    }

    public void updateTransient(double value1, double value2, double time) {
        if (Clock.getIstance().getCurrTime() >= Configuration.batchSize * this.batchPointer) {
            Clock.getIstance().setLastTransient(0);
            Cloudlet.getInstance().setTransN1Complete(0);
            Cloudlet.getInstance().setTransN2Complete(0);

            /*this.transientMean.add(welford.getMean());
            this.transientMeanTask1.add(welfordTask1.getMean());
            this.transientMeanTask2.add(welfordTask2.getMean());*/

            this.batchPointer++;

            /*welford.restoreCounter();
            welford.setMean(0);
            welford.setCount(0);
            welford.setOldMean(0);

            welfordTask1.restoreCounter();
            welfordTask1.setMean(0);
            welfordTask1.setCount(0);
            welfordTask1.setOldMean(0);

            welfordTask2.restoreCounter();
            welfordTask2.setMean(0);
            welfordTask2.setCount(0);
            welfordTask2.setOldMean(0);*/
        }

        /*welford.incrementCount();
        welford.updateMean((value1+value2)/time);

        welfordTask1.incrementCount();
        welfordTask1.updateMean((value1)/time);

        welfordTask2.incrementCount();
        welfordTask2.updateMean((value2)/time);*/

        this.transientMean.add((value1+value2)/time);
        //this.transientMeanTask1.add((value1)/time);
        //this.transientMeanTask2.add((value2)/time);
    }



    public void printList(){
        System.out.println("\nGlobal Transient List:");
        System.out.println(this.transientMean);

        System.out.println("Task1 Transient List:");
        System.out.println(this.transientMeanTask1);

        System.out.println("Task2 Transient List:");
        System.out.println(this.transientMeanTask2);
    }
}
