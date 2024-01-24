class Solution {
    public int findLongestChain(int[][] pairs) {
        // Take or not take
        int n = pairs.length;
        if (n == 1) return 1;
        Arrays.sort(pairs, (a,b) -> Integer.compare(a[0], b[0]));
        int[] memo = new int[n]; // cache for indices
        return solve(pairs, 0, n, memo);
    }

    private int solve(int[][] pairs, int i, int n, int[] memo){
        if (i >= n){
            return 1;
        }

        if (memo[i] > 0){
            return memo[i];
        }

        int maxChain = 0;
        for (int j=i+1;j<n;j++){
            if (pairs[j][0] > pairs[i][1]){
                maxChain = Math.max(maxChain, 1+solve(pairs,j,n,memo));
            }
        }

        // Max of take and not take
        return memo[i] = Math.max(maxChain, solve(pairs,i+1,n,memo));
    }
}