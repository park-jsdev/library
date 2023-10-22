// Backtracking
// Time: O(n * 4^n), due to a combination of 4 possibilities per character.
// Space: O(n * 4^n), the same as all strings are stored in the recursive stack in the worst case
/**

    The crux is that we implicitly "choose", "explore", and "unchoose" with the recursive call itself, by passing incrementing
    parameters. This is a technique in recursion that could be called State carrying recursion. Therefore, the main logic is
    contained implicitly.

 */

class Solution {

    // Map the digits to chars
    private Map<Character, String> digitToChar = Map.of(
        '2',
        "abc",
        '3',
        "def",
        '4',
        "ghi",
        '5',
        "jkl",
        '6',
        "mno",
        '7',
        "pqrs",
        '8',
        "tuv",
        '9',
        "wxyz"
    );

    public List<String> letterCombinations(String digits) {
        // Basic Check
        if (digits.length() == 0){
            return new ArrayList();
        }

        // Model
        List<String> res = new ArrayList();
        String cur = ""; // initialize empty string
        backtrack(digits, res, cur, 0); // Call the function, remember we need the object, result, index and start 
        return res;
    }
        
    public void backtrack(
        String digits,
        List<String> res,
        String cur,
        int index
    ){
        // Base Case
        if (cur.length() == digits.length()){ // If the current combination reaches the digits length, that one is finished
            res.add(cur);
            return;
        } else if (index >= digits.length()){ // Edge case check if the index goes out of bounds
            return;
        } else { // Explore
            String digit = digitToChar.get(digits.charAt(index)); // for the digit, we need to lookup in our map
            for (char c : digit.toCharArray()){ // explore each char c in the digits, remember to convert to charArray
                backtrack(digits, res, cur + c, index + 1); // cur + c is "choose" next, index + 1 is "explore" next
                // In this example, there is no explicit "unchoose" step, as the cur value is implicitly reverted
                // after the recursive call, as strings are immutable in Java, and we initialized it as empty.
            }
        }
    }
}