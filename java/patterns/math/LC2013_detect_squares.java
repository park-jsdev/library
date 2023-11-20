// Math
// Time: O(n)
// Space: O(n)
/**
    We save all coordinates to a list, and count the frequencies of each coordinate in a hashmap.
    In the count method, we check if each coordinate forms a square diagonal with the query point, if it does,
    we use the count of the other 2 coordinates of the square to calculate the total.

    The crux is this line: if (px == x || py == y || (Math.abs(px - x) != Math.abs(py - y))) continue;
 */

class DetectSquares {
    List<int[]> coordinates;
    Map<String, Integer> counts;

    public DetectSquares() {
        coordinates = new ArrayList<>();
        counts = new HashMap<>();
    }
    
    public void add(int[] point) {
        coordinates.add(point);
        String key = point[0] + "@" + point[1];
        counts.put(key, counts.getOrDefault(key, 0) + 1);
    }
    
    public int count(int[] point) {
        int sum = 0, px = point[0], py = point[1];
        for (int[] coordinate : coordinates){
            int x = coordinate[0], y = coordinate[1];
            // Checks if same row or column, or if the width and heights are same or not
            if (px == x || py == y || (Math.abs(px - x) != Math.abs(py - y))) continue;
            sum += counts.getOrDefault(x + "@" + py, 0) * counts.getOrDefault(px + "@" + y, 0);
        }
        return sum;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */