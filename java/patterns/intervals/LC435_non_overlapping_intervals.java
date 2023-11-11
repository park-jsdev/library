// Merge Intervals
// Time: O(nlogn) due to sort, O(n) for the scan
// Space: O(1) due to in place
/**
    The crux is to visualize and sort the problem, and it becomes simple.
    The key implementation details are to recognize overlapping intervals with the start of 2nd < end of 1st,
    and removing the interval which ends last in case they overlap.

    At each iteration, the interval will be shifted no matter what, but if they overlap, we handle which one to keep.

    The inner loop can be tricky, but remember that if there is no interval, we need to set the prev interval
    to the current one at i. For the removal, we 

    Remember the Array sorting in Java using comparator
 */

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int intervalsRemoved = 0; // track count

        // First sort
        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));
        
        int[] intervalFirst = intervals[0]; // mark first interval

        for (int i=1;i<intervals.length;i++){
            if (firstIntervalwithinSecond(intervalFirst, intervals[i])){
                // mark first interval to be removed
                intervalsRemoved++;
                // determine which interval to remove
                // remove the interval that ends last, keep the one that ends first
                if (intervalFirst[1] > intervals[i][1]){
                    // if the first interval ends later, we switch with the 2nd interval
                    intervalFirst = intervals[i];
                }
                // if the 2nd interval ends later, we implicitly handle by not switching. The intervalFirst
                // remains what it was before
            } else { // no overlap, but still need to shift interval
                intervalFirst = intervals[i];
            }
        }
        return intervalsRemoved;
    }
    
    // The first interval is within the second if it begins earlier than the 1st's end
    public boolean firstIntervalwithinSecond(int[] intervalFirst, int[] intervalSecond){
        return intervalSecond[0] < intervalFirst[1];
    }
}