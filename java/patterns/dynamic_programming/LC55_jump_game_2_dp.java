// Time: O(n^2)
// Space: O(n)

class Solution {
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int n = nums.length;
        int[] dp = new int[n];

        for (int i=0;i<n;i++){
            dp[i] = n + 1; // max placeholder value
        }

        dp[n-1] = 0; // base case

        for (int i=n-2;i>=0;i--){
            int steps = nums[i];
            for (int j=1;j<=steps && i+j<n;j++){
                dp[i] = Math.min(dp[i], dp[i+j]+1);
            }
        }

        return dp[0];
    }
}