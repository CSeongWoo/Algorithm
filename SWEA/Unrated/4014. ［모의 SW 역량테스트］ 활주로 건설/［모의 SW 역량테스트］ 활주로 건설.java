import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	static int N, X;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = 0;
			for (int i = 0; i < N; i++) {
				ans = ans + garo(i) + sero(i);
			}
			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	// 가로줄 순회 - r 고정
	static int garo(int r) {
		// 오른쪽으로 경사면 둘 수 있는지
		boolean[] visited = new boolean[N];
		for(int c = 0; c < N - 1; c++) {
			// 높이차가 2 이상 -> 안됨
			if (Math.abs(map[r][c + 1] - map[r][c]) >= 2) return 0;
			if (map[r][c] > map[r][c + 1]) {
				int k;
				for(k = c + 1; k <= c + X; k++) {
					// 활주로 세우다가 범위 벗어남. or 이미 활주로 건설됨.
					if (k >= N || visited[k] || map[r][k] != map[r][c + 1]) return 0;
					visited[k] = true;
				}
				c = c + X - 1;
			}
		}
		for (int c = N - 1; c > 0; c--) {
			if (Math.abs(map[r][c] - map[r][c - 1]) >= 2) return 0;
			if (map[r][c - 1] < map[r][c]) {
				int k;
				for(k = c - 1; k >= c - X; k--) {
					// 활주로 세우다가 범위 벗어남. or 이미 활주로 건설됨.
					if (k < 0 || visited[k] || map[r][k] != map[r][c - 1]) return 0;
					visited[k] = true;
				}
				c = c - X + 1;
			}
		}
		return 1;
	}

	// 세로줄 순회 - c 고정
	static int sero(int c) {
		boolean[] visited = new boolean[N];
		for(int r = 0; r < N - 1; r++) {
			// 높이차가 2 이상 -> 안됨
			if (Math.abs(map[r + 1][c] - map[r][c]) >= 2) return 0;
			if (map[r][c] > map[r + 1][c]) {
				int k;
				for(k = r + 1; k <= r + X; k++) {
					// 활주로 세우다가 범위 벗어남. or 이미 활주로 건설됨.
					if (k >= N || visited[k] || map[k][c] != map[r + 1][c]) return 0;
					visited[k] = true;
				}
				r = r + X - 1;
			}
		}
		for (int r = N - 1; r > 0; r--) {
			if (Math.abs(map[r][c] - map[r - 1][c]) >= 2) return 0;
			if (map[r][c] > map[r - 1][c]) {
				int k;
				for(k = r - 1; k >= r - X; k--) {
					// 활주로 세우다가 범위 벗어남. or 이미 활주로 건설됨.
					if (k < 0 || visited[k] || map[k][c] != map[r - 1][c]) return 0;
					visited[k] = true;
				}
				r = r - X + 1;
			}
		}
		return 1;
	}
}