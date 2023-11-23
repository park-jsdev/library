// Dynamic Programming
// Time: O(nm)
// Space: O(nm)
/**
    The crux is that the position at s3 is given by the sum of positions of s1 and s2. That is, s3 (i+j) = s1[i] and s2[j].
    In building the dp table, we need to also check the previous position, because it tells us if the substring (subproblems)
    can form a valid string.
 */

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()){ // if the lengths are not equal then return false
            return false;
        }

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[s1.length()][s2.length()] = true; // The base case, empty string for all 3

        for (int i=dp.length-1;i>=0;i--){
            for (int j=dp[0].length-1;j>=0;j--){
                if (
                    i < s1.length() && // in bounds
                    s1.charAt(i) == s3.charAt(i + j) && // checks if the character at the pos matches
                    dp[i+1][j] // check prev subproblem
                ){
                    dp[i][j] = true;
                }
                if (
                    j < s2.length() && // in bounds
                    s2.charAt(j) == s3.charAt(i + j) && // checks if the character at the pos matches
                    dp[i][j+1] // check prev subproblem
                ){
                    dp[i][j] = true;
                }
            }
        }
        return dp[0][0]; // we build our way to the top left
    }
}