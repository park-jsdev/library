class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // Base Case
        // grid of size 1
        if (m == 1 && n == 1){
            return obstacleGrid[0][0] == 1 ? 0 : 1;
        }

        // There is an obstacle in the goal
        if (obstacleGrid[m-1][n-1] == 1){
            return 0;
        }

        int[][] memo = new int[m][n];

        return helper(obstacleGrid, m, n, 0, 0, memo);   
    }

    private int helper(int[][] grid, int m, int n, int i, int j, int[][] memo){
        // At each step we can go right, down, or get stuck

        // Base Case
        // Out of bounds or obstacle
        if (i >= m || j >= n || grid[i][j] == 1){
            return 0;
        }

        // Goal
        if (i == m-1 && j == n-1){
            return 1;
        }

        // Cache
        if (memo[i][j] != 0){
            return memo[i][j];
        }

        // Recursive step
        // Right and down
        return memo[i][j] = helper(grid, m, n, i+1, j, memo) + helper(grid, m, n, i, j+1, memo);
    }
}