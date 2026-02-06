import java.io.*;
import java.util.*;

/**
 * 메모리: 26,496 kb, 시간: 141 ms
 * DFS 부분집합
 *
 */

public class Solution {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T;
		T = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case);
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int maxCalory = Integer.parseInt(st.nextToken());
			int[][] ingredient = new int [N][2]; // 맛 / 칼로리
			
			int[][] dp = new int[N + 1][maxCalory + 1];
			
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int calory = Integer.parseInt(st.nextToken());
				ingredient[i][0] = score;
				ingredient[i][1] = calory;	
			}
			// dp[i][w] = max(dp[i - 1][w], dp[i - 1][w - ingredient[i][1]] + ingredient[i][1]);
			// or dp[i][w] = dp[i-1][w];
			for(int i = 1; i<N + 1; i++) {
				for(int calory = 1; calory < maxCalory + 1; calory++) {
					// 해당 w부터 음식을 넣을 수 있음.						
					if (calory - ingredient[i - 1][1] >= 0) { // ingredient는 0-index;
						dp[i][calory] = Math.max(dp[i - 1][calory], 
										dp[i-1][calory - ingredient[i - 1][1]] + ingredient[i - 1][0]);
					} else { // 음식 못넣으면, 이전 최대 dp값 가져오기.
						dp[i][calory] = dp[i - 1][calory];
					}
					dp[i][calory] = Math.max(dp[i][calory], dp[i][calory - 1]);
				}
			}
			int maxScore = dp[N][maxCalory];
			sb.append(" ").append(maxScore).append("\n");
		}
		System.out.println(sb);
	}
}
