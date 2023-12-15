class Solution {
    public String removeStars(String s) {
        Stack<Character> st = new Stack<>();
        String res = "";

        for (int i=0;i<s.length();i++){
            if (s.charAt(i) != '*'){
                st.push(s.charAt(i));
            } else { // '*'
                st.pop();
            }
        }
        // what remains is the string
        while (!st.isEmpty()){
            res = st.pop() + res; // add to string in reverse order
        }
        return res;
    }
}