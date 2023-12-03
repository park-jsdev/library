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

// Trees
// Time: O(n)
// Space: O(n)
/**
    The recursive solution involves a method of exhaustion where reaching both but not 1 null child is true.
    Each step must have, left = right, left.left = right.right, left,right = right.left.
 */

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode p, TreeNode q){
        // Base Case
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        // Recursive Step
        return (p.val == q.val) && helper(p.left, q.right) && helper(p.right, q.left);
    }
}