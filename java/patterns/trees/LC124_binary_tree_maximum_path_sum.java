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

// Trees - DFS with global variable
// Time: O(n) as we visit every node once
// Space: O(n) due to the recursive stack
/**
    The crux is to capture the decision of: 1. taking the sum at the node, having taken the continuous path from the root to 
    the left OR right, 2. the sum at the current node if we consider it as the new root of a sub-tree, including both left 
    AND right starting from that pivot point.

    The key implementation detail is the global variable (an optimal solution can use a non-global variable),
    the computation of the left and right paths at each recursive step,
    computing the global max at each step,
    and the return value of the recursive call (to be used in the left and right path computations at the next recursive step).
 */

class Solution {
    public int maxPathSum(TreeNode root) {
        int[] res = {Integer.MIN_VALUE};  // We use an array to pass the global value as reference.
        // Passing in by value would not allow it to be updated outside the function call,
        // while passing in by reference (as an array value) will allow it to be updated by the function
        maxPathSum(root, res);
        return res[0];
    }

    public int maxPathSum(TreeNode root, int[] res){
        if (root == null) return 0; // Base Case

        int l = Math.max(0, maxPathSum(root.left, res)); // we consider the max of 0 and the path, incase it is negative
        int r = Math.max(0, maxPathSum(root.right, res));
        res[0] = Math.max(res[0], root.val + l + r); // This computes the max of the current continuous path through the current node
        // + the previous max sum
        // and the sum of the subtree considering the current node as the root (including the split, Curr + L + R)
        // res[0] is the global variable that stores the global max (continuous, vs subtree)

        // The return value captures the continous path sum, it is called in the left and right continuous paths at each step
        return root.val + Math.max(l,r); // we return the max path sum that passes through the current node and either left or right
        // child, but not both, because, a path has to be continuous, and cannot take both splits.
    }
}