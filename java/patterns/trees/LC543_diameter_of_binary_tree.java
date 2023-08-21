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
    // DFS with recursion
    // Time: O(n)
    // Space: O(n)

    // Note: Be able to explain the subproblem visually on a whiteboard
    // Consider where you are on the recursive stack when walking through the subproblems


    int result = -1; // Given that a single node has a height of 0
    // A null tree is considered of height -1. 
    // This helps the math to work. Remember storing this variable globally.

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return result;
    }

    // Recursively find the diameter of each subtree, which is the max height of left + max height of right
    // Consider that every node in the tree is an anchor
    private int dfs(TreeNode curr){
        // Base case
        if (curr == null) return -1;

        // Recursive case
        int left = 1 + dfs(curr.left); // add 1 for the current node
        int right = 1 + dfs(curr.right);
        result = Math.max(result, (left + right));
        return Math.max(left, right);
    }
}