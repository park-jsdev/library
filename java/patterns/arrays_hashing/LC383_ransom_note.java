// HashMap
// Time: O(n)
// Space: O(n)

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i=0;i<magazine.length();i++){
            Character c = magazine.charAt(i);
            if (!map.containsKey(c)){
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }

        for (int i=0;i<ransomNote.length();i++){
            Character c = ransomNote.charAt(i);
            if (!map.containsKey(c) || map.get(c) <= 0) return false;
            else {map.put(c, map.get(c) - 1);}
        }
        return true;
    }
}