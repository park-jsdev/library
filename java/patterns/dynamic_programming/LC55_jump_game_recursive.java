// Recursive Solution
// Time: O(2^n)
// Space: O(n)

class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        return search(nums, 0, nums.length-1);
    }

    private boolean search(int[] nums, int start, int end){
        if (start >= end) return true; // base case

        for (int i=start+1;i<=start + nums[start];i++){
            if (search(nums, i, end)){
                return true;
            }
        }
        return false;
    }
}