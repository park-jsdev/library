class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        long cost = 0;
        int i = 0;
        int j = costs.length - 1;

        // 2 min heaps
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();

        while (k-- > 0){
            while (pq1.size() < candidates && i <= j){
                pq1.offer(costs[i++]);
            }
            while (pq2.size() < candidates && i <= j){
                pq2.offer(costs[j--]);
            }

            int t1 = pq1.size() > 0 ? pq1.peek() : Integer.MAX_VALUE;
            int t2 = pq2.size() > 0 ? pq2.peek() : Integer.MAX_VALUE;

            if (t1 <= t2){
                cost += t1;
                pq1.poll();
            } else {
                cost += t2;
                pq2.poll();
            }
        }
        return cost;
    }
}