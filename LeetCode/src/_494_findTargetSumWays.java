public class _494_findTargetSumWays {
    //把所有符号为正的数总和设为一个背包的容量，容量为x；把所有符号为负的数总和设为一个背包的容量，容量为y。在给定的数组中，有多少种选择方法让背包装满。令sum为数组的总和，则x+y = sum。而两个背包的差为S,则x-y=S。从而求得x=(S+sum)/2。
    //基于上述分析，题目转换为背包问题：给定一个数组和一个容量为x的背包，求有多少种方式让背包装满。
    //
    //作者：inkblack
    //链接：https://leetcode-cn.com/problems/target-sum/solution/huan-yi-xia-jiao-du-ke-yi-zhuan-huan-wei-dian-xing/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int num : nums)
            sum += num;
        if(S > sum || ((sum + S) & 1) == 1)
            return 0;
        int size = (sum + S) >> 1;
        int[] dp = new int[size + 1];
        dp[0] = 1;
        for(int num : nums)
            for(int i = size; i >= num; i--)
                dp[i] += dp[i - num];
        return dp[size];
    }
}
