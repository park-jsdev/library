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
    public ListNode rotateRight(ListNode head, int k) {
        // We need to remove the tail and add as head k times
        if (head == null || head.next == null) return head;
        ListNode curr = head;

        // Step 1 - Calculate the size of the LL
        int size = 1;
        while (curr.next != null){
            size++;
            curr = curr.next;
        }

        // Step 2 - pointer curr to head of LL
        curr.next = head;
        k = k % size;
        k = size - k;

        // Step 3 - move curr till k == 0 and make head to curr.next annd curr.next = null
        while (k > 0){
            curr = curr.next;
            k--;
        }

        // Step 4 - Rotation
        head = curr.next;
        curr.next = null;

        return head;
    }
}