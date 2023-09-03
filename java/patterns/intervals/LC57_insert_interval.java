// Merge Intervals with output container
// Time: O(n)
// Space: O(n)

/**
    Remember the 3 cases (6 implied).
    Implementation is tricky, especially note the output container and .toArray().
 */

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Output container
        List<int[]> res = new ArrayList<>();

        // Check cases
        for (int[] interval : intervals) {
            // There is no new interval
            // Add intervals that come before the new interval
            if (newInterval == null || interval[1] < newInterval[0]) res.add(
                interval
            ); 
            // Add intervals that come after the new interval
            else if (interval[0] > newInterval[1]) {
                res.add(newInterval);
                res.add(interval);
                newInterval = null; // clear the new interval

            // Merge overlapping intervals with new interval
            } else {
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }

        // Add the interval to the end if loop gets here 
        if (newInterval != null) res.add(newInterval);

        // Convert to int array
        return res.toArray(new int[res.size()][]);
    }
}