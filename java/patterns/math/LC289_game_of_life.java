// Matrix
// Time: O(mn)
// Space: O(mn)
/**
    The key implementation details are to work with a copy board, and then to copy all the result values from the copy back
    to the board individually. Setting board = res only changes the reference, not the original board.
 */

 class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] res = new int[m][n]; // populate the copy board

        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                // Eight neighbors - count the live cells
                int live_cells = 0; // reset live cells for each cell
                if (i-1 >=0 && j-1 >=0 && board[i-1][j-1] == 1) live_cells++; // Left upper diagonal
                if (i-1 >=0 && board[i-1][j] == 1) live_cells++; // Upper
                if (i-1 >=0 && j+1 < n && board[i-1][j+1] == 1) live_cells++; // Right upper diagonal
                if (j-1 >=0 && board[i][j-1] == 1) live_cells++; // Left 
                if (j+1 < n && board[i][j+1] == 1) live_cells++; // Right
                if (i+1 < m && j-1 >=0 && board[i+1][j-1] == 1) live_cells++; // Left lower diagonal
                if (i+1 < m && board[i+1][j] == 1) live_cells++; // Lower
                if (i+1 < m && j+1 < n && board[i+1][j+1] == 1) live_cells++; // Right lower diagonal 

                // With live cells count in the window:
                int curr_cell = board[i][j];

                if (curr_cell == 1){ // live cell
                    // Rule 1
                    if (live_cells < 2) res[i][j] = 0;

                    // Rule 2
                    if (live_cells == 2 || live_cells == 3) res[i][j] = 1;

                    // Rule 3
                    if (live_cells > 3) res[i][j] = 0;
                }
                // Rule 4
                else if (curr_cell == 0 && live_cells == 3) res[i][j] = 1;
                
                // Default case
                else res[i][j] = 0;
            }
        }
        // Copy the result back to the board
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                board[i][j] = res[i][j];
            }
        }
    }
}