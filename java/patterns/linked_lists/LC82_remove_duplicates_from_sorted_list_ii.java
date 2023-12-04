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

// Linked Lists - Two Pointers
// Time: O(n)
// Space: O(1)
/**
    While it can be solved with a HashMap, the optimal is to use 2 pointers. Slow to track the node before the duplicate nodes,
    fast to find the last node of duplicates. The crux is that fast uses node values to detect duplicates, while slow uses
    the node value of fast (i.e. if slow.next != fast then there are duplicates in the range).

    The key implementation details are to use the fast pointer to detect duplicates and let it remain as the last of the
    duplicates, slow detects duplicates by node reference, rather than the value.
 */

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // Basic Setup
        ListNode dummy = new ListNode(0), fast = head, slow = dummy;
        slow.next = fast; // initialize slow (dummy) -> fast

        while (fast != null){
            while (fast.next != null && fast.val == fast.next.val){
                fast = fast.next; // while loop to find the last node of the duplicates
            } // fast will be the last of, but still a duplicate value
            if (slow.next != fast){ // detect duplicate
                // Reposition both 
                slow.next = fast.next; // remove duplicate
            } else { // no duplicate
                slow = slow.next; // move slow
            }
            fast = fast.next; // reposition fast in either case
        }
        return dummy.next;
    }
}