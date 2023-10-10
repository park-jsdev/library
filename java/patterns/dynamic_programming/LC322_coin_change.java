// Dynamic Programming
// Time: O(n * amount), as we need to iterate each coin for each i in (1, amount)
// Space: O(amount) = O(1)
/**
    Famous classic problem, second only to Fibonacci numbers.

    The crux is to recognize the bottom up DP, cache the subamounts in the range (0,amount), and apply the recurrence relation
    using the nested for loop. I.e. for i in (1,amount), iterate coins for j in (0, coins), then compute using the cache.

    The key implementation details are filling the dp array with default values, the diff condition (i - coin >= 0)
    and the computation of dp[i], and the return value, only if it does not equal the default value.

    A small optimization is that you can sort the Array in advance, as it would break out of some for loops.
 */

class Solution {
    public int coinChange(int[] coins, int amount) {
        // Basic checks
        if (amount < 0 || coins.length == 0 || coins == null){
            return 0;
        }

        int[] dp = new int[amount+1]; // we need an extra space for the 0 amount
        Arrays.fill(dp, amount+1); // we use amount+1 as the default value, as it is never used, and the constraints allow it.
        dp[0] = 0; // Base case, 0 amount takes 0 coins

        for (int i=1;i<=amount;i++){ // we compute the min cost at each sub-amount up to the amount
            for (int coin : coins){
                if (i - coin >= 0){ // we consider i as the value itself, therefore i - coin is the difference
                    // The recurrence relation
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]); // We take the min between dp[i],
                    // which would be the default placeholder value, or 1 + dp[i-coin],
                    // which means to add 1 to the count (by taking the coin at the current index of coins)
                    // and the value we cached at dp[i-coin] (the remainder).
                    // The recurrence relation effectively accepts or rejects dp[i], and computes the cost if accepts.
                }
            }
        }
        return dp[amount] != amount + 1 ? dp[amount] : -1; // we will only return the value at
        // dp[amount] if it does not equal the default value, meaning there was a valid solution
    }
}