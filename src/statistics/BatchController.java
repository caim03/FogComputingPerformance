package statistics;

public class BatchController {
    private static BatchController controller = null;

    /* ------ RESPONSE TIMES ------ */

    /* System response time batches */
    private BatchMeans systemResponseTime;
    private BatchMeans systemResponseTimeTask1;
    private BatchMeans systemResponseTimeTask2;
    private BatchMeans systemResponseTimeInterrupted;

    /* Cloudlet response time batches */
    private BatchMeans cletResponseTimeTask1;
    private BatchMeans cletResponseTimeTask2;

    /* Cloud response time batches */
    private BatchMeans cloudResponseTimeTask1;
    private BatchMeans cloudResponseTimeTask2;

    /* ---------------------------- */


    /* -------- THROUGHPUT -------- */

    /* System throughput batches */
    private BatchMeans systemThroughput;
    private BatchMeans systemThroughputTask1;
    private BatchMeans systemThroughputTask2;

    /* Cloudlet throughput batches */
    private BatchMeans cletThroughput;
    private BatchMeans cletThroughputTask1;
    private BatchMeans cletThroughputTask2;

    /* ---------------------------- */


    /* -------- POPULATION -------- */

    /* Population cloudlet batches */
    private BatchMeans cletPopulationTask1;
    private BatchMeans cletPopulationTask2;

    /* Population cloud batches */
    private BatchMeans cloudPopulationTask1;
    private BatchMeans cloudPopulationTask2;

    /* ---------------------------- */


    private BatchController(){
        this.systemResponseTime = new BatchMeans();
        this.systemResponseTimeTask1 = new BatchMeans();
        this.systemResponseTimeTask2 = new BatchMeans();
        this.systemResponseTimeInterrupted = new BatchMeans();

        this.cletResponseTimeTask1 = new BatchMeans();
        this.cletResponseTimeTask2 = new BatchMeans();

        this.cloudResponseTimeTask1 = new BatchMeans();
        this.cloudResponseTimeTask2 = new BatchMeans();

        this.systemThroughput = new BatchMeans();
        this.systemThroughputTask1 = new BatchMeans();
        this.systemThroughputTask2 = new BatchMeans();

        this.cletThroughput = new BatchMeans();
        this.cletThroughputTask1 = new BatchMeans();
        this.cletThroughputTask2 = new BatchMeans();

        this.cletPopulationTask1 = new BatchMeans();
        this.cletPopulationTask2 = new BatchMeans();

        this.cloudPopulationTask1 = new BatchMeans();
        this.cloudPopulationTask2 = new BatchMeans();
    }

    public static BatchController getInstance(){
        if(controller == null){
            controller = new BatchController();
        }
        return controller;
    }

    public BatchMeans getSystemResponseTime() {
        return systemResponseTime;
    }

    public void setSystemResponseTime(BatchMeans systemResponseTime) {
        this.systemResponseTime = systemResponseTime;
    }

    public BatchMeans getSystemResponseTimeTask1() {
        return systemResponseTimeTask1;
    }

    public void setSystemResponseTimeTask1(BatchMeans systemResponseTimeTask1) {
        this.systemResponseTimeTask1 = systemResponseTimeTask1;
    }

    public BatchMeans getSystemResponseTimeTask2() {
        return systemResponseTimeTask2;
    }

    public void setSystemResponseTimeTask2(BatchMeans systemResponseTimeTask2) {
        this.systemResponseTimeTask2 = systemResponseTimeTask2;
    }

    public BatchMeans getSystemResponseTimeInterrupted() {
        return systemResponseTimeInterrupted;
    }

    public void setSystemResponseTimeInterrupted(BatchMeans systemResponseTimeInterrupted) {
        this.systemResponseTimeInterrupted = systemResponseTimeInterrupted;
    }

    public BatchMeans getCletResponseTimeTask1() {
        return cletResponseTimeTask1;
    }

    public void setCletResponseTimeTask1(BatchMeans cletResponseTimeTask1) {
        this.cletResponseTimeTask1 = cletResponseTimeTask1;
    }

    public BatchMeans getCletResponseTimeTask2() {
        return cletResponseTimeTask2;
    }

    public void setCletResponseTimeTask2(BatchMeans cletResponseTimeTask2) {
        this.cletResponseTimeTask2 = cletResponseTimeTask2;
    }

    public BatchMeans getCloudResponseTimeTask1() {
        return cloudResponseTimeTask1;
    }

    public void setCloudResponseTimeTask1(BatchMeans cloudResponseTimeTask1) {
        this.cloudResponseTimeTask1 = cloudResponseTimeTask1;
    }

    public BatchMeans getCloudResponseTimeTask2() {
        return cloudResponseTimeTask2;
    }

