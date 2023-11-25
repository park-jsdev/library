// Bit Manipulation
// Time: O(n)
// Space: O(1)
/**
    The crux of the solution is to understand that we can check the number with the integer max value up to the last digit,
    then compare the last digit, so we do not encounter overflow during the comparison when we hold it in memory.

    Initialize the resulting num from 0, and build it up with checks before each step.

    A key implementation detail is to handle the negative before and after the core logic.
 */

class Solution {
    public int reverse(int x) {
        boolean isNegative = x < 0; // handle negative before checking
        x = Math.abs(x);

        int num = 0; // num that we are building is initialized from 0

        while (x > 0){
            if (Integer.MAX_VALUE / 10 < num) return 0; // if the max value up to the last digit < than the num
            // if the current num is greater than the max value up to the last digit (/ 10), then the next operation (* 10)
            // would give a value greater than the Integer MAX VALUE

            num = 10 * num + x % 10; // shifts the num to the left, then adds the last digit of x
            x /= 10; // removes last digit of x after adding
        }

        return isNegative ? -num : num; // set back to negative if required
        
    }
}