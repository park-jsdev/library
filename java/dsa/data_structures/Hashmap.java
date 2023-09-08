// HashMap dynamic array implementation
// Time: Amortized O(1) for array operations, and worst case O(n) for collision handling
// Space: O(m + n), where m = |hashMap|, and n = (k,v)s

// - Each bucket handles collisions
// - Note that linked list implementations of buckets costs more overhead memory and
// has O(n) lookup time per node, but is more efficient for removals 

/** 
 * Design a hash map without using the built-in libraries. We only need to cater integer keys and integer values in the hash map. Return NULL if the key doesn’t exist.

It should support the following three primary functions of a hash map:

    - Put(key, value): This function inserts a key and value pair into the hash map. If the key is already present in the map, then the value is updated. Otherwise, it is added to the bucket.
    - Get(key): This function returns the value to which the key is mapped. It returns −1, if no mapping for the key exists.
    - Remove(key): This function removes the key and its mapped value.
 * 
*/


import java.util.*;

class MyHashMap {
    public int keySpace;
    public List<Bucket> hashMap; 

    public MyHashMap(int keySpace) {
        // Select a prime number (preferably a large one)
        this.keySpace = keySpace;

        // Create an array and initialize it with empty buckets equal to the number chosen
        this.hashMap = new ArrayList<>(Collections.nCopies(keySpace, new Bucket()));
    }

    // Put: Avg time: O(1), Worst case: O(n)
    public void put(int key, int value) {
        int hashKey = generateHashKey(key, this.keySpace);
        this.hashMap.get(hashKey).update(key, value);
    }

    // Get: Avg time: O(1), Worst case: O(n)
    public int get(int key) {
        int hashKey = generateHashKey(key, this.keySpace);
        return this.hashMap.get(hashKey).get(key);
    }
    
    // Remove: Avg time: O(1), Worst case: O(n)
    public void remove(int key) {
        int hashKey = generateHashKey(key, this.keySpace);
        this.hashMap.get(hashKey).remove(key);
    }

    // Generate a hash key by taking the modulus of the input key with the number chosen
    private int generateHashKey(int key, int keySpace){
        return key % keySpace;
    }
}
