// Linked List
// Time: O(1)
// Space: O(n)
/**
    The crux is to design a customized hash map, where the values are pointers to nodes in a doubly linked list, an internal
    data structure that we use to maintain the LRU condition.

    The key implementation details are to modify the hashmap to use pointers to nodes as values, and design the internal DLL.
 */

class LRUCache {
    private Map<Integer, Node> cache;
    private int capacity;

    // We need the pointers for the DLL to implement the insertion/deletion
    private Node left;
    private Node right;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        // left = LRU, right = most recent
        this.left = new Node(0,0); // dummy node
        this.right = new Node(0,0); // dummy node
        this.left.next = this.right;
        this.right.prev = this.left;
    }
    
    // When we perform a get on a key, we need to update it to become the most recent
    public int get(int key) {
        if (cache.containsKey(key)){
            remove(cache.get(key));
            insert(cache.get(key));
            return cache.get(key).val;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)){
            remove(cache.get(key));
        }
        cache.put(key, new Node(key, value));
        insert(cache.get(key));

        if (cache.size() > capacity){
            // remove from list and delete the LRU from the hashmap
            Node lru = this.left.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }
    
    // Remove node from list
    public void remove(Node node){
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    // Insert node at right
    public void insert(Node node){
        Node prev = this.right.prev;
        Node next = this.right;
        prev.next = node;
        next.prev = node;
        node.next = next;
        node.prev = prev;
    }

        private class Node {
        private int key;
        private int val;

        Node next;
        Node prev;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */