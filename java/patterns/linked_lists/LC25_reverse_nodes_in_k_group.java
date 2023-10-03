/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// Linked List, Iterative Solution
// Time: O(n)
// Space: O(1)
/**
    The algorithm to call the k group reversal and to reconnect the resulting lists is straightforward, but has many
    pointers to keep track of.

    The key implementation detail is the standard reversal function. We also need to maintain dummy, curr, prev pointers, as
    well as kStart and kLast within the main loop.
 */

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0); // We need to use the dummy node "trick" to handle all edge cases
        dummy.next = head;
        ListNode curr = head; // pointer to navigate the list
        ListNode prev = dummy; // needed to maintain an anchor point

        while (curr != null){
            // The start of each reversed list will become the head after reversing
            ListNode kLast = curr;
            int num = k;

            // Jump k
            while (num > 0 && curr != null){
                curr = curr.next;
                num--; // decrement k
            }

            // If cannot reverse, then it is the end
            if (num != 0){
                prev.next = kLast;
                break; // break out of the loop
            }

            // Start of each reversed group
            // After reversing, kStart will be be the end
            ListNode kStart = reverse(kLast, k);

            // Linking the reversed group back to the main list

            // Use prev's next to point to curr reversed
            prev.next = kStart;
            // Set prev to curr reversed end
            prev = kLast;
            // In this way we move the k group being considered forward, with kStart and kLast pointers
        }
        return dummy.next;
    }

    // Standard reverse function
    public ListNode reverse(ListNode head, int k){
        ListNode prev = null;
        while (head != null && k > 0){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
            k--;
        }
        return prev;
    }
}