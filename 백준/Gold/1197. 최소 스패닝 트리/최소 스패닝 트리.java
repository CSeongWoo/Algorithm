import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge>{
		int a, b, weight;
		Edge(int a, int b, int weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static Edge[] edgeArr;
	static int[] parents;
	static int V;
	static int E;
	
	static void makeSets(int V) {
		parents = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			parents[i] = -1;
		}
	}
	
	static int findSets(int a) {
		if (parents[a] < 0) return a; // 현재 위치가 루트노드
		return parents[a] = findSets(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = findSets(a);
		int rootB = findSets(b);
		if (rootA == rootB) return false; // 사이클이므로 연결하면 안됨
		// 집합 크기가 큰쪽으로 편입
		if (parents[rootA] <= parents[rootB]) {
			parents[rootA] += parents[rootB];
			parents[rootB] = rootA; // rootB의 부모는 rootA
		} else {
			parents[rootB] += parents[rootA];
			parents[rootA] = rootB;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		makeSets(V);
		edgeArr = new Edge[E];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edgeArr[i] = new Edge(s, e, c);
		}
		Arrays.sort(edgeArr);
		int costs = 0;
		for(Edge edge: edgeArr) {
			if (union(edge.a, edge.b)) {
				costs += edge.weight;
			}
		}
		System.out.println(costs);
	}

}
