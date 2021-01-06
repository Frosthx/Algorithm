public class Solution {
    public int _03_findRepeatNumber(int[] nums) {
        if(nums==null)
            return -1;
        for(int num:nums){
            if(num<0 || num>=nums.length)
                return -1;
        }
        int temp = -1;
        for(int i = 0; i < nums.length; ++i){
            while(nums[i]!=i){
                if(nums[i]==nums[nums[i]])
                    return nums[i];
                temp=nums[i];
                nums[i]=nums[temp];
                nums[temp]=temp;
            }
        }
        return -1;
    }
    public boolean _04_findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return false;
        int row=0;
        int col=matrix[0].length-1;
        while(row<matrix.length && col>=0){
            if(matrix[row][col]==target)
                return true;
            else if(matrix[row][col]<target)
                ++row;
            else
                --col;
        }
        return false;
    }
    public String _05_replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c==' ')
                sb.append("%20");
            else
                sb.append(c);
        }
        return sb.toString();
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x; }
    }
    public int[] _06_reversePrint(ListNode head) {
        int count=0;
        ListNode node=head;
        while(node!=null){
            count++;
            node=node.next;
        }
        int[] res=new int[count];
        for(int i=res.length-1;i>=0;i--){
            res[i]=head.val;
            head=head.next;
        }
        return res;
    }
}
