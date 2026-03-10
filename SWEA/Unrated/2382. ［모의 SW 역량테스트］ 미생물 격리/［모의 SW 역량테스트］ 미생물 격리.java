import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	static class Group {
		int r;
		int c;
		int sum;
		int dir;
		int maxCnt;
		
		boolean alive = true;

		Group(int r, int c, int sum, int dir, int maxCnt) {
			this.r = r;
			this.c = c;
			this.sum = sum;
			this.dir = dir;
			this.maxCnt = maxCnt;
		}
		void move() {
			r += dr[dir];
			c += dc[dir];
			if (r == N - 1 || r == 0 || c == N -1 || c == 0) {
				dir = reverseDir(dir);
				sum /= 2;
			}
			if (sum == 0) alive = false;
		}
		
		int reverseDir(int dir) {
			if (dir <= 1) {
				return dir == 0 ? 1 : 0;
			} else {
				return dir == 3 ? 2 : 3;
			}
		}
	}

	static int N, M, K; // map 크기, timer, 군집 갯수

	// 상 하 좌 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Group[] map;
	static List<Group> list = new ArrayList<>();
	
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
			
			map = new Group[N * N];
			list.clear();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cell = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				list.add(new Group(r, c, cell, dir, cell));
			}
			
			int timer = 0;
			List<Integer> posList = new ArrayList<>();
			while(timer < M) {
				posList.clear();
				for(Group g : list) {
					if (!g.alive) continue;
					g.maxCnt = g.sum;
					g.move();
					if (!g.alive) continue;
					int pos = g.r * N + g.c;
					posList.add(pos);
					if (map[pos] == null) {
						map[pos] = g;
					} else {
						Group curr = map[pos];
						if (curr.maxCnt < g.maxCnt) {
							g.sum += curr.sum;
							curr.alive = false;
							map[pos] = g;
						} else {
							curr.sum += g.sum;
							g.alive = false;
						}
					}
				}
				for(int pos : posList) {
					map[pos] = null;
				}
				timer++;
			}
			
			int ans = 0;
			for (Group g : list) {
				if (!g.alive) continue;
				ans += g.sum;
			}
			
			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	
}
