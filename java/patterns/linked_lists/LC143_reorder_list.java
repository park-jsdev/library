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

// Reverse and Merge LL
// Time: O(n), as all operations are linear time
// Space: O(1), as we use a constant number of variables to access the list 
/**
    The crux is to use fast and slow to find the middle, then both reversal and a merge.
    Key implementation detail is to use fast and slow to not use extra memory, as well as each component (reversal, merge).
 */

 class Solution {
    public void reorderList(ListNode head) {
        // Find the middle of list using fast and slow pointers
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse the second half of the list
        ListNode second = slow.next;
        ListNode prev = slow.next = null;
        while (second != null){
            ListNode temp = second.next;
            second.next = prev;
            prev = second;
            second = temp;
        }

        // Re-assign the pointers to match the pattern
        ListNode first = head;
        second = prev;
        while (second != null){
            // merge
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;
            first.next = second;
            second.next = tmp1;
            // shift
            first = tmp1;
            second = tmp2;
        }

    }
}