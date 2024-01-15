class Solution {
    public int numSquares(int n) {
        int[] memo = new int[n+1];
        return helper(n, memo);
    }

    private int helper(int n, int[] memo){
        if (n < 4) return n;
        if (memo[n] != 0) return memo[n];

        int res = n;
        for (int i=1;i*i<=n;i++){
            int square = i*i;
            res = Math.min(res, 1+helper(n-square, memo));
        }
        return memo[n] = res;
    }
}