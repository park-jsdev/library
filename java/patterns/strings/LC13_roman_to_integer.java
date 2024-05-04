// Time: O(n)
// Space: O(n)

class Solution {
    public int romanToInt(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        int cur = 0;

        // first char
        if (chars[0] == 'I') cur = 1;
        if (chars[0] == 'V') cur = 5;
        if (chars[0] == 'X') cur = 10;
        if (chars[0] == 'L') cur = 50;
        if (chars[0] == 'C') cur = 100;
        if (chars[0] == 'D') cur = 500;
        if (chars[0] == 'M') cur = 1000;

        if (s.length() == 1){
            return cur;
        }
        res += cur;

        // rest of chars
        for (int i=1;i<chars.length;i++){
            if (chars[i] == 'I') cur = 1;
            if (chars[i] == 'V') cur = 5;
            if (chars[i] == 'X') cur = 10;
            if (chars[i] == 'L') cur = 50;
            if (chars[i] == 'C') cur = 100;
            if (chars[i] == 'D') cur = 500;
            if (chars[i] == 'M') cur = 1000;

            if ((chars[i] == 'V' || chars[i] == 'X') && chars[i-1] == 'I'){
                cur -= 2; // also subtract prev I
            }
            if ((chars[i] == 'L' || chars[i] == 'C') && chars[i-1] == 'X'){
                cur -= 20;
            }
            if ((chars[i] == 'D' || chars[i] == 'M') && chars[i-1] == 'C'){
                cur -= 200;
            }
            res += cur;
        }
        return res;
    }
}