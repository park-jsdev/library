// Dynamic Programming - Memoization (Top Down Recursion)
// Time: O(st)
// Space: O(st)
/**
    The crux is to understand the state transition possibilities:
        Base case 1: if t is empty, there is only 1 possible s, which is also empty
        Base case 2: if t is non-empty, yet s is empty, there can be no solutions, so 0

        Then if s[i] == t[j]:
            Check (i+1,j+1)
            Check (i+1, j)
        else:
            (i+1,j)

    In recursion, these iterations are handled implicitly by the function calls.
 */

class Solution {
    public int numDistinct(String s, String t) {

        // Initialize
        int n = s.length() + 1;
        int m = t.length() + 1;
        int[][] memo = new int[n][m];

        // Default flag
        for (int[] row : memo){
            Arrays.fill(row, -1);
        }

        return recursion(s, t, 0, 0, memo);
    }

    public int recursion(String s, String t, int sIdx, int tIdx, int[][] memo){
        if (memo[sIdx][tIdx] != -1){ // Already cached
            return memo[sIdx][tIdx];
        }

        if (tIdx >= t.length()){ // base case 1
            return 1;
        }

        if (sIdx >= s.length()){ // base case 2
            return 0;
        }

        // Recursive Step
        if (t.charAt(tIdx) == s.charAt(sIdx)){ // When matching
            memo[sIdx][tIdx] =
                recursion(s, t, sIdx + 1, tIdx + 1, memo) +
                recursion(s, t, sIdx + 1, tIdx, memo);
            return memo[sIdx][tIdx];
        }

        // Not matching, continue
        memo[sIdx][tIdx] = recursion(s, t, sIdx + 1, tIdx, memo);
        return memo[sIdx][tIdx];
    }
}