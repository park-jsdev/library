// DFS from edges
// Time: O(m x n). A key caveat is that although we call dfs from within a for-loop, it remains O(m x n) time as long as we handle
// visited cells.
// Space: O(1), as we perform changes in-place.
/**
    The crux move is the introduction of the third, temporary variable 'T' to mask/filter cells before flipping in place.
    The key implementation detail is the DFS on a matrix. Make sure to consider edge indices carefully in graph problems.
 */

 class Solution {
    public void solve(char[][] board) {
        // Dimensions
        int m = board.length-1, n = board[0].length-1;

        // DFS from edges
        for (int i=0;i<=m;i++){
            for (int j=0;j<=n;j++){
                if (i == 0 && board[i][j] == 'O') dfs(board, m, n, i, j); // top row
                if (i == m && board[i][j] == 'O') dfs(board, m, n, i, j); // last row
                if (j == 0 && board[i][j] == 'O') dfs(board, m, n, i, j); // left column
                if (j == n && board[i][j] == 'O') dfs(board, m, n, i, j); // right column
            }
        }

        // Capture the rest
        for (int i=0;i<=m;i++){
            for (int j=0;j<=n;j++){
                if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }

        // Flip the Ts back
        for (int i=0;i<=m;i++){
            for (int j=0;j<=n;j++){
                if (board[i][j] == 'T') board[i][j] = 'O';
            }
        }
    }

    private void dfs(char[][] board, int m, int n, int r, int c){
        // Base Case
        if (
            r > m ||
            r < 0 ||
            c > n ||
            c < 0 ||
            board[r][c] != 'O' // We can track visited in place
        ) return;

        board[r][c] = 'T';

        // Recursive Step
        dfs(board, m, n, r+1, c); // right
        dfs(board, m, n, r-1, c); // left
        dfs(board, m, n, r, c+1); // up
        dfs(board, m, n, r, c-1); // down
    }
}