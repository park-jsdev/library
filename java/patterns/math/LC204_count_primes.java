class Solution {
    private static int count;
    public int countPrimes(int n) {
        count = 0;
        sieve(n);
        return count;
    }

    private void sieve(int n){
        boolean[] primes = new boolean[n];
        for (int i=0;i<n;i++){
            primes[i] = true;
        }

        for (int p=2;p*p<n;p++){
            if (primes[p] == true){
                for (int i=p*p;i<n;i+=p){
                    primes[i] = false;
                }
            }
        }
        for (int i=2;i<n;i++){
            if (primes[i] == true) count++;
        }
    }
}