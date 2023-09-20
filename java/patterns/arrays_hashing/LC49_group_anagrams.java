// Hashmap with ASCII character/frequency array
// Time: O(m x n)
// Space: O(m)
/**
    The crux move is to map the char count to a hash map's key. In this way, we group anagrams by using hashmap's functionality.
    The key implementation details are the char frequency table, ASCII to index, table as the hashmap key string, 
    and the hashmap's inner list.
 */

 class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Basic Check
        if (strs == null || strs.length == 0) return new ArrayList<>();

        // map the char count to list of anagrams:
        // K = the String value of the char frequency array
        // V = the List of anagrams
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs){
            char[] ca = new char[26]; // the char frequency array (26 characters in the alphabet)
            for (char c : s.toCharArray()) ca[c-'a']++; // method to map ASCII to index
            String keyStr = String.valueOf(ca); // Get the string value of the array (think hashcode)
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>()); // if no associated char count, create a new list
            map.get(keyStr).add(s); // else get the list associated with the char count
        }
        return new ArrayList<>(map.values()); // at the end, get the hash map's values as an array list.
    }
}