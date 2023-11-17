// Math
// Time: O(logn)
// Space: O(1)
/**
    The Brute force solution of O(n) time gives TLE, and the Divide and Conquer Recursion solution is expected.

    The key implementation detail is to divide by 2 whenever the number is negative and even, as it contains the
     edge case of Integer.MIN_VALUE, which would become greater than the max value of Integer.MAX_VALUE if multiplied by -N. 
 */

class Solution {
    public double myPow(double x, int n) {
        
        // Base Case
        if (n == 0){
            return 1;
        }

        // Make negative vals positive
        else if (n < 0){
            // when even, divide by 2, and handles Integer.MIN_VALUE
            if (n % 2 == 0){
                n = n/2;
                n = -n;
                x = (1/x) * (1/x);
            } else {
                n = -n;
                x = 1/x;
            }
        }
        if (n % 2 == 0){ // even nums
            return myPow(x*x, n/2); // recurse
        } else { // odd
            return x * myPow(x*x, n/2);
        }
    }
}