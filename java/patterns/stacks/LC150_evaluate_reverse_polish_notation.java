// Stack
// Time: O(n)
// Space: O(n)
/**
    Note the Stack<Integer> and parsing string to integer.
 */
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int curr = 0;

        for (int i=0;i<tokens.length;i++){
            if (tokens[i].equals("+")){
                curr = stack.pop() + stack.pop();
                stack.push(curr);
                curr = 0;
            } else if (tokens[i].equals("-")){
                int a = stack.pop();
                int b = stack.pop();
                curr = b - a;
                stack.push(curr);
                curr = 0;
            } else if (tokens[i].equals("*")){
                curr = stack.pop() * stack.pop();
                stack.push(curr);
                curr = 0;
            } else if (tokens[i].equals("/")){
                int a = stack.pop();
                int b = stack.pop();
                curr = b / a;
                stack.push(curr);
                curr = 0;
            } else {
                // Use parseInt or valueOf
                stack.add(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
}