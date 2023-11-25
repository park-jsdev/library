// Dynamic Programming - Bottom Up
// Time: O(mn)
// Space: O(mn)
/**
    General bottom up 2D Dynamic Programming solution. Represent visually the 2D grid, where the right and bottom layer are the
    base cases, and solve from bottom right to top left. The indices represent the characters in the string up to that point,
    and the value represents the minimum operations to get there.

    Initialize with -1 as default flag

    The base cases are if either 1 or both the strings are empty. When the values are the same, we consider the given
    permitted operations for the next value, and fill the value in the dp grid with the min of those.
 */

class Solution {
    public int minDistance(String word1, String word2) {
        // initialization
        int m = word1.length() - 1;
        int n = word2.length() - 1;
        int[][] dp = new int[m+2][n+2]; // extra layer
        for (int[] d : dp){
            Arrays.fill(d, -1); // fill with -1 default flag
        }
        return helper(word1, word2, m, n, dp);
    }

    public int helper(String word1, String word2, int m, int n, int[][] dp){
        // Base Cases
        // strings are both null
        if (m + 1 == 0 && n + 1 == 0){
            return 0;
        }

        // one of the strings are null
        if (m + 1 == 0 || n + 1 == 0){
            return Math.max(m+1,n+1);
        }

        // Recursive Step
        // both values at the index are equal
        if (dp[m][n] != -1) return dp[m][n];
        if (word1.charAt(m) == word2.charAt(n)){
            dp[m][n] = helper(word1, word2, m-1, n-1, dp);
            return dp[m][n];
        } else {
            // try deletion
            int delete = 1 + helper(word1, word2, m-1, n, dp);
            // try insertion
            int insert = 1 + helper(word1, word2, m, n-1, dp);
            // try replacing
            int replace = 1 + helper(word1, word2, m-1, n-1, dp);
            // now we'll choose the minimum out of these 3 and add 1 for the operation cost
            dp[m][n] = Math.min(Math.min(delete, insert), replace);
            return dp[m][n];
        }
    }
}