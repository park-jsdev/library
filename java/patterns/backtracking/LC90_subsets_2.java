// Backtracking
// Time: O(n 2^n), as copying the list to res costs O(n)
// Space: O(n 2^n) 
/**
    The crux move is to skip iterations with duplicates.
    The key implementation details are the backtracking helper function, sorting the array
 */

 class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        subSet(nums, 0, res, list);
        return res;
    }

    public void subSet(
        int[] nums,
        int idx,
        List<List<Integer>> res,
        List<Integer> list
    ) {
        res.add(new ArrayList<>(list));

        for (int i=idx;i<nums.length;i++){
            // skip the duplicate elements, they have been sorted so we can compare i == i-1
            if (i > idx && nums[i] == nums[i-1]) continue;
            list.add(nums[i]);
            subSet(nums,i+1,res,list);
            list.remove(list.size()-1); // remove the last, backtrack
        }
    }

}