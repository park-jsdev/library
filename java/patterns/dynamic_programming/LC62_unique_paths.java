// Dynamic Programming 2D
// Time: O(m*n)
// Space: O(m*n)
/**
    We define the dp array as a representation of the input grid. Each cell in the dp array is filled with the number of unique
    paths to the end from that cell.

    We first define the goal as the base case, with a value of 1, and the last row and last col as 1. By the conditions
    of the problem, they can only be 1. You can solve it by only setting the end point as 1, but you would define the
    boundaries of the search using dimensions m and n.

    By the end of the function, the last state (or the first cell, as we started from the end) will return the sum of the
    unique paths.
 */

 class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Fill last row
        for (int j=0;j<n;j++){
            dp[m-1][j] = 1;
        }

        // fill last col
        for (int i=0;i<m;i++){
            dp[i][n-1] = 1;
        }

        for (int i=m-2;i>=0;i--){
            for (int j=n-2;j>=0;j--){
                dp[i][j] = dp[i][j+1] + dp[i+1][j];
            }
        }
        return dp[0][0];
    }
}