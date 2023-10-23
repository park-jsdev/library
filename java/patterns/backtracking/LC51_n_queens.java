// Backtracking
// Time: O(n! * n), as each decision as a worst case of n-1 choices at each step after the first. 
// Space: O(n^2)
/**
    This can be solved with a general backtracking template. The only troublesome detail is the logic for
    checking if the cell is safe.

    The crux is to understand that the left (negative) diagonal is given by row - col, which is constant. To iterate the
    negative diagonal, you use [row - i][col - i], while the right (positive diagonal) is given by [row - i][col + i].
    We need to perform backtracking (explore all possibilities) as there may be multiple distinct solutions.
    
    This key intuition is easy to derive if you visualize the grid, as the pattern is apparent if you do a dry run.

    The key implementation details are:
    - The boolean flag grid and building the string with it, it is best placed in its own function.
    - Backtracking: Recursion with base case, choose, explore, unchoose.
    - isSafe(board, row, col) function which checks the vertical row, negative diagonal, and positive diagonal.
    start from i=1 for checking diagonals to not check the cell itself.

 */

class Solution {
    public List<List<String>> solveNQueens(int n) {
        // Model
        List<List<String>> res = new ArrayList<List<String>>();
        boolean[][] board = new boolean[n][n];
        backtrack(board, 0, res);
        return res;
    }

    public void backtrack(boolean[][] board, int row, List<List<String>> res){
        // base case
        if (row == board.length){
            ArrayList<String> curr = new ArrayList<String>();
            createAnswer(board, curr);
            res.add(curr);
            return;
        }
        for (int col=0;col<board.length;col++){ // the first level decision is the columns
            if (isSafe(board, row, col)){
                board[row][col] = true; // choose
                backtrack(board, row+1,res); // explore
                board[row][col] = false; // unchoose
            }
        }
    }

    // Checks the boolean grid and adds "Q" for queen if it is true, or "." if not
    public void createAnswer(boolean[][] board, ArrayList<String> res){
        for (int i=0;i<board.length;i++){
            StringBuilder str = new StringBuilder();
            for (int j=0;j<board[0].length;j++){
                if (board[i][j]){
                    str.append("Q");
                } else str.append(".");
            }
            res.add(str.toString());
        }
    }

    public boolean isSafe(boolean[][] board, int row, int col){
        for (int i=0;i<row;i++){
            if (board[i][col]){
                return false;
            }
        }
        int maxLeft = Math.min(row, col);
        for (int i=1;i<=maxLeft;i++){
            if (board[row-i][col-i]){ // negative diagonal is given by row - col (iterate by i)
                return false;
            }
        }
        int maxRight = Math.min(row, board.length - 1 - col);
        for (int i=1;i<=maxRight;i++){
            if (board[row-i][col+i]) // positive diagonal is given by row + col (iterate by i)
                return false;
        }
        return true;
    }
}