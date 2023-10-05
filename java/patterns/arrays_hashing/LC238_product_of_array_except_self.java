// Arrays - Prefix/Postfix Solution
// Time: O(n)
// Space: O(1)
/**
    The crux is that we use a prefix and postfix approach to exclude the element at the index, yet for constant memory,
    we can do this in place, without the extra arrays. The l and r pointers effectively lag behind the index by 1, so we
    implicitly handle the index i exclusion.

    The key implementation details are the l and r pointers, and the 2 passes, first from left, then from right.
 */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        int r = 1, l = 1; // The pointers must start at 1
        for (int i=0;i<nums.length;i++){
            answer[i] = l;
            l *= nums[i];
        }
        for (int i=nums.length-1;i>=0;i--){
            answer[i] *= r;
            r *= nums[i];
        }
        return answer;
    }
}