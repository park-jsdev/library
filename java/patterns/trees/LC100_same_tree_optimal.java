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

// Optimal Recursive solution:
// Time: O(min(n, m)), where n = |p|, and m = |q|
// Space: O(min(h1, h2)), where h1 = height of p, and h2 = height of q
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case:
        if (p == null && q == null) return true; // AND nulls
        if (p == null || q == null || p.val != q.val) return false; // XOR null, AND null is caught above, also check val

        // Recursive step:
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right); // Recurrence relation
    }
}