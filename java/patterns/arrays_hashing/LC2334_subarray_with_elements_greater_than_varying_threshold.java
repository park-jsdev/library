class Solution {
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        return count(nums,threshold,0,n-1);
    }

    private int count(int[] nums, int threshold, int start, int end){
        if (start > end) return -1;
        int cur = threshold/(end-start+1);
        for (int i=start;i<=end;i++){
            if (nums[i] <= cur){
                int left = count(nums,threshold,start,i-1);
                int right = count(nums,threshold,i+1,end);
                if (left!=-1) return left;
                else return right;
            }
        }
        return end-start+1;
    }
}