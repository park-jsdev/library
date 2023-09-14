// Min Heap Solution
// Time: O(n logn)
// Space: O(n)
/**
    Note the comparator and heap implementation.
 */

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->
            Integer.compare(
                    (a[0]*a[0]+a[1]*a[1]),
                    (b[0]*b[0]+b[1]*b[1])
                )
        ); // min heap, note the comparator

        for (int[] point : points){
            minHeap.offer(point);
        }

        int[][] res = new int[k][2];
        for (int i=0;i<k;i++){
            int[] cur = minHeap.poll();
            res[i][0] = cur[0];
            res[i][1] = cur[1];
        }
        return res;
    }
}