class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        // Build adj list, only unidirectional needed
        int m = maze.length;
        int n = maze[0].length;
        HashMap<String, ArrayList<String>> g = new HashMap<>(); // graph (node, edges) where both are "i,j"

        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (maze[i][j] == '.'){ // valid cell
                    String node = "" + i + "," + j; // node Key: "i,j"
                    ArrayList<String> edges = new ArrayList<>(); // list of edges

                    // check 4 directions

                    // Up
                    if (i > 0 && maze[i - 1][j] == '.') {
                        edges.add((i - 1) + "," + j);
                    }
                    // Down
                    if (i < m - 1 && maze[i + 1][j] == '.') {
                        edges.add((i + 1) + "," + j);
                    }
                    // Left
                    if (j > 0 && maze[i][j - 1] == '.') {
                        edges.add(i + "," + (j - 1));
                    }
                    // Right
                    if (j < n - 1 && maze[i][j + 1] == '.') {
                        edges.add(i + "," + (j + 1));
                    }

                    // Put the node with its edges into the graph
                    g.put(node, edges);
                }
            }
        }

        // Graph BFS
        Queue<String> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        String start = "" + entrance[0] + "," + entrance[1];
        int level = 0;
        q.offer(start);
        visited.add(start);
        
        while (!q.isEmpty()){
            int s = q.size();
            for (int i=0;i<s;i++){
                String node = q.poll();

                // Process node
                String[] parts = node.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);

                if ((x == 0 || x == m-1 || y == 0 || y == n-1) && (x != entrance[0] || y != entrance[1])) {
                    return level;
                }

                // Add neighbors
                ArrayList<String> neighbors = g.getOrDefault(node, new ArrayList<>()); // create copy
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        q.offer(neighbor);
                        visited.add(neighbor); // Mark as visited when enqueued
                    }
                }
            }
            // after 1 iteration increase level
            level++;

        }

        // return path length
        return -1;
    }
}