import java.util.*;

class Solution {
    static final int INF = 100_000_000;
    static int[][] dp;
    static int len;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        len = onboard.length;
        dp = new int[len][51];
        temperature += 10;
        t1 += 10;
        t2 += 10;
        
        for(int i = 0; i < len; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][temperature] = 0;
        
        for(int i = 1; i < len; i++) {
            for(int j = 0; j <= 50; j++) {
                if (onboard[i] == 1 && (j < t1 || j >t2)) continue;
                
                // 에어컨 온도 유지
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + b);
                // 에어컨 1도 올리기
                if (j - 1 >= 0)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + a);
                // 에어컨 1도 내리기
                if (j + 1 <= 50)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1] + a);
                // 에어컨 끄고 자연풍 받기
                if (j == temperature) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                    if (j - 1 >= 0) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                    if (j + 1 <= 50) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1]);
                }
                if (j < temperature && j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                } else if (j > temperature && j + 1 <= 50) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1]);
                }
            }
        }
        int answer = INF;
        for(int i = 0; i <= 50; i++) {
            answer = Math.min(answer, dp[len - 1][i]);
        }
        return answer;
    }
/**
    int DFS(int time, int currTemp, int[] onboard, int temperature, int t1, int t2, int a, int b) {
        // 최종 도착
        if (time == len) return 0;
        // 값이 범위를 벗어나는 경우
        if (currTemp < 0 || currTemp > 50) return INF;
        // 온보딩 && t1 t2를 벗어난 경우
        if (onboard[time] == 1 && (currTemp < t1 || currTemp > t2)) return INF;
            
        // 방문한 노드인 경우
        if (dp[time][currTemp] != -1) return dp[time][currTemp];
        
        int minCost = INF;
        // 에어컨 1도 올리기
        minCost = Math.min(minCost, DFS(time + 1, currTemp + 1, onboard, temperature, t1, t2, a, b) + a);
        // 에어컨 1도 내리기
        minCost = Math.min(minCost, DFS(time + 1, currTemp - 1, onboard, temperature, t1, t2, a, b) + a);
        // 에어컨 킨 상태로 유지
        minCost = Math.min(minCost, DFS(time + 1, currTemp, onboard, temperature, t1, t2, a, b) + b);
        // 에어컨 끄고 실외온도 따라가기
        int nextTemp = currTemp;
        if (currTemp > temperature) {
            nextTemp--;
        } else if (currTemp < temperature) {
            nextTemp++;
        }
        
        minCost = Math.min(minCost, DFS(time + 1, nextTemp, onboard, temperature, t1, t2, a, b));
        
        return dp[time][currTemp] = minCost;
    }
    */
}