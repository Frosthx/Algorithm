import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
class _18_deleteNode_Modified {
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

//正大表达式匹配（时间换空间）
class _19_isMatch_Space {
    private char[] s;
    private char[] p;
    public boolean isMatch(String s, String p) {
        if(s == null || p == null)
            return false;
        this.s = s.toCharArray();
        this.p = p.toCharArray();
        return match(0, 0);
    }
    private boolean match(int i, int j){
        if(i == s.length){
            if(((p.length - j) & 1) != 0)   return false;
            for(int k = j+1; k < p.length; k+=2)
                if(p[k] != '*') return false;
            return true;
        }
        if(j == p.length)
            return false;
        if(j < p.length - 1 && p[j+1] == '*'){
            if(s[i] == p[j] || p[j] == '.')
                return match(i+1, j+2)
                    || match(i+1, j)
                    || match(i, j+2);
            else
                return match(i, j+2);
        }
        if(s[i] == p[j] || p[j] == '.')
            return match(i+1, j+1);
        return false;
    }
}

//正大表达式匹配（空间换时间）
class _19_isMatch_Time {
    public boolean isMatch(String s, String p) {
        char[] chs = s.toCharArray(), chp = p.toCharArray();
        int m = chs.length + 1, n = chp.length + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        for(int j = 2; j < n; j += 2)
            dp[0][j] = dp[0][j - 2] && chp[j-1] == '*';
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = chp[j-1] == '*' ?
                        dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j] && (chs[i-1] == chp[j-2] || chp[j-2] == '.') :
                        dp[i - 1][j - 1] && (chp[j-1] == '.' || chs[i-1] == chp[j-1]);
            }
        }
        return dp[m - 1][n - 1];
    }
}

//表示数值的字符串
class _20_isNumber {
    public boolean isNumber(String s) {
        if(s == null || s.length() == 0)
            return false;
        boolean hasNum = false;
        boolean hasDot = false;
        boolean hasE = false;
        char[] chs = s.trim().toCharArray();
        for(int i = 0; i < chs.length; ++i){
            if(chs[i] >= '0' && chs[i] <= '9')
                hasNum = true;
            else if(chs[i] == '.'){
                if(hasDot || hasE)
                    return false;
                hasDot = true;
            }
            else if(chs[i] == 'e' || chs[i] == 'E'){
                if(hasE || !hasNum)
                    return false;
                hasE = true;
                hasNum = false;
            }
            else if(chs[i] == '+' || chs[i] == '-'){
                if(i != 0 && chs[i - 1] != 'E' && chs[i - 1] != 'e')
                    return false;
            }
            else
                return false;
        }
        return hasNum;
    }
}

//调整数组顺序使奇数位于偶数前面
class _21_exchange {
    public int[] exchange(int[] nums) {
        if(nums == null || nums.length <= 1)
            return nums;
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            while(left < nums.length && (nums[left] & 1) == 1)    left++;
            while(right >= 0 && (nums[right] & 1) == 0)   right--;
            if(left < right){
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
                left++;
                right--;
            }
        }
        return nums;
    }
}

//调整数组顺序使奇数位于偶数前面（可扩展）
class _21_exchangeExtendable {
    public int[] exchange(int[] nums, Predicate<Integer> predicate) {
        if(nums == null || nums.length <= 1)
            return nums;
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            while(left < right && predicate.test(nums[left]))    left++;
            while(left < right && !predicate.test(nums[right]))   right--;
            if(left < right){
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
                left++;
                right--;
            }
        }
        return nums;
    }
    @Test
    public void test(){
        _21_exchangeExtendable e = new _21_exchangeExtendable();
        int[] nums = e.exchange(new int[]{2,2,1,4,3,5},n->(n&1)==1);
        System.out.println(Arrays.toString(nums));
    }
}

//链表中倒数第k个节点
class _22_getKthFromEnd {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head == null || k <= 0)
            return null;
        ListNode last = head;
        k--;
        while(k-- != 0){
            last = last.next;
            if(last == null)
                return null;
        }
        ListNode res = head;
        while(last.next != null){
            last = last.next;
            res = res.next;
        }
        return res;
    }
}

