class Solution {
    public int hIndex(int[] citations) {
        if (citations.length == 1){
            return citations[0] > 0 ? 1 : 0; 
        }

        int hIndex = 0;

        // Sort the array in desc order
        Arrays.sort(citations);
        for (int i=0;i<citations.length/2;i++){
            int temp = citations[i];
            citations[i] = citations[citations.length-1-i];
            citations[citations.length-1-i] = temp;
        }

        for (int i=0;i<citations.length;i++){
            if (citations[i] > i){
                hIndex++;
            } else {
                return hIndex;
            }
        }
        return hIndex;
    }
}