// Heaps
// Time: O(n)
// Space: O(n)
/**
    We use a max heap to store the frequency of each task in descending order. The highest freq task is given the highest priority.
    The queue stores tasks during their cooldown period. The queue pairs are stored as (task freq, time available again).
    The frequency array for 'A' to 'A' is created and used to manage frequencies of tasks.
 */

class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        int[] arr = new int[26];
        for (char c : tasks) arr[c - 'A']++;
        for (int val : arr) if (val > 0) pq.add(val);
        int time = 0;

        // The loop runs until both pq and q are empty
        while ((!pq.isEmpty() || !q.isEmpty())){
            time++; // each iteration represents a unit of time
            if (!pq.isEmpty()){
                int val = pq.poll();
                val--;
                if (val > 0) q.add(new Pair(val, time + n));
            }
            // If the front task's cooldown has expired, add it back to pq
            if (!q.isEmpty() && q.peek().getValue() == time) pq.add(q.poll().getKey());
        }
        return time;
    }
}