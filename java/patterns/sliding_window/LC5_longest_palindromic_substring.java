// Time: O(n^3)
// Space: O(n)

class Solution {
    String res = "";

    public String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        res = s.substring(0,1);
        
        int r = 1;

        while (r < s.length()){
            int l = 0;

            while (l < r){
                String sub = s.substring(l,r+1);

                if (isPalindrome(sub)){
                    if (sub.length() > res.length()){
                        res = sub;
                    }
                }
                l++;
            }
            r++;
        }
        return res;
    }

    private boolean isPalindrome(String substring){
        int n = substring.length();
        for (int i=0;i<=n/2;i++){
            if (substring.charAt(i) != substring.charAt(n-1-i)) return false;
        }
        return true;
    }
}