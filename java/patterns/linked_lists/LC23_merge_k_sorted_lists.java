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

// Linked Lists - Merge List 1 at a time
// Time: O(kn)
// Space: O(1), merge in place
/**
    Time: O(kn) as we call the merge lists operation with worst case of n total number of nodes k times, where k is the
    number of lists.
    Space: O(1) as we merge in place.
    Note that Divide and Conquer (Merge Sort) is the optimal solution.
 */

 class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // User the -th list as a return list, modify in place
        for (int i=1;i<lists.length;i++){
            lists[0] = mergeList(lists[0], lists[i]);
        }
        return lists[0];
    }

    private ListNode mergeList(ListNode n1, ListNode n2){
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        // While both nodes are not null
        while (n1 != null && n2 != null){
            if (n1.val < n2.val){
                prev.next = n1;
                n1 = n1.next;
            } else {
                prev.next = n2;
                n2 = n2.next;
            }
            prev = prev.next;
        }
        // Handle the remaining nodes
        prev.next = (n1 != null) ? n1 : n2; // if n1 is not null then n1, else n2

        return dummy.next;
    }
}