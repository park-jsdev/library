// Dynamic Programming - Bottom Up
// Time: O(n), each decision is O(1) and each element will be considered once.
// Space: O(n), this can be improved to O(1) with the constant variables optimal solution.
/**
    The conditions for the computation is to consider 1. the single digit, and 2. the double digit, if it is in the range 1, 26.

    In the bottom up with tabulation solution, we track the count at each index (representing the overlapping subproblems
    in recursion), then return the cached value at the index of the last character.

    Instead of recomputing at each iteration i-1 and i-2, we simply take the stored values from the cache dp[].

    The key implementation detail is the cache dp[], pointers to handle the empty string, and the iteration loop.
 */

 class Solution {
    public int numDecodings(String s) {
        int[] dp  = new int[s.length() + 1]; // we need to have dp of size len+1, as we need a "dummy" pointer

        // Base Case
        dp[0] = 1; // empty string, this is still a valid choice
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        // Iteration (representing Recursive Step)
        for (int i=2;i<s.length()+1;i++){
            if (s.charAt(i-1) != '0'){ // if the digit is not 0, there is 1 count, we take the count of dp[i-1]
                dp[i] += dp[i-1];
            }
            if (
                s.charAt(i-2) == '1' || // first digit is 1
                (s.charAt(i-2) == '2' && s.charAt(i-1) < '7') // the 1st digit is 2 and the 2nd is less than 7 (within 27)
            ){
                dp[i] += dp[i-2]; // the double digits is also valid, we add another count from dp[i-2]
            }
        }
        return dp[s.length()]; // we return the index of the string length, before the "dummy" pointer
    }
}