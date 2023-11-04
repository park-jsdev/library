// DP - Top-Down Memoization
// Time: O(n)
// Space: O(n)
/**
    The crux is that ub the cache, we encode further information via the key, where we build the key as a string. 
    In this way, we are able to capture the index and the state in one key, whereas with a dp array, 
    you would need either a 3D dp array or a 2D dp array and handle the cooldown implicitly.

    In the cache, we are actually storing the state at each index as the key (buy at i, sell at i)
     and for the keys (max of buy, max of sell, max of cooldown) at that key.

    The flow of the program is the have the base case as 0, for the day beyond the index length, then recursively
    search for the maximum of the sequence. 
    
    State Definitions:

    There are 2 possible states:
    1. Currently buying
    2. Currently selling

    State transition:

    There are 3 actions you can perform:
    1. Buy
    2. Sell
    3. Cooldown - do nothing

    In this implementation, we have cooldown as an implicit choice, as we search the maximum of trade and cooldown.
    Cooldown is also a restriction after selling, and implicitly coded as i+2 after selling.

    For each recursive step, we flip the buying state with !buying, and then get the maximum of the 3 actions recursively.

    The cache will be populated as we progress through the recursion.

    It can be thought of as a for loop through the prices array, but at each step, we go deeper with recursion (like a detailed
    nested for loop).
 */

class Solution {
    public int maxProfit(int[] prices) {
        Map<String, Integer> cache = new HashMap<>();
        return dfs(prices, cache, 0, true); // The design of the dfs allows for the max of not buying on the first day,
        // so this still covers that case.
        // This is actually asking, what is the maximum if we have the option to buy on the first day, not if we do buy
    }

    /**
        In the dfs function, we include the original prices array, the cache as a Map, the index (to implicitly iterate),
        and the boolean (buying/selling) for that index.
     */
    public int dfs(int[] prices, Map<String, Integer> cache, int index, boolean buying){
        // Base Case
        if (index >= prices.length){ // if the index is greater than the prices arr, then we end, return 0
            return 0;
        }

        // Recursive Step

        String key = index + "-" + buying; // the key is generated from the curr index and buying state.
        // If the key is already present in the cache, it means that this state has already been calculated (overlapping subproblem)

        if (cache.containsKey(key)){
            return cache.get(key);
        }

        // Cooldown logic
        int cooldown = dfs(prices, cache, index+1, buying); // the profit for the cooldown action, simply move to the next day
        // without making a trade

        // Trade logic
        // recursively calculates profit for next day with the opposite action.
        int buyOsell = Integer.MIN_VALUE; // the profit of the next trade, it will be stored as the max of buy or sell below

        // !buying will flip the current buying state for each iteration
        if (buying){ // if buying, calc profit for the buy action
            buyOsell = dfs(prices, cache, index + 1, !buying) - prices[index]; // we are currently buying, so subtract the price
        } else { // if selling, calc profit for the buy action, but after cooldown
            buyOsell = dfs(prices, cache, index + 2, !buying) + prices[index]; // we are currently selling, so add the price
        }

        cache.put(key, Math.max(buyOsell, cooldown));
        return cache.get(key); // it will return the value after the recursive step propagates to the end
    }
}