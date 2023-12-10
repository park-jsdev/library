class Solution {
    public int largestAltitude(int[] gain) {
        // Initialized to 0, 0 is possible
        int max = 0; // global max
        int cur = 0; // local max

        for (int i=0;i<gain.length;i++){
            cur += gain[i];
            max = Math.max(max, cur);
        }
        return max;
    }
}