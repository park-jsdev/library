class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;

        for (int num : nums){
            if (num <= first){
                first = num; // update cur smallest
            } else if (num <= second){
                second = num; // update second smallest
            } else {
                return true; // found triplet
            }
        }
        return false;
    }
}