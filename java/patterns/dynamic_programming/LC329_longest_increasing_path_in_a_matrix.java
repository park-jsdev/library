// Dynamic Programming - DFS
// Time: O(nm)
// Space: O(nm)
/**
    The crux of the solution is the state transition/subproblem calculation where we take 1 + the maximum of the DFS
    of all 4 directions, and place it in the position in the dp matrix. We encode into our boundary conditions check
    the condition that value is greater than its parent, to maintain an increasing path.

    Key implementation details are: 
    - the initialization of the base state of -1, flagging the cell as unvisited.
    - DFS on a 2D matrix
    - 2nd pass to find the max value
 */

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        // Initializations
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n]; // 2D DP array
        for (int d[] : dp) Arrays.fill(d, -1); // Note filling the dp array, -1 is the default value and flags not checked
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (dp[i][j] == -1) dfs(matrix, dp, m, n, i, j, -1); // if not checked
            }
        }
        
        // After the DFS, we do a 2nd pass to find the max value in the dp grid
        int max = Integer.MIN_VALUE;
        for (int[] d : dp){
            for (int i : d) max = Math.max(i, max);
        }
        return max;
    }

    public int dfs(
        int[][] matrix,
        int[][] dp,
        int m,
        int n,
        int i,
        int j,
        int parent
    ){
        if (i >= m || j >= n || i < 0 || j < 0 || matrix[i][j] <= parent) return 0; // Boundary conditions
    
    parent = matrix[i][j];
    if (dp[i][j] != -1) return dp[i][j];
    int left = dfs(matrix, dp, m, n, i, j-1, parent);
    int right = dfs(matrix, dp, m, n, i, j+1, parent);
    int bottom = dfs(matrix, dp, m, n, i+1, j, parent);
    int top = dfs(matrix, dp, m, n, i-1, j, parent);
    dp[i][j] = 1 + Math.max(Math.max(left, right), Math.max(top, bottom));
    return dp[i][j];
    }
}