// Sliding Window
// Time: O(26n) = O(n)
// Space: O(n)
/**
    The idea is that we count the frequency of each character in the window,
    and consider the size of the window - count of the most frequent character in the window.
    The difference is the number of characters to change to maintain our window condition.

    The crux move is the character frequency map usage, and the sliding window condition:
    while r-l+1 - max > k, l++

    A key implementation detail is when to consider the max length of the window during the loop.
 */

 class Solution {
    public int characterReplacement(String s, int k) {
        int[] arr = new int[26]; // Char frequency map
        int res = 0;
        int max = 0;
        int l = 0;
        for (int r=0;r<s.length();r++){
            arr[s.charAt(r) - 'A']++; // method to map ASCII chars to the array indices
            max = Math.max(max, arr[s.charAt(r) - 'A']); // find the most frequent character
            if (r - l + 1 - max > k){ // window conditions broken
                arr[s.charAt(l) - 'A']--; // decrement the char count at left pointer
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}