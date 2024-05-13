// Time: O(nLogn) due to sorting
// Space: O(1)

class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int l = 0, r = nums.length-1;
        while (l<=r){
            int mid = (l+r)/2;
            if (nums[mid] > mid) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }
}