import java.util.*;
import java.io.*;

public class Solution {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int weight;

		Point() {
		}

		Point(int r, int c, int weight) {
			this.r = r;
			this.c = c;
			this.weight = weight;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	// 2차원 배열의 가중치
	static int[][] map;
	// 방문여부
	static boolean[][] visited;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	// 각 노드마다 최소 시간 - 다익스트라로 갱신
	static int[][] time;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			time = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
					time[i][j] = Integer.MAX_VALUE;
				}
			}
			dijkstra(0, 0);
			System.out.println("#" + test_case + " " + time[N - 1][N - 1]);
		}
	}

	static void dijkstra(int r, int c) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		// 좌표값과 현재 위치 저장
		pq.offer(new Point(r, c, map[r][c]));
		time[r][c] = 0;
		while (!pq.isEmpty()) {
			Point curr = pq.poll();
			if (visited[curr.r][curr.c])
				continue;
			visited[curr.r][curr.c] = true;
			for(int dir = 0; dir <4; dir++) {
				int nr = curr.r + dr[dir];
				int nc = curr.c + dc[dir];
				if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;
				if (time[nr][nc] > curr.weight + map[nr][nc]) {
					time[nr][nc] = curr.weight + map[nr][nc];
					pq.offer(new Point(nr, nc, time[nr][nc]));
				}
			}
		}
	}

}
