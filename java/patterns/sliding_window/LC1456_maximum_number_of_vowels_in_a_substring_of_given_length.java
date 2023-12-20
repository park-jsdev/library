class Solution {
    public int maxVowels(String s, int k) {
        int max = 0;
        int count = 0;
        int l = 0;
        for (int i=0;i<k;i++){
            if (isVowel(s.charAt(i))){
                count++;
            }
        }
        // Handle the base case
        max = Math.max(max, count);

        for (int i=k;i<s.length();i++){
            if (isVowel(s.charAt(i))){
                count++;
            }
            if (isVowel(s.charAt(l))){
                count--;
            }
            l++;
            max = Math.max(max, count);
        }
        return max;
    }

    private boolean isVowel(char c){
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
        return false;
    }
}