/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// Naive solution:
// Recursively check if the trees are the same at each node of root
// Time: O(m * n), as we call sameTree from dfs
// Space: O(max(m, n)

class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        // flag method
        boolean flag = false;
        
        // Perform dfs on root, propagate the recurrence relation
        flag = dfs(root, subRoot);

        return flag;
    }

    public boolean dfs(TreeNode root, TreeNode subRoot){

        // Base case, assume false:
        boolean flag = false;
        if (root == null) return false;

        // Recursive Step:
        // In-Order Traversal:
        // visit node
        if (sameTree(root, subRoot)) return true;

        // left node
        boolean lflag = dfs(root.left, subRoot);
        // right node
        boolean rflag = dfs(root.right, subRoot);

        // Check subproblem
        if (lflag || rflag){
            flag = true;
        }

        return flag;
    }

    // Same Tree - see LC 100
    public boolean sameTree(TreeNode p, TreeNode q){
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return sameTree(p.left, q.left) && sameTree(p.right, q.right);
    }
}