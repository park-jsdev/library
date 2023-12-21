class Solution {
    public int pivotIndex(int[] nums) {
        int res = -1;
        int l = 0, r = 0, prev = -1;
        for (int i=0;i<nums.length;i++){
            r += nums[i];
        }
        for (int i=0;i<nums.length;i++){
            r -= nums[i];
            if (prev >= 0){
                l += nums[prev];
            }
            if (l == r) return i; // immediate return
            prev = i;
        }
        return res;
    }
}