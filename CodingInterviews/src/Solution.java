import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    @Test
    public void test1(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < 10000000; i++)
            sb.append(i);
        StringBuilder sb1 = new StringBuilder(sb);
        long start = System.currentTimeMillis();
        while(sb.length() != 0)
            sb.deleteCharAt(sb.length() - 1);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        while(sb1.length() != 0)
            sb1.setLength(sb1.length() - 1);
        System.out.println(System.currentTimeMillis() - start);
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
class UnitTest{
    @Test
    public void test(){
    }
}
