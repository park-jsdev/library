class Solution {
    public String smallestSubsequence(String s) {
        Stack<Character> stack = new Stack<>();
        int[] freq = new int[26];
        boolean[] exists = new boolean[26];

        for (int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            freq[ch-'a']+=1;
        }

        for (int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            freq[ch-'a'] -= 1;
            if (exists[ch-'a'] == true) continue;

            while (stack.size() > 0 && stack.peek() > ch && freq[stack.peek()-'a'] > 0){
                exists[stack.pop()-'a'] = false;
            }
            stack.push(ch);
            exists[ch-'a'] = true;
        }

        char[] res = new char[stack.size()];
        int i = res.length - 1;
        while (i >= 0){
            res[i] = stack.pop();
            i-=1;
        }
        return new String(res);
    }
}