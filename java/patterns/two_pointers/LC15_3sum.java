// 3 Pointers with HashSet
// Time: O(n^2)
// Space: O(n)
/**
    The crux is to use the HashSet (you can use hashset of lists), then you can convert the hashset to a list to return.
    Also note iterating the left pointer in the while loop to continue after condition is met.
 */

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>(); // HashSet of List

        for (int i=0;i<nums.length-2;i++){
            int l=i+1;
            int r=nums.length-1;
            // how to check for duplicates
            while (l<r){
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0 && i != l && i != r){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    res.add(list);
                    l++; // after adding inner list to res, we need to iterate l to continue the loop
                } 
                else if (sum < 0) l++;
                else if (sum > 0) r--;
            }
        }
        return new ArrayList<>(res); // Return the HashSet as a List
    }
}