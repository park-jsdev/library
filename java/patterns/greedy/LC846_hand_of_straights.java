// Greedy - Min Heap
// Time: O(n^2), for building the heap and then searching each element
// Space: O(n) for the heap
/**
    There are several solutions, but the min heap is intuitive. You can also sort the array and then use a HashMap which has
    optimal time complexity.

    The crux is to apply the greedy algorithm by starting at the minimum (as it must be the start of each combination,
    given that there is a a count remaining of that value), and that we assume that the smallest card is a part of the
    final set. Hence, if we encounter a non-consecutive value after the smallest card, we immediately return false.

    The key implementation detail is the priority queue, and incrementing it while it is empty.
    We exhaust the heap until it is empty, and for each min value, we peek the next consecutive numbers up till the groupSize.
    IF this condition is broken, we return false immediately.
 */

 class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // Basic Check, see if the hand is divisible by the group size
        if (hand.length % groupSize != 0) return false;

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap
        for (int card : hand) pq.add(card);
        while (!pq.isEmpty()){
            int smallest = pq.peek();
            for (int i=0;i<groupSize;i++){ // For each "smallest" we look for the consecutive values up till groupSize
                if (!pq.contains(smallest + i)) return false; // If there is a "hole" in the set, then we immediately return false
                else pq.remove(smallest + i); // we remove them from consideration if found
            }
        }
        return true; // if we escape the loop then it must be true
    }
}