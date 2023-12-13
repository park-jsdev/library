// Hashing
// Time: O(n)
// Space: O(3n) = O(n)
/**
    The crux is that we use 2 hashmaps to perform the bijection. 
    The reason to use 2 hashmaps is to check for unique mappings in either direction.
 */

 class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()){
            return false;
        }

        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();

        for (int i=0;i<pattern.length();i++){
            char c = pattern.charAt(i);
            String word = words[i];

            if (!charToWord.containsKey(c)){
                charToWord.put(c, word);
            }

            if (!wordToChar.containsKey(word)){
                wordToChar.put(word, c);
            }

            if (!charToWord.get(c).equals(word) || !wordToChar.get(word).equals(c)){
                return false;
            }
        }

        return true;
    }
}