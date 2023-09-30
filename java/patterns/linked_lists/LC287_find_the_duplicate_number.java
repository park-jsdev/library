// Fast and Slow, Floyd's
// Time: O(n)
// Space: O(1)
/**
    The crux move is to identify:
    1. Use the values as index pointers, and model it as a linked list with an array representation.
    The index of the cycle start is the duplicate number.
    2. Apply Floyd's Algorithm to find the start of the cycle.

    The key implementation details are the linked list traversal using index pointers and Floyd's
    algorithm for cycle start detection.

 */

 class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;

        // Flyod's Algorithm (2 phases):

        // Phase 1 - detect cycle
        do {
            slow = nums[slow]; // Use the value as a pointer to next
            fast = nums[nums[fast]]; // Use the value of the value as a pointer to next.next
        } while (slow != fast);

        // Phase 2 - second slow pointer
        int slow2 = 0;

        do {
            slow = nums[slow];
            slow2 = nums[slow2];
        } while (slow != slow2);

        return slow2; // The intersection of slow1 and slow2 is the location of the cycle start
    }
}