//链表中环的入口节点
class _23_entryNodeOfLoop{
    public ListNode entryNodeOfLoop(ListNode head){
        if(head == null)
            return null;
        Set<ListNode> nodeSet = new HashSet<>();
        ListNode node = head;
        while(node != null){
            if(!nodeSet.add(node))
                return node;
            node = node.next;
        }
        return null;
    }
    @Test
    public void test(){
        ListNode head = new ListNode(1);
        ListNode node = head;
        for(int i = 2; i <= 6; ++i){
            node.next = new ListNode(i);
            node = node.next;
        }
        node.next = head.next.next;
        System.out.println(entryNodeOfLoop(head).val);
    }
}

//反转链表
class _24_reverseList {
    public ListNode reverseList(ListNode head) {
        ListNode result = null;
        ListNode node = head;
        ListNode prev = null;
        while(node != null){
            ListNode next = node.next;
            if(next == null)
                result = node;
            node.next = prev;
            prev = node;
            node = next;
        }
        return result;
    }
}

//合并两个排序的链表
class _25_mergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        while(l1 != null && l2 !=null){
            if(l1.val <= l2.val){
                node.next = l1;
                l1 = l1.next;
            }
            else{
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        node.next = l1!=null ? l1 : l2;
        return dummy.next;
    }
}

//树的子结构
class _26_isSubStructure {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) &&
                (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }
    private boolean recur(TreeNode A, TreeNode B){
        if(B == null)   return true;
        if(A == null || A.val != B.val)    return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}

//二叉树的镜像（除递归外，可用栈或队列）
class _27_mirrorTree {
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null)
            return null;
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }
}

//对称的二叉树（递归，比较二叉树的前序遍历序列和对称前序遍历序列）
class _28_isSymmetric {
    public boolean isSymmetric(TreeNode root) {
        return root == null || recur(root.left, root.right);
    }
    private boolean recur(TreeNode L, TreeNode R){
        if(L == null && R == null)   return true;
        if(L == null || R == null || L.val != R.val)   return false;
        return recur(L.left, R.right) && recur(L.right, R.left);
    }
}

//顺时针打印矩阵
class _29_spiralOrder {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0)  return new int[0];
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] res = new int[rows * cols];
        int start = 0;
        int pos = 0;
        while(rows > start * 2 && cols > start * 2){
            int endX = rows - 1 - start;
            int endY = cols - 1 - start;
            for(int i = start; i <= endY; i++)
                res[pos++] = matrix[start][i];
            if(start < endX)
                for(int i = start + 1; i <= endX; i++)
                    res[pos++] = matrix[i][endY];
            if(start < endX && start < endY)
                for(int i = endY - 1; i >= start; i--)
                    res[pos++] = matrix[endX][i];
            if(start < endX - 1 && start < endY)
                for(int i = endX - 1; i > start; i--)
                    res[pos++] = matrix[i][start];
            start++;
        }
        return res;
    }
}

class _30_MinStack {
    private final Deque<Integer> data;
    private final Deque<Integer> min;
    public _30_MinStack() {
        data = new LinkedList<>();
        min = new LinkedList<>();
    }
    public void push(int x) {
        data.offerLast(x);
        if(min.size() == 0 || min.peekLast() > x)
            min.offerLast(x);
        else
            min.offerLast(min.peekLast());
    }
    public void pop() {
        assert(data.size() > 0 && min.size() > 0);
        data.pollLast();
        min.pollLast();
    }
    public int top() {
        assert(!data.isEmpty());
        return data.peekLast();
    }
    public int min() {
        assert(!min.isEmpty());
        return min.peekLast();
    }
    @Test
    public void test(){
        _30_MinStack ms = new _30_MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        System.out.println(ms.min());
        ms.pop();
        System.out.println(ms.top());
        System.out.println(ms.min());
    }
}

//栈的压入、弹出序列
class _31_validateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<>();
        int pop = 0;
        for(int num : pushed){
            stack.offerLast(num);
            while(!stack.isEmpty() && stack.peekLast() == popped[pop]){
                stack.pollLast();
                pop++;
            }
        }
        return stack.isEmpty();
    }
    public boolean validateStackSequences_Map(int[] pushed, int[] popped) {
        if(pushed.length != popped.length)
            return false;
        if(pushed.length == 0)
            return true;
        int len = pushed.length;
        Map<Integer, Integer> pushOrder = new HashMap<>(len);
        for(int i = 0; i < len; i++)
            pushOrder.put(pushed[i], i);
        int push = 0;
        int pop = 0;
        Deque<Integer> stack = new ArrayDeque<>(len);
        while(pop < len){
            int elementIndex = pushOrder.get(popped[pop]);
            while(elementIndex >= push)
                stack.offerLast(pushed[push++]);
            if(!stack.isEmpty() && stack.pollLast() != popped[pop++])
                return false;
        }
        return true;
    }
}

