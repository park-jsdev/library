// Time: O(n)
// Space: O(n)

class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int[] prefixSum = new int[nums.length+1];
        for (int i=0;i<nums.length;i++){
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }
        return Math.max(maxSum(prefixSum, firstLen, secondLen), maxSum(prefixSum, secondLen, firstLen));
    }
    private int maxSum(int[] p, int L, int M){
        int res = 0;
        for (int i=L+M,maxL=0;i<p.length;i++){
            maxL = Math.max(maxL, p[i-M]-p[i-M-L]); // update max of L-len subarray
            res = Math.max(res, maxL + p[i] - p[i-M]); // update max of the sum of L-len & M-len subarrays.
        }
        return res;
    }
}