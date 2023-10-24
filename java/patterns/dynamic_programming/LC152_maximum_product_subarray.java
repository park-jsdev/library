// Dynamic Programming
// Time: O(n)
// Space: O(1)
/**
    The crux is to understand the series and to track both the max and the min (for negative values). The reason is
    because large negative values turn a min product into a max product when multiplied.

    The key implementation details are:
    - Max and min, of comparing all 3 values: (n, temp, min * n)
    - max = Math.max(n, Math.max(tmp, min * n)); We need to also consider the element n as a potential max, for the following cases:
        - Starting a new subarray. If the previous is a negative, and the new n is postive, it is better to start a new subarray.
        However, in the case that the next element is negative, the previous product is contained in min.
        - max is negative, min is postive, and n is positive.
 */

class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) return nums[0];

        int res = nums[0];
        int max = 1;
        int min = 1;

        for (int n : nums){
            int tmp = max * n; // at each iteration, we will multiply the element with the curr max
            max = Math.max(n, Math.max(tmp, min * n)); // after iteration, we then compute the max and see if 
            min = Math.min(n, Math.min(tmp, min * n));
            res = Math.max(res, max);
        }
        return res;
    }
}