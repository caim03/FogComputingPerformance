package utilities;

public class RvgsWrapper {
    private static RvgsWrapper rvgsWrapper = null;
    private Rngs rngs;
    private Rvgs rvgs;

    private RvgsWrapper(){
        this.rngs = new Rngs();
        this.rvgs = new Rvgs(rngs);
    }

    public static RvgsWrapper getInstance(){
        if(rvgsWrapper == null){
            rvgsWrapper = new RvgsWrapper();
        }
        return rvgsWrapper;
    }

    public void setSeed(long seed){
        this.rngs.plantSeeds(seed);
    }

    public void selectStream(int stream){
        this.rngs.selectStream(stream);
    }

    public double exponential(double mean){
        return this.rvgs.exponential(mean);
    }

    public double uniform(double a, double b){
        return this.rvgs.uniform(a, b);
    }

    public long equilikely(long a, long b){
        return this.rvgs.equilikely(a, b);
    }
}
