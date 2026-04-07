import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int dp[][] = new int[N + 1][2];
		int map[] = new int[N + 1];

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = Integer.parseInt(st.nextToken());
			dp[i][0] = map[i];
			dp[i][1] = map[i];
		}
		Arrays.fill(dp[0], 0);
		for(int i = 2; i <= N; i++) {
			int twoStepBest = 0;
			for(int j = 0; j < 2; j++) {
				twoStepBest = Integer.max(dp[i - 2][j], twoStepBest);
			}
			
			dp[i][0] = Integer.max(dp[i][0], twoStepBest + map[i]);
			dp[i][1] = Integer.max(dp[i][1], dp[i - 1][0] + map[i]);

		}
		int maxVal = 0;
		for(int i = 0; i < 2; i++) {
			maxVal = Integer.max(maxVal, dp[N][i]);
		}
		System.out.println(maxVal);
	}
}
