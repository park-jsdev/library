// Sliding Window
// Time: O(n)
// Space: O(1), we only consider constant variables and the 2 frequency maps of size 26 integers.
/**
    The crux move is to use 2 frequency maps. We use 2 passes, 1st pass for the s1 freq map, it will be static, and the 2nd pass
    is where we require the sliding window, as it is dynamic. At each iteration, we check if the frequency maps are equal and 
    immediately return true if they are, as we are loking for the first permutation, not a count.

    Frequency maps of size 26 for characters is a common formula which is used for many string problems.

    The key implementation details are the char frequency maps, and the sliding window. Remember:
    - We use int[] for the ASCII values: freq[s1.charAt(i)-'a']++, etc.
    - Maintain the window condition of size i >= n.
    - Arrays.equals(freq,freq2) at each iteration of the window.
 */

 class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[] freq = new int[26];
        int[] freq2 = new int[26];

        // First build a frequency map for s1
        for (int i=0;i<n;i++){
            freq[s1.charAt(i)-'a']++;
        }

        // Then build a frequency map for s2
        for (int i=0;i<m;i++){
            freq2[s2.charAt(i)-'a']++;
            if (i >= n){ // n is the size of the window
                freq2[s2.charAt(i-n)-'a']--; // we decrement and remove the left of the window from consideration at i-n
            }
            if (Arrays.equals(freq,freq2)) return true; // Immediately return true if the freq maps are equal (permuation found).
        }
        return false;
    }
}