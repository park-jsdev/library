// Intervals - Min Heap
// Time: O(n log n + m log n)
// Space: O(n + m)
/**
    This implementation is OOP heavy, but it can be solved with the algorithm:
    0. Setup: Sort the queries array define wrapper classes if possible
    1. take the intervals with a lower start time than each query we encounter and add to our pq
    2. then remove intervals which are inconsistent with the query (ends before it so irrelevant)
    3. get the size of the smallest interval (top of the min heap of valid intervals) and add to our result list

    The key detail is that the min heap nodes should be defined as (s, r), where s is the size of the interval, and
    r is the end of that interval. Visualize it on a number line, and after sorting the queries, see where each falls (which interval)

    Use an array based approach, not an OOP one in an interview.
 */
class Query {
    int index;
    int queryTimeStamp;
    int result;

    public Query(int index, int queryTimeStamp){
        this.index = index;
        this.queryTimeStamp = queryTimeStamp;
        this.result = -1; // initial
    }

    @Override
    public String toString(){
        return "[" + index + "," + queryTimeStamp + "," + result + "]";
    }

    public void setResult(int result){
        this.result = result;
    }
}

class IntervalComparator implements Comparator<int[]>{
    public static int getSize(int[] interval){
        return (interval[1] - interval[0] + 1);
    }

    @Override
    public int compare(int[] o1, int[] o2) {
        int o1Size = getSize(o1), o2Size = getSize(o2);
        if (o1Size != o2Size) {
            return (o1Size - o2Size);
        }
        return (o1[1] - o2[1]);
    }
}

class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        // Book keeping and sorting
        int numIntervals = intervals.length;
        int numQueries = queries.length;

        // Sort by start times
        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));

        Query[] sortedQueries = new Query[numQueries];
        for (int i=0;i<numQueries;i++) sortedQueries[i] = new Query(i, queries[i]);

        Arrays.sort(
            sortedQueries,
            (q1, q2) -> (q1.queryTimeStamp - q2.queryTimeStamp)
        );

        // Main algorithm
        Comparator<int[]> comparator = new IntervalComparator();
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
        int idx = 0;

        for (Query query : sortedQueries){
            // 1. Keep taking all those queries which have lower start time than the query time and add to pq
            while (
                (idx < numIntervals) &&
                (query.queryTimeStamp >= intervals[idx][0])
            ) {
                pq.add(intervals[idx]);
                idx++;
            }

            // 2. Keep removing inconsistent intervals and get min size interval from priority queue
            while (!pq.isEmpty() && (pq.peek()[1] < query.queryTimeStamp)){
                pq.remove();
            }

            // Now pq must have the consistend & smallest interval
            int ans = pq.isEmpty() ? -1 : IntervalComparator.getSize(pq.peek());
            query.setResult(ans);
        }

        // reconversion
        int[] results = new int[numQueries];
        for (Query query : sortedQueries){
            results[query.index] = query.result;
        }

        return results;
    }
}