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

// Trees - DFS
// Time: O(n) as we traverse every node in the tree
// Space: O(h) due to the recursive stack
/**
    Use DFS. Only need to track the max at each path. Use Recursion (Pre-order)
 */

class Solution {

    public int goodNodes(TreeNode root) {
        return dfs(root, root.val); // Call DFS on the root
    }

    public int dfs(TreeNode node, int max){
        // Base Case
        if (node == null) return 0;

        // Recursive Step
        int res = 0;
        if (node.val >= max){ // Conditions for "Good Node"
            res = 1;
        } else {
            res = 0;
        }
        max = Math.max(max, node.val); // Track max on the path
        res += dfs(node.left, max);
        res += dfs(node.right, max);
    
        return res;
    }
}