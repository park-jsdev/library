// BFS Solution
// Time: O(n*m) + O(n*m)
// Space: O (n*m)
/**
    The main concepts are BFS and visited array.
    The problem can be seen as a BFS with a condition on a graph (requires visited).
    The Pair class and the directional checks are the 'tricks'.
    8 directional check is possible.
    DFS solution is easier.
 */

// Pair exists in Java 8 javafx.util.Pair, but if not provided, we can create our own implementation.
class Pair {
    int first;
    int second;
    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        boolean visited[][] = new boolean[m][n];

        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                // if not visited and is an island
                if (visited[i][j] == false && grid[i][j] == '1'){
                    count++; // the count of islands is generated from the top level loop
                    bfs(grid, i, j, m, n, visited); // bfs will mark connected nodes as visited
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int r, int c, int m, int n, boolean[][] visited){
        // BFS
        visited[r][c] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(r,c)); // note Pair class
        
        while (!q.isEmpty()){
            r = q.peek().first;
            c = q.peek().second;
            q.poll();

            // Traverse the neighbors - move the position to all delta rows and columns
            for (int delRow=-1;delRow<=1;delRow++){ // we only check 1 level at a time
                for (int delCol=-1;delCol<=1;delCol++){
                    if (
                        // Check 4 directions: this will filter out the diagonal movements
                        (delRow == -1 && delCol == 0) || // Left
                        (delRow == 0 && delCol == 1) || // Up
                        (delRow == 1 & delCol == 0) || // Right
                        (delRow == 0 && delCol == -1) // Down
                    ){
                        // Initialize new rows and columns
                        int newRow = r + delRow;
                        int newCol = c + delCol;

                        // Check if cell is in the boundaries
                        if (
                            (newRow >= 0 && newRow < m) &&
                            (newCol >= 0 && newCol < n) &&

                            // visited check
                            (visited[newRow][newCol] == false) &&

                            // 1 check
                            (grid[newRow][newCol] == '1')
                            ){
                                visited[newRow][newCol] = true; 
                                q.add(new Pair(newRow,newCol));
                            }
                    }
                }
            }
        }
    }
}