// Time: O(n)
// Space: O(1)

class Solution {
    public boolean canJump(int[] nums) {
        int reach = 0;
        for (int i=0;i<nums.length;i++){
            if (i>reach){
                return false; // cant reach this position
            }
            reach = Math.max(reach, i + nums[i]);
            if (reach >= nums.length-1){
                return true; // reach the end, return true
            }
        }
        return false;
    }
}