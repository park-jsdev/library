class Solution {
    public int longestSubarray(int[] nums) {
        if (nums.length == 1) return 0;
        int max = 0, l = 0;
        boolean hasDeleted = false;
        for (int r=0;r<nums.length;r++){
            if (nums[r] == 0){
                if (!hasDeleted){
                    hasDeleted = true;
                } else {
                    max = Math.max(max, r - l - 1);
                    while (nums[l] != 0){
                        l++;
                    }
                    l++;
                }
            }
        }
        // If we reach the end having deleted
        if (!hasDeleted){
            max = nums.length - 1; // no 0s were found, so delete 1 of the 0s
        } else {
            max = Math.max(max, nums.length - l - 1);
        }
        return max;
    }
}