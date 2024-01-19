class Solution {
    public String decodeString(String s) {
        if (s.length() == 1) return s;

        char [] chs = s.toCharArray();
        Stack<Integer> countStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        String currStr = "";
        int count = 0;

        for (int i=0;i<chs.length;i++){
            char curr = chs[i];

            if (Character.isDigit(curr)){
                count = count * 10 + curr - '0';
            }

            else if (curr == '['){
                countStack.push(count);
                count = 0;
                strStack.push(currStr);
                currStr = "";
            }

            else if (curr == ']'){
                int currCount = countStack.pop();
                String prevStr = strStack.pop();
                while (currCount-- > 0){
                    prevStr += currStr;
                }
                currStr = prevStr;
            } else {
                currStr += Character.toString(curr);
            }
        }

        return currStr;
    }
}