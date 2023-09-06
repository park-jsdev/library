/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

// DFS with HashMap Solution - Connected Undirected Graph, Deep Copy
// Time: O(n)
// Space: O(n)

class Solution {
    HashMap<Integer, Node> map = new HashMap(); // Hashmap for cloned nodes

    public Node cloneGraph(Node node) {
        // Basic Check
        if (node == null) return null;

        // Base condition:
        // If the node has been cloned, return the cloned node
        if (map.containsKey(node.val)) return map.get(node.val);

        // Recursive step:
        // When a new node is visited, create the clone
        Node newNode = new Node(node.val, new ArrayList<Node>());
        map.put(node.val, newNode); // put it in the cloned nodes map

        for (Node neighbor : node.neighbors){
            newNode.neighbors.add(cloneGraph(neighbor)); // recurrence relation
        }

        // After the recursive stack, it will return the cloned node of the first node to be passed in
        return newNode;
    }
}