    public void setCloudResponseTimeTask2(BatchMeans cloudResponseTimeTask2) {
        this.cloudResponseTimeTask2 = cloudResponseTimeTask2;
    }

    public BatchMeans getSystemThroughput() {
        return systemThroughput;
    }

    public void setSystemThroughput(BatchMeans systemThroughput) {
        this.systemThroughput = systemThroughput;
    }

    public BatchMeans getSystemThroughputTask1() {
        return systemThroughputTask1;
    }

    public void setSystemThroughputTask1(BatchMeans systemThroughputTask1) {
        this.systemThroughputTask1 = systemThroughputTask1;
    }

    public BatchMeans getSystemThroughputTask2() {
        return systemThroughputTask2;
    }

    public void setSystemThroughputTask2(BatchMeans systemThroughputTask2) {
        this.systemThroughputTask2 = systemThroughputTask2;
    }

    public BatchMeans getCletThroughput() {
        return cletThroughput;
    }

    public void setCletThroughput(BatchMeans cletThroughput) {
        this.cletThroughput = cletThroughput;
    }

    public BatchMeans getCletThroughputTask1() {
        return cletThroughputTask1;
    }

    public void setCletThroughputTask1(BatchMeans cletThroughputTask1) {
        this.cletThroughputTask1 = cletThroughputTask1;
    }

    public BatchMeans getCletThroughputTask2() {
        return cletThroughputTask2;
    }

    public void setCletThroughputTask2(BatchMeans cletThroughputTask2) {
        this.cletThroughputTask2 = cletThroughputTask2;
    }

    public BatchMeans getCletPopulationTask1() {
        return cletPopulationTask1;
    }

    public void setCletPopulationTask1(BatchMeans cletPopulationTask1) {
        this.cletPopulationTask1 = cletPopulationTask1;
    }

    public BatchMeans getCletPopulationTask2() {
        return cletPopulationTask2;
    }

    public void setCletPopulationTask2(BatchMeans cletPopulationTask2) {
        this.cletPopulationTask2 = cletPopulationTask2;
    }

    public BatchMeans getCloudPopulationTask1() {
        return cloudPopulationTask1;
    }

    public void setCloudPopulationTask1(BatchMeans cloudPopulationTask1) {
        this.cloudPopulationTask1 = cloudPopulationTask1;
    }

    public BatchMeans getCloudPopulationTask2() {
        return cloudPopulationTask2;
    }

    public void setCloudPopulationTask2(BatchMeans cloudPopulationTask2) {
        this.cloudPopulationTask2 = cloudPopulationTask2;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------------------------------------------\n" +
                " RESPONSE TIMES: \n" +
                " - System Global: " + this.systemResponseTime.getMeanOfBatches() + "\n" +
                " - System Task1: " + this.systemResponseTimeTask1.getMeanOfBatches() + "\n" +
                " - System Task2: " + this.systemResponseTimeTask2.getMeanOfBatches() + "\n" +
                " - System Interrupted: " + this.systemResponseTimeInterrupted.getMeanOfBatches() + "\n" +
                " - Cloudlet Task1: " + this.cletResponseTimeTask1.getMeanOfBatches() + "\n" +
                " - Cloudlet Task2: " + this.cletResponseTimeTask2.getMeanOfBatches() + "\n" +
                " - Cloud Task1: " + this.cloudResponseTimeTask1.getMeanOfBatches() + "\n" +
                " - Cloud Task2: " + this.cloudResponseTimeTask2.getMeanOfBatches() + "\n\n" +
                " THROUGHPUT: \n" +
                " - System Global: " + this.systemThroughput.getMeanOfBatches() + "\n" +
                " - System Task1: " + this.systemThroughputTask1.getMeanOfBatches() + "\n" +
                " - System Task2: " + this.systemThroughputTask2.getMeanOfBatches() + "\n" +
                " - Cloudlet Global: " + this.cletThroughput.getMeanOfBatches() + "\n" +
                " - Cloudlet Task1: " + this.cletThroughputTask1.getMeanOfBatches() + "\n" +
                " - Cloudlet Task2: " + this.cletThroughputTask2.getMeanOfBatches() + "\n\n" +
                " POPULATION: \n" +
                " - Cloudlet Task1: " + this.cletPopulationTask1.getMeanOfBatches() + "\n" +
                " - Cloudlet Task2: " + this.cletPopulationTask2.getMeanOfBatches() + "\n" +
                " - Cloud Task1: " + this.cloudPopulationTask1.getMeanOfBatches() + "\n" +
                " - Cloud Task2: " + this.cloudPopulationTask2.getMeanOfBatches() + "\n" +
                "--------------------------------------------------------------------------------";

    }
}
