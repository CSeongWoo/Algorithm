import java.io.*;
import java.util.*;

public class Solution {

	static int N, C, R;
	static int[][] map;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static int bricks;
	static int maxBrokenBrick;
	static int remainBricks;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			map = new int[R][C];
			bricks = maxBrokenBrick = remainBricks = 0;
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0) {
						bricks++;
					}
				}
			}
			shootBid(0, 0);
			remainBricks = bricks - maxBrokenBrick;
			sb.append("#").append(test_case).append(" ").append(remainBricks).append("\n");
		}
		System.out.println(sb);
	}

	static void sortMap() {
		for (int c = 0; c < C; c++) {
			int[] temp = new int[R];
			int count = R - 1;
			for (int r = R - 1; r >= 0; r--) {
				if (map[r][c] != 0) {
					temp[count--] = map[r][c];
				}
			}
			for (int r = 0; r < R; r++) {
				map[r][c] = temp[r];
			}
		}
	}

	static void shootBid(int count, int brokenBrick) {
		if (count == N) {
			maxBrokenBrick = Math.max(maxBrokenBrick, brokenBrick);

			return;
		}
		int[][] tempMap = copyMap(map);
		for (int c = 0; c < C; c++) {
			int nextB = breakBrick(c) + brokenBrick;
			sortMap();
			shootBid(count + 1, nextB);
			map = copyMap(tempMap);
		}
	}

	static int breakBrick(int c) {
		int res = 0;
		Deque<int[]> deque = new ArrayDeque<>();
		for(int r = 0; r < R; r++) {
			if (map[r][c] != 0) {
				deque.offer(new int[] {r, c, map[r][c] - 1});
				map[r][c] = 0;
				res++;
				break;
			}
		}
		while(!deque.isEmpty()) {
			int[] curr = deque.poll();
			int cr = curr[0];
			int cc = curr[1];
			int size = curr[2];
			for(int dir = 0; dir < 4; dir++) {
				for(int i = 1; i <= size; i++) {
					int nr = cr + (dr[dir] * i);
					int nc = cc + (dc[dir] * i);
					if (nr < 0 || nr >= R || nc < 0 || nc >= C) break;
					if (map[nr][nc] == 0) continue;
					
					if (map[nr][nc] > 1) {
						deque.offer(new int[] {nr, nc, map[nr][nc] - 1});
					}
					map[nr][nc] = 0;
					res++;
				}
			}
		}
		return res;
	}
	static int[][] copyMap(int[][] origin) {
	    int[][] temp = new int[R][C];
	    for (int i = 0; i < R; i++) {
	        temp[i] = origin[i].clone();
	    }
	    return temp;
	}
}
