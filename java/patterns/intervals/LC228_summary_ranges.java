// Intervals
// Time: O(n)
// Space: O(n)
/**
    1 pass and track the start and end of the previous range. Put the range into res when encountering a new range.
    Handle the remaining range after the loop.
 */

 class Solution {
    public List<String> summaryRanges(int[] nums) {
        // 1 pass
        ArrayList<String> res = new ArrayList<>();
        if (nums.length == 0) return res; // basic check
        int first = nums[0]; // set prev first
        int last = nums[0]; // set prev last
        String curr = ""; // begin range
        for (int i=1;i<nums.length;i++){
           if (nums[i] != last+1){ // if curr is not prev + 1
                if (last == first){
                    curr = "" + first;
                } else {
                    curr = first + "->" + last;
                }
                res.add(curr); // add prev to res
                // start new range
                curr = "";
                first = nums[i];
                last = nums[i];
           } 
           // set prev last to i
           last = nums[i];
        }
        // Remaining
        if (last == first){
            curr = "" + first;
        } else {
            curr = first + "->" + last;
        }
        res.add(curr); // add prev to res
        return res;
    }
}