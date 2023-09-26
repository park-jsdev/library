/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

// Linked List
// Time: O(n)
// Space: O(1), we don't use extra space for new nodes.
/**
    The crux move is adding a map between the original and the deep clone, and using 
    2 passes with 1. copy nodes and hashmap, 2. pointing the nodes.
    They key implementation is the Node, Node map.
 */

class Solution {
    public Node copyRandomList(Node head) {
        Node curr = head;
        HashMap<Node, Node> map = new HashMap<>();
        
        // First Pass
        while (curr != null){
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // Second Pass
        curr = head;
        while (curr != null){
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }
}