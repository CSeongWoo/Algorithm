import java.io.*;
import java.util.*;

/**
 * 메모리: 27,264 kb, 시간: 191 ms
 * 비트마스킹을 통해 가지치기.
 */

public class Solution {

	static int N;
	static int M;
	static int allCount;
	static int[] saves;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			allCount = 0;
			saves = new int[N];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(st.nextToken()) - 1;
				int x2 = Integer.parseInt(st.nextToken()) - 1;
//				saves[x1] |= 1 << x2;
//				saves[x2] |= 1 << x1;
				if (x1 < x2) {
					saves[x2] |= 1 << x1;
				} else {
					saves[x1] |= 1 << x2;
				}
			}
			DFS(0, 0);
			sb.append("#").append(test_case).append(" ").append(allCount).append("\n");
		}
		System.out.println(sb);
	}

	static void DFS(int depth, int flag) {
		if (depth == N) {
			allCount += 1;
			return;
		}
		if ((saves[depth] & flag) == 0) { // 현재 index와 현재 재료 조합이 가능하면
			DFS(depth + 1, flag | 1 << depth);
			
		}
		DFS(depth + 1, flag); // 안넣기

	}

}