//从上到下打印二叉树
class _32_levelOrder {
    public int[] levelOrder(TreeNode root) {
        if(root == null)
            return new int[0];
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>(){{offer(root);}};
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            ans.add(node.val);
            if(node.left != null)   queue.offer(node.left);
            if(node.right != null)  queue.offer(node.right);
        }
        int[] res = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);
        return res;
    }
    public List<List<Integer>> levelOrder_lines(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null)    queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> line = new LinkedList<>();
            for(int i = queue.size(); i > 0; i--){
                TreeNode node = queue.poll();
                assert node != null;
                line.add(node.val);
                if(node.left != null)   queue.offer(node.left);
                if(node.right != null)  queue.offer(node.right);
            }
            res.add(line);
        }
        return res;
    }
    public List<List<Integer>> levelOrder_Z(TreeNode root) {        //亦可用双栈
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null) queue.offer(root);
        boolean oddLevel = true;
        while(!queue.isEmpty()){
            oddLevel = !oddLevel;
            LinkedList<Integer> line = new LinkedList<>();
            for(int i = queue.size(); i > 0; i--){
                TreeNode node = queue.poll();
                assert node != null;
                if(oddLevel) line.offerFirst(node.val);
                else    line.offerLast(node.val);
                if(node.left != null)   queue.offer(node.left);
                if(node.right != null)  queue.offer(node.right);
            }
            res.add(line);
        }
        return res;
    }
}

//二叉搜索树的后序遍历序列（在递归前判断是否继续递归可减少栈的使用）
class _33_verifyPostorder {
    public boolean verifyPostorder(int[] postorder) {
        return postorder == null || postorder.length <= 1 ||
                recurVerify(postorder,0,postorder.length-1);
    }
    private boolean recurVerify(int[] postorder, int start, int end){
        int pos = start;
        while(postorder[pos] < postorder[end]) pos++;
        int leftEnd = pos - 1;
        while(postorder[pos] > postorder[end]) pos++;
        if(pos != end) return false;
        boolean left = leftEnd <= start || recurVerify(postorder,start,leftEnd);
        boolean right = leftEnd + 1 >= end - 1 || recurVerify(postorder,leftEnd+1,end-1);
        return left && right;
    }
    public boolean verifyPostorder_monoStack(int[] postorder) {     //单调栈解法
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for(int i = postorder.length - 1; i >= 0; i--) {
            if(postorder[i] > root) return false;
            while(!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }
}

//二叉树中和为某一值的路径
class _34_pathSum {
    private final List<List<Integer>> paths = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null)    return paths;
        Deque<TreeNode> path = new LinkedList<>();
        findPath(path, root, 0, sum);
        return paths;
    }
    private void findPath(Deque<TreeNode> path, TreeNode root, int curVal, int target){
        path.offerLast(root);
        curVal += root.val;
        if(curVal == target && root.left == null && root.right == null){
            List<Integer> newPath = new ArrayList<>(path.size());
            for(TreeNode node : path)
                newPath.add(node.val);
            paths.add(newPath);
        }
        if(root.left != null)
            findPath(path, root.left, curVal, target);
        if(root.right != null)
            findPath(path, root.right, curVal, target);
        path.pollLast();
    }
}

//复杂链表的复制
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
class _35_copyRandomList {
    public Node copyRandomList(Node head) {
        if(head == null)    return null;
        Node cur = head;
        while(cur != null){
            Node copy = new Node(cur.val);
            copy.next = cur.next;
            copy.random = cur.random;
            cur.next = copy;
            cur = copy.next;
        }
        cur = head;
        while(cur != null){
            cur = cur.next;
            if(cur.random != null)
                cur.random = cur.random.next;
            cur = cur.next;
        }
        Node copyHead = head.next;
        cur = head;
        while(cur != null){
            Node copy = cur.next;
            cur.next = copy.next;
            if(cur.next != null)
                copy.next = cur.next.next;
            cur = cur.next;
        }
        return copyHead;
    }
    public Node copyRandomList_hashMap(Node head) {
        if(head == null)    return null;
        Map<Node,Node> nodeMap = new HashMap<>();
        Node origin = head;
        Node dummyHead = new Node(-1);
        Node copy = dummyHead;
        while(origin != null){
            copy.next = new Node(origin.val);
            nodeMap.put(origin,copy.next);
            origin = origin.next;
            copy = copy.next;
        }
        origin = head;
        copy = dummyHead.next;
        while(origin != null){
            if(origin.random != null)
                copy.random = nodeMap.get(origin.random);
            origin = origin.next;
            copy = copy.next;
        }
        return dummyHead.next;
    }
}

