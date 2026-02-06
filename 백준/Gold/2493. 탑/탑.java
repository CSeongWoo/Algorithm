import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int index;
		int height;
		Point() {}
		Point(int i, int h) {
			this.index = i;
			this.height = h;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		Point[] arr = new Point[N];
		Deque<Point> stack = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			int height = Integer.parseInt(st.nextToken());
			arr[i] = new Point(i, height);
		}
		
		int[] ans = new int[N];
		for(int i = N-1; i >= 0; i--) {
			while(!stack.isEmpty()) {
				// 현재 height보다 낮은 값들은 전부 pop하고 기록하기.
				if (stack.peek().height < arr[i].height) {
					Point p = stack.pop();
					ans[p.index] = arr[i].index + 1;
				}
				else break; // height보다 낮은 값이 없음.
			}
			// 스택에 추가 -> 단조 감소 스택 유지
			stack.push(arr[i]);
		}
		for(int i = 0; i<N; i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb);
	}
}
