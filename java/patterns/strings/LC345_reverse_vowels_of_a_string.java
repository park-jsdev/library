class Solution {
    public String reverseVowels(String s) {
        if (s.length() == 1) return s;
        char[] chars = s.toCharArray();
        int l = 0, r = s.length()-1;
        while (l<r){
            while (l < r && !isVowel(chars[l])){
                l++;
            }
            while (l < r && !isVowel(chars[r])){
                r--;
            }
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
        return new String(chars);
    }

    private boolean isVowel(char c){
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}