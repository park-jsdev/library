/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Trees - DFS
// Time: O(n)
// Space: O(n)
/**
    This problem is more of a design problem, and demonstrates an application of Tree concepts.
    The optimal and elgant solution is to use DFS for serialization and BFS for deserialization.

    The crux move is to understand the structure of a tree in string serial representation. That is - we recursively look
    to the right for 2 null nodes, in which case that is a leaf node and the base case. Until that point, we consider each
    node a child node. 
    
    There are 3 cases:
    - 1st element (root node): the next 2 elements (2,3) are the children of the root node.
    - 2nd element (left child of root node): the 2 elements starting from i+1 are its children.
    - 3rd element (right child of root node), and each node after: the next 2 elements starting from i+2 are its children.

    Key implementation details: 
        global variable,
        2 DFS functions, serialize and deserialize,
        String.join(",", list),
        String[] tokens = data.split(","),
 */

 public class Codec {

    private int i;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> list = new ArrayList<>();
        serializeDFS(root, list);

        return String.join(",", list);
    }

    // DFS
    private void serializeDFS(TreeNode root, List<String> list){
        // Base Case
        if (root == null){
            list.add("N");
            return;
        }

        // Recursive Step - Preorder
        list.add(String.valueOf(root.val));
        serializeDFS(root.left, list);
        serializeDFS(root.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tokens = data.split(",");
        return deserializeDFS(tokens);
    }

    private TreeNode deserializeDFS(String[] tokens){
        String token = tokens[this.i];
        if (token.equals("N")){
            this.i++;
            return null;
        }
        var node = new TreeNode(Integer.parseInt(token));
        this.i++;
        node.left = deserializeDFS(tokens);
        node.right = deserializeDFS(tokens);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));