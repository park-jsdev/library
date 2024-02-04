class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<Integer>(), k, 1, n);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> comb, int k, int start, int n){
        if (comb.size() > k) return;
        if (comb.size() == k && n == 0){
            List<Integer> li = new ArrayList<Integer>(comb);
            res.add(li);
            return;
        }
        for (int i=start;i<=9;i++){
            comb.add(i);
            backtrack(res, comb, k, i+1, n-i);
            comb.remove(comb.size()-1);
        }
    }
}