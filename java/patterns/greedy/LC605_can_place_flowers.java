class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;

        // Basic Check
        if (n == 0) return true;
        if (flowerbed.length == 1){ // l=1
            return flowerbed[0] == 0;
        }
        if (flowerbed.length == 2){ // l=2
            if (n >= 2) return false;
            return (flowerbed[0] == 0 && flowerbed[1] == 0);
        }

        for (int i=0;i<flowerbed.length;i++){
            if (i == 0){ // start edge case
                if (flowerbed[0] == 0 && flowerbed[i+1] == 0){
                    flowerbed[0] = 1;
                    count++;
                }
            } else if (i == flowerbed.length-1){ // end edge case
                if (flowerbed[flowerbed.length-1] == 0 && flowerbed[flowerbed.length-2] == 0){
                    flowerbed[flowerbed.length-1] = 1;
                    count++;
                }
            } else {
                if (flowerbed[i-1] == 0 && flowerbed[i] == 0 && flowerbed[i+1] == 0){
                    flowerbed[i] = 1; 
                    count++;
                }
            }
        }
        return count >= n;
    }
}