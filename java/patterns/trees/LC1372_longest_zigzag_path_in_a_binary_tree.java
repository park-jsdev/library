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
    int max = 0;
    public int longestZigZag(TreeNode root) {
        if (root == null) return 0;
        dfs (root.left, false, 0);
        dfs (root.right, true, 0);
        return max;
    }

    private void dfs(TreeNode node, boolean right, int depth){
        max = Math.max(max, depth);
        if (node == null) return;
        dfs(node.left, false, right ? depth+1: 0);
        dfs(node.right, true, !right ? depth+1: 0);
    }
}