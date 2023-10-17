// Stack - Monotonic Stack
// Time: O(n)
// Space: O(n)
/**
    The crux is to maintain the left limit, right limit, and height limit, and use a monotonic stack to store the previous.
    We compute the area only when the current limits are broken and set new limits.

    Whenever we encounter a shorter bar, we know that many of the taller bars before it can't extend their rectangles any further,  
    and we compute their areas accordingly. We look for the limits to the right and the left, then compute area upon finding them.

    The idea is that the stack tries to maintain an increasing order, but when it encounters a bar that breaks this order (i.e., a 
    shorter bar than the one on the top of the stack), it starts popping all the bars that are taller than the current bar. Each of 
    these popped bars has their potential rectangle limited by the current bar, so we calculate the area for each of them.

    Consider the left limit, right limit, and height limit:
    - Increasing bar heights, keep pushing on to the stack. Currently the left limit is index 0, and there is no right limit yet.
    - Encounter a smaller bar: then a right limit is found, and we need to compute the area up that point (exclusive).
    We keep popping until a bar smaller than the current one is found, and compute the area at each step.
    The smaller bar in the stack is the new left limit.
    Due to the increasing order, the right limit in each previous element is still the current index (exclusive).
    When we find the new left limit, we add the current bar, then continue the for loop.
    - The main loop tries to maintain a left limit, right limit, and an increasing order stack, then computes the area
    only when the conditions are broken, or upon reaching the end. At the end, the remaining rectangles will have their right
    limit as the end of the array, and the left limit as their starting index, and no limiting height.

    We store the index and the height in Pairs, to store in our monotonic stack. The area can be computed with b*h, so
    i-index will represent the b.
 */

class Solution {
    public int largestRectangleArea(int[] heights) {
        int max_area = 0, n = heights.length;
        int start;
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        for (int i=0;i<heights.length;i++){
            int curr_h = heights[i]; // compare at each index the current height against the prev in the stack
            start = i; // Store the current index
            while (!stack.isEmpty() && stack.peek().getValue() > curr_h){
                // We pop in the stack until we encounter a prev bar that is smaller than the current
                // Any bar in the stack that is taller than the current, we compute the max area
                Pair<Integer, Integer> pair = stack.pop();
                int idx = pair.getKey();
                int h = pair.getValue();
                max_area = Math.max(max_area, h * (i - idx)); // the key is that we do not consider the curr index
                start = idx;
            }
            stack.push(new Pair(start, curr_h)); // Push the new bar
        }

        // Compute the remaining, check for max area. These bars remaining extend to the right, as there were no limits.
        while (!stack.isEmpty()){
            Pair<Integer, Integer> pair = stack.pop();
            int idx = pair.getKey();
            int h = pair.getValue();
            max_area = Math.max(max_area, h * (n - idx)); // if the element is remaining until the end, the width will be len - index
        }
        return max_area;
    }
}