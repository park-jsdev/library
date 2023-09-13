// Dynamic Programming with 2 variables
// Time: O(n)
// Space: O(1)
/**
    We modify the function from House Robber 1 to work on this problem.
    The crux is that with a circular array, we need to consider 2 cases:
    1. the array nums minus the last element
    2. the array nums minus the first element
 */

 class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        return Math.max(
            helper(nums, 0, nums.length-2),
            helper(nums, 1, nums.length-1)
            );
    }

    // Helper function from House Robber 1, we modify to take differing subsets
    public int helper(int[] nums, int start, int end){
        int rob1 = 0;
        int rob2 = 0;

        for (int i=start;i<=end;i++){
            int temp = Math.max(nums[i] + rob1, rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }
}