// Dynamic Programming - Iterative Bottom-Up
// Time: O(n*m)
// Space: O(n*m)
/**
    As we compute from the end (base case), when we look for dp[i][j], we look at the required subproblems dp[i+1][j+1],
    dp[i][j+1] and dp[i+1][j].

    If the character at i j matches, then the length is 1 + the remaining substrings (dp[i+1][j+1], or right diagonal).
    If they do not match, then we take the max length either by skipping i or j. We only check the next iteration as we have
    already solved the subproblem. If there are no more matches for the rest of the substring, the 0 values from the boundaries
    will be propagated into the current cell.
 */

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1]; // extra element to handle base case of empty string

        // iterate from the end
        for (int i=text1.length()-1;i>=0;i--){
            for (int j = text2.length()-1;j>=0;j--){
                if (text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = 1 + dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }
        return dp[0][0];
    }
}