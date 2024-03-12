// Time: O(n)
// Space: O(n)

class Solution {
    public List<Integer> goodIndices(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        int[] nonIncreasingBefore = new int[n];
        int[] nonDecreasingAfter = new int[n];

        nonIncreasingBefore[0] = 1;
        for (int i=1;i<n;i++){
            if (nums[i] <= nums[i-1]) nonIncreasingBefore[i] = nonIncreasingBefore[i-1] + 1;
            else nonIncreasingBefore[i] = 1;
        }

        nonDecreasingAfter[n-1] = 1;
        for (int i=n-2;i>=0;i--){
            if (nums[i] <= nums[i+1]) nonDecreasingAfter[i] = nonDecreasingAfter[i+1] + 1;
            else nonDecreasingAfter[i] = 1;
        }

        for (int i=k;i<n-k;i++){
            if (nonIncreasingBefore[i-1] >= k && nonDecreasingAfter[i+1] >= k) res.add(i);
        }
        return res;
    }
}