// DFS - 2 DFS from edges
// Time: O(n x m) in the worst case
// Space: O(n x m) in the worst case
/**
    The crux is the strategy of flooding from the ocean for the 1st pass, then finding the islands that are reached by both.
    The key implementation details are:
        1. That we run 2 separate graph DFS's, which their own visited. 
        2. The DFS implementation (note the parameters and base case rules). 
 */

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // Define dimensions
        int rows = heights.length, cols = heights[0].length;
        
        // Store visited coordinates of each ocean
        boolean[][] pac = new boolean[rows][cols];
        boolean[][] atl = new boolean[rows][cols];

        // DFS from the vertical edges
        for (int col=0;col<cols;col++){
            dfs(0, col, rows, cols, pac, heights[0][col], heights); // pacific from top
            dfs(rows-1, col, rows, cols, atl, heights[rows-1][col], heights); // atlantic from bottom
        }

        // DFS from the horizontal edges
        for (int row=0;row<rows;row++){
            dfs(row, 0, rows, cols, pac, heights[row][0], heights); // pacific from left
            dfs(row, cols-1, rows, cols, atl, heights[row][cols-1], heights); // atlantic from edge
        }

        // For the '2nd pass', we check which cells are also contained in the visited arrays of both ocean
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (pac[i][j] && atl[i][j]){
                    res.add(Arrays.asList(i,j));
                }
            }
        }
        return res;
    }

    // DFS on graph implementation
    // Takes as parameters the visited, prevHeight, and heights
    // Heights is the value in the cells of the input space in this case. Water can only flow from high to low.
    private void dfs(int row, int col, int rows, int cols, boolean[][] visited, int prevHeight, int[][] heights){
        // Base case:
        if (row < 0 ||
            row >= rows ||
            col < 0 ||
            col >= cols || 
            visited[row][col] ||
            prevHeight > heights[row][col]
            ) return;
        
        // Recursive step:
        // dfs on each direction
        visited[row][col] = true;
        dfs(row+1, col, rows, cols, visited, heights[row][col], heights);
        dfs(row-1, col, rows, cols, visited, heights[row][col], heights);
        dfs(row, col+1, rows, cols, visited, heights[row][col], heights);
        dfs(row, col-1, rows, cols, visited, heights[row][col], heights);
    }
}