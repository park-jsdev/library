class Solution {
    public int[] maxUpgrades(int[] count, int[] upgrade, int[] sell, int[] money) {
        int[] res = new int[count.length];

        for (int i=0;i<count.length;i++){
            int c = count[i];
            int upCost = upgrade[i];
            int sellCost = sell[i];
            int mon = money[i];

            // upgrade what is possible first
            int numUpgrades = mon / upCost;

            // how much remaining
            int moneyRem = mon%upCost;

            // how mnay servers left
            int rem = c - numUpgrades;

            // covering all upgrades?
            if (numUpgrades >= c){
                res[i] = c;
                continue;
            }

            int low = 0;
            int high = rem;
            int mid = 0;

            int bestMid = 0; // var for best mid val which is the answer

            while (low <= high){
                mid = low + (high-low)/2;

                // upgrade mid num of remaining servers
                long cost = (long)upCost * mid;

                // sell (rem-mid) servers since not upgrading
                long gain = (long)sellCost * (rem-mid) + moneyRem;

                // Binary Search:
                // 1. is it doable? Can cost from selling fund upgrades?
                // 2. if not, reduce servers to upgrade
                // 3. if doable, can we upgrade more?
                if (cost <= gain){
                    bestMid = mid;
                    low = mid + 1;
                } else {
                    high = mid-1;
                }
            }
            res[i] = bestMid + numUpgrades;
        }
        return res;
    }
}