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

// In-place Linked List
// Time: O(n)
// Space: O(n)
/**
    Note that we are building in place. Understand that passing in Java objects as parameters to functions
    passes in references, not the objects themselves. If you want to maintain the original objects (SOLID)
    you need to clone them within the function.
 */

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Basic Checks
        if (list1 == null && list2 == null) return null;
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        // they are both not null

        ListNode res; // set the head of output
        // Modify 1 in place, pick the head
        if (list1.val <= list2.val){
            res = list1;
            list1 = list1.next;
        } else {
            res = list2;
            list2 = list2.next;
        }
        ListNode temp = res; // the temp pointer to modify the output

        // while there exists non-null nodes
        while (list1 != null && list2 != null){
            // if list 1 <= list 2
            if (list1.val <= list2.val){
                temp.next = list1;
                list1 = list1.next;
            } else { // if list 2 < list 1
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        // Append remaining nodes, does not need to iterate
        if (list1 != null){
            temp.next = list1;
        } else if (list2 != null){
            temp.next = list2;
        }
        
        return res;
    }
}