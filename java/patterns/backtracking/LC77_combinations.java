// Backtracking
// Time: O(nk)
// Space: O(k)

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        backtrack(res, comb, 1, n, k);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> comb, int start, int n, int k){
        if (comb.size() == k){ // if the combination reaches size k, a valid combination is formed. Add to res.
            res.add(new ArrayList<>(comb));
            return;
        }

        for (int num=start;num<=n;num++){ // iterate start to n, the above will handle combinations of size k
            // Take
            comb.add(num);
            backtrack(res, comb, num+1, n, k);
            // Undo
            comb.remove(comb.size()-1);
        }
    }
}