// Dynamic Programming - Unbounded Knapsack - Tabulation
// Time: O(nm)
// Space: O(m)
/**
    The inutition is that each coin introduces new ways to make up amounts. We cumulatively build up the number of ways to make
    up each amount using the coins seen so far.
 */

class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];

        // Basic Check
        dp[0] = 1; // amount 0 has only 1 way

        for (int coin : coins){
            for (int i=1;i<=amount;i++){
                if (coin <= i){
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }
}