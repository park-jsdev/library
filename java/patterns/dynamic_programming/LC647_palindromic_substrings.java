// Dynamic Programming - No Functions
// Time: O(n^2) as we perform 2 passes of O(n) time for every i. O(n * 2n) = O(n^2)
// Space: O(1) as we only consider a constant number of variables
/**
    This is the simplest approach, but optimizations include functions for the palindrome check and the dp array solution.

    The crux is that we consider each index as mid and expand outwards, considering the bounds. However, to capture both odd length
    and even length, we need to do 2 passes per iteration, where odd starts from mid, and even starts with the pair (i, i+1).

    The key implementation detail is the boundary checks. For the function solution, simply put initializations of pointers
    in the parameters, and put the while loop in the function, return value is the same.
 */

 class Solution {
    public int countSubstrings(String s) {
        if (s.length() < 2) return s.length();

        int res = 0;
        // we perform 2 while loops per iteration within an outer for loop
        for (int i=0;i<s.length();i++){
            // 1st pass: Odd Length
            int l = i, r = i; // Start with l and r at i
            while (
                l >= 0 && // check left bound
                r < s.length() && // check right bound
                s.charAt(l) == s.charAt(r) // check if chars match
                ){ // expand the window
                    res++;
                    l--;
                    r++;
                }
            // 2nd pass: Even Length
            l = i; // reset l back to i
            r = i + 1; // this time, r is the next index
            while (
                // Same checks as above
                l >= 0 && 
                r < s.length() &&
                s.charAt(l) == s.charAt(r)
            ) {
                res++;
                l--;
                r++;
            }
        }
        return res;
    }
}