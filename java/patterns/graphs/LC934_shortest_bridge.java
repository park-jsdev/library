class Solution {
    public int shortestBridge(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        boolean flag = false;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j] == 1){
                    // find 1 island cell, traverse the whole island to add to queue
                    dfs(grid, i, j, q); // connected island cells
                    flag = true;
                    break;
                }
            }
            if (flag == true) break;
        }
        return bfs(grid, q);
    }

    // preorder DFS is used to add the sub-land of island into the queue
    private void dfs(int[][] grid, int i, int j, Queue<int[]> q){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == -1 || grid[i][j] == 0){
            return;
        }
        grid[i][j] = -1; // mark as visited
        q.offer(new int[]{i, j}); // add sub land of island into the queue

        dfs(grid, i-1, j, q); // top
        dfs(grid, i, j+1, q); // r
        dfs(grid, i+1, j, q); // bot
        dfs(grid, i, j-1, q); // l

        return;
    }

    private int bfs(int[][] grid, Queue<int[]> q){
        int[][] trav = {{-1,0},{0,1},{1,0},{0,-1}};
        int level = 0; // curr level, soruce starting island level

        while (!q.isEmpty()){
            int size = q.size();
            while (size-- > 0){
                int[] temp = q.poll();
                for (int[] dir : trav){
                    int i_ = temp[0] + dir[0];
                    int j_ = temp[1] + dir[1];
                    if (i_ >= grid.length || i_ < 0 || j_ >= grid[0].length || j_ < 0 || grid[i_][j_] == -1){
                        continue;
                    }
                    if (grid[i_][j_] == 1){
                        return level;
                    } else {
                        grid[i_][j_] = -1;
                        q.offer(new int[]{i_, j_});
                    }
                }
            }
            level += 1;
        }
        return -1;
    }
}