import java.util.Deque;
import java.util.LinkedList;

public class _1438_longestSubarray {
    public int longestSubarray(int[] nums, int limit) {
        int max = 0;
        if(nums == null || nums.length == 0)
            return 0;
        Deque<Integer> maxQ = new LinkedList<>();
        Deque<Integer> minQ = new LinkedList<>();
        for(int i = 0, j = 0; j < nums.length; j++){
            while(!maxQ.isEmpty() && maxQ.peekLast() < nums[j])
                maxQ.pollLast();
            while(!minQ.isEmpty() && minQ.peekLast() > nums[j])
                minQ.pollLast();
            maxQ.offerLast(nums[j]);
            minQ.offerLast(nums[j]);
            while(!maxQ.isEmpty() && !minQ.isEmpty() && maxQ.peekFirst() - minQ.peekFirst() > limit){
                if(nums[i] == maxQ.peekFirst())
                    maxQ.pollFirst();
                if(nums[i] == minQ.peekFirst())
                    minQ.pollFirst();
                i++;
            }
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
}
