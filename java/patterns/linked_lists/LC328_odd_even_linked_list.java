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
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return head;

        ListNode odd = head; // Init the 1st odd at position 1
        ListNode even = head.next; // The base case is that even will start at start + 1 and be odd + 1
        ListNode evenHead = even;

        while (even != null && even.next != null){
            odd.next = even.next; // skips the even node for odds
            odd = odd.next; // then increments odd
            even.next = odd.next; // even's next is the next of the new odd
            even = even.next; // increments even
        }
        odd.next = evenHead; // ensures that evens come after odd
        return head;
    }
}