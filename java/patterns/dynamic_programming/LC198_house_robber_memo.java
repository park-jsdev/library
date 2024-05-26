// Time: O(n)
// Space: O(n)

class Solution {
    public int rob(int[] nums) {
        int n = nums.length-1;
        int[] memo = new int[n+2];
        Arrays.fill(memo, -1);
        return rob(nums, n, memo);
    }

    private int rob(int[] nums, int i, int[] memo){
        if (i<0){
            return 0;
        }
        if (memo[i] >= 0){
            return memo[i];
        }
        int res = Math.max(rob(nums, i-2, memo) + nums[i], rob(nums, i-1, memo));
        memo[i] = res;
        return res;
    }
}