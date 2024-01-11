class Solution {
    public int maxProfit(int[] prices, int fee) {
        Map<String, Integer> cache = new HashMap<>();
        return dfs(prices, fee, cache, 0, true);
    }

    private int dfs(int[] prices, int fee, Map<String, Integer> cache, int index, boolean buying){
        // Base Case
        if (index >= prices.length) {
            return 0;
        }
        
        // Recursive Step
        // we sell upon encountering a local max, and buy upon encountering a local min
        String key = index + "-" + buying; // for overlapping subproblem (index and action)
        if (cache.containsKey(key)){
            return cache.get(key);
        }

        // Cooldown logic
        int wait = dfs(prices, fee, cache, index + 1, buying); // cooldown isnt required, but it is a possibility to wait

        // Trade Logic
        int buyOrSell = Integer.MIN_VALUE; // potential profit

        if (buying){
            buyOrSell = dfs(prices, fee, cache, index + 1, !buying) - prices[index];
        } else { // selling
            buyOrSell = dfs(prices, fee, cache, index + 1, !buying) + prices[index] - fee; // we simply add
            // the fee to this problem, but only for selling.
            // There is no cooldown.
        }
        cache.put(key, Math.max(buyOrSell, wait));
        return cache.get(key);
    }
}