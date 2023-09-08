// DFS
// Time: O(n * m)
// Space: O(n)
/**
    To track visited, we can either manipulate the grid itself, but if we cannot assume that we can modify it,
    we can use a HashSet.
 */

class Solution {
    int max_area = 0;
    HashSet<String> visited = new HashSet<>(); // Note HashSet with String

    public int maxAreaOfIsland(int[][] grid) {
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                max_area = Math.max(max_area, maxAreaOfIsland(grid, i, j)); // Recurrence Relation
            }
        }
        return max_area;
    }

    public int maxAreaOfIsland(int[][] grid, int r, int c){
        // Base Case:

        // The cell is 0 or out of bounds
        if (r < 0 || c < 0 ||
            r == grid.length ||
            c == grid[0].length ||
            visited.contains(r + "," + c) ||
            grid[r][c] == 0){
                return 0; // If so, return 0
            }
        visited.add(r + "," + c); // Add the coordinates as one string

        // Recursive Step: 
        return (1 + maxAreaOfIsland(grid, r+1, c) +
                    maxAreaOfIsland(grid, r-1, c) +
                    maxAreaOfIsland(grid, r, c+1) +
                    maxAreaOfIsland(grid, r, c-1));
    }
}