//二叉搜索树与双向链表
class _36_treeToDoublyList {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
    private Node pre;
    private Node head;
    public Node treeToDoublyList(Node root) {
        if(root == null)    return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    private void dfs(Node cur){
        if(cur == null) return;
        dfs(cur.left);
        if(pre != null) pre.right = cur;
        else    head = cur;
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }
}

//序列号二叉树
class _37_Codec {
    public String serialize(TreeNode root) {
        if(root == null) return "[]";
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>() {{ add(root); }};
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node != null) {
                res.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }
            else res.append("null,");
        }
        return res.deleteCharAt(res.length() - 1).append("]").toString();
    }
    public TreeNode deserialize(String data) {
        if(data.equals("[]")) return null;
        String[] vals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>() {{ add(root); }};
        int i = 1;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(!vals[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.left);
            }
            i++;
            if(!vals[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        TreeNode node3 = root.right = new TreeNode(3);
        TreeNode node4 = node3.left = new TreeNode(4);
        TreeNode node5 = node3.right = new TreeNode(5);
        node4.left = new TreeNode(6);
        node4.right = new TreeNode(7);
        node5.right = new TreeNode(8);
        System.out.println(serialize(deserialize(serialize(root))));
    }
}

//字符串的排列
class _38_permutations {
    private boolean[] visited;
    private char[] chs;
    private char[] instance;
    private List<String> res;
    public String[] permutation(String s) {
        visited = new boolean[s.length()];
        res = new LinkedList<>();
        chs = s.toCharArray();
        Arrays.sort(chs);
        instance = new char[chs.length];
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    private void dfs(int pos){
        if(pos == chs.length){
            res.add(String.valueOf(instance));
            return;
        }
        for(int i = 0; i < chs.length; i++){
            if(visited[i])  continue;
            if(i > 0 && chs[i] == chs[i - 1] && !visited[i - 1]) continue;  //确保a1a2中只保留a1a2而剔除重复项a2a1
            visited[i] = true;
            instance[pos] = chs[i];
            dfs(pos+1);
            visited[i] = false;
        }
    }
}

class _38_permutations_hashSetSwap {
    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c)); // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++) {
            if(set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x); // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1); // 开启固定第 x + 1 位字符
            swap(i, x); // 恢复交换
        }
    }
    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }
}

//求字符的所有组合
class _38_composition{
    public String[] getComposition(String s){       //当参数中含重复字符时，该方法不能去除重复项
        if(s.length() == 0) return new String[0];
        char[] chs = s.toCharArray();
        int max = (1 << chs.length) - 1;
        String[] res = new String[max];
        int pos = 0;
        for(int i = 1; i <= max; i++){
            StringBuilder tmp = new StringBuilder();
            int ex = Math.getExponent(i);
            for(int j = 0; j <= ex; j++)
                if(((i >> j) & 1) == 1)
                    tmp.append(chs[j]);
            res[pos++] = tmp.toString();
        }
        return res;
    }
    public String[] getComposition_hashSet(String s){   //利用哈希集去重
        if(s.length() == 0) return new String[0];
        char[] chs = s.toCharArray();
        int max = (1 << s.length()) - 1;
        Set<String> res = new HashSet<>();
        for(int i = 1; i <= max; i++){
            StringBuilder tmp = new StringBuilder();
            int ex = Math.getExponent(i);
            for(int j = 0; j <= ex; j++)
                if(((i >> j) & 1) == 1)
                    tmp.append(chs[j]);
            res.add(tmp.toString());
        }
        return res.toArray(new String[0]);
    }
    @Test
    public void test(){
        for(String s : getComposition_hashSet("aabb"))
            System.out.print(s+'\n');
    }
}

