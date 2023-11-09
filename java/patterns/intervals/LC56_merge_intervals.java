// Merge Intervals
// Time: O(nlogn) due to sort
// Space: O(n)
/**
    Classic merge intervals. We first need to sort the input, then compare ends of the prev with the start of the next.
    If they overlap, extend the prev interval's end to the curr's end, else add the curr to the result list.

    Key implementation details are to maintain an ArrayList for results list, sorting it with the comparator, and
    converting back to the int[][] format to return.
 */

 class Solution {
    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> ans  = new ArrayList<>();
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));
        ans.add(intervals[0]);
        for (int i=1;i<intervals.length;i++){
            // compare the values of prevEnd and curStart
            int curStart = intervals[i][0];
            if (curStart <= ans.get(ans.size() - 1)[1]){
                // merge
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size()-1)[1], intervals[i][1]); // extend the end of the prev interval
            } else { // add non-overlapping interval
                ans.add(intervals[i]);
            }
        }
        int[][] res = new int[ans.size()][2];
        ans.toArray(res);
        return res;
    }
}