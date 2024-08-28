class Solution {
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        long[] dp = new long[n];
        dp[1] = 2;
        int mod = 1000000007;
        for (int i=2;i<n;i++){
            dp[i] = dp[i-1] + dp[i-1] + mod - dp[nextVisit[i-1]] + 2;
            dp[i] %= mod;
        }
        return (int)dp[n-1];
    }
}