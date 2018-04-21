package statistics;

import utilities.Clock;
import utilities.Configuration;
import utilities.Rvms;

import java.util.ArrayList;

public class BatchMeans {
    private ArrayList<Double> meanList;
    private ArrayList<Double> megaLista;
    private int batchPointer;

    private Welford welford;

    private double meanOfBatches;
    private double lowerEndPoint, upperEndPoint;

    public BatchMeans(){
        this.meanList = new ArrayList<>();
        this.megaLista = new ArrayList<>();
        this.batchPointer = 1;
        this.welford = new Welford();
        this.meanOfBatches = 0;
    }

    public void batchMeansAlgorithm(double value){
        /* If we are in a new batch */
        if(Clock.getIstance().getCurrTime() >= Configuration.batchSize * this.batchPointer){
            /* Retrieve mean of previous batch and put it into a list */
            this.meanList.add(welford.getMean());

            /* Update counters and Welford values */
            this.batchPointer++;
            welford.restoreCounter();
            welford.setMean(0);
            welford.setCount(0);
            welford.setOldMean(0);
        }

        this.megaLista.add(value);
        /* Send new value to Welford Algorithm */
        welford.incrementCount();
        welford.updateMean(value);
    }

    public ArrayList<Double> getMegaLista() {
        return megaLista;
    }

    public void computeMean(){
        int k = this.meanList.size();
        double sum = 0;
        for(Double mean : this.meanList) {
            sum = sum + mean;
        }
        this.meanOfBatches = ((double)1/k) * sum;
    }

    private double computeDevStandard(){
        int k = this.meanList.size();
        double sum = 0;
        for(Double mean : this.meanList) {
            sum = sum + Math.pow(mean - this.meanOfBatches,2);
        }
        return Math.sqrt(((double)1/k) * sum);
    }

    private double computeCriticalValue(){
        Rvms rvms = new Rvms();
        return rvms.idfStudent(Configuration.batchNumber - 1, 1 - (Configuration.alfa/2));
    }

    public void computeEndpoints(){
        double criticalValue = computeCriticalValue();
        double devStandard = computeDevStandard();

        this.lowerEndPoint = this.meanOfBatches - (criticalValue * devStandard)/Math.sqrt(Configuration.batchNumber - 1);
        this.upperEndPoint = this.meanOfBatches + (criticalValue * devStandard)/Math.sqrt(Configuration.batchNumber - 1);
    }

    public ArrayList<Double> getMeanList() {
        return meanList;
    }

    public void setMeanList(ArrayList<Double> meanList) {
        this.meanList = meanList;
    }

    public int getBatchPointer() {
        return batchPointer;
    }

    public void setBatchPointer(int batchPointer) {
        this.batchPointer = batchPointer;
    }

    public double getMeanOfBatches() {
        return meanOfBatches;
    }

    public void setMeanOfBatches(double meanOfBatches) {
        this.meanOfBatches = meanOfBatches;
    }

    public double getLowerEndPoint() {
        return lowerEndPoint;
    }

    public void setLowerEndPoint(double lowerEndPoint) {
        this.lowerEndPoint = lowerEndPoint;
    }

    public double getUpperEndPoint() {
        return upperEndPoint;
    }

    public void setUpperEndPoint(double upperEndPoint) {
        this.upperEndPoint = upperEndPoint;
    }

    public void printMeanList2(){
        for(int i=0; i<this.meanList.size(); i++){
            if(i == this.meanList.size() -1){
                System.out.println("[" + this.meanList.get(i) + "]\n");
                System.out.println(" ------------ ");
            }
            else{
                System.out.println("[" + this.meanList.get(i) + "],");
            }
        }
    }

    public void printMeanList(){
        System.out.println(this.meanList);
    }
}
