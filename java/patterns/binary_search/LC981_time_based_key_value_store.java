// Binary Search
// Time: O(log n)
// Space: O(n)
/**
    A good design based problem that would mirror a real-life application with DS&A concepts, similar to LRU Cache.
    Timestamps are common in production.

    The crux is that we create a custom hash map based data structure, which returns a list of pairs as the value.
    We do this because there is a timestamp associated with the value. The list contains pairs of the value and the timestamp.
    So K -> (V, t), and we need an intermediate data structure with the hash map. So there is a list for every overlapping value
    of the Hash map. We need to search each list to return the value with the matching timestamp, or the one with the closest
    previous timestamp to it. We could search in linear time, but for a more optimized solution, we perform binary search.

    Key implementation details:
    - The HashMap linked with the List of Pairs manipulation
    - Pair class, default in Java: https://www.baeldung.com/java-pairs
    - Binary Search: int mid = start + (end - start + 1) / 2; and start = mid; The + 1 is crucial to get the correct answer.
 */

 class TimeMap {

    HashMap<String, List<Pair<String, Integer>>> map; // the Time based Key-Value Store

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) map.put(key, new ArrayList<>());
        map.get(key).add(new Pair(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        List<Pair<String, Integer>> list = map.get(key);
        return search(list, timestamp);
    }

    // Binary Search for the timestamp
    public String search(List<Pair<String, Integer>> list, int timestamp){
        int start = 0;
        int end = list.size() - 1;
        while (start < end){
            int mid = start + (end - start + 1) / 2; // with + 1, the search will converge when there are 2 elements left
            // the + 1 is crucial as it ensures a bias toward the upper bound, so it will return the largest value, rather than
            // any value less than the timestamp
            if (list.get(mid).getValue() <= timestamp){
                start = mid; // include the mid, we need the largest value that is less than or equal to the timestamp
            } else {
                end = mid - 1;
            }
        }
        return list.get(start).getValue() <= timestamp ? list.get(start).getKey() : "";
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */