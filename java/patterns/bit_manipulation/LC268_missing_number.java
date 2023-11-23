// Bit Manipulation - XOR
// Time: O(n)
// Space: O(1)
/**
    The idea is that XOR a^b^b = a. XOR on the same numbers will give 0, while a different number will give a 1, isolating it.

    A non-bit manipulation solution is to take the sum of both input and target arrays, then find the difference, and what
    remains is the missing number.
 */

class Solution {
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for(int i=0; i<nums.length; i++){
            res = res ^ i ^ nums[i]; // a^b^b = a
        }
        return res;
    }
}