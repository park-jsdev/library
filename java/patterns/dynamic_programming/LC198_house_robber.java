// Dynamic Programming - Bottom Up, Optimal
// Time: O(n)
// Space: O(1)

class Solution {
    public int rob(int[] nums) {
        int rob1 = 0; // The max amount robbed until house i-2
        int rob2 = 0; // The max amount robbed until house i-1

        // rob 2 is the last house
        // rob 1 is the one before that (similar to Fibonacci)
        for (int i=0;i<nums.length;i++){
            // we are maintaining the 3 values in a sliding window fashion
            // We compute the max at each step using the sliding window, and store it in rob2
            int temp = Math.max(nums[i] + rob1, rob2); // i + rob1 simply means there is a gap between them, rob2 is prev
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2; // rob2 is the max at the end, as we computed maximum of prevs
    }
}