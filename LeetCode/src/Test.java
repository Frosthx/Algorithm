import java.util.*;

public class Test {
    public static long NUM = 10000 * 10000 * 100;
    public static void main(String[] args) throws InterruptedException{
//        int[] nums = {1,2,3};
//        for(int num : nums)
//            nums[num % nums.length] ++;
//        System.out.println(Arrays.toString(nums));
        String s = "11231";
/*        parallel();
        serial();*/
    }
    public static void parallel() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for(long i = 0; i < NUM; i++);
        });
        long start = System.currentTimeMillis();
        thread.start();
        for(long i = 1; i < NUM; i*=5);
        thread.join();
        System.out.println((System.currentTimeMillis() - start));
    }
    public static void serial(){
        long start = System.currentTimeMillis();
        for(long i = 0; i < NUM; i++);
        for(long i = 1; i < NUM; i*=5);
        System.out.println((System.currentTimeMillis() - start));
    }
}
