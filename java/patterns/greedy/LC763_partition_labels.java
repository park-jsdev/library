// Greedy
// Time: O(n), 2 passes
// Space: O(1), as there can only be a max of 26 characters
/**
    The crux move is to store the last index for each char in a map, and have the while loop iterate i but dynamically
    update the current partition to extend and cover all the characters that have a last occurrence inside the partition.

    By exhaustion, after i passes j, we find a valid partition.
 */

 class Solution {
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i=0;i<s.length();i++){
            map.put(s.charAt(i), i); // this will effectively give the last index of the char by overriding
        }
        int j = 0;
        int i = 0;
        while (i < s.length()){
            int count = 0;
            j = Math.max(j, map.get(s.charAt(i))); // j tracks the last index to consider for the partition
            while (i <= j){ // this while loop extends the current partition to cover all the characters that have
                // a last occurrence inside the partition
                j = Math.max(j, map.get(s.charAt(i)));
                i++;
                count++;
            }
            // only after i passes j, we find a valid partition of size count. This is added to the result list.
            list.add(count);
        }
        return list;
    }
}