// Backtracking
// Time: O(2^n)
// Space: O(2^n)
/**
    String questions and palindromes/combinations/permutations are common. Be comfortable with them.

    The general template for backtracking is:
        0. Base case
        1. Choose
        2. Explore
        3. Unchoose

    Usually you need a helper (dfs) function, to accept more parameters. The parameters usually include:
        1. The object we are working on.
        2. Start index and index for the part we are working on.
        3. A step result, to remember current 'choose' and then do 'unchoose'
        4. A final result, to remember the final result, usually when we add, we use 'result.add(new ArrayList<>(step))'
        since we do not want to pass the step by reference.

 */

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (s.equals("")){
            res.add(new ArrayList<String>()); // empty string
            return res;
        }
        for (int i=0;i<s.length();i++){
            if (isPalindrome(s, i+1)){
                for (List<String> list : partition(s.substring(i+1))){
                    list.add(0, s.substring(0, i+1));
                    res.add(list);
                }
            }
        }
        return res;
    }

    public boolean isPalindrome(String s, int n){
        for (int i=0;i<n/2;i++){
            if (s.charAt(i) != s.charAt(n- i - 1)) return false;
        }
        return true;
    }
}