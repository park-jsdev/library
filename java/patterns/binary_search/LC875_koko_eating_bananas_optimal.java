// Binary Search with template, speed optimal
// Time: O(n) + O(n) + O(n log(maxpile)) = O(n log(maxpile))
// Space: O(1)
/**
    The crux move is binary search on speed.
    Key implementation details are: binary search template with feasible function, ceiling division inside feasible.
    With this solution, totalHours requires a long datatype to prevent overflow.
 */

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int speed = 0;
        for (int pile : piles){
            speed = Math.max(pile, speed);
        }
        
        int l = 1, r = speed; // binary search params
        // l should be initialized at 1 to prevent division by 0, and it is impossible to eat at speed 0.

        while (l <= r){
            int mid = l + (r - l)/2;
            if (feasible(mid, piles, h)){
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    boolean feasible(int speed, int[] piles, int h){
        long totalHours = 0; // note long to prevent overflow
        for (int pile : piles){
            totalHours += (pile + speed - 1) / speed; // This is a way to compute the ceiling of pile/speed, aka ceiling.
        } // now have the total hours spent

        return totalHours <= h;
    }
}