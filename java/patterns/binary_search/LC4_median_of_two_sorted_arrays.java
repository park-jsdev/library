// Binary Search
// Time: O(log(min(m,n))) = O(Log(n))
// Space: O(1)
/**
    The crux is to use binary search (pointers) to partition the smaller of the two input arrays into two parts. Visualize
    the arrays, partition, and observe that the right end of the left total partition should be approximately the median.

    Find the partition of the larger array such that the sum of left partitions of both arrays is half the total elements.
    The right end of that left partition should be approximately the median, depending on even or odd, as the input arrays are
    sorted. At the end, check if partition is valid, else move the left or right pointer by 1 until it is, then calculate and
    return the median.

    Key implementation details:
    - Find the smaller array, find the total and the half size.
    - Binary Search Setup
    - Handle edge cases by setting to infinity (Integer.MIN_VALUE/MAX_VALUE)
    - Consider even, odd, invalid median
    - res = res = (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0, res = Math.max(l1, l2)
    - move pointers in case of invalid median

 */

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if (m > n){ // We only need to run the algorithm on the smaller of the 2
            return findMedianSortedArrays(nums2, nums1);
        }

        int total = m + n;
        int half = (total + 1) / 2; // Half of the total of both lists is the size of each partition

        int l = 0;
        int r = m;

        double res = 0.0;

        while (l <= r){ // Binary Search
            int i = l + (r - l) / 2;
            int j = half - i;

            // Get the four bounds of the partitions using the binary search pointers
            int l1 = (i > 0) ? nums1[i-1] : Integer.MIN_VALUE; // we set to min/max value, effectively infinity for out of range
            int r1 = (i < m) ? nums1[i] : Integer.MAX_VALUE;
            int l2 = (j > 0) ? nums2[j-1] : Integer.MIN_VALUE;
            int r2 = (j < n) ? nums2[j] : Integer.MAX_VALUE;

            // partition is correct
            if (l1 <= r2 && l2 <= r1){
                // Even size
                if (total % 2 == 0){
                    res = (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else { // Odd
                    res = Math.max(l1, l2);
                }
                break;
            }
            // partition is wrong, search and update left/right pointers
            else if (l1 > r2){
                r = i - 1;
            } else {
                l = i + 1;
            }
        }
        return res;
    }
}