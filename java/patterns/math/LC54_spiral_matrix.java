// Math
// Time: O(mn)
// Space: O(1)
/**
    The crux move is to define the dimension boundaries, and shift them by 1 after each full outer iteration.
    Be cautious and handle the off by 1 errors.
 */


class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int rb = 0; // row start
        int re = matrix.length - 1; // row end
        int cb = 0; // col start
        int ce = matrix[0].length - 1; // col end

        while (rb <= re && cb <= ce){
            //  Add top row
            for (int j=cb;j<=ce;j++){
                list.add(matrix[rb][j]);
            }
            rb++;

            //  Add right col
            for (int i=rb;i<=re;i++){
                list.add(matrix[i][ce]);
            }
            ce--;

            //  Add bottom row
            if (rb <= re){
                for (int j=ce;j>=cb;j--){
                    list.add(matrix[re][j]);
                }
            }
            re--;

            // Add left col
            if (cb <= ce){
                for (int i=re;i>=rb;i--){
                    list.add(matrix[i][cb]);
                }
            }
            cb++;
        }
        return list;

    }
}