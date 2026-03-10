import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static class Edge implements Comparable<Edge>{
		int start, end, weight;
		Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int V;
	static int E;
	static int[] parents;
	static Edge[] edgeList;
	
	static void makeSets() {
		parents = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			parents[i] = -1; // 부모노드가 없음을 의미.
		}
	}
	
	static int findSet(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;
		
		if (parents[aRoot] <= parents[bRoot]) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		} else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}
		return true;
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edgeList = new Edge[E];
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(start, end, weight);
			}
			
			makeSets();

			Arrays.sort(edgeList);
			
			int count = 0;
			long ans = 0;
			for(Edge edge : edgeList) {
				// 사이클이 없음 -> 합치기
				if (union(edge.start, edge.end)) {
					ans += edge.weight;
					// 선택한 간선 갯수가 V - 1 -> 모든 선 연결 완료.
					if(++count == V - 1) break;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}

