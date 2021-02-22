import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

public class _1116_ZeroEvenOdd {
    private final int n;
    private volatile int state = 0;

    public _1116_ZeroEvenOdd(int n) {
        this.n = n;
    }
    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++){
            while(state != 0)   Thread.yield();
            printNumber.accept(0);
            if((i & 1) == 1)
                state = 1;
            else
                state = 2;
        }
    }
    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i <= n; i += 2){
            while(state != 2)   Thread.yield();
            printNumber.accept(i);
            state = 0;
        }
    }
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i += 2){
            while(state != 1)   Thread.yield();
            printNumber.accept(i);
            state = 0;
        }
    }
    public static void main(String[] args){
        _1116_ZeroEvenOdd zeroEvenOdd = new _1116_ZeroEvenOdd(10);
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try{
                latch.await();
                zeroEvenOdd.zero(System.out::print);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                latch.await();
                zeroEvenOdd.even(System.out::print);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                latch.await();
                zeroEvenOdd.odd(System.out::print);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
        latch.countDown();
    }
}
