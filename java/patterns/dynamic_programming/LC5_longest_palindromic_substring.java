// Dynamic Programming
// Time: O(n^2)
// Space: O(1)
/**
    The idea for finding palindromes is to expand outward with pointers i and j from the mid point, and checking if they are equal.
    The crux is to use dynamic programming with a window.

    The key implementation details are to store palindrome start position, the max length, dp[n][n] table,
    and the palindrome checking using the dp array. The conditions j-1<3 and dp[i+1][j-1] should be noted.

 */

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int palindrome_starts_at = 0, max_len = 0;

        // dp[i][j] indicates if substring s starting at i and ending at j is a palindrome
        boolean[][] dp = new boolean[n][n];

        for (int i=n-1;i>=0;i--){ // Keep increasing the possible palindrome string
            for (int j=i;j<n;j++){ // Find the max palindrome within this window (i,j)

                // check if substring between (i,j) is palindrome
                dp[i][j] = (s.charAt(i) == s.charAt(j)) // chars i and j should match
                &&
                (j-i<3 // if the window is less than or equal to 3, then indices are correct
                || dp[i+1][j-1]); // if window is > 3, take the substring of (i+1, j-1), expanding pointers outwards
                // The idea is that we only need to consider (i+1, j-1) because the inner window has already been checked
                // at each iteration

                // update the max palindrome string
                if (dp[i][j] && (j-i+1>max_len)){
                    palindrome_starts_at = i;
                    max_len = j-i+1;
                }

            }
        }
        return s.substring(palindrome_starts_at, palindrome_starts_at + max_len);
    }
}