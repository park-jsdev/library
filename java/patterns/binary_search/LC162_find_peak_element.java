// Binary Search
// Time: O(log n)
// Space: O(1)
/**
    Binary search but with custom check conditions.

    We partition based on the neighbors of the pivot value. An enum approach is the cleanest.
 */

class Solution {
    public enum NeighborState {
        CHECK_LEFT, CHECK_RIGHT, PEAK_FOUND, ERROR
    }

    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1, m = 0;

        while (l < r) {
            m = l + (r - l) / 2;
            NeighborState state = checkNeighbors(nums, m);
            switch (state) {
                case PEAK_FOUND:
                    return m;
                case CHECK_LEFT:
                    r = m - 1;
                    break;
                case CHECK_RIGHT:
                    l = m + 1;
                    break;
                default: // ERROR case, handle appropriately
                    throw new IllegalStateException("Unexpected state");
            }
        }
        return l;
    }

    public NeighborState checkNeighbors(int[] nums, int m) {
        // Check if the current element is a peak
        if ((m == 0 || nums[m - 1] < nums[m]) && (m == nums.length - 1 || nums[m + 1] < nums[m])) {
            return NeighborState.PEAK_FOUND;
        }

        // Decide direction: if the right neighbor is greater or equal, move right, otherwise move left.
        return (m < nums.length - 1 && nums[m] < nums[m + 1]) ? NeighborState.CHECK_RIGHT : NeighborState.CHECK_LEFT;
    }


}