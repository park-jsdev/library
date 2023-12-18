class Solution {
    public ListNode deleteMiddle(ListNode head) {
        ListNode temp = head;
        int n = 1;

        // find length n
        while (temp != null && temp.next != null){
            temp = temp.next;
            n++;
        }

        if (n == 1) return null;

        int k = n/2; // mid point

        temp = head;
        ListNode tail = new ListNode();
        tail.next = head;

        for (int i=0;i<k;i++){ // reach the middle node with fast ptr
            temp = temp.next;
            tail = tail.next;
        }

        // skip middle node at k
        temp = temp.next;
        tail.next = temp; // can do tail.next.next
        return head;
    }
}