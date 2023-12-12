class Solution {
    public String simplifyPath(String path) {
        Stack<String> st = new Stack<>();
        int p1 = 0;
        int p2 = 0;
        while (p2 <= path.length()){
            if (p2 < path.length() && path.charAt(p2) == '/' || p2 == path.length()){
                String word = path.substring(p1, p2);
                if (word.equals(".") || word.equals("")){
                    p2++;
                    p1 = p2;
                    continue;
                } else if (word.equals("..")){
                    if (!st.isEmpty()){
                        st.pop();
                    }
                } else {
                    st.add(word + "/");
                }
                p2++;
                p1 = p2;
            } else {
                p2++;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()){
            sb.insert(0, st.pop());
        }
        String ans = sb.toString();
        if (!ans.isEmpty() && ans.charAt(0) != '/'){
            ans = '/' + ans;
        }
        if (ans.isEmpty()){
            return "/";
        }
        if (ans.charAt(ans.length() - 1)== '/'){
            ans = ans.substring(0, ans.length() - 1);
        }
        return ans;
    }
}