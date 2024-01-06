// Bottom Up
// Time: O(n^2)
// Space: O(n^2)
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int len=1;len<=n;len++){
            for (int start = 0;start<=(n-len);start++){
                int end = start + len - 1; // [start, end] denotes the string under consideration
                if (len == 1){ // base case for 1 char
                    dp[start][end] = 1;
                    continue;
                }
                if (s.charAt(start) == s.charAt(end)){
                    dp[start][end] = 2 + dp[start + 1][end-1]; // how you access the inner subsequence
                } else {
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end-1]);
                }
            }
        }
        return dp[0][n-1]; // the end is the top right cell
    }
}