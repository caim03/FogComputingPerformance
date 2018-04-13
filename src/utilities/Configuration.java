package utilities;


public class Configuration {
    /* System parameters */
    public static int N = 3;
    public static int S = 3;
    public static double lambda1 = 6;
    public static double lambda2 = 6.25;
    public static double mu1Clet = 0.45;
    public static double mu2Clet = 0.27;
    public static double mu1Cloud = 0.25;
    public static double mu2Cloud = 0.22;
    public static double setup = 0.8;

    /* Simulation parameters */
    public static double stop = 64000;
    public static long seed = 123456789;

    /* Batch means parameters */
    public static int batchNumber = 64;
    public static double batchSize = stop/batchNumber;
    public static double alfa = 0.05;
}