//数组中出现次数超过一半的数字
class _39_majorityElement {
    public int majorityElement(int[] nums) {
        int x = 0;
        int votes = 0;
        for(int num : nums){
            if(votes == 0)  x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }
}

//最小的k个数
class _40_getLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k <= 0)  return new int[0];
        else if(k == arr.length)    return arr;
        partitionK(arr, 0, arr.length - 1, k - 1);
        return Arrays.copyOf(arr, k);
    }
    private void partitionK(int[] arr, int start, int end, int k){
        int pos = partition(arr, start, end);
        int left = start;
        int right = end;
        while(pos != k){
            if(pos > k) right = pos - 1;
            else        left = pos + 1;
            pos = partition(arr, left, right);
        }
    }
    private int partition(int[] arr, int start, int end){
        if(start + 1 >= end){
            if(arr[start] > arr[end])   swap(arr, start, end);
            return end;
        }
        int mid = start + ((end - start) >> 1);
        if(arr[start] > arr[mid])   swap(arr,start,mid);
        if(arr[mid] > arr[end])     swap(arr,mid,end);
        if(arr[start] > arr[mid])   swap(arr,start,mid);
        if(start + 2 == end)
            return end - 1;
        swap(arr, mid, end - 1);
        int pivot = arr[end - 1];
        int left = start;
        int right = end - 1;
        while(true){
            while(arr[++left] < pivot);
            while(pivot < arr[--right]);
            if(left < right)    swap(arr, left, right);
            else                break;
        }
        swap(arr, left, end - 1);
        return left;
    }
    private void swap(int[] arr, int x, int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
    @Test
    public void test(){
        int[] arr = new int[]{0,1,2,1};
        int[] res = getLeastNumbers(arr, 1);
        System.out.println(Arrays.toString(res));
    }
}

class _40_getLeastNumbers_heap{
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k <= 0)  return new int[0];
        else if(k == arr.length)    return arr;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b - a);
        for(int i = 0; i < arr.length; i++){
            if(maxHeap.size() < k)
                maxHeap.offer(arr[i]);
            else{
                if(maxHeap.peek() > arr[i]){
                    maxHeap.poll();
                    maxHeap.offer(arr[i]);
                }
            }
        }
        int[] res = new int[k];
        for(int i = 0; i < k; i++)
            res[i] = maxHeap.poll();
        return res;
    }
}

//数据流中的中位数
class _41_MedianFinder {
    Queue<Integer> left;
    Queue<Integer> right;
    public _41_MedianFinder() {
        left = new PriorityQueue<>((a,b)->(b-a));
        right = new PriorityQueue<>();
    }
    public void addNum(int num) {
        if(left.size() != right.size()){
            left.offer(num);
            right.offer(left.poll());
        }
        else{
            right.offer(num);
            left.offer(right.poll());
        }
    }
    public double findMedian() {
        return left.size() != right.size() ? left.peek() : (left.peek() + right.peek()) / 2.0;
    }
}

//连续子数组的最大和
class _42_maxSubArray {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = 0;
        for(int num : nums){
            if(currentSum < 0)  currentSum = num;
            else    currentSum += num;
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}

//1~n整数中1出现的次数
class _43_countDigitOne {
    public int countDigitOne(int n) {
        int res = 0;
        for(long i = 1; i <= n; i *= 10){
            long divide = 10 * i;
            res += n / divide * i + Math.min(Math.max(n % divide - i + 1,0),i);
        }
        return res;
    }
}

//数字序列中某一位的数字
class _44_findNthDigit {
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while(n > count){
            n -= count;
            digit++;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;         //第1位数即最小数字的最低位（个位）
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }
}

//把数组排成最小的数
class _45_minNumber {
    public String minNumber(int[] nums) {
        String[] strNums = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strNums[i] = String.valueOf(nums[i]);
        Arrays.sort(strNums, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for(String str : strNums)
            res.append(str);
        return res.toString();
    }
}

//把数字翻译成字符串（斐波那契数列的应用）
class _46_translateNum {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int a = 1;
        int b = 1;
        for(int i = 2; i <= s.length(); i++){
            String tmp = s.substring(i - 2, i);
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : b;
            a = b;
            b = c;
        }
        return b;
    }
    public int translateNum_space(int num){
        int a = 1, b = 1, x, y = num % 10;
        while(num != 0) {
            num /= 10;
            x = num % 10;
            int tmp = 10 * x + y;
            int c = (tmp >= 10 && tmp <= 25) ? a + b : b;
            a = b;
            b = c;
            y = x;
        }
        return b;
    }
}

//礼物的最大价值
class _47_maxValue {
    public int maxValue(int[][] grid) {
        for(int j = 1; j < grid[0].length; j++)
            grid[0][j] += grid[0][j - 1];
        for(int i = 1; i < grid.length; i++)
            grid[i][0] += grid[i - 1][0];
        for(int i = 1; i < grid.length; i++)
            for(int j = 1; j < grid[0].length; j++)
                grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
        return grid[grid.length -1][grid[0].length - 1];
    }
}

//最长不含重复字符的子字符串
class _48_lengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) return 0;
        int max = 0;
        int[] pos = new int[256];
        Arrays.fill(pos, -1);
        int last = -1;
        for(int i = 0; i < s.length(); i++){
            last = Math.max(last, pos[s.charAt(i)]);
            max = Math.max(max, i - last);
            pos[s.charAt(i)] = i;
        }
        return max;
    }
}

