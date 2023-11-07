// Trie
// Time: O(n^2) for the search, O(n) for building the trie
// Space: O(n)
/**

 */


 class WordDictionary {

    Node root;

    private class Node {
        char value; // the char
        boolean isWord; // signifies the last char in a word
        Node[] children; // direct children char Nodes to this char Node

        public Node(char value){
            this.value = value;
            isWord = false;
            children = new Node[26];
        }
    }

    public WordDictionary() {
        root = new Node('\0'); // dummy node
        
    }
    
    public void addWord(String word) {
        Node curr = root;

        for (int i=0;i<word.length();i++){
            char ch = word.charAt(i);

            if (curr.children[ch - 'a'] == null){
                curr.children[ch - 'a'] = new Node(ch);
            }
            curr = curr.children[ch - 'a'];
        }
        curr.isWord = true; // mark as the last char in the word
    }
    
    // DFS
    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }

    private boolean searchHelper(String word, Node curr, int index){
        for (int i=index;i<word.length();i++){
            char ch = word.charAt(i);

            if (ch == '.'){
                for (Node temp : curr.children){
                    if (temp != null && searchHelper(word, temp, i+1)){
                        return true;
                    }
                }
                return false;
            }

            if (curr.children[ch - 'a'] == null) { // if it encounters null (end of word) then returns null
                return false;
            }

            curr = curr.children[ch-'a'];
        }
        return curr.isWord; // returns true if the word is found
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */