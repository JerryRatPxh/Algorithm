import javafx.util.Pair;

import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class TreeTraversal {

    //前中后相似版本
    public List<Integer> preorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<Pair<Boolean, TreeNode>> stack = new LinkedList<>();

        stack.push(new Pair<>(false,root));

        while (!stack.isEmpty()){
            Pair<Boolean, TreeNode> pair = stack.pop();
            Boolean visited = pair.getKey();
            TreeNode node = pair.getValue();
            if (node == null)
                continue;
            if (!visited){
                stack.push(new Pair<>(false,node.right));
                stack.push(new Pair<>(false,node.left));
                stack.push(new Pair<>(true,node));
            }
            else
                res.add(node.val);
        }
        return res;
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        List<Integer> res = new ArrayList<>();
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList <>();
        List<Integer> res = new ArrayList<>();
        while(!stack.isEmpty() || root != null) {
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList <>();
        List<Integer> res = new ArrayList<>();
        Set<TreeNode> set = new HashSet<>();


        while(!stack.isEmpty() || root != null) {
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();

            if (set.contains(root) || root.right == null){
                root = stack.pop();
                res.add(root.val);
                root = null;
            } else {
                set.add(root);
                root = root.right;
            }
        }
        return res;
    }
}