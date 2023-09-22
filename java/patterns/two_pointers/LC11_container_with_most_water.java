// Two pointers
// Time: O(n)
// Space: O(1)
/**
    This is a straight-forward application of the two-pointer formula.
    The crux move is to know to apply 2 pointers, and checking the area of the container such that the water
    does not spill out as a condition.
    The key implementation detail is the two pointers algorithms, and the area computation.
 */

class Solution {
    public int maxArea(int[] height) {
        int res = 0;
        int l = 0, r = height.length - 1;
        
        while (l < r){
            // The area of a rectangle is given by base (r-l) * h
            int area = (r - l) * Math.min(height[l], height[r]); // We need to take the min height of l and r for the container
            res = Math.max(res, area);

            // if one side is smaller than the other, shift it inward, if they are the same, shift any (right in this case)
            if (height[l] < height[r]){
                l++;
            } else {
                r--;
            }
        }
        return res;
    }
}