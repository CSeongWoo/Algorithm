import java.io.*;
import java.util.*;

/**
 * 메모리: 38,392 kb, 시간: 129 ms
 * DP 활용
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
			int gradient[][] = new int[N][2];
			int dp[][] = new int [N + 1][maxCalory + 1];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				gradient[i][0] = Integer.parseInt(st.nextToken());
				gradient[i][1] = Integer.parseInt(st.nextToken());
			}
			for(int i = 1; i <= N; i++) {
				for(int calory = 1; calory <= maxCalory; calory++) {
					if (calory - gradient[i - 1][1] >= 0) {
						dp[i][calory] = Math.max(dp[i - 1][calory], dp[i - 1][calory - gradient[i - 1][1]] + gradient[i - 1][0]);
					} else {
						dp[i][calory] = dp[i - 1][calory];
					}
				}
			}
			int maxScore = dp[N][maxCalory];
			sb.append(" ").append(maxScore).append("\n");
		}
		System.out.println(sb);
	}
}
