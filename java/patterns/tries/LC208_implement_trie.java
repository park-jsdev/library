// Trie implementation
// Time: O(n) worst case for operations
// Space: O(26 * n) = O(n), 26 child pointers per node
/**
    Tries inherently avoid collisions by design. They are efficient for prefixes, hence "Prefix Tree".
    Commonly used in word search.
    The key is the Trie Node, and each node having a size 26 array (for alphabet) for its children.
 */

class Trie {

    Node root;

    public Trie() {
        root = new Node('\0'); // dummy node
    }
    
    public void insert(String word) {
        Node curr = root;
        for (char x : word.toCharArray()){
            if (curr.children[x - 'a'] == null){ // character to 0-based index conversion
            // if it is null, then curr does not yet have a child
                curr.children[x - 'a'] = new Node(x);
            }
            curr = curr.children[x - 'a'];
        }
        // only at the end of the loop (leaf node) will we set isWord to true
        curr.isWord = true;
    }
    
    public boolean search(String word) {
        Node res = getLast(word);
        return (res != null && res.isWord);
    }

    // helper method
    public Node getLast(String word){
        Node curr = root;
        for (char x : word.toCharArray()){
            if (curr.children[x - 'a'] == null){
                return null;
            }
            curr = curr.children[x - 'a'];
        }
        return curr;
    }
    
    public boolean startsWith(String prefix) {
        Node res = getLast(prefix);
        if (res == null) return false;
        return true;
    }
}

class Node {
    char value;
    boolean isWord;
    Node[] children; // Each Node has a Node array of its children

    public Node(char val){
        this.value = val;
        this.isWord = false;
        this.children = new Node[26];
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */