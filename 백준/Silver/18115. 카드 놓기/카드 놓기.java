import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 역순으로 가면 됨
		Deque<Integer> deque = new ArrayDeque<>();
		int num = 1;
		for(int i = N - 1; i >= 0; i--) {
			if (arr[i] == 1) {
				deque.push(num);
			} else if (arr[i] == 2) {
				int top = deque.pop();
				deque.push(num);
				deque.push(top);
			} else { // 3
				deque.offer(num);
				
			}
			num++;
		}
		while(!deque.isEmpty()) {
			sb.append(deque.poll()).append(' ');
		}
		System.out.println(sb);
	}
}
