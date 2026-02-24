import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());


		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			int N, K;
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int weight[] = new int[N];
			int values[] = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				weight[i] = Integer.parseInt(st.nextToken());
				values[i] = Integer.parseInt(st.nextToken());
			}
			// 무게 w까지 허용할때, i번째 물건을 골라서 넣었을 경우의 최대 밸류 = dp[i번째 물건까지][무게 w 허용치일때]
			int dp[][] = new int[N + 1][K + 1];
			// 1번째 물건부터
			for(int i = 1; i <= N; i++) {
				// 해당 무게를 가질때 dp[i][w] == 최대 밸류
				// w - weight[i] < 0인 경우에는 dp[i][w] = dp[i - 1][w];
				for (int w = 1; w<= K; w++) {
					if (w - weight[i - 1] >= 0) {
						// [현재 물건을 안 넣은 경우] vs [현재 물건을 넣은 경우] 중 최댓값
						dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weight[i - 1]] + values[i - 1]);
					}
					else {
						// 현재 배낭의 무게를 넣을 수 없음 -> 이전 물건까지 넣었을 때, w 무게의 최적값 가져오기.
						dp[i][w] = dp[i - 1][w];
					}
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(dp[N][K]).append("\n");
		}
		System.out.println(sb);
	}

}
