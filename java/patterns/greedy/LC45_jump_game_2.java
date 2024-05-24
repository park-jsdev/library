// Time: O(n)
// Space: O(1)

class Solution {
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;

        int jumps = 0, curEnd = 0, reach = 0, n = nums.length-1;

        for (int i=0;i<n;i++){
            reach = Math.max(reach, i + nums[i]);

            if (i == curEnd){
                jumps++;
                curEnd = reach;

                if (curEnd >= n){
                    break;
                }
            }
        }
        return jumps;
    }
}