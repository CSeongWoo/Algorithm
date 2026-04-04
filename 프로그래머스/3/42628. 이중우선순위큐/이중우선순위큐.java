import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> doublePq = new TreeMap<>();
        for(String op: operations) {
            String[] parts = op.split(" ");
            String command = parts[0];
            int val = Integer.parseInt(parts[1]);
            if (command.equals("I")) {
                doublePq.put(val, doublePq.getOrDefault(val, 0) + 1);
            } else {
                if (doublePq.isEmpty()) continue;
                int target;
                if (val == 1) {
                    target = doublePq.lastKey();
                } else {
                    target = doublePq.firstKey();
                }
                int count = doublePq.get(target);
                if (count == 1) {
                    doublePq.remove(target);
                } else {
                    doublePq.put(target, count - 1);
                }
            }
        }
        int ans1 = 0;
        int ans2 = 0;
        if (!doublePq.isEmpty()) {
            ans1 = doublePq.firstKey();
            ans2 = doublePq.lastKey();
        }
        int[] answer = {ans2, ans1};
        return answer;
    }
}