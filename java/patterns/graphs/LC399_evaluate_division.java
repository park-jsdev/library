class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Build adj list, from values, we can determine the weights. They are bidirectional
        Map<String, Map<String, Double>> graph = makeGraph(equations, values);
        double[] ans = new double[queries.size()];

        // then for each query, perform graph DFS from start to goal and store answer in res
        for (int i=0;i<queries.size();i++){
            ans[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), new HashSet<>(), graph);
        }
        return ans;
    }

    private Map<String, Map<String, Double>> makeGraph(List<List<String>> e, double[] values){
        // a -> b = values[i]
        // b -> a = 1.0 / values[i]
        Map<String, Map<String, Double>> graph = new HashMap<>();
        String u, v;

        for (int i=0;i<e.size();i++){
            u = e.get(i).get(0);
            v = e.get(i).get(1);

            graph.putIfAbsent(u, new HashMap<>());
            graph.get(u).put(v, values[i]);
            graph.putIfAbsent(v, new HashMap<>());
            graph.get(v).put(u, 1/values[i]);
        }
        return graph;
    }

    public double dfs(String src, String dest, Set<String> visited, Map<String, Map<String, Double>> graph){
        // Check terminated
        // if string is not present in graph return -1.0
        if (!graph.containsKey(src)) return -1.0;

        // if src and dest are equal, then return dest
        if (graph.get(src).containsKey(dest)){
            return graph.get(src).get(dest);
        }

        visited.add(src);

        for (Map.Entry<String, Double> nbr : graph.get(src).entrySet()){
            if (!visited.contains(nbr.getKey())){
                double weight = dfs(nbr.getKey(), dest, visited, graph);

                // if weight is not -1.0, multiply it like queries a -> c => 2 * 3 = 6
                if (weight != -1.0){
                    return nbr.getValue() * weight;
                }
            }
        }
        return -1.0;
    }
}