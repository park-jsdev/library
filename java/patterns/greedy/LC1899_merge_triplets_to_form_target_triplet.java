// Greedy
// Time: O(n)
// Space: O(1)
/**
    The key intuition here is to try to see if the target values exist in any of the triplets, then if we find that they do at
    the end of the loop, we return true. We need to understand from the problem definition that a triplet containing a value
    above the target value can no longer be considered for merging (even if the other values are below target).

    The crux is to define the condition of the loop as: if any of the values of that triplet are above the target,
    it is no longer considered, and we continue (skip) the iteration. Else: we set the greedy array as the max between
    the curr max and the triplet being considered.

    In this way, we greedily increment the values until they either reach or surpass the target, and our return statement
    at the end checks the equality.
 */

 class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int[] greedy = new int[3];
        for (int i=0;i<triplets.length;i++){
            if (triplets[i][0] > target[0] ||
                triplets[i][1] > target[1] ||
                triplets[i][2] > target[2]
            ){
                continue;
            } else {
            greedy[0] = Math.max(greedy[0], triplets[i][0]);
            greedy[1] = Math.max(greedy[1], triplets[i][1]);
            greedy[2] = Math.max(greedy[2], triplets[i][2]);
            }
        }
        return greedy[0] == target[0] && greedy[1] == target[1] && greedy[2] == target[2];
    }
}