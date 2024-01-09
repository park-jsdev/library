class Solution {
    public int longestOnes(int[] nums, int k) {
        int l = 0, max = 0, count = 0, w = k;

        for (int r=0;r<nums.length;r++){
            if (nums[r] == 0){
                if (w > 0){ // we have flips left
                    w--; // use one
                } else { // no flips left
                    while (nums[l] == 1){
                        l++;
                    }
                    l++; // move l past a 0
                }
            }
            count = r - l + 1;
            max = Math.max(max, count);
        }
        return max;
    }
}