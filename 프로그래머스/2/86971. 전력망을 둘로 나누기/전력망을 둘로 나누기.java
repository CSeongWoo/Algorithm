import java.util.*;

class Solution {
    List<Integer> tree[];
    int subTrees[];
    boolean visited[];
    public int solution(int n, int[][] wires) {
        tree = new ArrayList[n + 1];
        subTrees = new int[n + 1];
        visited = new boolean[n + 1];
        for(int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for(int[] edges : wires) {
            int from = edges[0];
            int to = edges[1];
            tree[from].add(to);
            tree[to].add(from);
        }
        findSubTree(1);
        int answer = n;
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, Math.abs(n - 2 * subTrees[i]));
            
        }

        return answer;
    }
    int findSubTree(int currIdx) {
        visited[currIdx] = true;
        subTrees[currIdx] = 1;
        if (tree[currIdx].size() == 0) {

            return subTrees[currIdx];
        }
        for(int next : tree[currIdx]) {
            if (visited[next]) continue;
            subTrees[currIdx] += findSubTree(next);
        }
        return subTrees[currIdx];
    }
}