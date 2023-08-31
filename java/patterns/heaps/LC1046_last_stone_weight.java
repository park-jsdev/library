// Max Heap Solution
// Time: O(n) + O(log n) = O(n), due to filling the heap
// Space: O(n)
// The stones are processed then placed back into the heap if they remain

class Heap {
    // Max heap implementation
    PriorityQueue<Integer> maxheap = new PriorityQueue<>((a, b) -> b - a);

    // Constructor
    public Heap(int[] arr){
        for (int i=0;i<arr.length;i++){
            maxheap.offer(arr[i]);
        }
    }

    public int smash(){
        while (maxheap.size() > 1){
            int x = maxheap.poll();
            int y = maxheap.poll();
            int z = 0;

            // if x == y they are destroyed and we move forward
            if (x != y){
                // x is greater:
                if (x > y){
                    z = x - y;
                }
                // y is greater:
                else {
                    z = y - x;
                }
            }

            // If there is a stone remaining, place it back in the heap
            if (z > 0){
                maxheap.offer(z);
            }
        }

        // After the game, there can be 1 or 0 stones remaining
        if (maxheap.size() == 1){
            return maxheap.poll();
        }
        // else 0 stones
        return 0;
    }
}

class Solution {
    public int lastStoneWeight(int[] stones) {
        // Basic Check
        if (stones.length == 1) return stones[0];

        // put all of the stones in a max heap
        Heap heap = new Heap(stones);

        // return last stone or 0
        return heap.smash();
    }
}