// Binary Search (Revised)
// Time: O(log n)
// Space: O(1)
/**
    Binary search, except we need to handle when portions are rotated. I.e. perform binary search on subarrays.
    The crux move is to visualize graphically the pivot point, and find the side of the pivot that the mid point lies.
    The principle is to look for the most ideal conditions to narrow the search, and reject the rest.

    The key implementation detail is the modified binary search:

    If left <= target < mid: drop the right half
    Else drop the left half

    If mid < target <= right: drop the left half
    Else drop the right half
 */

 class Solution {
    public int search(int[] nums, int target) {
        int l=0, r=nums.length-1;

        while (l <= r){
            int mid = l + (r-l)/2;
            if (target == nums[mid]) return mid;

            // Left sorted portion
            if (nums[l] <= nums[mid]){
                if (nums[l] <= target && target < nums[mid]){
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }

            // Right sorted portion
            } else {
                if (nums[mid] < target && target <= nums[r]){
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1; // not found
    }
}