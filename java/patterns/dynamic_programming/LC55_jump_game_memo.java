// Memoization Solution
// Time: O(n)
// Space: O(n)

class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        int end = nums.length-1;
        boolean[] memo = new boolean[end+1];
        return search(memo, nums, 0, end);
    }

    private boolean search(boolean[] memo, int[] nums, int start, int end){
        // Base Case
        if (start >= end) return true;
        if (memo[start] != false){
            return memo[start];
        }

        int k = nums[start];
        if (k == 0){
            memo[start] = false;
            return false;
        }

        
        // Recursive Step
        for (int i=1;i<=k;i++){
            if (search(memo, nums, start+i, end)){
                return true;
            }
        }

        memo[start] = false;
        return false;
    }
}