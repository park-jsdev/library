// Graphs - Bellman Ford
// Time: O(nt)
// Space: O(n)
/**
    Bellman-Ford algorithm starts by setting the shortest distance to all vertices to infinity (MAX_VALUE), except the source (0),
    Then we perform "Relaxation", which updates the shortest path to a vertex if a shorter path is found.
    We repeat relaxation for V-1 iterations,
    At the end, we make a final pass over all the edges, and if any distance is updated during this pass, it indicates
    the presence of a negative weight cycle in the graph. Shortest paths are undefined in this scenario.
 */

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] paths = new int[n];
        Arrays.fill(paths, Integer.MAX_VALUE);

        paths[k-1] = 0;

        for (int i=0;i<n;i++){
            // Make a copy of paths
            int[] temp = new int[n];
            temp = Arrays.copyOf(paths, paths.length);

            // loop through times
            for (int j=0;j<times.length;j++){
                int src = times[j][0];
                int tgt = times[j][1];
                int time = times[j][2];

                if (
                    temp[src-1] != Integer.MAX_VALUE &&
                    temp[src-1] + time < temp[tgt-1]
                ){
                    temp[tgt-1] = temp[src-1] + time;
                }
            }
            paths = temp;
        }
        int result = Integer.MIN_VALUE;

        // calculate max value
        for (int i=0;i<n;i++){
            if (paths[i] == Integer.MAX_VALUE){
                return -1;
            }
            result = Math.max(result, paths[i]);
        }
        return result;
    }
}