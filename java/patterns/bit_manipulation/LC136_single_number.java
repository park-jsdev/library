// Bit Manipulation
// Time: O(n)
// Space: O(1)
/**
    Use XOR on each element. Only the unique will remain.
 */

 class Solution {
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i=1;i<nums.length;i++){
            res ^= nums[i];
        }
        return res;
    }
}