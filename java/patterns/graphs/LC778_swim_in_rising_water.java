// Greedy - Modified Dijkstra's
// Time: O((n^2)logn)
// Space:
/**
    Dijkstra's is a BFS which uses a PQ instead of a regular queue, but in this problem, we need to modifiy it to minimize
    not the cost, but the max height (time). AKA we take the height as the cost.
    The change between this problem and classic Dijkstra's is that it polls from the PQ the node with the min height, 
    rather than min cost, so we need to define the nodes that way.

    The difficulty in this problem is understanding the problem statement and defining the data structure and conditions.
    Time t does not need to be tracked, as it is implicitly handled by keeping total t minimized by Dijkstra's.

    I.e. it is Dijkstra's but by finding the valid path with the smallest single maximum of the cell values.
 */ 

class Solution {

    private int[][] dirs = { {-1,0}, {1,0}, {0,-1}, {0,1}}; // Direction vectors

    public int swimInWater(int[][] grid) {
        int len = grid.length;

        if (len == 1){
            return 0;
        }

        var seen = new boolean[len][len];
        seen[0][0] = true;

        var minHeap = new PriorityQueue<Integer[]>((a,b) -> a[0] - b[0]);
        minHeap.add(new Integer[]{grid[0][0],0,0});

        int result = 0;

        while (!minHeap.isEmpty()){
            var curr = minHeap.poll();

            result = Math.max(result, curr[0]);

            if (curr[1] == len-1 && curr[2] == len-1){
                break;
            }

            // Expand in 4 directions
            for (int i=0;i<4;i++){
                int x = curr[1] + dirs[i][0];
                int y = curr[2] + dirs[i][1];

                // check boundaries
                if (x < 0 || x >= len || y < 0 || y >= len || seen[x][y]){
                    continue;
                }

                minHeap.add(new Integer[] {grid[x][y], x, y});
                seen[x][y] = true;
            }
        }

        return result;
    }
}