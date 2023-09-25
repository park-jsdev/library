// Bit Shifting
// Time: O(32) = O(1)
// Space: O(1)
/**
    Know Bit representation and manipulation.
    The key implementation detail is that you need to use the unsigned shift (>>>) in Java.
 */

 public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0){
            count += (n & 1);
            n >>>= 1;
        }
        return count;
    }
}