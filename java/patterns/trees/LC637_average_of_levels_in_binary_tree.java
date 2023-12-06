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

// Trees - BFS
// Time: O(n) - explores all nodes once
// Space: O(n) - ""
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);
        while (!q.isEmpty()){
            int n = q.size();
            double level_sum = 0.0;
            for (int i=0;i<n;i++){
                TreeNode node = q.poll();
                level_sum += node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            double level_average = level_sum / n;
            res.add(level_average);
        }
        return res;
    }
}