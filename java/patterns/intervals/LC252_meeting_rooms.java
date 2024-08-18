class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null) return false;
        Arrays.sort(intervals, (x,y) -> x[0] - y[0]);
        for (int i=1;i<intervals.length;i++){
            if (intervals[i-1][1] > intervals[i][0]) return false;
        }
        return true;
    }
}