class Solution {
    public String mergeAlternately(String word1, String word2) {
        String res = "";
        int m = 0;
        int n = 0;

        for (int i=0;i<word1.length()+word2.length();i++){
            if (i%2 == 0 && m < word1.length()){
                res += word1.charAt(m);
                m++;
            }
            if (i%2 == 1 && n < word2.length()){
                res += word2.charAt(n);
                n++;
            }
            if (m >= word1.length() && n < word2.length()){
                res += word2.charAt(n);
                n++;
            }
            if (n >= word2.length() && m < word1.length()){
                res += word1.charAt(m);
                m++;
            }
        }
        return res;
    }
}