import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class _03_findRepeatNumber{
    public int findRepeatNumber(int[] nums) {
        if(nums==null)
            return -1;
        for(int num:nums){
            if(num<0 || num>=nums.length)
                return -1;
        }
        int temp;
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
}

class _04_findNumberIn2DArray{
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
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
}

class _05_replaceSpace{
    public String replaceSpace(String s) {
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
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x; }
}
class _06_reversePrint{
    public int[] reversePrint(ListNode head) {
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class _07_buildTree {
    private int[] preorder;
    private final Map<Integer,Integer> rootPos=new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder=preorder;
        for(int i=0;i<inorder.length;i++)
            rootPos.put(inorder[i],i);
        return getRoot(0,0,inorder.length-1);
    }
    private TreeNode getRoot(int root,int left,int right){
        if(left>right)  return null;
        TreeNode node=new TreeNode(preorder[root]);
        int pos=rootPos.get(preorder[root]);
        node.left=getRoot(root+1,left,pos-1);
        node.right=getRoot(root+1+pos-left,pos+1,right);
        return node;
    }
}


class UnitTest{
    @Test
    public void test1(){
        _05_replaceSpace s = new _05_replaceSpace();
        System.out.println(s.replaceSpace(" We are not happy. !"));
    }
}
