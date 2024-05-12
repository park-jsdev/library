// Time: O(r*c) = O(n)
// Space: O(r*c) = O(n)

class Solution {
    private int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public int numIslands(char[][] grid) {
        int res = 0;
        int r = grid.length;
        int c = grid[0].length;

        for (int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                if (grid[i][j] == '1'){
                    search(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void search(char[][] grid, int r, int c){
        grid[r][c] = '0';
        for (int[] dir : dirs){
            int newR = r + dir[0], newC = c + dir[1];
            if (isSafe(newR, newC, grid)){
                search(grid, newR, newC);
            }
        }
    }

    private boolean isSafe(int newR, int newC, char[][] grid){
        return !(newR == grid.length || newC == grid[0].length || newR < 0 || newC < 0 || grid[newR][newC] == '0');
    }
}