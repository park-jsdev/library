// Linked List Traversal
// Time: O(n)
// Space: O(1) as we only maintain constant pointer variables
/**
    Be mindful with the pointers.
 */


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

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode slow_prev = null;

        // Track the next and prev
        
        // Move the fast ptr to n ahead, n <= size
        for (int i=0;i<n;i++){
            fast = fast.next;
        }

        // Increment all pointers
        while (fast != null){
            fast = fast.next;
            slow_prev = slow;
            slow = slow.next;
        }

        // when fast reaches null, we have reached the end with the conditions:
        // slow is at node to remove
        // slow_prev is the prev node of slow

        if (slow == head) {
            return head.next;
        }

        // simply connect slow_prev to slow.next
        if (slow_prev != null){
            slow_prev.next = slow.next;
        }

        return head;        
    }
}