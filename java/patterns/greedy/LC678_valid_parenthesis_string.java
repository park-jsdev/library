// Greedy
// Time: O(n)
// Space: O(1)
/**
    This is building on the stack problem 20. Valid Parentheses, so it can be solved by a stack, but this optimal, trick solution
    uses variables to maintain left count.

    The key intuition is that as there are wildcards, we cannot maintain only the count of left parentheses, but rather left min
    and left max. We keep left min above 0, as negative left min does not necessarily mean that the answer is invalid, but
    a negative left max does, as we can never recover from that state.
 */

class Solution {
    public boolean checkValidString(String s) {
        int low = 0, high = 0;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) == '('){
                low++;
                high++;
            } else if (s.charAt(i) == ')'){
                if (low > 0){ // maintain low over 0
                    low--;
                }
                high--;
            } else {
                if (low > 0){
                    low--;
                }
                high++;
            }
            if (high < 0){
                return false;
            }
        }
        return low == 0;
    }
}