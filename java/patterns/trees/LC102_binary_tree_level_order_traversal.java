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

// BFS on a Tree
// Time: O(n)
// Space: O(n)
/**
    Note BFS implementation
 */

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if (root == null) return res;
        q.offer(root);

        while (!q.isEmpty()){
            // We want to track the nodes of the level
            int levelSize = q.size();
            List<Integer> level = new ArrayList<>();

            for (int i=0;i<levelSize;i++){
                TreeNode node = q.poll();
                level.add(node.val);

                if (node.left != null){
                    q.offer(node.left);
                }
                if (node.right != null){
                    q.offer(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }
}