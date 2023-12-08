class Solution {
    public int snakesAndLadders(int[][] board) {
        // BFS Setup
        int n = board.length;
        int moves = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        q.add(1);
        visited[n-1][0] = true;

        // BFS
        while (!q.isEmpty()){
            int size = q.size();
            for (int i=0;i<size;i++){
                int currBoardVal = q.poll();
                if (currBoardVal == n*n) return moves; // end condition
                for (int diceVal = 1;diceVal<=6;diceVal++){
                    if (currBoardVal + diceVal > n*n) break; // boundary
                    int[] pos = findCoordinates(currBoardVal + diceVal, n); // get coordinates of each move
                    int row = pos[0];
                    int col = pos[1];
                    if (visited[row][col] == false){
                        visited[row][col] = true;
                        if (board[row][col] == -1){ // All cell values are -1 except snakes and ladders
                            q.add(currBoardVal + diceVal); // regular cell
                        } else{
                            q.add(board[row][col]); // snake or ladder, check the cell value
                        }
                    }
                }
            }
            moves++;
        }
        return -1;
    }

    public int[] findCoordinates(int curr, int n){
        int r = n - (curr - 1) / n - 1;
        int c = (curr - 1) % n;
        if (r % 2 == n % 2){
            return new int[]{r, n-1-c};
        } else {
            return new int[]{r, c};
        }
    }
}