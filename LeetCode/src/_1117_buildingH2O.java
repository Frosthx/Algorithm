import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class _1117_buildingH2O {
    private Semaphore h = new Semaphore(2);
    private Semaphore o = new Semaphore(1);
    private CyclicBarrier cb = new CyclicBarrier(3, () -> {
        h.release(2);
        o.release();
    });
    public _1117_buildingH2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        try{
            cb.await();
        }
        catch(BrokenBarrierException e){
            e.printStackTrace();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        try{
            cb.await();
        }
        catch(BrokenBarrierException e){
            e.printStackTrace();
        }
    }
}
