// Two Pointers Solution
// Time: O(n)
// Space: O(n)

// The input array is sorted and 1-indexed. Exactly one solution is guaranteed.
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length-1;

        while (l < r){
            int curr = numbers[l] + numbers[r];

            if (curr > target){
                r--;
            } else if (curr < target){
                l++;
            } else {
                return new int[]{l+1,r+1};
            }
        }
        return new int[]{};
    }
}