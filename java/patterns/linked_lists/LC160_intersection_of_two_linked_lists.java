/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

// Time: O(n)
// Space: O(n)

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode node = headA;

        // Iterate list A
        while (node != null){
            set.add(node);
            node = node.next;
        }

        // Iterate list B
        node = headB;
        while (node != null){
            if (set.contains(node)){
                return node;
            }
            node = node.next;
        }

        // no intersect
        return null; 
    }
}