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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        dfs(root1, l1);
        dfs(root2, l2);

        // if (l1.size() != l2.size()) return false;
        // for (int i=0;i<l1.size();i++){
        //     if (l1.get(i) != l2.get(i)){
        //         return false;
        //     }
        // }
        // return true;
        return l1.equals(l2);
    }

    private void dfs(TreeNode root, List<Integer> list){
        if (root == null) return; // base case

        // visit node
        if (root.left == null && root.right == null){ // is child node
            list.add(root.val);
        }

        // recursive step
        dfs(root.left, list);
        dfs(root.right, list);
    }
}