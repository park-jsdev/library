// Dynamic programming
// Time: O(n^2)
// Space: O(n)
/**
    Classic DP problem.
    
    The key intuition is that we do a n^2 nested loop, i starting from the right, and j starting from the left (inner),
    and the dp[], LIS, stores the max subsequence length to the right starting at the index i. Whenever we encounter an element
    nums[j] greater than nums[i], it meets the criteria of the monotonic increasing subsequence, and we need to consider a new
    max length. However, since we cache repeated subproblems, we will store the maximum of the current value at LIS[j] + 1
    (considering i itself), and the current LIS[i]. 

    As usual, we proceed by method of exhaustion, and we maintain the local max as a variable, and it will be the global max
    at the end.

    The key implementation detail is Arrays.fill(LIS, 1), as we need the default to be 1.
    Also, we need to consider LIS[i] = Math.max(1+ LIS[j], LIS[1]).
 */

 class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) return 1;

        int[] LIS = new int[nums.length];
        Arrays.fill(LIS, 1);
        int max_so_far = 1;

        for (int i=nums.length-1;i>=0;i--){ // decrement from the end, default max is 1
            for (int j=i+1;j<nums.length;j++){ // inner loop, iterate j to the right
                if (nums[i] < nums[j]){ // if j is greater than i then consider a new max starting at the outer loop i
                    LIS[i] = Math.max(1 + LIS[j], LIS[i]);
                }
            }
            max_so_far = Math.max(max_so_far, LIS[i]);
        }
        return max_so_far;
    }
}