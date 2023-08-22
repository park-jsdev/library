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
    public boolean isBalanced(TreeNode root) {
        // By definition of a Height-balanced Tree (Given):
        // A binary tree is height balanced iff |h of l - h of r| <= 1

        // Time: O(n)
        // Space: O(n)

        int z = getHeight(root); // call the recursive function
        
        // The concept of a binary tree allows us to check iff condition
        if (z != -1){ // z is propagated from recursive stack by recurrence relation
            return true;
        } else {
            return false;
        }
    }

    public int getHeight(TreeNode root){
        // Base condition
        if (root == null) return 0;

        // Recursive step
        int lh = getHeight(root.left);
        int rh = getHeight(root.right);

        if ((lh == -1) || (rh == -1)){ // either are a null
            return -1;
        }

        // The condition of a balanced binary tree
        if (Math.abs(lh-rh) > 1){
            return -1;
        }

        return 1 + Math.max(lh,rh); // The height to propagate
    }
}