// HashMap
// Time: O(n)
// Space: O(1)

class Solution {
    public int majorityElement(int[] nums) {
        int m = nums.length/2;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            if (!map.containsKey(num)){
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
            if (map.get(num) > m) return num;
        }
        return -1;
    }
}