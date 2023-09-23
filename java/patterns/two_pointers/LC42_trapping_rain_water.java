// Two Pointers, Optimal
// Time: O(n)
// Space: O(1)
/**
    In the O(n) time and O(n) space solution, you can use 3 arrays to track maxLeft, maxRight, and min(L,R) at each index.
    The two pointers solution optimizes by computing at each step, as we only need to track the max up to that point,
    as the smaller of the two will be the limiting factor.

    The intuition is that we are pooling from the outside to the center, and subtracting elevations from the area.
    If we assume that water will not spill out (we simply consider the area), it makes sense geometrically.
    We also get the area by tracking how much water is at each point (index).

    The crux move is that we track the water at each index, only need l_max and r_max up to that point,
    and that we subtract elevation from the water at each step.
    The key implementation detail is the two pointers algorithm.
 */

class Solution {
    public int trap(int[] height) {
        if (height.length == 0) return 0;

        int l = 0, r = height.length-1;
        int left_max = height[l], right_max = height[r];
        int res = 0;

        while (l < r){
            if (left_max < right_max){
                l++;
                left_max = Math.max(left_max, height[l]);
                res += left_max - height[l];
            } else {
                r--;
                right_max = Math.max(right_max, height[r]);
                res += right_max - height[r];
            }
        }
        return res;
    }
}