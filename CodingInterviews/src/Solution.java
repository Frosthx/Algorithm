import org.junit.jupiter.api.Test;

import java.util.*;

//数组中重复的数字
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

//二维数组中的查找
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

//替换空格
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

//从尾到头打印链表
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

//重建二叉树
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

//用两个栈实现队列
class _09_CQueue {
    private final Deque<Integer> stack1;
    private final Deque<Integer> stack2;
    public _09_CQueue() {
        stack1=new ArrayDeque<>();
        stack2=new ArrayDeque<>();
    }
    public void appendTail(int value) {
        stack1.push(value);
    }
    public int deleteHead() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty())
                stack2.push(stack1.pop());
            if(stack2.isEmpty())
                return -1;
        }
        return stack2.pop();
    }
}

//斐波那契数列
class _10_fib {
    public int fib(int n) {
        if(n<2)    return n;
        int num1=0;
        int num2=1;
        int res=0;
        for(int i=2;i<=n;++i){
            res=(num1+num2)%1000000007;
            num1=num2;
            num2=res;
        }
        return res;
    }
}

//青蛙跳台阶问题
class _10_numWays {
    public int numWays(int n) {
        if(n<2) return 1;
        if(n==2)    return 2;
        int numMinusTwo=1;
        int numMinusOne=2;
        int num=0;
        for(int i=3;i<=n;++i){
            num=(numMinusTwo+numMinusOne)%1000000007;
            numMinusTwo=numMinusOne;
            numMinusOne=num;
        }
        return num;
    }
}

//旋转数组中的最小数字
class _11_minArray {
    public int minArray(int[] numbers) {
        int left=0;
        int right=numbers.length-1;
        if(numbers[left]<numbers[right])
            return numbers[left];
        int pivot=left+((right-left)>>1);
        if(numbers[left]==numbers[pivot] && numbers[pivot]==numbers[right]){
            int min=numbers[left];
            for(int i=left+1;i<=right;i++)
                min=Math.min(min,numbers[i]);
            return min;
        }
        while(left+1!=right){
            pivot=left+((right-left)>>1);
            if(numbers[pivot]<=numbers[right])
                right=pivot;
            else if(numbers[pivot]>numbers[right])
                left=pivot;
        }
        return numbers[right];
    }
}

//矩阵中的路径
class _12_exist {
    private boolean[][] visited;
    private char[] word;
    private char[][] board;
    public boolean exist(char[][] board, String word) {
        this.board=board;
        this.word=word.toCharArray();
        visited=new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++)
                if(DFS(0,i,j))
                    return true;
        return false;
    }
    private boolean DFS(int pos,int x,int y){
        if(visited[x][y] || board[x][y]!=word[pos])
            return false;
        visited[x][y]=true;
        if(pos==word.length-1)
            return true;
        if((x>0 && DFS(pos+1,x-1,y)) ||
                (y>0 && DFS(pos+1,x,y-1)) ||
                (x<board.length-1 && DFS(pos+1,x+1,y)) ||
                (y<board[0].length-1 && DFS(pos+1,x,y+1)))
            return true;
        visited[x][y]=false;
        return false;
    }
}

//机器人的运动范围
class _13_movingCount {
    private int m;
    private int n;
    private int k;
    private BitSet visited;
    public int movingCount(int m, int n, int k) {
        if(k==0)    return 1;
        this.m=m;
        this.n=n;
        this.k=k;
        visited=new BitSet(m*n);
        DFS(0,0);
        return visited.cardinality();
    }
    private void DFS(int x,int y){
        visited.set(x*n+y);
        if(checkAccess(x+1,y) && !visited.get((x+1)*n+y))    DFS(x+1,y);
        if(checkAccess(x,y+1) && !visited.get(x*n+y+1))    DFS(x,y+1);
    }
    private boolean checkAccess(int x,int y){
        if(x<0 || x>=m || y<0 || y>=n)
            return false;
        int sum=0;
        while(x!=0){
            sum+=x%10;
            x/=10;
        }
        while(y!=0){
            sum+=y%10;
            y/=10;
        }
        return sum <= k;
    }
}

//剪绳子
class _14_cuttingRope {
    public int cuttingRope(int n) {
        if(n<2) return 0;
        if(n==2)    return 1;
        if(n==3)    return 2;
        int[] res=new int[n+1];
        res[0]=0;
        res[1]=1;
        res[2]=2;
        res[3]=3;       //3(4)及以下长度绳子的最大乘积为其自身（m==1）
        int max;
        for(int i=4;i<=n;i++){
            max=0;
            for(int j=1;j<=i/2;j++){
                max=Math.max(max,res[j]*res[i-j]);
                res[i]=max;
            }
        }
        return res[n];
    }
}

//二进制中1的个数
class _15_hammingWeight {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count=0;
        while(n!=0){
            count++;
            n&=(n-1);
        }
        return count;
    }
}

//数值的整数次方（快速幂）
class _16_myPow {
    public double myPow(double x, int n) {
        if(x==0 || n==0)    return 1;
        long b=n;       //用long代替int以防b=-b溢出
        if(b<0){
            x=1/x;
            b=-b;
        }
        double result=1.0;
        while(b>0){
            if((b&1)==1)
                result*=x;
            x*=x;
            b>>=1;
        }
        return result;
    }
}

//打印从1到最大的n位数，未考虑大数
class _17_printNumbers {
    public int[] printNumbers(int n) {
        int numSize=(int)Math.pow(10,n)-1;
        int[] nums=new int[numSize];
        for(int i=0;i<numSize;i++)
            nums[i]=i+1;
        return nums;
    }
}

//17题考虑大数
class _17_printNumbers_string {
    private StringBuilder res;
    private int start;
    private int n;
    private int nine = 0;
    private char[] num;
    private final char[] loop = {'0','1','2','3','4','5','6','7','8','9'};
    public String printNumbers(int n){
        this.n = n;
        num = new char[n];
        res = new StringBuilder();
        start = n - 1;
        DFS(0);
        res.deleteCharAt(res.length() - 1);
        return res.substring(2,res.length());       //去除开头的"0,"和末尾的","
    }
    private void DFS(int x){
        if(x == n){
            res.append(String.valueOf(num).substring(start)).append(",");
            if(n - start == nine)   start--;
            return;
        }
        for(char i : loop){
            if(i == '9')    nine++;
            num[x] = i;
            DFS(x + 1);
        }
        nine--;
    }
}

//删除链表的节点
class _18_deleteNode {
    public ListNode deleteNode(ListNode head, ListNode toBeDeleted) {
        if(head == null || toBeDeleted == null) return null;
        if(head == toBeDeleted) return head.next;
        if(toBeDeleted.next != null){
            ListNode next = toBeDeleted.next;
            toBeDeleted.val = next.val;
            toBeDeleted.next = next.next;
        }
        else{
            ListNode node = head;
            while(node.next != toBeDeleted)
                node = node.next;
            node.next = null;
        }
        return head;
    }
}

//删除链表的节点（相比原题有改动）
class _18_deleteNodeModified {
    public ListNode deleteNode(ListNode head, int val) {
        if(head == null)    return null;
        if(head.val == val) return head.next;
        ListNode node = head;
        while(node.next != null){
            if(node.next.val == val){
                node.next = node.next.next;
                return head;
            }
            node = node.next;
        }
        return null;
    }
}

class UnitTest{
    @Test
    public void test1(){

    }
}
