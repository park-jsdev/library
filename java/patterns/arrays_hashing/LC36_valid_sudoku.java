// Arrays
// Time: O(n^2) as we check each col for each row
// Space: O(n^2) as we maintain 3 sets for each rule
/**
    The crux is to use a hash set for each rule, and to understand 2d grid representations/manipulations.
    The sub-block can be represented and accessed with coordinates (i,j), where the sub-block is given by the
    coordinates (i/3, j/3) in the dimensions of a 3x3 matrix m x n, where m = 0 to 2, and n = 0 to 2.

    The key implementation details are the hash set for each rule, grid representation and traversal, and the
    sub-block check.

    This is a good question to practice grid representation, which is a foundation for graph and dynamic programming
    questions later.
 */

 class Solution {
    public boolean isValidSudoku(char[][] board) {

        // Set of characters that we have already come across (excluding '.' which denotes an empty space)
        Set<Character> row_set = null;
        Set<Character> col_set = null;

        for (int i=0;i<9;i++){
            // Reinitialize the sets per row
            row_set = new HashSet<>();
            col_set = new HashSet<>();
            for (int j=0;j<9;j++){
                char r = board[i][j];
                char c = board[j][i];
                if (r != '.'){
                    if (row_set.contains(r)){
                        return false;
                    } else {
                        row_set.add(r);
                    }
                }
                if (c != '.'){
                    if (col_set.contains(c)){
                        return false;
                    } else {
                        col_set.add(c);
                    }
                }
            }
        }

        // Sub-block check
        for (int i=0;i<9;i+=3){ // increment by 3 indices (a block size)
            for (int j=0;j<9;j+=3){
                // check_block will return true if valid
                if (!check_block(i, j, board)){ // therefore we call on r = 1, 4, 7 and c = 1, 4, 7 and iterate within the fn
                    return false;
                }
            }
        }
        // Passed all tests, therefore the board is valid
        return true;
    }

    public boolean check_block(int idx_i, int idx_j, char[][] board){
        Set<Character> block_set = new HashSet<>();
        // block size is 3x3
        int rows = idx_i + 3; // we iterate i<rows, so we add 3
        int cols = idx_j + 3;
        for (int i=idx_i;i<rows;i++){
            for (int j=idx_j;j<cols;j++){
                if (board[i][j] == '.'){
                    continue;
                }
                if (block_set.contains(board[i][j])){
                    return false;
                }
                block_set.add(board[i][j]);
            }
        }
        return true;
    }
}