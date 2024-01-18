class Solution {
    public boolean closeStrings(String word1, String word2) {
        // Base Case
        if (word1.length() != word2.length()) return false;

        // freq maps
        int[] f1 = new int[26];
        int[] f2 = new int[26];

        // Populate freq maps
        for (int i=0;i<word1.length();i++){
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            f1[c1-'a']++;
            f2[c2-'a']++;
        }

        // Hypothesis - order does not matter. 
        // if the unique chars are the same, and there is a character with the matching freq then it is similar
        // i.e. the chars do not matter nor their order. Map them to buckets, and as long as the num of buckets
        // and their frequencies are equal, then it is true.

        // First check that they have the same characters
        for (int i=0;i<f1.length;i++){
            if ((f1[i] == 0 && f2[i] != 0) || (f2[i] == 0 && f1[i] != 0)){
                return false;
            }
        }
        
        Arrays.sort(f1);
        Arrays.sort(f2);

        for (int i=0;i<f1.length;i++){
            // once sorted, the frequencies must be equal
            if (f1[i] != f2[i]){
                return false;
            }
        }
        return true;
    }
}