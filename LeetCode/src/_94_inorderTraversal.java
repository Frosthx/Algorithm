import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _94_inorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while(!stack.isEmpty() || root != null){
            while(root != null) {
                stack.offerLast(root);
                root = root.left;
            }
            root = stack.pollLast();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
    public List<Integer> inorderTraversalRecursive(TreeNode root){
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    public void dfs(TreeNode root, List<Integer> res){
        if(root == null)
            return;
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }
}