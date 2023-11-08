// Graphs - Eulerian Path
// Time: O(V + E)
// Space: O(V)
/**
    This problem is solved with the Eulerian path algorithm. The intuition is that we go forward until we cant anymore, then
    the remaining nodes form cycles which are found on the way back and get merged into the main path.
 */

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        if (tickets == null || tickets.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        Map<String, PriorityQueue<String>> graph = buildGraph(tickets);

        List<String> itinerary = new ArrayList<>(graph.size());
        dfs(itinerary, graph, "JFK");

        Collections.reverse(itinerary);
        return itinerary;
    }

    // This helper method builds a graph from the given list of tickets
    private Map<String, PriorityQueue<String>> buildGraph(List<List<String>> tickets){
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets){
            String source = ticket.get(0);
            String destination = ticket.get(1);

            if (!graph.containsKey(source)){
                graph.put(source, new PriorityQueue<>());
            } 

            graph.get(source).add(destination);
        }
        return graph;
    }

    private void dfs(List<String> itinerary, Map<String, PriorityQueue<String>> graph, String source){
        // Base Case
        // if no more destinations possible, then we are at the end
        if (graph.get(source) == null || graph.get(source).isEmpty()){
            itinerary.add(source);
            return;
        }

        // try all destinations from this source incrementally
        while (!graph.get(source).isEmpty()){
            String nextDestination = graph.get(source).poll();
            dfs(itinerary, graph, nextDestination);
        }
        // at the bottom, traverse back
        itinerary.add(source);
    }
}