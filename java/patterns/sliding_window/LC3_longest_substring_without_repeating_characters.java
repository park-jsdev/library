// Sliding Window
// Time: O(n)
// Space: O(n), worst case, the window will equal the string
/**
    Note using the set and the implementation details. Tricky to get everything right.
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        if (s.length() == 0) return max;
        if (s.length() == 1) return 1;
        Set<Character> set = new HashSet<>();
        int l = 0;
        int r = 0;
        int len = 1;
        while (r < s.length()){
            if (!set.contains(s.charAt(r))){
                set.add(s.charAt(r));
                r++;
            } else {
                set.remove(s.charAt(l));
                l++;
            }
            len = r-l;
            max = Math.max(max,len);
        }
        return max;
    }
}