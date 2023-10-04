// Backtracking
// Time: O(2^n), in the worst case scenario, all possible combinations will be explored, and there are 2 choices at each step.
// Space: O(2^n), same as time
/**
    The crux is to use distance to target as the loop condition. The base casae is if target == 0, then it is valid.
    The key implementation detail is to sort the array first and consider prev to handle duplicates. Add a new copy
    of the sublist (ls) to the result list as to not override references.
 */

 class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> ls = new ArrayList<Integer>();
        backtrack(candidates, target, res, ls, 0);
        return res;
    }

    public void backtrack(int[] candidates,int target, List<List<Integer>> res, List<Integer> ls, int index){
        if (target == 0){ // is a valid combination
            res.add(new ArrayList(ls)); // We add a copy of the current list to the result as to not override references
        } else if (target < 0) return; // if the current combination exceeds than target, the path is rejected
        else {
            // Iterative backtracking
            for (int i=index;i<candidates.length;i++){
                if (i > index && candidates[i] == candidates[i-1]) continue; // skips the duplicates, as the list is sorted
                // the duplicates will be next to each other
                ls.add(candidates[i]); // as long as conditions are not broken, add the value to the current combination
                backtrack(candidates, target - candidates[i], res, ls, i+1); // Take the value, update d to target, index
                ls.remove(ls.get(ls.size()-1)); // Remove the last value, this is done so in the next iteration, a different
                // candidate can be added and a new combination is explored
            }
        }
    }
}