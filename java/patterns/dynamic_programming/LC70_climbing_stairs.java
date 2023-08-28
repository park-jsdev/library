// Optimal solution: Bottom-Up Dynamic Programming (Fibonacci)
// Time: O(n)
// Space: O(4) ~= O(1). Only 4 variables must be stored in memory (n is not counted).

/**
    For all Dynamic Programming questions, follow the steps:
    1. Represent visually. All dynamic programming questions can
    be solved with recursion, and therefore, all dynamic programming questions can
    be represented as a decision tree.
    2. Solve Brute Force logically (pseudocode)
    3. Solve Top-Down Recursion logically (pseudocode), It will lead to TLE, as O(2^n) Runtime.
    4. Solve Top-Down Recursion with memoization (pseudocode), This should be O(n) Runtime, and accepted as a solution.
    5. Solve Bottom-Up Tabulation (implement)
    5. Solve Bottom-Up Dynamic Programming with Space Optimization (implement if possible)
 */

class Solution {
    public int climbStairs(int n) {

        // Base Case:
        int a = 1; // prev, or "one step"
        int b = 1; // curr, or "two steps"
        int c; // temp ptr

        // Basic Check:
        if (n < 2){
            return b;
        }

        // Recursive Step:
        // At each step, we only need to store 2 variables - therefore, we do not need to memoize
        for (int i=2;i<=n;i++){
            c = b;
            b = a + b; // we are interested in the "two steps" condition, and this is computed from
            // "one step" + "two steps" of how to get to that point from the previous iteration.
            a = c;
        }
        return b; // we return the final "two steps" condition. If n >= 2, then it must have a "two steps" case.
    }
}