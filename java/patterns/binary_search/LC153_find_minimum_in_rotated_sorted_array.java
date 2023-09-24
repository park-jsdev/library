// Binary Search
// Time: O(log n)
// Space: O(1)
/**
    The crux move is to look for the pivot in the left and right portions.
    The key implementation detail is the basic check for a sorted portion, and the pivot finding logic.
 */

class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length-1;

        while (l <= r){
            // Basic Check
            if (nums[l] <= nums[r]){
                // if the current portion is sorted, it does not contain the pivot, and we simply return the minimum
                return nums[l]; // we have found the min value
            }
            int mid = l + (r-l)/2;

            // If a pivot exists in the portion:

            // if the mid is equal to or greater than left, we know the left component does not contain the pivot,
            // and is a sorted portion. Therefore, the pivot must be in the right portion, so we search it.
            if (nums[mid] >= nums[l]){
                l = mid + 1;
            } else {
                // if mid is smaller than the left, then the left portion contains the pivot, and we need to
                // search that portion for the minimum.
                r = mid;
            }
        }
        return 0;
    }
}