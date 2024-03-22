class Solution {
    private static final int[][] DIRECTIONS = {{1,0},{-1,0},{0,-1},{0,1}};

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        // Store {i, j, # obstacles eliminated}
        Deque<int[]> q = new LinkedList<>();
        q.offerLast(new int[]{0,0,0});

        int[][] visited = new int[m][n];
        for (int[] i : visited) Arrays.fill(i, Integer.MAX_VALUE);
        visited[0][0] = 0;

        int dist = 0;

        while (!q.isEmpty()){
            int size = q.size();

            while (size-- > 0){
                int[] curr = q.poll();
                if (curr[0] == m-1 && curr[1] == n-1) return dist;

                for (int[] dir : DIRECTIONS){
                    int newX = curr[0] + dir[0];
                    int newY = curr[1] + dir[1];

                    if (newX < 0 || newY < 0 || newX >= m || newY >= n) continue;

                    int newK = curr[2] + grid[newX][newY];
                    if (newK > k) continue;

                    if (visited[newX][newY] <= newK) continue;

                    visited[newX][newY] = newK;
                    q.offerLast(new int[]{newX, newY, newK});
                }
            }
            dist++;
        }
        return -1;
    }
}