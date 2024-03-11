// Time: O(n)
// Space: O(n)

class Solution {
    public int findShortestSubArray(int[] nums) {
        if (nums.length == 1) return 1;
        int degree = 0;

        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (!map.containsKey(nums[i])){
                // [frequency, firstOccurrence, lastOccurence]
                map.put(nums[i], new int[]{1, i, i});
            } else {
                int[] info = map.get(nums[i]);
                info[0]++;
                info[2] = i;
                map.put(nums[i], info);
            }
            degree = Math.max(degree, map.get(nums[i])[0]);
        }
        int minLength = Integer.MAX_VALUE;
        for (int[] value : map.values()){
            if (value[0] == degree){
                minLength = Math.min(minLength, value[2] - value[1] + 1);
            }
        }

        return minLength;
    }
}