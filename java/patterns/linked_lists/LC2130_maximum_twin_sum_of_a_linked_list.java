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
    public int pairSum(ListNode head) {
        int max = 0;
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }

        int half = stack.size() / 2;

        while (stack.size() > half){
            max = Math.max(max, (head.val + stack.pop().val));
            head = head.next;
        }
        return max;
    }
}