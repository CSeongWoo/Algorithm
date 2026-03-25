import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		/**
		 * 1. 길이가 1인 경우
		 * 2. 길이가 2이며 같은 수인 경우
		 * 3. 길이가 3이며 끝 두 수가 같은 경우 
		 * 4. 길이가 4이며 끝 두 수가 같고 안의 두 수가 dp인 경우
		 * 5. 길이가 5이며 끝 두 수가 같고 안의 범위가 dp인경우
		 * ... -> arr[i] == arr[j] && dp[i + 1][j - 1]
		 * 길이 3을 모두 탐색해야 4를 탐색할 수 있음.
		 * 이후도 마찬가지
		 * 즉, for문에 있어 len = 3인경우, 4인경우 를 모두 탐색해야 함
		 */
		
		boolean[][] dp = new boolean[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			dp[i][i] = true;
			if (i != N && arr[i] == arr[i + 1]) dp[i][i + 1] = true;
		}
		for(int length = 3; length <= N; length++) {
			for(int start = 1; start <= N - length + 1; start++) {
				int end = start + length - 1;
				if (arr[start] == arr[end] && dp[start + 1][end - 1]) dp[start][end] = true;
			}
		}
		st = new StringTokenizer(br.readLine());
		int query = Integer.parseInt(st.nextToken());
		for(int i = 0; i < query; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			sb.append(dp[start][end] ? 1 : 0).append('\n');
		}
		
		System.out.println(sb);
	}
}
