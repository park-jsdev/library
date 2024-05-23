// Time: O(n^2)
// Space: O(n)

class Solution {
    private int min = Integer.MAX_VALUE;

    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int n = nums.length - 1;
        int[] memo = new int[n+1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        search(nums, 0, n, 0, memo);
        return min;
    }

    private void search(int[] nums, int start, int n, int steps, int[] memo){
        if (steps >= min){
            return; // prune paths already worse than curr min
        }

        // Base case
        if (start >= n){
            min = Math.min(steps, min);
            return;
        }

        if (memo[start] <= steps){
            return; // found in cache
        }

        memo[start] = steps;

        int k = nums[start];
        int end = Math.min(start + k, n);

        for (int i=start+1;i<=end;i++){
            search(nums, i, n, steps+1, memo);
        }
    }
}