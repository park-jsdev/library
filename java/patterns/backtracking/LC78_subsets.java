// Backtracking solution
// Time: The set of n elements will have 2 choices for each element, and each possibility must be generated once. Therefore, Time = O(2^n)
// Space: The output will hold 2^n subsets, but each subset can contain n elements. Therefore, Space = O(2^n * n)

/**
    The fundamental idea behind backtracking is:
    Represent as a tree > get to each leaf node > pop via recursive stack >
    From the parent of the leaf node, take each decision:
    E.g. 1. take value, 2. don't take value
    The list we return which is the subset is the bottom level of the tree
 */

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtrack(list, temp, nums, 0);
        return list;
    }

    // The idea is to have two conditions: 
    // One in which we will take the element into consideration, 
    // Second in which we won't take the element into consideration.
    private void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums, int start){
        // Base Case:
        if (start >= nums.length){ // if start has reached the end, it means that the current subset is complete
            list.add(new ArrayList<>(temp)); // Copy the current temp list with data inside, this is crucial 
        } else {
            // Recursive Step:

            // Note that there is no for loop in this implementation, we iterate via recursion
            temp.add(nums[start]); // Decision to add
            backtrack(list, temp, nums, start+1);
            temp.remove(temp.size()-1); // Decision to not add (pop)
            backtrack(list, temp, nums, start+1); // we popped the last element but still iterated the index
        }
    }
}