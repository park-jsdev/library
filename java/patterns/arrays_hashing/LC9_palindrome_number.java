class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;

        List<Integer> nums = new ArrayList<>();
        while (x > 0){
            nums.add(x % 10);
            x /= 10;
        }
        int n = nums.size();
        int mid = n / 2;
        boolean even = (n % 2 == 0);

        // Spreading out from the center algorithm
        int l = mid - 1, r = mid;
        if (even) {
            r = mid;  // Adjusted for even case
        } else {
            l = mid - 1;
            r = mid + 1;
        }

         while (l >= 0 && r < n) {
            if (nums.get(l) != nums.get(r)) {
                return false;
            }
            l--;  // Decrement left pointer
            r++;  // Increment right pointer
        }
        return true;
    }
}