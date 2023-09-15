// HashMap
// Time: O(n)
// Space: O(n) worst case, as all elements may need to be stored in the hashmap but the last one.
/**
    Tricky implementation, as you need to compare values, but track and return indices in O(n) time.
    Therefore, cannot do a sort.
    The crux is the computation: t - nums[i] = d > check if d exists in hashmap > if yes, get the index value; if not,
    store the numerical value and the index.
 */

 class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        // Have a hashmap containing diffs and index
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            int d = target - nums[i];
            if (map.containsKey(d)){
                res[0] = map.get(d); // Get the index associated with the diff
                res[1] = i; // current index
                return res;
            }
            map.put(nums[i],i); // put the number and its index
        }
        return res;
    }
}