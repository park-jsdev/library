class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, max = 0;
        int[] len = new int[n]; // LIS that ends at i
        int[] cnt = new int[n]; // count of LIS that ends at i

        for (int i=0;i<n;i++){
            for (int j=i-1;j>=0;j--){
                if (nums[j]<nums[i]){ // if combining with i makes an increasing subs.
                    if (len[j]+1 > len[i]){ // if combining with i makes a longer increasing subs.
                        len[i] = len[j]+1; // use len[j]+1 as there may be other subs. already there
                        cnt[i] = cnt[j];
                    } else if (len[j]+1 == len[i]){ // if combining with i makes another LIS
                        cnt[i] += cnt[j];
                    }
                }
            }
            // if the curr nums[i] is the min val so far
            if (len[i] == 0){
                len[i] = 1;
                cnt[i] = 1;
            }

            // update the longest length
            max = Math.max(max, len[i]);
        }

        // get the computed results
        int res = 0;
        for (int i=0;i<n;i++){
            if (len[i] == max){
                res += cnt[i];
            }
        }
        return res;
    }
}