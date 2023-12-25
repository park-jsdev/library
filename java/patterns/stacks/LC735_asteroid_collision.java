class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> s = new Stack<>();
        int n = asteroids.length;

        for (int i=0;i<n;i++){
            if (asteroids[i] > 0 || s.isEmpty()){ // positive or first
                s.push(asteroids[i]);
            } else { // negative asteroids
                while (!s.isEmpty() && s.peek() > 0 && s.peek() < Math.abs(asteroids[i])){
                    s.pop(); // if there is a positive at the top which is smaller, pop it
                }
                if (s.isEmpty() || s.peek() < 0){ // if empty or 2 negatives
                    s.push(asteroids[i]);
                } else if (asteroids[i] + s.peek() == 0){ // if they are equal
                    s.pop();
                }
            }
        }
        
        int[] res = new int[s.size()];
        
        for (int i=s.size()-1;i>=0;i--){
            res[i] = s.pop();
        }
        return res;
    }
}