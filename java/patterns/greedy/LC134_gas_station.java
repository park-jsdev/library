// Greedy
// Time: O(n)
// Space: O(1)
/**
    We do not need to set res to every index because we only take the maximum value of gas (greedy approach).
    E.g. if we pass the surplus conditions and set res to i, we either have the correct starting point, or
    we will fail conditions later at j. In that case, there is no point to set res to any index between i or j.
    If i was the correct index, the conditions will pass until the end of the loop, and any subsquent indices
    will only reduce the surplus diff in gas.

    The crux move is to track by diffs, set the greedy condition as the sum becoming negative, 
    and find the index by exhaustion. This works because the result is guaranteed to be unique.
    The key implementation details are the initial check, the diff condition, and finding res by exhaustion.
 */

 class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum_gas = 0, sum_cost = 0, res = 0, total = 0;
        for (int i=0;i<gas.length;i++){
            sum_gas += gas[i];
            sum_cost += cost[i];
        }
        // If there is less gas than the cost of the trip, there can be no solution.
        if (sum_gas < sum_cost) return -1;
        for (int i=0;i<gas.length;i++){
            total += gas[i] - cost[i]; // We track diffs
            // if the total is less than 0 then we cannot get to the next position from that index
            if (total < 0){ 
                total = 0;
                res = i + 1; // We push res to next index, and arrive at the correct index by exhaustion
            }
        }
        return res;
    }
}