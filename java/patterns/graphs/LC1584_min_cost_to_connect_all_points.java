// Graphs - Prim's
// Time: O(n^2 log(n)), n^2 comes from the distance between currNode and every other nodes to pick the shortest distance.
// Log(n) from the Priority Queue
// Space Complexity: O(n^2)
/**
    When we see an MST problem, we need to remember Prim's and Kruskal's algorithms.
    In this problem, we need to first define the edges with an adjacency list.
    Prim's algorithm requires the visited hash set, the frontier min heap, and the cost.
 */

class Solution {
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]); // edge weight, index of next node
        pq.offer(new int[]{0,0});
        int len = points.length;
        Set<Integer> visited = new HashSet<>();
        int cost = 0;

        // when visited.size() == points.len means that all nodes have been connected.
        while (visited.size() < len){
            int[] arr = pq.poll();

            int weight = arr[0];
            int currNode = arr[1];

            if (visited.contains(currNode)) continue;

            visited.add(currNode);
            cost += weight;

            for (int nextNode = 0; nextNode < len; nextNode++){
                if (!visited.contains(nextNode)){
                    int nextWeight = 
                        Math.abs(points[nextNode][0] - points[currNode][0]) +
                        Math.abs(points[nextNode][1] - points[currNode][1]);
                    pq.offer(new int[] {nextWeight, nextNode});
                }
            }
        }
        return cost;
    }
}