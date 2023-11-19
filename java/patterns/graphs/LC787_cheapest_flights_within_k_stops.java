// Graphs - Bellman Ford
// Time: O(kn)
// Space: O(n)
/**
    This question can be solved with a classic Bellman Ford algorithm. Bellman Ford consists of initialization of infinity,
    and iterative Relaxation of all the edges.

    In Bellman Ford, we extend BFS, where we initialize an array with infinity for each node (MAX_VALUE), except the source.
    Then we iterate k+1 steps of "relax" the frontier, where we create a temp copy of the prices array for each layer,
    and if we find that there is a lower cost to each destination node at the step, we update the costs array with the cost.
    We consider the case that there is already a cost (lower than MAX_VALUE), and take the sum.

    At the end, we return the cost to the destination node, which will be populated if a path exists to it, and it should
    contain the minimum cost.
 */

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 1. initialize an array with max value of size n
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);

        // price from source to source is always 0
        prices[src] = 0;

        // 2. Iterative Relaxation
        for (int i=0;i<=k;i++){
            // make copy of prices
            int[] temp = new int[n];
            temp = Arrays.copyOf(prices, prices.length);

            // loop through flights
            for (int j=0;j<flights.length;j++){
                int s = flights[j][0]; // from
                int d = flights[j][1]; // to
                int p = flights[j][2]; // price

                if (prices[s] == Integer.MAX_VALUE){
                    continue;
                }

                if (prices[s] + p < temp[d]){
                    temp[d] = prices[s] + p;
                }
            }

            // set prices to temp
            prices = temp;
        }

        if (prices[dst] != Integer.MAX_VALUE){
            return prices[dst];
        }

        return -1;
    }
}