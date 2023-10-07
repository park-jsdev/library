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

// Trees - Design Problem
// Time: O(n) as it visits every node once
// Space: O(n)
/**
    The crux move is to recursively build subtrees where: 
    - the left most of the sublist in preorder being considered is the root of the subtree
    - that same value in the inorder sublist is the middle pointer, and the root of the next level subtree
    The reason that we can search by value in the inorder sublist is because the input is guaranteed to consist of unique values.

    The key implementation detail is the copying of the range in Java for sublists. You can use Arrays.copyOfRange() if you
    can remember it, else you need to create your own builder function.

 */

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // Base Case
        if (preorder.length == 0 || inorder.length == 0) return null; // Basic Check

        TreeNode root = new TreeNode(preorder[0]);
        int mid = 0;

        // Look for the same value of root in the inorder array to be the mid pointer
        for (int i=0;i<inorder.length;i++){
            if (preorder[0] == inorder[i]) mid = i; // This is the root of the next level subtree
        }

        // Recursive Step
        // In the subtrees we simply pass in the sublists of preorder and inorder and the base case will handle
        // the node selection. Each recursive step will cut out the left portions being used and we will arrive
        // at the result by exhaustion. We only consider windows of 3 at a time for the left, and the remaining as right 
        root.left = buildTree(
            Arrays.copyOfRange(preorder, 1, mid + 1), // Memorize this Java function, else have to implement builder yourself
            Arrays.copyOfRange(inorder, 0, mid)
        );

        root.right = buildTree(
            Arrays.copyOfRange(preorder, mid + 1, preorder.length), // Right subtree considers mid + 1 to the end
            Arrays.copyOfRange(inorder, mid + 1, inorder.length)
        );

        return root;
    }
}