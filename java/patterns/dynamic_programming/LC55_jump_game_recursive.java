// Recursive Solution
// Time: O(2^n)
// Space: O(n)

class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        int end = nums.length-1;
        return search(nums, 0, end);
    }

    private boolean search(int[] nums, int start, int end){
        // Base Case
        if (start >= end) return true;
        int k = nums[start];
        if (k == 0) return false;

        // Recursive Step
        for (int i=1;i<=k;i++){
            if (search(nums, start+i, end)){
                return true;
            }
        }

        return false;
    }
}