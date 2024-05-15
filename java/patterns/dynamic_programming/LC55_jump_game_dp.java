// Bottom-Up DP Solution
// Time: O(n^2)
// Space: O(n)

class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        int end = nums.length-1;
        boolean[] dp = new boolean[end+1];

        dp[end] = true; // the last idx is always true, base case

        for (int i=end-1;i>=0;i--){
            int k = Math.min(i + nums[i], end); // max idx we can reach from position i
            // important because we dont want to go out of bounds
            for (int j=i+1;j<=k;j++){
                if (dp[j]){
                    dp[i] = true; // if dp[j] is true then we know dp[i] is true. We already computed dp[j]
                    break; // we already know i can reach the end
                }
            }
        }
        return dp[0]; // first idx can reach the end
    }
}