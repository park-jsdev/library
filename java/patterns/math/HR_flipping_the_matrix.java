// Matrix
// Time: O(n^2)
// Space: O(1), as we only store a constant variable
/**
 * The crux is to recognize that each cell has its complement in the x direction and the y direction, and these can only be swapped with each other.
 * Therefore, there is a maximum of 4 possibilities for each cell, and we need to sum the max for each cell.
 * We only need to iterate half of the matrix (the size of the submatrix), and the complement in the x direction will be given by n-i-1 and
 * the complement in the y direction will be given by n-j-1.
 * 
 * The pattern extends for matrices of any length, so we need to remember the triple nested Math.max operation.
 */

class Result {

    public static int flippingMatrix(List<List<Integer>> matrix) {
    // Write your code here
    int n = matrix.size();
    int res = 0;
    for (int i=0;i<n/2;i++){
        for (int j=0;j<n/2;j++){
            res += Math.max(
                Math.max(
                matrix.get(i).get(j),
                matrix.get(n-i-1).get(j)),
                Math.max(matrix.get(i).get(n-j-1),
                matrix.get(n-i-1).get(n-j-1))
            );
        }
    }
    return res;
    }

}