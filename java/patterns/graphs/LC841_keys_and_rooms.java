// Graph DFS
// Time: O(k), worst case is O(n^2) but amortized to O(n(n-1)/2). Each room is visited once. 
// Space: O(n)
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n]; // initialized to false
        HashSet<Integer> set = new HashSet(); // set containing keys
        for (int i=0;i<rooms.get(0).size();i++){ // keys in first room
            if (rooms.get(0).get(i) != null) set.add(rooms.get(0).get(i));
        }
        dfs(rooms, 0, n, set, visited);

        // check if visited all
        for (int i=0;i<n;i++){
            if (visited[i] == false) return false;
        }
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int room, int n, HashSet<Integer> set, boolean[] visited){
        // Base case
        if (visited[room]){
            return;
        }

        // Visit and get keys
        visited[room] = true;
        set.remove(room);

        // Recursive Step
        for (int i=0;i<rooms.get(room).size();i++){ 
            if (rooms.get(room).get(i) != null) set.add(rooms.get(room).get(i));
            dfs(rooms, rooms.get(room).get(i), n, set, visited);
        }
    }
}