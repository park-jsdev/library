// Time: O(n)
// Space: O(1)

class Solution {
public:
    int rob(vector<int>& nums) {
        if (nums.size() == 1) return nums[0];

        int a = 0, b = 0, c = 0;
        a = nums[0]; // i-2
        b = max(a, nums[1]); // i-1
        if (nums.size() == 2) return b;

        for (int i=2;i<nums.size();i++){
            c = max(a + nums[i], b);
            a = b;
            b = c;
        }
        return c;
    }
};