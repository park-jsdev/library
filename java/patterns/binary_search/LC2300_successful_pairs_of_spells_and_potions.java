class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int m = spells.length;
        int n = potions.length;
        int[] pairs = new int[m];
        Arrays.sort(potions);

        for (int i=0;i<m;i++){
            int l=0, r=n-1;
            while (l <= r){
                int mid = l + (r-l)/2;
                if ((long)potions[mid] * spells[i] >= success){
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            pairs[i] = n - l; // l is the index of the first potion that meets the condition
        }
        return pairs;
    }
}