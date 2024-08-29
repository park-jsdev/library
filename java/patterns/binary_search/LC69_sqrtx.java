class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int start = 1, end = x/2;
        while (start <= end){
            int mid = start + (end - start) / 2;
            int temp = x/mid;
            if (temp == mid){
                return mid;
            } else if (temp < mid){
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }
}