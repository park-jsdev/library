class Solution {
    public int equalPairs(int[][] grid) {
        // 2d for loop
        // store each row and column
        // if overlap iterate count
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1) return 1;
        int count = 0;
        HashMap<String, Integer> rows = new HashMap<>();
        HashMap<String, Integer> cols = new HashMap<>();

        // Get rows and columns
        for (int i=0;i<m;i++){
            String row = "";
            String col = "";
 
            for (int j=0;j<n;j++){

                // Row and Col logic
                if (j<n-1){
                    row += grid[i][j] + ",";
                    col += grid[j][i] + ",";
                } else {
                    row += grid[i][j];
                    col += grid[j][i];
                }
            }

            // Map logic
            if (!rows.containsKey(row)){
                rows.put(row, 1);
            } else {
                rows.put(row, rows.get(row) + 1);
            }
            if (!cols.containsKey(col)){
                cols.put(col, 1);
            } else {
                cols.put(col, cols.get(col) + 1);
            }
        }

        // Counter Logic
        for (String key : rows.keySet()){
            if (cols.containsKey(key)){
                count += rows.get(key) * cols.get(key);
            }
        }

        return count;
    }
}