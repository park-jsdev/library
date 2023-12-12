class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] map_s = new int[256];
        int[] map_t = new int[256];

        for (int i=0;i<s.length();i++){
            if (map_s[s.charAt(i)] != map_t[t.charAt(i)]) return false; // this checks for inconsistency in previous
            // mapping

            map_s[s.charAt(i)] = i+1;
            map_t[t.charAt(i)] = i+1;
        }
        return true;
    }
}