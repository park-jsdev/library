class SmallestInfiniteSet {
    PriorityQueue<Integer> minHeap;
    HashSet<Integer> set;

    public SmallestInfiniteSet() {
        minHeap = new PriorityQueue<>();
        set = new HashSet<>();
        for (int i=1;i<=1000;i++){
            minHeap.add(i);
            set.add(i);
        }
    }
    
    public int popSmallest() {
        if (set.contains(minHeap.peek())){
            set.remove(minHeap.peek());
        }
        return minHeap.poll();
    }
    
    public void addBack(int num) {
        if (!set.contains(num)){
            set.add(num);
            minHeap.add(num);
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */