// Topological Sort
// Time: O(V + E)
// Space: O(V + E)
/**
    Classic Topological Sort. We first create the adjacency list, and perform BFS (or DFS).
    We need to detect cycles, and immediately return an empty list if we encounter one.
    This implementation is known as Kahn's algorithm, and the difference between standard BFS is that we track indegree.
    The indegree of a node is the number of incoming edges (number of prereqs in this context).
    We only add a node to the queue if the indegree is zero.

 */

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        // Create the adj list
        for (int i=0;i<prerequisites.length;i++){
            int post = prerequisites[i][0];
            int pre = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(pre, new ArrayList<Integer>());
            lst.add(post);
            adjList.put(pre, lst);

            indegree[post] += 1;
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for (int i=0;i<numCourses;i++){
            if (indegree[i] == 0){
                q.add(i);
            }
        }

        int i = 0;
        while (!q.isEmpty()){
            int node = q.remove();
            topologicalOrder[i++] = node;

            if (adjList.containsKey(node)){
                for (Integer neighbor : adjList.get(node)){
                    indegree[neighbor]--;

                    if (indegree[neighbor] == 0){
                        q.add(neighbor);
                    }
                }
            }
        }
        if (i == numCourses){
            return topologicalOrder;
        }

        return new int[0];
    }
}