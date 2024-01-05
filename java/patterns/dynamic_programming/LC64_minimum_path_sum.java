class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Traverse the first row and first column and calculate the sum 
        // of each cell by adding the value of the previous cell in the same row or column
        for (int i=1;i<m;i++){
            grid[i][0] += grid[i-1][0];
        }

        for (int j=1;j<n;j++){
            grid[0][j] += grid[0][j-1];
        }

        // Traverse the remaining cells and calculate the minimum sum to reach each cell
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }
}