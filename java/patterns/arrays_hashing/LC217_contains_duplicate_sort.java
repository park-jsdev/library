// Sort and window solution
// Time: O(n)
// Space: O(1)
/**
    Check 2 elements at a time after sorting.
 */

class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums.length == 1) return false;
        Arrays.sort(nums);
        
        for (int i=1;i<nums.length;i++){
            if (nums[i-1] == nums[i]) return true;
        }
        
        return false;
    }
}