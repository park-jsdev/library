// Time: O(n log n) because of sort, O(n) if no sort
// Space: O(n)

class Solution {
    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> li = new ArrayList<>();

        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0])); // sort by the first index
        
        int[] cur_int = intervals[0];
        li.add(cur_int);

        for (int i=1;i<intervals.length;i++){
            int cur_end = cur_int[1];
            int nxt_start = intervals[i][0];
            int nxt_end = intervals[i][1];

            if (cur_end >= nxt_start) {
                // There is an overlap, merge the current and next intervals
                cur_int[1] = Math.max(cur_end, nxt_end);
            } else {
                // No overlap, add the next interval to the list and update the currentInterval
                cur_int = intervals[i];
                li.add(cur_int);
            }
        }
        return li.toArray(new int[li.size()][]);
    }
}