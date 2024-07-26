class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int lsf = INT_MAX;
        int max = 0;
        int cur = 0;

        for (int i=0;i<prices.size();i++){
            if (prices[i] < lsf){
                lsf = prices[i];
            }
            cur = prices[i] - lsf;
            if (max < cur){
                max = cur;
            }
        }
        return max;
    }
};