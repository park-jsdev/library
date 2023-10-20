// Sliding Window - with Monotonically Decreasing Deque
// Time: O(n)
// Space: O(k), where k is the size of the window
/**
    The crux is that we use a (monotonically decreasing) Deque to maintain the maximum in the window. As soon as we encounter
    an element that is greater than the elements to the left (in the queue already), we remove them, as they can never be the
    maximum again, as a greater number on the right exists. In this manner, the queue will be maintained in decreasing order.

    We remove the leftmost element at each iteration, outside of the left boundary of the window, given by k-1. This algorithm
    ensures that at each iteration, the maximum of the window will be the leftmost element of the window (front of the queue).
    We need to return the local min at each window position, so remember the details for storing the answers array.

    The key implementation details are:
    - The model: the answer array of size nums.length - k + 1
    - global int j
    - Deque<Integer> q = new LinkedList<>();
    - Sliding Window algorithm with q operations
    - Populating the answer: if (i >= k - 1) ans[j++] = nums[q.peekFirst()];
 */

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // Model
        int[] res = new int[nums.length - k + 1]; // We need to return the local minimum at each window position
        int j = 0;
        Deque<Integer> q = new LinkedList<>();

        // Sliding Window
        for (int i=0;i<nums.length;i++){
            if (!q.isEmpty() && q.peekFirst() < i-k+1) q.pollFirst(); // remove the leftmost element in the deque (window)
            while (!q.isEmpty() && nums[i] > nums[q.peekLast()]) q.pollLast(); // any number in the queue which is smaller
            // than the new element being considered is removed, as they will never be the maximum in the window
            q.offer(i); // we store the indices
            if (i >= k - 1) res[j++] = nums[q.peekFirst()];
        }
        return res;
    }
}