//丑数
class _49_nthUglyNumber {
    public int nthUglyNumber(int n) {
        if(n <= 0)  return 0;
        int[] ugly = new int[n];
        ugly[0] = 1;
        int i = 0, j = 0, k = 0;
        for(int p = 1; p < n; p++){
            int min = Math.min(ugly[i] * 2, Math.min(ugly[j] * 3, ugly[k] * 5));
            if(min == ugly[i] * 2)  i++;
            if(min == ugly[j] * 3)  j++;
            if(min == ugly[k] * 5)  k++;
            ugly[p] = min;
        }
        return ugly[n - 1];
    }
}

//第一个只出现一次的字符
class _50_firstUniqChar {
    public char firstUniqChar(String s) {
        int[] count = new int[26];
        char[] chs = s.toCharArray();
        for (char ch : chs)
            count[ch - 'a']++;
        for (char ch : chs)
            if (count[ch - 'a'] == 1)
                return ch;
        return ' ';
    }
}

//数组中的逆序对
class _51_reversePairs {
    public int reversePairs(int[] nums) {
        if(nums.length == 0)    return 0;
        return mergeSort(nums);
    }
    private int mergeSort(int[] nums){
        int count = 0;
        int len = 1;
        int[] tmp = new int[nums.length];
        while(len < nums.length){
            count += mergeSortPass(nums, tmp, len);
            len *= 2;
            count += mergeSortPass(tmp, nums, len);
            len *= 2;
        }
        return count;
    }
    private int mergeSortPass(int[] src, int[] dst, int len){
        int count = 0;
        int i;
        for(i = 0; i <= src.length - 2 * len; i += 2 * len)
            count += mergeSortCore(src, dst, i, i + len, i + 2 * len - 1);
        if(i + len < src.length)
            count += mergeSortCore(src, dst, i, i + len, src.length - 1);
        else
            for(int j = i; j < src.length; j++)
                dst[j] = src[j];
        return count;
    }
    private int mergeSortCore(int[] src, int[] dst, int left, int right, int end){
        int count = 0;
        int leftEnd = right - 1;
        int target = left;
        while(left <= leftEnd && right <= end){
            if(src[left] > src[right]){
                dst[target++] = src[right++];
                count += leftEnd - left + 1;
            }
            else
                dst[target++] = src[left++];
        }
        while(left <= leftEnd)
            dst[target++] = src[left++];
        while(right <= end)
            dst[target++] = src[right++];
        return count;
    }
}

//两个链表的第一个公共节点
class _52_getIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)  return null;
        int lengthA = getLength(headA);
        int lengthB = getLength(headB);
        int cut = Math.abs(lengthA - lengthB);
        ListNode longList = lengthA >= lengthB ? headA : headB;
        ListNode shortList = longList == headA ? headB : headA;
        for(int i = 0; i < cut; i++)
            longList = longList.next;
        while(longList != shortList){
            longList = longList.next;
            shortList = shortList.next;
        }
        return longList;
    }
    private int getLength(ListNode list){
        int length = 0;
        while(list != null){
            length++;
            list = list.next;
        }
        return length;
    }
}

//在排序数组中查找数字I
class _53_search {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0)    return 0;
        int first = getIndex(nums, target, true);
        int last = getIndex(nums, target, false);
        if(first != -1 && last != -1)
            return last - first + 1;
        return 0;
    }
    private int getIndex(int[] nums, int target, boolean first){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            if(nums[mid] == target){
                if(first && mid > left && nums[mid - 1] == target) right = mid - 1;
                else if(!first && mid < right && nums[mid + 1] == target)  left = mid + 1;
                else    return mid;
            }
            else if(nums[mid] < target) left = mid + 1;
            else    right = mid - 1;
        }
        return -1;
    }
}

