class FileSystem {

    private static class Node {
        private final String name;
        private final Map<String, Node> nodeByName = new TreeMap<>();
        private final StringBuilder content = new StringBuilder();
        private boolean isFile;

        Node(final String name){
            this.name = name;
        }
        private boolean isFile(){
            return isFile;
        }
    }

    private final Node root = new Node("");

    public FileSystem() {
        
    }
    
    public List<String> ls(String path) {
        var node = traversePath(path);
        return node.isFile() ? List.of(node.name) : List.copyOf(node.nodeByName.keySet());
    }
    
    public void mkdir(String path) {
        traversePath(path);
    }
    
    public void addContentToFile(String filePath, String content) {
        var node = traversePath(filePath);
        node.isFile = true;
        node.content.append(content);
    }
    
    public String readContentFromFile(String filePath) {
        return traversePath(filePath).content.toString();
    }

    private Node traversePath(final String path){
        var current = root;
        var parts = path.split("/");
        var i = new int[]{1};

        while(i[0] < parts.length){
            current = current.nodeByName.computeIfAbsent(parts[i[0]], k -> new Node(parts[i[0]]));
            i[0]++;
        }
        return current;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */