// HashMap Solution
// Time: O(s) + O(t) + O(s) = O(n), n = s
// Space: O(s) + O(t) = O(n), n = s
// It can be solved in O(nlogn) time by sorting and comparing, but Hash solution is O(n).
// Follow up: We can adapt to use a bit solution, if the input contains Unicode characters

// s is and Anagram of t if their char counts are equal. We can use 2 hashmaps to compare
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] s_chars = s.toCharArray();
        char[] t_chars = t.toCharArray();
        Arrays.sort(s_chars);
        Arrays.sort(t_chars);
        for (int i=0;i<s_chars.length;i++){
            if (s_chars[i] != t_chars[i]){
                return false;
            }
        }
        return true;
    }
}