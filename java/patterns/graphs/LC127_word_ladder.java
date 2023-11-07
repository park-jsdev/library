// BFS - Generics Adjacency List
// Time: O(m^2 * n)
// Space: O(m^2 * n)
/**
    The adjacency list stores generic words as keys and the list of all words which could be derived from the word with
    the wild card.

    E.g. 
        "*og" -> ["dog", "fog", "log"]
        "d*g" -> ["dog", "dig", "dag"]
        "do*" -> ["dog", "dot", "doc"]

    For each word in the queue, starting with beginWord, we generate all of its possible patterns.
    The reason we made the adjacency list with the wildcards is to quickly look up all of the neighbors, but a simpler
    way is to look up every possible character at each index to see if they match any word in the word list.

    At each step of the BFS, if the current word is endWord, the shortest path has been found, and we return step,
     which gives the number of transformations
 */

 class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> adjlist = new HashMap<>();
        wordList.add(beginWord);

        // Preprocessing Adjacency list
        for (String word : wordList){
            StringBuilder string = null;
            for (int i=0; i<word.length();i++){
                string = new StringBuilder(word);
                string.setCharAt(i, '*');
                List<String> wordlist = adjlist.getOrDefault(string.toString(), new ArrayList<String>());
                wordlist.add(word);
                adjlist.put(string.toString(), wordlist);
            }
        }
        
        // BFS
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int step = 1;
        StringBuilder string = null;
        while (!queue.isEmpty()){
            step++;
            int size = queue.size();
            for (int j=0;j<size;j++){
                String str = queue.poll();

                for (int i=0;i<str.length();i++){
                    string = new StringBuilder(str);
                    string.setCharAt(i, '*');
                    for (String pat : adjlist.get(string.toString())){
                        if (pat.equals(endWord)){
                            return step;
                        }
                        if (visited.contains(pat)){
                            continue;
                        }
                        queue.offer(pat);
                        visited.add(pat);
                    }
                }
            }
            // step++;
        }
         return 0;
    }
}