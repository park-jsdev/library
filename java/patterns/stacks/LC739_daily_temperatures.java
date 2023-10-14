// Stack - Monotonic Stack
// Time: O(n)
// Space: O(n)
/**
    The crux is that we need to create a lagging pointer (fast and slow approach), we track the indices in our answers array,
    but lookup in the temperatures array. This is a general concept in many problems. Because we cannot know the future before
    observing it, we search the stack for previous elements that we stored upon encountering a warmer temperature.

    The key implementation detail is the stack operation, and the inner stack operation to track the prev indices.

    While it may seem that the inner while loop to check "behind" would cause an O(n^2) time, it is still O(n), as we add
    each element to the stack once, and remove them once.

    We use the Monotonic Stack general formula on a wide range of similar problems.
 */

 class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length]; // the default values are 0, handling the end days which have no warmer temps.
        Stack<Integer> stack = new Stack<>();
        for (int curr=0;curr<temperatures.length;curr++){
            while (!stack.isEmpty() && temperatures[curr] > temperatures[stack.peek()]){
                int prev = stack.pop();
                ans[prev] = curr - prev;
            }
            stack.add(curr);
        }
        return ans;
    }
}