// Bit Manipulation
// Time: O(n)
// Space: O(1)
/**
    The crux is to understand how to work with least significant bits (LSB) as a pointer, left and right shift,
    Logic OR and Logic AND to get and replace LSBs.

    Understand:
    - The structure of an int value (32 bits of 1 and 0)
    - Left and right shift <<= and >>=
    - Isolating the LSB of n: (n & 1). It "turns off" all bits (becomes all 0s), except the LSB, rightmost bit to 1. We can then
    shift that bit to the left as our pointer.
    - Setting the LSB of res: res |= (bit). This effectively sets the bit at our LSB to the (bit). If the bit to be set is 1, then
    the bit at res will be 1, if it is 0, then 0. If res is already 1, this operation would not work, but we have guaranteed that
    it will always be 0 before the operation, as the left shift operation will always leave a 0 in its place.

    Note that Right shifts have 2 types, arithmetic and logical right shifts, and in Java, right arithmetic shift on a positive
    number or logical right shift (>>>) introduces 0 bits on the left, while for negative numbers and arithmetic right shifts,
    1 bits are introduced on the left to maintain the sign. 
 */

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        
        for (int i=0;i<32;i++){
            res <<= 1;
            res |= (n & 1); // at each i, we shift the LSB (given by n&1) to the left by i, then replace the corresponding
            // bit in res with it. n & 1 gives the LSB at i, and 
            n >>= 1; // we right shift the LSB in n for the purpose of reversing the bits in res
        }
        return res;
    }
}