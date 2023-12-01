// Two Pointers - Fast and Slow
// Time: O(t)
// Space: O(1)

class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int slow = 0, fast = 0;
        for (int i=0;i<s.length();i++){
            // is s.charAt(i) matches t... then move forward
            while (fast < t.length() && s.charAt(slow) != t.charAt(fast)){
                fast++;
            }

            // if the end of t  is reached before the end of s, then return false
            if (fast >= t.length()) return false;
            
            slow++; // increment slow after each check at i
            fast++; // fast needs to be incremented as well so we do not re-use chars
        }
        return true;
    }
}