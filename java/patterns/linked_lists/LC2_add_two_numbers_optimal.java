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

// Linked List
// Time: O(n)
// Space: O(n)

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        int carry = 0;
        int val1 = 0;
        int val2 = 0;

        while (temp1 != null || temp2 != null || carry > 0){
            if (temp1 != null){
                val1 = temp1.val;
            } else {
                val1 = 0;
            }

            if (temp2 != null){
                val2 = temp2.val;
            } else {
                val2 = 0;
            }

            int local_sum = val1 + val2 + carry; // Note that carry can exist from last step
            carry = local_sum / 10;
            int val_res = local_sum % 10;

            ListNode temp3 = new ListNode(val_res);
            prev.next = temp3;
            prev = temp3;

            if (temp1 != null) temp1 = temp1.next;
            if (temp2 != null) temp2 = temp2.next;
        }
        return dummy.next;
        
    }
}