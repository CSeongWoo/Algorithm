import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] map;
	static int[][] cores;
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	static int coreCount;
	static int maxCount;
	static int maxLineSum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			// 코어 카운트 초기화
			coreCount = 0;
			cores = new int[12][2];
			// N, 배열 입력 == 초기화
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) { // 불필요한 탐색 줄이기
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
							continue;
						}
						cores[coreCount][0] = i;
						cores[coreCount][1] = j;
						coreCount++;
					}
				}
			}
			maxCount = -1;
			maxLineSum = Integer.MAX_VALUE;
			combination(0, 0, 0);
			sb.append("#").append(test_case).append(" ").append(maxLineSum).append("\n");
		}
		System.out.println(sb);
	}
	/**
	 * 1. 현재 넣은 코어 수 + 남은 코어수 < 현재 최대값이면 실패
	 * 2. 각 4개의 전선에 못넣으면 실패
	 * 
	 * 1. 코어를 4개의 방향으로 연결
	 * 2. 또는, 연결하지 않음
	 * 3. 재귀
	 */
	static void combination(int depth, int currCount, int currLineSum) {
		// 가지치기. 불가능한 조건
		if ((coreCount - depth) + currCount < maxCount ) {
			return;
		}
		if (depth == coreCount) { // 끝까지 도달.
			if (maxCount == currCount) {
				maxLineSum = Math.min(maxLineSum, currLineSum);
			} else if (maxCount < currCount) {
				maxCount = currCount;
				maxLineSum = currLineSum;
			}
			return;
		}
		int cr = cores[depth][0];
		int cc = cores[depth][1];
		for(int dir = 0; dir<4; dir++) {
			// 해당 위치에 line 또는 코어가 없는지
			if (isLine(cr, cc, dir)) {
				// line이 없으면 line깔기.
				int cnt = line(cr, cc, dir, 2);
				// 재귀
				combination(depth + 1, currCount + 1, currLineSum + cnt);
				// 원상복구
				line(cr, cc, dir, 0);
			}
		}
		combination(depth + 1, currCount, currLineSum);
	}
	static boolean isLine(int cr, int cc, int dir) {
		int nr = cr + dr[dir];
		int nc = cc + dc[dir];
		while(nr < N && nr >= 0 && nc < N && nc >= 0) {
			if (map[nr][nc] != 0) { // 전선이든 뭐든 존재.
				return false;
			}
			nr += dr[dir];
			nc += dc[dir];
		}
		return true;
	}
	static int line(int cr, int cc, int dir, int value) {
		int count = 0;
		int nr = cr + dr[dir];
		int nc = cc + dc[dir];
		while(nr < N && nr >= 0 && nc < N && nc >= 0) {
			map[nr][nc] = value;
			nr += dr[dir];
			nc += dc[dir];
			count++;
		}
		return count;
	}
}
