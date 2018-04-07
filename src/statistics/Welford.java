package statistics;

public class Welford {
    private double mean;
    private double oldMean;
    private double variance;
    private int count;

    public Welford(){
        this.mean = 0;
        this.oldMean = 0;
        this.variance = 0;
        this.count = 0;
    }

    public void incrementCount(){
        this.count++;
    }

    public void updateMean(double value){
        this.oldMean = this.mean;
        this.mean = this.mean + (value - this.mean)/count;
    }

    public void updateVariance(double value){
        this.variance = this.variance + (1 - 1/this.count) * Math.pow(value - this.oldMean, 2);
    }

    /**
     * Restores the count value to zero, in order to executes a new batch
     */
    public void restoreCounter(){
        this.count = 0;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getOldMean() {
        return oldMean;
    }

    public void setOldMean(double oldMean) {
        this.oldMean = oldMean;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
