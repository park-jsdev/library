// 1 Min Heap Optimal Solution
// Time: Worst case O(nlogn), Heap creation O(n), Pop O(logn)
// Space: O(n)
/**
    We use a min heap because in this case we only need to add elements to the stream.
    Therefore, a number in the stream which is less than one being added will never be
    the kth largest again, meaning we only need to track k elements.
 */ 


 class KthLargest {
    PriorityQueue<Integer> heap = new PriorityQueue<>(); // Min heap
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int i=0;i<nums.length;i++){
            add(nums[i]); // Use the class method
        }
    }
    
    public int add(int val) {
        if (heap.size() < k) heap.offer(val); // add vals to stream if it is smaller than k
        else if (val > heap.peek()){
            heap.remove(); // remove head (min element)
            heap.offer(val); // add new kth largest element
        }
        return heap.peek(); // return the head of the min heap which is the kth largest element in the stream
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */