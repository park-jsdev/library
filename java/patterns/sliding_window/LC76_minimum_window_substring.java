// Sliding Window
// Time: O(n)
// Space: O(n)
/**
    This is a straightforward Sliding Window problem with Strings.

    In this implementation, we keep 1 hashmap with the target character frequencies, and we expand the 
    window to the right, trying to get the counts of the target map to 0. If they are all 0, then our conditions for the
    substring are met in the window, then we can look for the minimum length substring.

    We move the left pointer forward until the conditions are broken, and that is the local minimum and 
    potential global minimum. We repeat until the right of the window reaches the end, then have our global minimum.

    The key implementation details are:
    - Target HashMap, incrementing and decrementing
    - 2 pointers (l,r)
    - Model
    - Sliding window conditions and updating the global minimum
    - minLen = endWindow - start + 1, due to 0-based index. Think about the example of end - start + 1 = 0 - 0 + 1 = 1.
 */

 class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>(); // the map contains the char freqs in the target string

        for (char x : t.toCharArray()){
            map.put(x, map.getOrDefault(x, 0) + 1); // default value is 0
        }
        
        // Model
        int matched = 0;
        int start = 0; // left limit
        int minLen = s.length() + 1; // we set the default as greater than the length of the string, it will return blank if unchanged
        int subStr = 0;
        
        for (int endWindow = 0; endWindow < s.length(); endWindow++){ // right limit
            char right = s.charAt(endWindow);
            if (map.containsKey(right)){
                map.put(right, map.get(right) - 1); // we track the diff (distance) to 0 in the window and the target string
                if (map.get(right) == 0) matched++;
            }

            while (matched == map.size()){ // when matched = the size of the target map, that is the valid condition,
                // we then move the left pointer forward until the condition is broken, to find potential minimum substrings.
                if (minLen > endWindow - start + 1){ // we check if the current portion of the window is smaller than the
                    // global minimum
                    minLen = endWindow - start + 1; // we need to add 1 due to 0-based index
                    subStr = start;
                }
                char deleted = s.charAt(start++); // we get the char at the left pointer, then check if it exists in the target
                if (map.containsKey(deleted)){
                    if (map.get(deleted) == 0) matched--; // if so, we need to decrement it. There could be more than 1, so
                    // decrement matched only if the deleted char's freq in the target map reaches 0
                    map.put(deleted, map.get(deleted) + 1); // As we previously decremented the 'deleted' character, we
                    // increment it again once we remove it from the window
                }
            }
        }
        return minLen > s.length() ? "" : s.substring(subStr, subStr + minLen); // we return either blank for none found
        // or the substring between the subStr start and the minLen
    }
}