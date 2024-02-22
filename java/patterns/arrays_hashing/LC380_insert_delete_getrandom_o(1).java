class RandomizedSet {
    HashMap<Integer, Integer> map; // Maps value to its index
    ArrayList<Integer> index;

    public RandomizedSet() {
        map = new HashMap<>();
        index = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        if (map.containsKey(val)){
            return false;
        } else {
            map.put(val, index.size());
            index.add(val);
            return true;
        }
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        } else {
            int removeIndex = map.get(val); // Get the index of 'val' in 'index'
            int lastElement = index.get(index.size() - 1); // Get the last element in 'index'

            index.set(removeIndex, lastElement); // Move the last element to the 'removeIndex' position
            map.put(lastElement, removeIndex); // Update the 'map' with the new index of 'lastElement'

            index.remove(index.size() - 1); // Remove the last element from 'index'
            map.remove(val); // Remove 'val' from 'map'

            return true;
        }
    }
    
    public int getRandom() {
        int randomIndex = (int) (Math.random() * index.size());
        return index.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */