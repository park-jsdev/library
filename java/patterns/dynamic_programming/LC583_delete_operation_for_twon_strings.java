// Time: O(l1 * l2), l1 is len of string word1 and l2 is len of string word2.
// Space: O(l1 * l2)
class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int[] d : dp) Arrays.fill(d, -1);
        return solve(word1, word2, 0, 0, dp);
    }

    private int solve(String w1, String w2, int i, int j, int[][] dp){
        if (i == w1.length() && j == w2.length()) return 0;
        if (i == w1.length() || j == w2.length()) return Math.max(w1.length() - i, w2.length() - j);
        if (dp[i][j] != -1) return dp[i][j];
        if (w1.charAt(i) == w2.charAt(j)) return solve(w1, w2, i+1, j+1, dp);
        return dp[i][j] = 1 + Math.min(solve(w1, w2, i+1, j, dp), solve(w1, w2, i, j+1, dp));
    }
}