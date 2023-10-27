// Graphs - DFS
// Time: O(m*n)
// Space: O(m*n)
/**
    We maintain the adjacency list as an array list of array lists, where we take the index value as the node num, and
    the prerequistes as its value.

    The crux is that we use isCyclic as the DFS function. The base case is that it is visited. For the
    recursive step, we use 3 states:
    0: Not visited
    2: Currently being visited
    1: Completely visited - all of its descendants and the node (prereqs and their prereqs) are visited.

    The third state, completely visited is an optimization, and a recursion memoization technique. However, with
    2 states, you would need a backtracking solution.
 */

 class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Data structure
        List<List<Integer>> adj = new ArrayList<>(); // adjacency list represented by arraylist<arraylist>
        for (int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }

        for (int i=0;i<prerequisites.length;i++){
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        int[] visited = new int[numCourses]; // visited array
        for (int i=0;i<numCourses;i++){
            if (visited[i] == 0){
                if (isCyclic(adj, visited, i)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCyclic(List<List<Integer>> adj, int[] visited, int curr){
        // Base Case
        if (visited[curr] == 2){
            return true;
        }

        // Recursive step
        visited[curr] = 2; // mark visited
        for (int i=0;i<adj.get(curr).size();i++){ // iterate the adj map which we previously created
            if (visited[adj.get(curr).get(i)] != 1){ 
                if (isCyclic(adj, visited, adj.get(curr).get(i))){ // we recursively (DFS) call the function for each adj node
                // which is not 1, therefore 1 or 2 would be detected as a cycle and return true
                    return true;
                }
            }
        }
        visited[curr] = 1; // mark the node as 1 after the recursive step
        return false;
    }
}