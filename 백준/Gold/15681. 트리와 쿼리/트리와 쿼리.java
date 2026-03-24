import java.io.*;
import java.util.*;

public class Main {
	static int N, R, Q;
	static int[] subTrees;
	static List<Integer>[] tree;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		int root = R;
		subTrees = new int[N + 1];
		tree = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			tree[to].add(from);
			tree[from].add(to);
		}
		findSubTrees(root);
		StringBuilder sb = new StringBuilder();
		for(int query = 0; query < Q; query++) {
			st = new StringTokenizer(br.readLine());
			int vertex = Integer.parseInt(st.nextToken());
			sb.append(subTrees[vertex]).append("\n");
		}
		System.out.println(sb);
	}
	static int findSubTrees(int index) {
		visited[index] = true;
		subTrees[index] = 1;
		for(int next : tree[index]) {
			if (visited[next]) continue;
			subTrees[index] += findSubTrees(next);
		}
		return subTrees[index];
	}
}
