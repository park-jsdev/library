// Math
// Time: O(n^2)
// Space: O(1) - in place, temp variable
/**
    Just mind the implementation. Make sure to visualize the matrix as they can be confusing to visualize.
    Review the matrix flipping algorithm.
 */

 class Solution {
    public void rotate(int[][] matrix) {
        int l = 0;
        int r = matrix.length - 1;

        while (l < r){
            for (int i=0;i<r-l;i++){
                int top = l;
                int bottom = r;

                //save the toleft
                int topLeft = matrix[top][l+i];

                // move bottom left into top left
                matrix[top][l+i] = matrix[bottom-i][l];

                // move bnototm right into bottom left
                matrix[bottom-i][l] = matrix[bottom][r-i];

                // move top right into bottom right
                matrix[bottom][r-i] = matrix[top+i][r];

                // move top left into top right
                matrix[top+i][r] = topLeft;
            }

            r--;
            l++;
        }
        
    }
}