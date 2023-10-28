// Dynamic Programming
// Time: O(n*m), but checking the substring can cause a worst-case of O(n*m*k)
// Space: O(n)
/**
    We use a bottom-up dynamic programming approach. The base case is the empty string which is set to true, then at each
    i, we check if the substring until the end matches a word in the word dict. Method of exhaustion and if dp[0] is true at
    the end, then the word must be valid.
 */

 class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1]; // the reason we initialize past the length of s is to capture the empty
        // substring (beyond the last character). It is considered the base case.
        dp[s.length()] = true; // default is set to true

        // starting from the last character, we check if the substring is a valid word from our dict
        for (int i=s.length()-1;i>=0;i--){
            for (String w: wordDict){
                if ((i + w.length()) <= s.length() && s.substring(i, i+w.length()).equals(w)){
                    // check if the char at i + the length of the word is within the string's bounds
                    // then check if the substring between i and the word length equals the word

                    dp[i] = dp[i + w.length()]; // if the segment of s starting at i of length w matches a word in dict
                    // then it will equal the previous point after the current word. The base case is true by default (empty char)
                }
                if (dp[i]){ // if dp[i] is true
                    break; // early break from iterating the word dict, saving an O(n^2) runtime
                }
            }
        }
        return dp[0]; // if we reach the end (first char) and it is still true, then the whole word must be valid.
    }
}