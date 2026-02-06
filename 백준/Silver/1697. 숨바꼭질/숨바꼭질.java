import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int x;
		int cnt;

		public Point(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] map = new int[100001]; // 0 ~ 100001이 범위

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Deque<Point> dq = new ArrayDeque<Point>();
		dq.offer(new Point(N, 0));
		map[N] = 0;
		int ans = 0;
		while (!dq.isEmpty()) {
			Point curr = dq.poll();
			if (curr.x == K) {
				ans = curr.cnt;
				break;
			}
			if (map[curr.x] == 0 || map[curr.x] > curr.cnt) {
				map[curr.x] = curr.cnt;
				if (curr.x + 1 <= 100000) {
					dq.offer(new Point(curr.x + 1, curr.cnt + 1));
				}
				if (curr.x - 1 >= 0) {
					dq.offer(new Point(curr.x - 1, curr.cnt + 1));
				}
				if (curr.x * 2 <= 100000) {
					dq.offer(new Point(curr.x * 2, curr.cnt + 1));
				}
			}

		}
		System.out.println(ans);
	}
}
