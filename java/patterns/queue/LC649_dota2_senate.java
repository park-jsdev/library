class Solution {
    public String predictPartyVictory(String senate) {
        if (senate.length() == 1){
            return senate.charAt(0) == 'R' ? "Radiant" : "Dire";
        } 

        int r = 0;
        int d = 0;
        int ban_r = 0;
        int ban_d = 0;
        Queue<Character> q = new LinkedList<>();
        for (int i=0;i<senate.length();i++){
            if (senate.charAt(i) == 'R'){
                r++;
            } else {
                d++;
            }
            q.offer(senate.charAt(i));
        }
        while (q.size() > 1){
            char s = q.poll();

            // Win conditions
            if (d <= 0 && r > 0){
                return "Radiant";
            }
            if (r <= 0 && d > 0){
                return "Dire";
            }

            if (s == 'R'){
                if (ban_r > 0){ // if there is a ban R then skip this node
                    ban_r--;
                    r--;
                    continue;
                } // if no bans left
                ban_d++; // ban a D
            }
            if (s == 'D'){
                if (ban_d > 0){ // if there is a ban R then skip this node
                    ban_d--;
                    d--;
                    continue;
                } // if no bans left
                ban_r++; // ban a D
            }
            q.offer(s); // push it back for the next round
        }
        // If 1 remains
        return q.poll() == 'R' ? "Radiant" : "Dire";
    }
}