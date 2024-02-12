class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // 2 Sets for B and R
        Set<Integer>[][] graph = new HashSet[2][n];
        for (int i=0;i<n;i++){
            graph[0][i] = new HashSet<>();
            graph[1][i] = new HashSet<>();
        }
        // Red edges in 0 col
        for (int[] r : redEdges){
            graph[0][r[0]].add(r[1]);
        }
        // B edges in 1 col
        for (int[] b : blueEdges){
            graph[1][b[0]].add(b[1]);
        }
        int[][] res = new int[2][n];
        // zero edge is always accessible to itself - leave as 0
        for (int i=1;i<n;i++){
            res[0][i] = 2*n;
            res[1][i] = 2*n;
        }
        // Q entries are vertices with a color (up to that point)
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0}); // either with red
        q.offer(new int[]{0, 1}); // or with blue
        while (!q.isEmpty()){
            int[] cur = q.poll();
            int vert = cur[0];
            int color = cur[1];
            // only track what color to keep and the length is auto derived from prev node
            for (int next : graph[1 - color][vert]){
                if (res[1-color][next] == 2 * n){
                    res[1-color][next] = 1 + res[color][vert];
                    q.offer(new int[]{next, 1 - color});
                }
            }
        }
        int[] ans = new int[n];
        for (int i=0;i<n;i++){
            int t = Math.min(res[0][i], res[1][i]);
            ans[i] = (t == 2*n) ? -1 : t;
        }
        return ans;
    }
}