// Math
// Time: O(n)
// Space: O(n) for digits array
/**
    The trick to this problem is to remember the carry and handle the edge cases.
    Think about the addition algorithm's details.
    The digits are already in array form, but sometimes this problem is presented as an int number, where you will need to use
    modulus and division to get the digits.

    The crux is to manage 2 potential result arrays, newDigits and the input digits array in place.
    If there is a carry at the end, we add it to newDigits[0] and then return it, else we just return the input array which
    we computed in place.
 */

class Solution {
    public int[] plusOne(int[] digits) {
        final int len = digits.length;
        int[] newDigits = new int[len + 1];
        int carry = 1;
        int currSum = 0;
        for (int i=len-1;i>=0;i--){
            currSum = digits[i] + carry;
            if (currSum > 9){
                digits[i] = currSum % 10;
                newDigits[i+1] = digits[i];
                carry = 1;
            } else {
                digits[i] = currSum;
                newDigits[i+1] = digits[i];
                carry = 0;
                break;
            }
        }
        // We save the first element of the array in case there is a carry
        if (carry == 1){
            newDigits[0] = 1;
            return newDigits;
        }

        return digits;
    }
}