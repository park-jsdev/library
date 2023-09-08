import java.util. * ;

// Helper class for HashMap
class Bucket {
  List<List<Integer>> bucket;

  public Bucket() {
    bucket = new ArrayList<>();
  }
  // put value in bucket
  public void update(int key, int value) {
    boolean found = false;
    for (int i=0;i<bucket.size();i++){
      if (key == bucket.get(i).get(0)){
        this.bucket.get(i).set(1, value);
        found = true;
        break;
      }
    }
    if (!found){
      this.bucket.add(Arrays.asList(key, value));
    }
  
  }
  // get value from bucket
  public int get(int key) {
    for (int i=0;i<bucket.size();i++){
      if (bucket.get(i).get(0) == key){
        return bucket.get(i).get(1);
      }
    }
    return -1;
  }
  // delete value from bucket
  public void remove(int key) {
    for (int i=0;i<bucket.size();i++){
      if (bucket.get(i).get(0) == key){
        bucket.remove(i);
        break;
      }
    }
  }
}