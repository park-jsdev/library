class Solution {
    public int compress(char[] chars) {
        int res = 0;
        int i = 0;

        while (i<chars.length){
            final char letter = chars[i]; // cur character being compressed
            int count = 0;

            while (i<chars.length && chars[i] == letter){
                count++;
                i++;
            }

            chars[res++] = letter; // write letter to compressed array

            // if count is > 1 then write count as string to compressed array
            if (count > 1){
                // String conversion then iterate characters
                for (final char c : String.valueOf(count).toCharArray()){
                    chars[res++] = c;
                }
            }
        }
        return res;
    }
}