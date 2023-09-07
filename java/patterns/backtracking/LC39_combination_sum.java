// Backtracking
// Time: O(2^t), where t = target
// Space: O(t/min(candidates) + n)
/**
    Combinations do not have repeating sets (even if they are re-ordered).
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> curr = new ArrayList();
        backtrack(candidates, target, res, curr, 0);
        return res;
    }

    public void backtrack(
        int[] candidates,
        int target,
        List<List<Integer>> res,
        List<Integer> curr,
        int index
    ){
        // Base cases:
        if (target == 0){
            res.add(new ArrayList(curr));
            // If the (distance to) target is less than 0 (we have gone past the target) then we exit the recursive call.
        } else if (target < 0 || index >= candidates.length){
            return;
        } else {
            // Backtracking:
            curr.add(candidates[index]); // accept
            backtrack(candidates, target - candidates[index], res, curr, index); // recursive step, we reduce the target by the num
            curr.remove(curr.get(curr.size() - 1)); // reject (undo)
            backtrack(candidates, target, res, curr, index + 1); // skip then recursive step, target remains the same
        }
    }
}