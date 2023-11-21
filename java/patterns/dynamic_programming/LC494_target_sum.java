// Dynamic Programming - recursive memoization solution
// Time: O(nm), where m is the range of sums
// Space: O(nm)
/**
    At each step (element in nums), there are 2 choices: add the element or subtract it. This forms a binary tree of decisions.
    We use a cache to store computations.

    The base case is when i == nums.length, and if sum equals the target, it is a valid way.

    The crux is the recursive step, which gets the sum of both choices by recursive calls, then sets the current index value to
    that sum, and puts the sum in the cache.
 */

class Solution {
    HashMap<String, Integer> dp;

    public int findTargetSumWays(int[] nums, int target) {
        dp = new HashMap<>();
        return calculate(nums, 0, 0, target);
    }

    public int calculate(int[] nums, int i, int sum, int target){
        String s = i + "," + sum;

        // Base Case
        if (i == nums.length){
            return (sum == target) ? 1 : 0;
        }

        // Use Cache
        if (dp.containsKey(s)){
            return dp.get(s);
        }

        // Recursive Step
        int res = calculate(nums, i+1, sum + nums[i], target) + calculate(nums, i+1, sum - nums[i], target);
        dp.put(s, res);
        return res;
    }
}