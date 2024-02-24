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
    public ListNode partition(ListNode head, int x) {
        if (head == null) return head;

        ListNode beforeHead = new ListNode(0); // dummy head
        ListNode before = beforeHead; // ptr for before list
        ListNode afterHead = new ListNode(0); // dummy head
        ListNode after = afterHead; // ptr for after list

        while (head != null){
            if (head.val < x){
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null; // avoid cycles in LL
        before.next = afterHead.next; // connect 2 lists

        return beforeHead.next;
    }
}