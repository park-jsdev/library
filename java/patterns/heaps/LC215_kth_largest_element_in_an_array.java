// Min Heap
// Time: O(n log k)
// Space: O(n)
/**
    We maintain a min heap of size k, and at the end, the top of the min heap will be the kth largest element in the array.
    The optimal solution is the quick select method.
 */

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Min Heap
        PriorityQueue<Integer> heap = new PriorityQueue();

        for (int n : nums){
            heap.add(n);
            if (heap.size() > k){
                heap.poll(); // remove the top (smallest element)
            }
        }
        return heap.peek(); // the top of the heap will be the kth largest element
    }
}