//0~n-1中缺失的数字
class _53_missingNumber {
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            if(nums[left] != left)  return left;
            int mid = (left + right) >> 1;
            if(nums[mid] == mid)    left = mid + 1;
            else right = mid;
        }
        return nums.length;
    }
}

//二叉搜索树的第K大节点
class _54_kthLargest {
    private int k;
    private int res;
    public int kthLargest(TreeNode root, int k) {
        if(root == null || k < 0)   return -1;
        this.k = k;
        inOrder(root);
        return res;
    }
    private void inOrder(TreeNode root){
        if(root == null)    return;
        inOrder(root.right);
        if(--k == 0)    res = root.val;
        if(k > 0)   inOrder(root.left);
    }
}

//二叉树的深度
class _55_maxDepth {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

//数组中数字出现的次数
class _56_singleNumbers {
    public int[] singleNumbers(int[] nums) {
        int xor = 0;
        for(int num : nums)
            xor ^= num;
        int div = 1;
        while((xor & div) == 0)
            div <<= 1;
        int a = 0;
        int b = 0;
        for(int num : nums){
            if((num & div) == 0)
                a ^= num;
            else
                b ^= num;
        }
        return new int[]{a, b};
    }
}

//数组中数字出现的次数II
class _56_singleNumber {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }
}

//和为s的两个数字（long型运算，避免溢出）
class _57_twoSum {
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while(i < j){
            long sum = nums[i] + nums[j];
            if(sum == target)   return new int[]{nums[i], nums[j]};
            else if(sum > target) j--;
            else    i++;
        }
        return new int[0];
    }
}

//和为s的连续正数序列
class _57_findContinuousSequence {
    public int[][] findContinuousSequence(int target) {
        int i = 1;
        int j = 2;
        List<int[]> res = new ArrayList<>();
        int cut = target >> 1;
        while(i <= cut){
            int sum = ((i + j) * (j - i + 1)) >> 1;
            if(sum == target){
                int[] arr = new int[j - i + 1];
                for(int k = i; k <= j; k++)
                    arr[k - i] = k;
                res.add(arr);
                i += 2;
            }
            else if(sum < target)
                j++;
            else
                i++;
        }
        return res.toArray(new int[0][]);
    }
}

//反转单词顺序
class _58_reverseWords {
    public String reverseWords(String s) {
        s = s.trim();
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while(i >= 0){
            while(i >= 0 && s.charAt(i) != ' ') i--;
            res.append(s, i + 1, j + 1).append(' ');
            while(i >= 0 && s.charAt(i) == ' ') i--;
            j = i;
        }
        return res.toString().trim();
    }
}

//滑动窗口的最大值
class _58_maxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k < 1)   return new int[0];
        Deque<Integer> dq = new LinkedList<>();
        for(int i = 0; i < k - 1; i++){
            while(!dq.isEmpty() && nums[i] > nums[dq.peekLast()])
                dq.pollLast();
            dq.offerLast(i);
        }
        int[] res = new int[nums.length - (k - 1)];
        for(int i = 0; i < res.length; i++){
            while(!dq.isEmpty() && nums[i + k - 1] > nums[dq.peekLast()])
                dq.pollLast();
            dq.offerLast(i + k - 1);
            while(dq.peekFirst() < i)
                dq.pollFirst();
            res[i] = nums[dq.peekFirst()];
        }
        return res;
    }
}

//队列的最大值
class _59_MaxQueue {
    private final Deque<Integer> data;
    private final Deque<Integer> max;
    public _59_MaxQueue() {
        data = new LinkedList<>();
        max = new LinkedList<>();
    }
    public int max_value() {
        if(max.isEmpty())
            return -1;
        return max.peekFirst();
    }
    public void push_back(int value) {
        data.offerLast(value);
        while(!max.isEmpty() && value > max.peekLast())
            max.pollLast();
        max.offerLast(value);
    }
    public int pop_front() {
        if(data.isEmpty())
            return -1;
        if(max.peekFirst().equals(data.peekFirst()))
            max.pollFirst();
        return data.pollFirst();
    }
}

