// Dynamic Programming
// Time: O(n*sum)
// Space: O(n*sum)
/**
    The crux is that we check the entire array for the half sum, as if it exists, the other half must exist, given that
    the sum is even. As an optimization, you can quick return upon finding it. Method of exhaustion.

    The relationship between dp[][] and the sum here is:
    - dimension i: represents the number from the nums array, but 1-indexed.
    - dimension j: represents the target sum (by the index). E.g. target of 3 would be j=3, not dp[i][j] = 3

    If dp[i][j] = true, it means that it is possible to achieve sum j using the first i numbers.
    If false, it is not possible.

    We fill the DP array by choose or not choosing the current number (similar to backtracking).
    By excluding, we simply check the cached value at dp[i-1][j] as we have already done the work.
    To include, we check dp[i][j] = dp[i-1][j-nums[i-1]].
    j-nums[i-1]: to make a sum of j, we need the remainder to be j-nums[i-1]. We use i-1 here because of 1-based index in our dp,
    while nums is 0-based indexed.
    We look at the previous numbers i-1 in dp[i-1][right hand side], because we have allocated i to be a part of the sum.
    We cannot call dp[i] as we have not yet computed it. So we check if j-the previous sum as the number that we need,
    and see if the previous numbers can sum up to it.


 */

class Solution {
    public boolean canPartition(int[] nums) {
        // Data model
        int sum = 0;
        for (int num : nums){
            sum += num;
        }
        // Basic check
        if (sum % 2 != 0) return false; // if it is odd, then the half sum cannot exist

        int target = sum/2;
        boolean[][] dp = new boolean[nums.length+1][target+1];
        int n = nums.length;

        for (int i=0;i<=n;i++){
            for (int j=0;j<=target;j++){
                // Base case
                if (i == 0 || j == 0){
                    if (i == 0){
                        dp[i][j] = false; // dp[0][j] is false because cannot form a sum j with 0 nums
                    } else if (j == 0) {
                        dp[i][j] = true; // dp[i][0] is true because a sum of 0 can always be formed with any number of nums
                    }
                // Filling the DP array
                // 2 possibilities:
                // 1. Exclude curr i, so dp[i][j] = dp[i-1][j]
                // 2. include curr, so check if the sum j-nums[i-1] can be formed using the first i-1 nums
                } else if (j >= nums[i-1]){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][target]; // by the end, if it is true, then it is valid
    }
}