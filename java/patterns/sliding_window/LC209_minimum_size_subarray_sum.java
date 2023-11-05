class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int l = 0, r = 0, sum = 0;

        while (r < nums.length){
            sum += nums[r];

            while (l <= r && sum >= target){ 
                min = Math.min(min, (r-l+1));
                sum -= nums[l];
                l++;
            }
            r++;
        }
        if (min == Integer.MAX_VALUE) return 0;
        return min;
    }
}