// Graph BFS
// Time: O(m*n)
// Space: O(m*n)
/**
    The crux is to understand that DFS will not work as it has multiple sources. Therefore we use a "multi-source" BFS.
    We first check the number of rotten and fresh oranges during intialization. The queue is initialized with all the
    rotten oranges, and the conditions of our return statement will be based on the distance between fresh and orange
    (fresh == 0 ? count : -1)

    The key implementation detail is BFS on a 2d graph. The direction vector is a detail but not necessary.
 */

 class Solution {
    public int orangesRotting(int[][] grid) {
        // Model
        int m = grid.length, n = grid[0].length; // dimensions
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;

        // Initialize queue
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j] == 2) { // rotten are defined as 2
                    q.offer(new int[]{i,j});
                } else if (grid[i][j] == 1){ // fresh are 1
                    fresh++;
                }
            }
        }

        int count = 0;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}}; // direction vector
        while (!q.isEmpty() && fresh != 0){
            count++;
            int sz = q.size();
            for (int i=0;i<sz;i++){
                int[] rotten = q.poll();
                int r = rotten[0], c = rotten[1];
                for (int[] dir : dirs){
                    int x = r + dir[0], y = c + dir[1];
                    if (0 <= x && x < m && 0 <= y && y < n && grid[x][y] == 1){
                        grid[x][y] = 2;
                        q.offer(new int[]{x,y});
                        fresh--;
                    }
                }
            }
        }
        return fresh == 0 ? count : -1; // return -1 if impossible
    }
}