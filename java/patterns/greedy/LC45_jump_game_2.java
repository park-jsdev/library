// Greedy
// Time: O(n)
// Space: O(1)
/**
    Note the BFS and Sliding Window approach
 */

class Solution {
    public int jump(int[] nums) {
        int res = 0;
        int l = 0;
        int r = 0;

        // Sliding Window approach
        while (r < nums.length-1){
            int furthest = 0;
            for (int i=l;i<=r;i++){
                furthest = Math.max(furthest, i + nums[i]);
            }
            l = r + 1;
            r = furthest;
            res++;
        }
        return res;
    }
}