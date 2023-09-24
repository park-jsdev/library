// Stack
// Time: The amortized time complexity is exponential, but the true growth rate is related to the nth Catalan number (difficult to
// remember in an interview).
// Space: Same as time.
/**
    The crux move is to track the string, number of open and closed parenthesis per iteration (combinations).
    Use a Stack and a class. We use the DFS loop method to drive the algorithm.
    The key implementation details are the Class, DFS loop, and the rules of the loop.
 */

class Solution {
    public List<String> generateParenthesis(int n) {

        // We will initialize our own data structure
        Stack<Str> s = new Stack<>();
        List<String> res = new ArrayList<>();
        s.push(new Str("(",1,0)); // we push 1 for the initial state

        // DFS loop
        while (s.size() > 0){
            Str curr = s.pop(); // Evaluate the current combination

            // Only add an open parenthesis if open < n 
            if (curr.open < n){
                // if the num of open and closed are the same, we can only add an open next
                if (curr.open == curr.close){
                    s.push(new Str(curr.str + "(", curr.open+1, curr.close));
                } else {
                    // If there are more open than closed, we can either add an open or add a closed
                    s.push(new Str(curr.str + "(", curr.open+1, curr.close));
                    s.push(new Str(curr.str + ")", curr.open, curr.close+1));
                }
            } else {
                // If we added all of the combinations for the open parentheses, what remains is
                // to add the rest of the closed parentheses

                // If closed < n, add a closed parenthesis
                while (curr.close < n){
                    curr.str = curr.str + ")";
                    curr.close++;
                }
                res.add(curr.str); // Add the resulting string to the result
            }
        } 
        return res;
    }

    // The class to encapsulate the current state of a combination
    class Str {
        String str;
        int open;
        int close;

        Str(String s, int o, int c){
            this.str=s;
            this.open=o;
            this.close=c;
        }
    }
}