/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

// Fast and Slow
// Time: O(n)
// Space: O(1) as we only use 2 pointers 
/**
    The optimal solution is a "trick" algorithm called "Tortoise and the Hare algorithm", or Floyd's Cycle-Finding algorithm.
    If the hare is moving at 2x speed of the turtle and the track has a cycle, the hare is guaranteed to catch up to the tortoise.

    The intuition for the proof is that at each iteration, the hare moves by 2, and the tortoise by 1, so the distance between them
    is decreasing by 1 at each step (the hare is catching up to the tortoise). You can formalize the proof with modulus
    or induction.

    You can also use this algorithm to find the position of the cycle start, and the length of the cycle.

    The crux is to know the "trick".
    The key implementation detail is to handle starting conditions: size 0 - 2.
 */

 public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}