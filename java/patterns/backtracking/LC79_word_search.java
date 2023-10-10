// Backtracking - DFS on 2D Grid
// Time: O(m * n * 4^n)
// Space: O(1)
/**
    Typical backtracking problem on a 2d grid, similar to Number of Islands. Use DFS backtracking. 
    We "flood" the visited cells like Number of Islands with an unused ASCII character, or a bitmask.
    We can also maintain a global visited[][] array, but global variables are discouraged.

    The crux move is the "flooding" concept where we mark and unmark visited cells to perform backtracking.

    The key implementation details are the typical DFS backtracking on a 2D grid.
 */

class Solution {
    public boolean exist(char[][] board, String word) {
        // Get Dimensions
        int m = board.length;
        int n = board[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (check(board, word, i, j, m, n, 0)){
                    return true; // immediately return true if word is found
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, String word, int i, int j, int m, int n, int cur){
        if (cur >= word.length()) return true; // If we get past the word length, we are done
        if ( // grid boundaries
            i < 0 ||
            j < 0 ||
            i >= m ||
            j >= n ||
            board[i][j] != word.charAt(cur) // char mismatch
        ) return false;
        boolean exist = false;
        if (board[i][j] == word.charAt(cur)){
            // Backtracking
            // We visit the cell
            board[i][j] += 100; // We choose 100 for the bitmask because + 100 will result in an ascii char not in the alphabet
            exist = check(board, word, i+1, j, m, n, cur+1) ||
                    check(board, word, i, j+1, m, n, cur+1) ||
                    check(board, word, i-1, j, m, n, cur+1) ||
                    check(board, word, i, j-1, m, n, cur+1); // check the 4 directions, we must always increment cur
            // we use OR because we only need 1 valid path
            board[i][j] -= 100; // set the cell back to its original value after the recursive calls, "backtrack"
            // we need to set the cell to unvisited for the next function call in the main loop
        }
        return exist;
    }
}