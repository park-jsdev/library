class Solution {
    public int[] closestPrimes(int left, int right) {
        boolean[] IsPrime = new boolean[right+1];
        Arrays.fill(IsPrime, true);
        IsPrime[0] = IsPrime[1] = false;

        int min = Integer.MAX_VALUE;
        findPrimes(right, IsPrime);

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i=left;i<=right;i++){
            if (IsPrime[i]){
                primes.add(i);
            }
        }

        int[] res = null;

        for (int i=0;i<primes.size()-1;i++){
            if (primes.get(i+1)-primes.get(i) < min){
                min = primes.get(i+1) - primes.get(i);
                res = new int[]{primes.get(i), primes.get(i+1)};
            }
        }

        return res == null ? new int[]{-1,-1}:res;
    }

    private void findPrimes(int n, boolean[] IsPrime){
        for (int i=2;i*i<=n;i++){
            if (IsPrime[i]){
                for (int j=i*i;j<=n;j+=i){
                    IsPrime[j] = false;
                }
            }
        }
    }
}