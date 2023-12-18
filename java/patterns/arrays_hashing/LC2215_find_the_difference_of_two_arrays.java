// Hashing - Symmetric Difference
// Time: O(n)
// Space: O(n)

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> res = new ArrayList<>();
        HashSet<Integer> s1 = new HashSet<>();
        HashSet<Integer> s2 = new HashSet<>();

        for (int x : nums1){
            s1.add(x);
        }

        for (int y : nums2){
            s2.add(y);
        }

        List<Integer> li1 = new ArrayList<>(s1);
        List<Integer> li2 = new ArrayList<>(s2);
        li1.removeAll(s2);
        li2.removeAll(s1);
        res.add(li1);
        res.add(li2);
        return res;
    }
}