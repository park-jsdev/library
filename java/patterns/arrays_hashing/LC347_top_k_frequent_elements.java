// Arrays - Bucket Sort
// Time: O(n)
// Space: O(n)
/**
    The crux move is to use bucket sort. Instead of mapping the values as indices, we take the count of values as the indices.
    We cannot use the values themselves as indices for linear time, as they are unbounded, but the count is bounded by the length
    of the input array.

    The key implementation detail is to use the bucket with counts as indices and numbers as values, and a hashmap to count
    occurrences.

 */

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency_map = new HashMap<>();
        List<Integer> bucket[] = new ArrayList[nums.length+1];

        for (int n : nums){
            frequency_map.put(n, frequency_map.getOrDefault(n,0)+1);
        }

        for (int key : frequency_map.keySet()) {
            int frequency = frequency_map.get(key);
            if (bucket[frequency] == null){
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        
        // Populate the result array from the bucket
        int index = 0;
        int[] res = new int[k];

        for (int i = nums.length; i >= 0; i--)
            if (bucket[i] != null)
                for (int val : bucket[i]){
                    res[index++] = val;
                    if(index == k)
                        return res;
                }
        return res;
    }
}