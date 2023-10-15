// Stack - Monotonic Stack
// Time: O(nlogn), due to sorting the array
// Space: O(n) due to the stack
/**
    The crux is to represent the cars as vectors or pairs of coordinates (pos, speed), then use a stack to search
    right to left, and remove all the cars which would reach the end faster than one on the right (as they would be combined).
    We compute this condition with v = delta d/delta t, and delta d is the target - the position, and the t_i would be the speed
    at the position (given by input):
    
    v = d*t, so t = d/v

    We return the size of the stack remaining, as that equals the number of fleets at the end.

    The key implementation details are:
    - The combining of the input coordinates into a 2D array combine,
    - Sorting the 2D array - use the lambda expression,
    - Search the array right to left, Stack operation.

    This formula is called the Monotonic Stack.
 */

class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length == 1) return 1;
        Stack<Double> stack = new Stack<>();
        int[][] combine = new int[position.length][2];
        for (int i=0;i<position.length;i++){
            combine[i][0] = position[i];
            combine[i][1] = speed[i];
        }

        Arrays.sort(combine, (a, b) -> Integer.compare(a[0], b[0])); // Sorts and compares based on the first element (position)

        for (int i=combine.length-1;i>=0;i--){
            double currTime = (double) (target - combine[i][0]) / combine[i][1]; // t = d/v
            if (!stack.isEmpty() && currTime <= stack.peek()){
                continue;
            } else {
                stack.push(currTime);
            }
        }
        return stack.size();
    }
}