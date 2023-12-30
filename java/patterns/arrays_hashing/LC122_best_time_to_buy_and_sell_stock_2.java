class Solution {
    public int maxProfit(int[] prices) {
        int i = 0, buy = 0, sell = 0, profit = 0, n = prices.length-1;
        while (i < n){
            while (i < n && prices[i+1] <= prices[i]){ // Iterate first to find the low, where the price
            // of i+1 is greater than price at i
                i++;
            }
            buy = prices[i]; // buy the low
            while (i < n && prices[i+1] > prices[i]){ // Then iterate to find the next high, where the price
            // of i+1 is lower than the price at i
                i++;
            }
            sell = prices[i]; // sell the high
            profit += sell - buy; // add to profits
        }
        return profit;
    }
}