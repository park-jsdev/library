// Math
// Time: O(n*m)
// Space: O(1) as we work in place
/**
    It is important to visualize and work from a brute force to an optimal solution.
    
    In a brute force solution, we start with a copy matrix, read the input, then fill the copy matrix, as we do not want
    dynamic reading.

    A space optimization would be to replace to copy matrix with a row array and a column array to mark which to zero.

    The optimal solution is to perform this in place by putting the arrays inside the input matrix itself. This works because
    we first read the input before we set it to the flag value. If we visualize, we see that the first cell would overlap
    with the row and col array, so we have 1 extra dedicated variable for the column flag for that cell only.

    In the second pass, we fill the input array with the 0s based on the row array (top row), and col array (extra var and first col).
 */

class Solution {
    public void setZeroes(int[][] matrix) {
        // Dimensions
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean firstRow = false; // set 1 extra dedicated variable for the overlapping cell

        // P1: check if 0
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    if (i == 0){
                        firstRow = true;
                    } else {
                        matrix[i][0] = 0;
                    }
                }
            }
        }

        // P2: fill 0s
        for (int i=1;i<rows;i++){
            for (int j=1;j<cols;j++){
                if (matrix[0][j] == 0 || matrix[i][0] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0){
            for (int i=0;i<rows;i++){
                matrix[i][0] = 0;
            }
        }

        if (firstRow){
            for (int j=0;j<cols;j++){
                matrix[0][j] = 0;
            }
        }
        
    }
}