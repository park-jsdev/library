// Time: O(k (n choose k). Amortized to O(n^k) if k is a constant.
// Space: O(k (n choose k). Amortized to O(n^k) if k is a constant.

class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        search(new ArrayList<>(), 1, k, n);
        return res;
    }

    private void search(List<Integer> li, int start, int k, int n){
        if (li.size() == k){
            res.add(new ArrayList<>(li));
            return;
        }
    
        for (int i=start;i<=n;i++){
            li.add(i); // take
            search(li, i+1, k, n); // recursive call with next start
            li.remove(li.size()-1); // not take
        }
    }
}