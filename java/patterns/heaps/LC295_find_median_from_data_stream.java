// Heaps - 2 Heaps
// Time: O(n), operations are O(1) except for removals.
// Space: O(n)
/**
    The crux is that we maintain 2 heaps, as the median in this context will be the (smallest + largest) / 2 if even values,
    and if odd, we return the true median. The heaps allow us to both retrieve the smallest and largest, and the middle
    value in the case of odd number of elements in O(1) time.

    The key implementation detail is the maintenance of the heaps and inserting in sorted order.

    We must maintain the heap sizes within 1 of each other, and ensure that the values of the small heap are smaller than
    the values of the large heap. We use peek and poll to maintain them during addNum.
 */

class MedianFinder {
    private Queue<Integer> smallHeap; // small elements - maxHeap
    private Queue<Integer> largeHeap; // large elements - minHeap

    public MedianFinder() {
        smallHeap = new PriorityQueue<>((a,b) -> b - a);
        largeHeap = new PriorityQueue<>((a,b) -> a - b);
    }
    
    public void addNum(int num) {
        smallHeap.add(num);
        if (
            smallHeap.size() - largeHeap.size() > 1 ||
            !largeHeap.isEmpty() &&
            smallHeap.peek() > largeHeap.peek()
        ) {
            largeHeap.add(smallHeap.poll());
        }
        if (largeHeap.size() - smallHeap.size() > 1){
            smallHeap.add(largeHeap.poll());
        }
        
    }
    
    public double findMedian() {
        if (smallHeap.size() == largeHeap.size()){
            return (double) (largeHeap.peek() + smallHeap.peek()) / 2;
        } else if (smallHeap.size() > largeHeap.size()){
            return (double) smallHeap.peek();
        } else {
            return (double) largeHeap.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */