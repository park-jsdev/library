class Solution {
    public long mostPoints(int[][] questions) {
        int m = questions.length;
        long[] memo = new long[m];
        long res = helper(questions, 0, m, memo);
        return res;
    }

    private long helper(int[][] questions, int i, int m, long[] memo){
        // Base case
        if (i >= m){
            return 0;
        }
        if (memo[i] != 0){
            return memo[i];
        }

        // Take
        long take = questions[i][0];
        if (i + questions[i][1] + 1 < m){ // add 1 to move to the next after cooldown period
            take += helper(questions, i+questions[i][1]+1, m, memo);
        }

        // Skip
        long not_take = helper(questions, i+1, m, memo);
        return memo[i] = Math.max(take, not_take);
    }
}