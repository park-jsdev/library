class Solution {
    public int minFlips(int a, int b, int c) {
        int res = 0, ab = a | b, equal = ab ^ c;
        for (int i=0;i<31;++i){
            int mask = 1 << i;
            if ((equal & mask) > 0){ // ith bits of a | b and c are not same, need at least 1 flip.
                // ith bits of a and b are both 1 and that of c is 0?
                res += (a & mask) == (b & mask) && (c & mask) == 0 ? 2 : 1;
            }
        }
        return res;
    }
}