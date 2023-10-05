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

// Tree Traversal
// Time: O(n) as we check each node once
// Space: O(n) in the worst case, due to the recursive stack
/**
    Use DFS to recursively iterate over the tree while defining min and max value for each node the value must fit in.
    The crux is that the min and max start null, but they are passed down to each child from the previous node.

    The key implementation detail is that we need to use TreeNode (or Integer) objects for the min and max, in the case of
    overflow.
 */

class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }

    private boolean dfs(TreeNode root, TreeNode min, TreeNode max){
        // Base Case
        if (root == null) return true; // We have returned a leaf node, this path should return true
        // Conditions
        if ((min != null && root.val <= min.val) || (max != null && root.val >= max.val)) return false;

        // Recursive Step
        return dfs(root.left, min, root) && dfs(root.right, root, max);
    }
}