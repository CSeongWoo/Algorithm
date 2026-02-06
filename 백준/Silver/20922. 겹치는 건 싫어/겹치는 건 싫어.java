import java.io.*;
import java.util.*;

public class Main {

	/**
	 * 홍대병에 걸린 도현이는 겹치는 것을 매우 싫어한다. 특히 수열에서 같은 원소가 여러 개 들어 있는 수열을 싫어한다. 도현이를 위해 같은
	 * 원소가 K개 이하로 들어 있는 최장 연속 부분 수열의 길이를 구하려고 한다. 100,000 이하의 양의 정수로 이루어진 길이가 N인 수열이
	 * 주어진다. 이 수열에서 같은 정수를 K개 이하로 포함한 최장 연속 부분 수열의 길이를 구하는 프로그램을 작성해보자.
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N;
		int K;
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Deque<Integer> window = new ArrayDeque<Integer>();
		
		int[] quantity = new int[100001];
		int max_window = -1;
		for (int i = 0; i < N; i++) {
			// window에 현재 수열의 값을 넣고 한도초과면 poll
			if (quantity[arr[i]] + 1>= K) {
				max_window = Math.max(window.size(), max_window);
				while (quantity[arr[i]] >= K) {
					int n = window.poll();
					quantity[n] -= 1;
				}
			}
			window.offer(arr[i]);
			quantity[arr[i]] += 1;
		}
		max_window = Math.max(window.size(), max_window);
		System.out.println(max_window);

		return;
	}
}