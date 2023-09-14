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

// BFS Solution
// Time: O(n), as all elements will be traversed once in the worst case
// Space: O(n)
/**
    Note the custom BFS implementation
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        while (!q.isEmpty()){
            // At each iteration (level), we add the right most node to res, but all the child nodes back into the queue.
            TreeNode right = null;
            int qLen = q.size(); // Track level but not in own list
            for (int i=0;i<qLen;i++){
                TreeNode node = q.poll();
                if (node != null){
                    right = node; // since we iterate the level left to right, the last node will be the rightmost in the level.
                    q.offer(node.left); // add children to the queue to continue the while loop
                    q.offer(node.right); // left to right order is critical here.
                }
            }
            if (right != null) res.add(right.val);
        }
        return res;
    }
}