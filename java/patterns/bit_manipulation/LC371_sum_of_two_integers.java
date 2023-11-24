// Bit Manipulation
// Time: O(1), linear time if infinite size, but constraints are constant
// Space: O(1)
/**
    For each digit, addition is XOR, except we need to handle the carry. So we use 2 passes, where the 2nd pass is an AND
    with left shift.
    We loop a ^ b, then (a & b) << 1.
    Use a temp value in the loop to not override during calculation. 
 */

class Solution {
    public int getSum(int a, int b) {
        while (b != 0){
            int tmp = (a & b) << 1;
            a = (a ^ b);
            b = tmp;
        } 
        return a;
    }
}