import java.io.*;
import java.util.*;

public class Main {
	static int population[];
	static List<Integer>[] nodes;
	static boolean isSelected[];
	static int populationSum;
	static int minDiff;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		population = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			populationSum += population[i];
		}
		nodes = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			nodes[i] = new ArrayList<>();
			for (int j = 0; j < cnt; j++) {
				// 0-index
				int next = Integer.parseInt(st.nextToken()) - 1;
				nodes[i].add(next);
			}
		}
		isSelected = new boolean[N];
		minDiff = populationSum;
		isSelected[0] = true;
		combination(1);
		if (minDiff == populationSum) {
			minDiff = -1;
		}
		System.out.println(minDiff);
	}

	static void combination(int depth) {
		findMinDiff();
		if (depth >= N) return;
		isSelected[depth] = true;
		combination(depth + 1);
		isSelected[depth] = false;
		combination(depth + 1);

	}

	static void findMinDiff() {
		
		int red = isRed();
		int blue = isBlue();
		int sum = red + blue;
		int diff = Math.abs(red - blue);
		if (sum != populationSum) 
			return;
		minDiff = Math.min(diff, minDiff);
		return;
	}
	static int isRed() {
		Deque<Integer> dq = new ArrayDeque<Integer>();
		int sum = 0;	
		boolean visited[] = new boolean[N];
		for (int start = 0; start < N; start++) {
			if (isSelected[start]) {
				dq.offer(start);
				visited[start] = true;		
				sum += population[start];
				break;
			}
		}
		if (sum == 0) return 0;
		while (!dq.isEmpty()) {
			int idx = dq.poll();
			for (int next : nodes[idx]) {
				if (isSelected[next] && !visited[next]) {
					dq.offer(next);
					visited[next] = true;
					sum += population[next];
 				}
			}
		}
		return sum;
	}
	static int isBlue() {
		Deque<Integer> dq = new ArrayDeque<Integer>();
		int start = 0;
		int sum = 0;
		for (start = 0; start < N; start++) {
			if (!isSelected[start]) {
				sum += population[start];
				break;
			}
		}
		if (sum == 0) return 0;
		
		boolean visited[] = new boolean[N];
		dq.offer(start);
		visited[start] = true;
		
		while (!dq.isEmpty()) {
			int idx = dq.poll();
			for (int next : nodes[idx]) {
				if (!isSelected[next] && !visited[next]) {
					dq.offer(next);
					visited[next] = true;
					sum += population[next];
 				}
			}
		}
		return sum;
	}
}
