class Solution {
    public boolean isValid(String s) {
        if (s.length() == 1) return false;
        Stack<Character> stack = new Stack<>();

        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (c == '(' ||c == '[' || c == '{'){
                stack.push(s.charAt(i));
            } else {
                if (stack.size() == 0) return false;
                
                char d = stack.pop();
                if (c == ')' && d != '(') return false;
                if (c == ']' && d != '[') return false;
                if (c == '}' && d != '{') return false;
            }
        }
        
        return stack.size() == 0;
    }
}