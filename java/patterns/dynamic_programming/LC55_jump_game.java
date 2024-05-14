// Time: O(n)
// Space: O(1)

class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        int x = nums.length-1;

        for (int i=nums.length-1;i>=0;i--){
            if (i + nums[i] >= x){
                x = i;
            }
        }
        if (x != 0) return false;
        return true;
    }
}