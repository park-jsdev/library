/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Recursion
// Time: Average O(log n), as cutting in half each iteration, Worst case O(n) for an unbalanced BST (LL on one side)
// Space: Average O(log n), Worst case O(n)
/**
    The intuition is that if p and q both are less than root, then the LCA must be in the left sub tree, and same with the right side.
    If p and q are on differing sides of the current root, then it must be that root is the LCA.
    By the nature of the BST, each node splits the tree into 2 subtrees, less and greater.
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base Case:
        // The recursion will naturally stop when conditions are met. Implicit base case.

        // Recursive Case:
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);

        // root can be if the val = p or q, or if it is between p and q.
        return root;
    }
}