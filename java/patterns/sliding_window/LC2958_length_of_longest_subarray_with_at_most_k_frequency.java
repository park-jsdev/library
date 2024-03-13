class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int res = 0, i = 0, n = nums.length;;
        for (int j=0;j<n;j++){
            freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
            while (freq.get(nums[j]) > k){
                freq.put(nums[i], freq.get(nums[i++]) - 1);
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
        
    }
}