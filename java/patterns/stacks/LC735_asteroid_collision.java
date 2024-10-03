class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> s = new Stack<>();
        for (int i: asteroids){
            if (i > 0){ // i is positive
                s.push(i);
            } else {
                while (!s.isEmpty() && s.peek() > 0 && s.peek() < Math.abs(i)){ // last asteroid is positive but smaller
                    s.pop();
                }
                if (s.isEmpty() || s.peek() < 0){ // both are negative or stack is empty (no more positives)
                    s.push(i); // no collision can happen if curr is negative and next is positive so push it.
                } else if (i + s.peek() == 0){ // they are equal
                    s.pop();
                }
            }
        }
        int[] res = new int[s.size()];
        for (int i=res.length-1;i>=0;i--){
            res[i] = s.pop();
        }
        return res;
    }
}