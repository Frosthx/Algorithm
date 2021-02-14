import java.util.*;

public class _480_medianSlidingWindow {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - (k - 1)];
        DualHeap dh = new DualHeap();
        for(int i = 0; i < k; i++)
            dh.add(nums[i]);
        res[0] = dh.getMedian();
        for(int i = 1; i < res.length; i++){
            dh.remove(nums[i - 1]);
            dh.add(nums[i + k - 1]);
            res[i] = dh.getMedian();
        }
        return res;
    }
}

class DualHeap{
    PriorityQueue<Integer> small;
    PriorityQueue<Integer> large;
    public DualHeap(){
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
    }
    public void add(int num){
        if(small.size() != large.size()){
            large.offer(num);
            small.offer(large.poll());
        } else{
            small.offer(num);
            large.offer(small.poll());
        }
    }
    public void remove(int num){
        if(num >= large.peek())
            large.remove(num);
        else
            small.remove(num);
        if(small.size() < large.size() - 1)
            small.offer((large.poll()));
        else if(small.size() > large.size())
            large.offer(small.poll());
    }
    public double getMedian(){
        return small.size() != large.size() ? large.peek() : ((double)small.peek() + large.peek()) / 2;
    }
}