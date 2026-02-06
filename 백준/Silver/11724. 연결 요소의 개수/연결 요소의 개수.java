import java.io.*;
import java.util.*;

public class Main {
	static boolean[] visited;
	static List<Integer>[] nodes;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		nodes = new ArrayList[N];
		visited = new boolean[N];
				
		for(int i = 0; i<N; i++) {
			nodes[i] = new ArrayList<>();
		}
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			nodes[n1].add(n2);
			nodes[n2].add(n1);
		}
		
		int count = 0;
		for(int i = 0; i<N; i++) {
			if (visited[i]) continue;
			
			int curr = i;
			DFS(curr);
			count++;
		}
		System.out.println(count);
	}
	static void DFS(int curr) {
		for(int idx = 0; idx< nodes[curr].size(); idx++) {
			int next = nodes[curr].get(idx);
			if (!visited[next]) {
				visited[next] = true;
				DFS(next);
			}
		}
	}
}
