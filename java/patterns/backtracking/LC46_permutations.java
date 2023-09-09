// Backtracking
// Time: O(n!)
// Space: O(n * n!)
/**
    Note the backtracking with the swap function, rather than popping with a stack.
 */

 class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, nums, 0);
        return res;
    }

    public void helper(List<List<Integer>> res, int[] nums, int start){
        // Base Case:
        if (start == nums.length){
            // If it reaches the end, a permutation is complete and added to the results
            List<Integer> list = new ArrayList<>();
            for (int i=0;i<nums.length;i++){
                list.add(nums[i]);
                }
            res.add(list);
            return;
        }
        
        // Recursive Step:
        for (int i=start;i<nums.length;i++){
            // Backtracking by swapping
            swap(nums, start, i); // Swap order
            helper(res, nums, start+1);
            swap(nums, start, i); // Swap back
        }
    }

    public void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}