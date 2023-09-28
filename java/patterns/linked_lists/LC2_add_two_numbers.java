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

// Linked List - Unoptimized
// Time: O(n^2) time due to nested structure (temp list). It can be optimized by maintaining tail pointer.
// Space: O(n)
/**
    The crux is to understand the addition operation, especially carry, and implement via the linked lists.
    The key implementation details are the linked list handling, traversals, and carry operation.
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0, r = 0, l1_val = 0, l2_val = 0, sum = 0;

        // Dummy values to handle edge cases
        ListNode head = null;
        ListNode temp = null;

        while (l1 != null || l2 != null || carry > 0){ // make sure to handle carry
            // One of the values may run into null l1 (they are of different lengths)
            if (l1 != null) {
                l1_val = l1.val;
            } else {
                l1_val = 0;
            }

            if (l2 != null) {
                l2_val = l2.val;
            } else {
                l2_val = 0;
            }
            sum = carry + l1_val + l2_val;
            r = sum % 10;
            carry = sum / 10;

            // Build result linked list
            ListNode newNode = new ListNode(r);
            if (head == null){ // track head
                head = newNode;
            } else {
                temp = head;
                while (temp.next != null){ // traversal of the list
                    temp = temp.next;
                }
                temp.next = newNode;
                newNode.next = null;
            }

            // Move to next digit
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
        }
        return head;
    }
}