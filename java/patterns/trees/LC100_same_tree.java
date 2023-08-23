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
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // perform any one traversal for both trees at the same time
        // if at any iteration, the nodes are unequal, then return false

        // Recursive DFS solution
        // Time: O(n)
        // Space: O(2n) = O(n)

        int flag = -1;
        flag = dfsComparison(p,q); // propagate a flag value (recurrence relation)
        if (flag == -1){
            return false;
        } 
        return true;
    }

    private int dfsComparison(TreeNode p, TreeNode q){

        // Base case:
        // Basic check for null values
        if (p == null){
            if (q == null){
                return 1;
            } else {
                return -1;
            }
        }

        if (q == null){
            if (p == null){
                return 1;
            } else {
                return -1;
            }
        }
        
        // visit nodes
        if (p.val != q.val) return -1;

        // Recursive case:
        // In order traversal DFS
        int lf = dfsComparison(p.left, q.left);
        int rf = dfsComparison(p.right, q.right);

        // Subproblem
        if (lf == -1 || rf == -1){
            return -1;
        }

        // Proof by induction: condition of subproblem is not broken
        return 1;
    }
}