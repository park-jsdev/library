// HashMap Solution
// Time: O(s) + O(t) + O(s) = O(n), n = s
// Space: O(s) + O(t) = O(n), n = s
// It can be solved in O(nlogn) time by sorting and comparing, but Hash solution is O(n).
// Follow up: We can adapt to use a bit solution, if the input contains Unicode characters

// s is and Anagram of t if their char counts are equal. We can use 2 hashmaps to compare
class Solution {
    public boolean isAnagram(String s, String t) {
        // if the lengths are different, we will immediately return false
        if (s.length() != t.length()) return false;

        Map<Character, Integer> s_map = new HashMap<>();
        Map<Character, Integer> t_map = new HashMap<>();
        
        // Iterate String s
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (!s_map.containsKey(c)){
                s_map.put(c, 1);
            } else {
                s_map.put(c, s_map.get(c)+1);
            }
        }

        // Iterate String t
        for (int i=0;i<t.length();i++){
            char c = t.charAt(i);
            if (!t_map.containsKey(c)){
                t_map.put(c, 1);
            } else {
                t_map.put(c, t_map.get(c)+1);
            }
        }

        // Compare both maps to ensure they are equal
        return s_map.equals(t_map);
    }
}