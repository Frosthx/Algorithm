import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException{
        Semaphore sem = new Semaphore(1);
        sem.tryAcquire(1, TimeUnit.MILLISECONDS);
        Thread test = new TestThread();
        test.start();
        Thread.sleep(10);
        test.interrupt();
    }
}

class TestThread extends Thread{
    public void run(){
        try{
            BigInteger i = BigInteger.ZERO;
            while(!Thread.currentThread().isInterrupted()){
                System.out.println(i = i.add(BigInteger.ONE));
            }
        }
        catch (Exception e){
            System.out.println(Thread.currentThread() + "has a exception" + e.getCause());
        }
    }
}