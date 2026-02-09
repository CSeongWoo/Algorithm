import java.io.*;
import java.util.*;

/**
 * 비트마스킹으로 풀기
 *
 */
public class Solution {
	static int arr[][];
	static int N;
	static int ans;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T;
		T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}			
			ans = Integer.MAX_VALUE;
			visited = new boolean[N];
			DFS(0, 0);

			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	// 현재 조합 -> + 추가할 조합
	/**
	 * 1. 이 조합이 이미 있는지를 판별하기 2. 어떻게.. List에서 pop하기?
	 */
	static void DFS(int lv, int idx) {
		if (lv == N / 2) { // N / 2개만큼 탐색 완료.
			getRes();
			return;
		}
		if (idx >= N)
			return;
		visited[idx] = true;
		DFS(lv + 1, idx + 1);
		visited[idx] = false;
		DFS(lv, idx + 1);
	}

	static void getRes() {
		int aSum = 0;
		int bSum = 0;
		int arrA[] = new int[N / 2];
		int arrB[] = new int[N / 2];
		int cntA = 0;
		int cntB = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i])
				arrA[cntA++] = i;
			else
				arrB[cntB++] = i;
		}
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				aSum += arr[arrA[i]][arrA[j]];
				bSum += arr[arrB[i]][arrB[j]];
			}
		}
		int res = Math.abs(aSum - bSum);
		ans = Math.min(ans, res);
	}
}
