package statistics;

// TODO NESSUNO CHIAMA IL COMPUTE MEAN

import utilities.Clock;
import utilities.Configuration;
import utilities.Rvms;

import java.util.ArrayList;

public class BatchMeans {
    private ArrayList<Double> meanList;
    private int batchPointer;

    private Welford welford;

    private double meanOfBatches;
    private double devStandard;
    private double criticalValue;
    private double lowerEndPoint, upperEndPoint;

    public BatchMeans(){
        this.meanList = new ArrayList<>();
        this.batchPointer = 1;
        this.welford = new Welford();
        this.meanOfBatches = 0;
        this.devStandard = 0;
    }

    public void batchMeansAlgorithm(double value){
        /* If we are in a new batch */
        if(Clock.getIstance().getCurrTime() >= Configuration.batchSize * this.batchPointer){
            /* Retrieve mean of previous batch and put it into a list */
            this.meanList.add(welford.getMean());

            /* Update counters and Welford values */
            this.batchPointer++;
            welford.setMean(0);
            welford.setCount(0);
            welford.setOldMean(0);
        }

        /* Send new value to Welford Algorithm */
        welford.incrementCount();
        welford.updateMean(value);
    }

    public void computeMean(){
        int k = this.meanList.size();
        double sum = 0;
        for(Double mean : this.meanList) {
            sum = sum + mean;
        }
        this.meanOfBatches = (1/k) * sum;
    }

    public void computeDevStandard(){
        int k = this.meanList.size();
        double sum = 0;
        for(Double mean : this.meanList) {
            sum = sum + Math.pow(mean - this.meanOfBatches,2);
        }
        this.devStandard = (1/k) * sum;
    }

    public void computeCriticalValue(){
        Rvms rvms = new Rvms();
        this.criticalValue = rvms.idfStudent(Configuration.batchNumber - 1, 1 - Configuration.alfa/2);
    }

    public void computeEndpoints(){
        this.lowerEndPoint = this.meanOfBatches - (this.criticalValue * this.devStandard)/Math.sqrt(Configuration.batchNumber - 1);
        this.upperEndPoint = this.meanOfBatches + (this.criticalValue * this.devStandard)/Math.sqrt(Configuration.batchNumber - 1);
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

    public double getDevStandard() {
        return devStandard;
    }

    public void setDevStandard(double devStandard) {
        this.devStandard = devStandard;
    }

    public double getCriticalValue() {
        return criticalValue;
    }

    public void setCriticalValue(double criticalValue) {
        this.criticalValue = criticalValue;
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
}
