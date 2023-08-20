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
class Solution {
    public int maxDepth(TreeNode root) {
        // DFS with Recursion solution
        // Time: O(n)
        // Space: O(n)

        // Basic Check
        if (root == null) return 0;
        
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}