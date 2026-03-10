import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	static class Group {
		int r;
		int c;
		int cell;
		int dir;
		int timer;

		Group(int r, int c, int cell, int dir, int timer) {
			this.r = r;
			this.c = c;
			this.cell = cell;
			this.dir = dir;
			this.timer = timer;
		}
	}

	static int N, M, K; // map 크기, timer, 군집 갯수

	// 상 하 좌 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			ArrayList<Group> list = new ArrayList<>();
			Deque<Group> moved = new ArrayDeque<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cell = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				list.add(new Group(r, c, cell, dir, 0));
			}
			int ans = 0;
			int currTime = 0;
			for (int i = 0; i < M; i++) {
				for(Group curr : list) {
					int nr = curr.r + dr[curr.dir];
					int nc = curr.c + dc[curr.dir];
					int cell = curr.cell;
					int dir = curr.dir;
					int time = curr.timer + 1;
					if (nr == N - 1 || nr == 0 || nc == N - 1 || nc == 0) {
						cell = curr.cell / 2;
						dir = reverseDir(dir);
						if (cell == 0) {
							continue;
						}
					}
					moved.offer(new Group(nr, nc, cell, dir, time));
				}
				list.clear();
				int[][][] map = new int[N][N][3]; // cell 합, 최대 군집, 방향
				int count = 0;
				// 모든 움직인 셀들을 병합.
				while (!moved.isEmpty()) {
					Group group = moved.poll();
					count += group.cell;
					if (map[group.r][group.c][0] != 0) {
						if (map[group.r][group.c][1] < group.cell) {
							map[group.r][group.c][1] = group.cell;
							map[group.r][group.c][2] = group.dir;
						}
					} else {
						map[group.r][group.c][1] = group.cell;
						map[group.r][group.c][2] = group.dir;
					}
					map[group.r][group.c][0] += group.cell;
				}
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (map[r][c][0] > 0) {
							list.add(new Group(r, c, map[r][c][0], map[r][c][2], currTime));
						}
					}
				}
				ans = count;
			}

			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static int reverseDir(int dir) {
		if (dir <= 1) {
			return dir == 0 ? 1 : 0;
		} else {
			return dir == 2 ? 3 : 2;
		}
	}
}
