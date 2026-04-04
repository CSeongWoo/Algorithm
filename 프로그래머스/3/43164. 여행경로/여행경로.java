import java.util.*;

class Solution {
    static boolean[] used;
    static List<String> allPaths = new ArrayList<>();
    static String[] ticketsCopy;
    static int n;
    static String[] answer;
    static boolean found = false;

    public String[] solution(String[][] tickets) {
        n = tickets.length;
        used = new boolean[n]; // 티켓 사용 여부
    
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));

        List<String> path = new ArrayList<>();
        path.add("ICN");

        dfs("ICN", tickets, path, 0);
        
        return answer;
    }

    private void dfs(String current, String[][] tickets, List<String> path, int depth) {
        if (found) return;

        if (depth == n) {
            answer = path.toArray(new String[0]);
            found = true;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!used[i] && tickets[i][0].equals(current)) {
                used[i] = true;
                path.add(tickets[i][1]);
                
                dfs(tickets[i][1], tickets, path, depth + 1);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }
}