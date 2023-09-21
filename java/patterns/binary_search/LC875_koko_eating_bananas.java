// Binary Search
// Time: O(n) + O(log n) = O(n), due to finding the max of the pile
// Space: O(1) as we use a constant amount of variables
/**
    The crux move is that we apply binary search on the speed, rather than an index location.
    The key implementation details are the general binary search formula, and the hours (feasibility) computation.
 */

 class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // Initialize boundaries
        int l = 0, r = 1;
        for (int pile : piles){
            r = Math.max(r, pile); // we look for the greatest value, not index
        }

        // Binary Search
        while (l < r){
            int mid = l + (r - l)/2;
            int hours = 0;
            
            // hours calculation
            for (int pile : piles){
                hours += Math.ceil((double) pile / mid);
            }

            // Search with hours as the search param
            if (hours <= h){
                r = mid;
            }
            if (hours > h){
                l = mid + 1;
            }
        }
        return r;
    }
}