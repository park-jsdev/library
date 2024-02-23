class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene)) return 0;

        // First create hashset of bank for O(1) lookup
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // graph representation
        // each valid node is a gene in the bank
        char[] charSet = new char[]{'A', 'C', 'G', 'T'};

        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(startGene);
        visited.add(startGene);

        // BFS
        // stop condition is matches a string in the bank
        while (!q.isEmpty()){
            int size = q.size();
            while (size-- > 0){
                String curr = q.poll();
                if (curr.equals(endGene)) return level; // if endgene is found, return level

                char[] currArray = curr.toCharArray();
                // iterate each char in the current gene
                for (int i=0;i<currArray.length;i++){
                    char old = currArray[i]; // hold the original char at this level
                    for (char c: charSet){ // iterate the 4 valid chars
                        currArray[i] = c;
                        String next = new String(currArray); // try each mutation
                        if (!visited.contains(next) && bankSet.contains(next)){ // if valid, add to the q
                            visited.add(next);
                            q.offer(next);
                        }
                    }
                    currArray[i] = old; // set the gene back to the original state after adding all neighbors
                }
            }
            level++; // iterate lvl
        }
        return -1;
    }
}