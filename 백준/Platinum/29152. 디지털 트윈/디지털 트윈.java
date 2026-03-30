import java.io.*;
import java.util.*;

public class Main {

	static int map[][];
	static final int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		
		int count[] = new int[N]; // 행에 존재하는 기계의 수
		int left[] = new int[N]; // 해당 행의 가장 왼쪽에 있는 기계의 좌표값
		int right[] = new int[N]; // 해당 행의 가장 오른쪽에 있는 기계
		// 시작 -> 해당 행에 있는 (방향) 끝쪽 기계로 향한다. -> 만약 현재 위치보다 현재 방향보다 더 끝쪽에 기계가 있다 -> 글로 감. -> 반대 방향에 존재하면 달려감.
		
		map = new int[N][N];
		Arrays.fill(left, 10000);
		Arrays.fill(right, -1);
		for(int i = 0; i < N; i++) {
			char[] charArray = br.readLine().toCharArray();
			for(int j = 0; j < N; j++) {
				map[i][j] = charArray[j] - '0';
				if (map[i][j] == 1) {
					count[i]++;
					left[i] = Integer.min(j, left[i]);
					right[i] = Integer.max(j, right[i]);
				}
			}
		}
		// 각 행의 시작 시점에서 왼쪽 방향인지 오른쪽 방향인지 확인

		int[][] dp = new int[N][2]; 
		
		for (int i = 0; i < N; i++) {
			dp[i][0] = INF; // 왼쪽에서 끝나는 최단 거리
			dp[i][1] = INF; // 오른쪽에서 끝나는 최단 거리
		}
		// 1. 다음 행을 향할 때 왼쪽 방향 혹은 오른쪽 방향 둘 다 정할 수 있는가?
		// 2. 일반적으로 count가 0이 아닌 이상 방향은 하나로 고정
		int prev = -1; // 기계가 있었던 가장 최근 행의 인덱스를 기억할 변수
		
		for (int i = 0; i < N; i++) {
			if (count[i] == 0) continue; 

			if (prev == -1) {
				// 맨 처음 만나는 기계 행 처리
				if (i == 0) {
					// 우측 방향으로만 진행 가능
					dp[0][1] = right[0];
					dp[0][0] = right[0] == 0 ? 0 : INF;
				} else {
					dp[i][1] = i + right[i]; // 바로 i깊이만큼 내려가고 오른쪽으로 가기.
					dp[i][0] = i + right[i] + (right[i] - left[i]); // 오른쪽으로 이동 -> i만큼 내려가고 왼쪽 이동
				}
			} else {
				int h = i - prev; // 수직거리
				
				// 직전 행 방향이 왼쪽인 경우 + 다음행 오른쪽 방향으로 갈 경우.
				int leftToRight = dp[prev][0] + h + Math.abs(left[prev] - left[i]) + (right[i] - left[i]);

				// 직전 행 방향이 오른쪽이지만 다음행도 오른쪽으로 움직이려는 경우.
				int leftToRight2 = INF;
				// 오른쪽으로 움직여놓고 다시 왼쪽 -> 오른쪽으로 움직이려면 h == 1이면 불가능.
				if (right[prev] <= left[i] || h != 1) {
					leftToRight2 = dp[prev][1] + h + Math.abs(right[prev] - left[i]) + (right[i] - left[i]);
				}
				dp[i][1] = Math.min(leftToRight, leftToRight2);
				
				// 직전 행 오른쪽 방향 + 왼쪽으로 움직일 경우
				int rightToLeft = dp[prev][1] + h + Math.abs(right[prev] - right[i]) + (right[i] - left[i]);
				int rightToLeft2 = INF;
				// 직전 행 왼쪽 방향 + 왼쪽으로 움직일 경우
				if (left[prev] >= right[i] || h != 1) {
					rightToLeft2 = dp[prev][0] + h + Math.abs(left[prev] - right[i]) + (right[i] - left[i]);
				}
				
				dp[i][0] = Math.min(rightToLeft, rightToLeft2);
			}
			prev = i; // 이전행 갱신
		}
		int ans = INF;
		if (prev == -1) {
			ans = (N - 1) + (N - 1);
		} else if (prev == N - 1) {
			ans = dp[N - 1][1] + ((N - 1) - right[N - 1]);
		} else {
			int fromLeft = dp[prev][0] + (N - 1 - prev) + (N - 1 - left[prev]);
			int fromRight = dp[prev][1] + (N - 1 - prev) + (N - 1 - right[prev]);
			ans = Math.min(fromLeft, fromRight);
		}
		System.out.println(ans >= INF ? -1 : ans + 1);
	}
}
