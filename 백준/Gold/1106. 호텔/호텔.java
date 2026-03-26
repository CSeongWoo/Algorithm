import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int C = Integer.parseInt(st.nextToken()); // 최소 목표 고객
		int N = Integer.parseInt(st.nextToken()); // 도시 갯수
		int arr[][] = new int[N][2]; // 도시 i[0] = 비용 / i[1] = 가치
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			arr[i][0] = cost;
			arr[i][1] = value;
		}
		// dp[i] = i명을 얻기 위한 최소 코스트
		int dp[] = new int[C + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i <= C; i++) {
			if (dp[i] == Integer.MAX_VALUE)
				continue;
			for (int j = 0; j < N; j++) {
				int cost = arr[j][0];
				int value = arr[j][1];
				int nextV = i + value;
				int n = nextV >= C ? C : nextV;
				if (dp[i] + cost < dp[n]) {
					dp[n] = dp[i] + cost;
				}
			}
		}
		System.out.println(dp[C]);
	}

}
