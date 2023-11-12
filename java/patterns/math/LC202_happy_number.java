// Math
// Time: O(n)
// Space: O(n) for the hash set
/**
    The crux of this problem is to recognize that there will be a cycle no matter what. Determine if it is 1 or another number.

    The key implementation details are the Hash Set and the digit computation using modulus
 */

class Solution {
    public boolean isHappy(int n) {
        if (n == 1 || n == -1){
            return true;
        }

        Set<Integer> visit = new HashSet<Integer>();

        // compute square until we get duplicate value
        while (!visit.contains(n)){
            visit.add(n);
            n = sumOfSquare(n); // helper

            if (n == 1) return true;
        }
        return false;
    }

    public int sumOfSquare(int n){
        int output = 0;

        while (n != 0){
            int digit = n % 10;
            digit = digit * digit;
            output += digit;
            n = n / 10;
        }
        return output;
    }
}