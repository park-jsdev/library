// Dynamic Programming - Top Down Memoization
// Time: O(nm)
// Space: O(nm)
/**
    The crux is to use the indices of the strings to perform the operations. In the cache, we can store valid or 
    invalid up to that point.

    In the base case, recognize that if index j reaches the end only, then we have exhausted the characters in p, the String
    we use to match, therefore the check should return false.

    There are 3 core logics to code:
    1. Matching characters: s(i) and p(j) are matching OR p(j) is '.'
    2. p(j) is '*':
        2.a. We can choose to not use the '*', or empty set. We skip both its preceding char and the '*'.
        2.b. We do take the '*' and its preceding character, by i+1 and j. Moving past j will be handled in subsequent calls.

    Note that we check j+1 at each step for the occurrence of '*'.

 */

class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] cache = new boolean[s.length()+1][p.length()+1];
        return dfs(cache, s, p, 0, 0);
    }

    private boolean dfs(boolean[][] cache, String s, String p, int i, int j){
        // Base Cases
        if (cache[i][j] != false) return cache[i][j]; // cached value

        // Both out of bounds, reached the end so true
        if (i >= s.length() && j >= p.length()) return true;

        // If only j reaches out of bounds, then invalid. False
        if (j >= p.length()) return false;

        // Recursive Step
        boolean match = i < s.length() &&
        (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'); // check if a match at i within bounds.
        // '.' allows any char to match

        // If a wildcard, there are 2 scenarios to explore, note that we check j+1 for the '*'
        if (j+1 < p.length() && p.charAt(j+1) == '*'){
            // 1. Skip the wildcard entirely (not appear at all), j+2
            // 2. If the current chars match (or a '.'), then we move i+1 and keep j, as the char at j can be used again.
            cache[i][j] = dfs(cache, s, p, i, j+2) || (match && dfs(cache, s, p, i+1, j));
        } else {
            // store the cache and recursively call on the next step
            cache[i][j] = match && dfs(cache, s, p, i+1, j+1);
        }
        return cache[i][j];
    }
}