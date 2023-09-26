// Bit Manipulation and Dynamic Programming
// Time: O(n)
// Space: O(1)
/**
    Work through it from the O(nlog n) solution by using mod, then Recursive to Dynamic Programming solution.
    You can find the overlapping subproblem (pattern) by visualizing the bit representations and handle with the offset.

    The crux move is finding the pattern and handling with offset.
    Key implementation detail is range from i=1 to i=n and using the dp array.
 */

class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n+1];
        int offset = 1;
        for (int i=1;i<=n;i++){
            if (offset*2 == i){
                offset = i;
            }
            dp[i] = 1 + dp[i - offset];
        }
        return dp;
    }
}