// Arrays - HashSet Solution
// Time: O(n)
// Space: O(n)
/**
    The crux is to visualize the problem on a number line, then observe that we can identify sequences in O(n) time by
    checking their neighbors. A number is the start of a sequence if it has no left neighbor, and only then do we enter the
    loop to buid the sequence. While it has a right neighbor, the sequence is contiguous.

    We do 2 passes, first storing all numbers into a set, then we scan the array again while looking up the neighbors
    in the set.

    The key implementation details are the hash set and the loop to build the sequences. Note the for > if > while structure.
 */

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int longest = 1;
        for (int num : nums){
            // check if the num is the start of a sequence by checking if the left exists
            if (!set.contains(num-1)){ // it is the start of a sequence
                int count = 1;
                while (set.contains(num + 1)){ // check is the set contains the next num
                    num++;
                    count++;
                }
                longest = Math.max(longest, count);
            }
            if (longest > nums.length/2) break; // This optimization works because we check it after building the sequence
            // If the curr longest sequence is greater than half the length of the array, then it is not possible for any
            // other sequence to be longer than it.
        }
        return longest;
    }
}