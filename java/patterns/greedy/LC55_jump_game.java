// Greedy Solution
// Time: O(n)
// Space: O(1)
/**
    For greedy patterns: use "shifting the goalpost" at each step (local maximum).
    Greedy algorithms typically have good space efficiency.
 */

class Solution {
    public boolean canJump(int[] nums) {
        int goal = nums.length-1; // set final goal
        for (int i=nums.length-1;i>=0;i--){ // shift the goalpost
            if (i + nums[i] >= goal){
                goal = i;
            }
        }
        return goal == 0 ? true : false;
    }
}