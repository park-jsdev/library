// Greedy Solution
// Time: O(n)
// Space: O(n)
/**
    The intuition is that we don't consider any negative numbers in the subarray,
    as they don't contribute anything.
 */

class Solution {
    public int maxSubArray(int[] nums) {
        // Basic Check
        if (nums.length == 1) return nums[0];

        int max = nums[0];
        int sub = 0;
        for (int i=0;i<nums.length;i++){
            sub += nums[i];
            max = Math.max(max, sub);

            // If the subarray is negative then reset it
            if (sub < 0){
                sub = 0;
            }
        }
        return max;
    }
}