// 2x Binary Search
// Time: O(log m) + O(log n) = O(log(mn))
// Space: O(1). We use a constant amount of variables.
/**
    2 good solutions are: Use 1 binary search, treating as 1 sorted list using 'rolling indices', 
    and using 2 binary searches (Outer and inner). 
    The latter is cleaner and simpler to remember under pressure.

    The crux is to be able to implement the custom binary search on the matrix structure (outer) and the row (inner).
    The key implementation details are getting the dimension indices correct to prevent out of bounds error.
    The 1st binary search should manipulate the right pointer int this case, while the 2nd can use both.
    Carefully consider the indices.
 */

 class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Basic Check
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        
        // Setup: Dimensions
        int r1 = 0;
        int r2 = matrix.length-1;
        int c1 = 0;
        int c2 = matrix[0].length-1;

        // Binary Search 1 (Outer, on the matrix)
        while (r1 < r2){
            int mid = r1 + (r2-r1)/2;
            if (matrix[mid][c2] == target){ // We look for the last element of the mid row
                return true;
            } 
            else if (target < matrix[mid][c2]){
                r2 = mid;
            }
            else {
                r1 = mid + 1;
            }
        } // We have found the row r2 that the target is contained in

        // Binary Search 2 (Inner, on the matrix row)
        while (c1 <= c2){
            int mid = c1 + (c2-c1)/2;
            if (matrix[r2][mid] == target){
                return true;
            }
            else if (target < matrix[r1][mid]){
                c2 = mid - 1;
            }
            else {
                c1 = mid + 1;
            }
        }
        return false;
    }
}