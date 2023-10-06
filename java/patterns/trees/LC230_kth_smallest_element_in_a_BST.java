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

// Trees - DFS In-Order Iterative
// Time: O(n)
// Space: O(n)
/**
    We can solve with iterative stack or DFS. An array can be used if values are modified frequently.
    For the follow-up question, we need to use extra space to save the number of nodes in its left subtree.
 */

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        // Basic Check
        while (root != null){
            stack.push(root);
            root = root.left; // for in order we always go left first, then process node
        }

        while (k != 0){
            TreeNode n = stack.pop();
            k--;
            if (k == 0) return n.val;
            TreeNode right = n.right;
            while (right != null){
                stack.push(right);
                right = right.left;
            }
        }
        return -1;
    }
}