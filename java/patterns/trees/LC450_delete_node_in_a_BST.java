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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;

        // Recursively set the left and right subtrees until we find the root which equals the key
        if (root.val < key) root.right = deleteNode(root.right, key);
        else if (root.val > key) root.left = deleteNode(root.left, key);
        else {
            if (root.left == null) return root.right; // left subtree is null
            else if (root.right == null) return root.left; // right subtree is null
            else {
                // We need to find the node with the smallest value that is larger than the vlaue of
                // the node to be deleted to set it as the new node.
                // It is the left most node in the right subtree.

                TreeNode newRoot = root.right, par = null; // Replace the node with the
                // smallest node in the right subtree by choice.
                // The successor must be in the right subtree (once we decide to take the right subtree)
                while (newRoot.left != null){ // find the leftmost node in this subtree
                    par = newRoot; // keep track of parent
                    newRoot = newRoot.left;
                }
                if (par == null){ // if parent is still null after the loop, there is no left child
                    newRoot.left = root.left; // we simply keep left child as root.left
                    // the root node is set to root.right, so the root is deleted
                    return newRoot;
                } // if there was a left subtree

                // currently, newRoot is the leftmost node in the right subtree of the node to be deleted
                // newRoot.left is null
                par.left = newRoot.right; // set the right child if any to the left child of its parent
                // therefore no nodes are lost (takes place of newRoot)

                // we are only concerned with the left subtree of parent, because we chose the leftmost part of
                // the right subtree of root to be the new root
                
                // Now reassign newRoot's children to be the children of the node that was deleted
                newRoot.left = root.left; 
                newRoot.right = root.right;

                // The new root may be above the previous parent in the hierarchy
                // newRoot's position is not determined by par, but it is the leftmost child of the right
                // subtree of the original root to be deleted

                return newRoot;
            }
        }
        return root;
    }
}