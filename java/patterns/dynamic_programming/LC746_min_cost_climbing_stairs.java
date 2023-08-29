/**
    Dynamic Programming Tabulation solution
    Time: O(n)
    Space: O(n)

    Review the Top down Recursive, Recursive with Memoization solution.
    There can be further optimization to reduce space to O(1).
 */ 
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        for (int i=cost.length-3;i>=0;i--){ // Note that we iterate from the end to the start
            cost[i] += Math.min(cost[i+1], cost[i+2]);
        }
        return Math.min(cost[0], cost[1]);
    }
}