//n个骰子的点数
class _60_dicesProbability {
    public double[] dicesProbability(int n) {
        double[] dp = new double[n * 6 + 1];
        for(int i = 1; i <= 6; i++)
            dp[i] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = i * 6; j >= i; j--){
                dp[j] = 0;                      //从后往前更新，须将数据清零
                for(int k = 1; k <= 6; k++){
                    if(j - k < i - 1)          //投第n个骰子时，n-1个骰子的最小点数为n-1，小于该点数会取到n-2个骰子的数据
                        break;
                    dp[j] += dp[j - k];
                }
            }
        }
        double total = Math.pow(6, n);
        double[] res = new double[n * 6 - (n - 1)];
        for(int i = 0; i < res.length; i++)
            res[i] = dp[i + n] / total;
        return res;
    }
}

//扑克牌中的顺子
class _61_isStraight {
    public boolean isStraight(int[] nums) {
        Set<Integer> card = new HashSet<>();
        int max = 0;
        int min = 14;
        for(int num : nums){
            if(num == 0)    continue;
            if(!card.add(num))   return false;
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return max - min < 5;
    }
}

//圆圈中最后剩下的数字
class _62_lastRemaining {
    public int lastRemaining(int n, int m) {    //数学
        int ans = 0;
        for(int i = 2; i <= n; i++)
            ans = (ans + m) % i;
        return ans;
    }
    public int lastRemaining_simulate(int n, int m) {    //数组模拟
        ArrayList<Integer> arr = new ArrayList<>(n);
        for(int i = 0; i < n; i++)
            arr.add(i);
        int idx = 0;
        while(n > 1){
            idx = (idx + m - 1) % n;
            arr.remove(idx);
            n--;
        }
        return arr.get(0);
    }
}

//股票的最大利润
class _63_maxProfit {
    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE;
        int sell = 0;
        for(int price : prices){
            buy = Math.max(buy, -price);
            sell = Math.max(sell, buy + price);
        }
        return sell;
    }
}

//求1+2+...+n（不能使用加减乘除、for/while/if/else/switch/三目运算符等关键字及条件判断语句）
class _64_sumNums {
    private int sum = 0;
    public int sumNums(int n) {
        boolean x = n > 1 && sumNums(n - 1) > 0;
        sum += n;
        return sum;
    }
    public int sumNums_slim(int n) {
        boolean x = n > 1 && (n += sumNums_slim(n - 1)) > 0;
        return n;
    }
}

//不用加减乘除做加法
class _65_add {
    public int add(int a, int b) {
        while(b != 0){
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }
}

//构建乘积数组
class _66_constructArr {
    public int[] constructArr(int[] a) {
        if(a.length == 0)   return new int[0];
        int[] res = new int[a.length];
        res[0] = 1;
        for(int i = 1; i < res.length; i++)
            res[i] = res[i - 1] * a[i - 1];
        int tmp = 1;
        for(int i = res.length - 2; i >=0; i--){
            tmp *= a[i + 1];
            res[i] *= tmp;
        }
        return res;
    }
}

//把字符串转换成整数
class _67_strToInt {
    public int strToInt(String str) {
        char[] chs = str.toCharArray();
        int i = 0;
        while(i < chs.length && chs[i] == ' ')  i++;
        if(i == chs.length) return 0;
        boolean positive = true;
        int res = 0;
        if(chs[i] != '+' && chs[i] != '-' && (chs[i] < '0' || chs[i] > '9'))    return 0;
        else if(chs[i] == '-')  positive = false;
        else if(chs[i] != '+')  res = chs[i] - '0';
        i++;
        int boundry = Integer.MAX_VALUE / 10;
        while(i < chs.length && chs[i] >= '0' && chs[i] <= '9'){
            if(res > boundry || res == boundry && chs[i] > '7'){
                if(positive)    return Integer.MAX_VALUE;
                else            return Integer.MIN_VALUE;
            }
            res = res * 10 + (chs[i] - '0');
            i++;
        }
        if(!positive)   res = -res;
        return res;
    }
}

//二叉搜索树的最近公共祖先
class _68_lowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null)
            return null;
        if(p.val > q.val){
            TreeNode temp = p;
            p = q;
            q = temp;
        }
        while(root != null){
            if(root.val > q.val)   root = root.left;
            else if(root.val < p.val)   root = root.right;
            else    break;
        }
        return root;
    }
}

//二叉树的最近公共祖先
class _68_lowestCommonAncestor_II {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)  return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null)    return right;
        if(right == null)   return left;
        return root;
    }
}

class UnitTest{
    @Test
    public void test(){
    }
}
