import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static class Edge implements Comparable<Edge>{
		int start, end;
		double weight;
		Edge(int start, int end, double weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	static int V;
	static int[] parents;
	static List<Edge> edgeList;
	
	static int[] xArr;
	static int[] yArr;
	
	static void makeSets() {
		parents = new int[V];
		for(int i = 0; i < V; i++) {
			parents[i] = -1;
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
			edgeList = new ArrayList<>();
			xArr = new int[V];
			yArr = new int[V];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < V; i++) {
				int x = Integer.parseInt(st.nextToken());
				xArr[i] = x;
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < V; i++) {
				int y = Integer.parseInt(st.nextToken());
				yArr[i] = y;
			}

			st = new StringTokenizer(br.readLine());
			double E = Double.parseDouble(st.nextToken());
			// 모든 섬 간의 거리 구하기
			for(int i = 0; i < V - 1; i++) {
				for(int j = i + 1; j < V; j++) {
					double distance = Math.pow(Math.abs(xArr[i] - xArr[j]), 2)  +  Math.pow(Math.abs(yArr[i] - yArr[j]), 2);
					edgeList.add(new Edge(i, j, distance * E));
				}
			}
			
			makeSets();
			
			Collections.sort(edgeList);
			
			int count = 0;
			double ans = 0;
			for(Edge edge : edgeList) {
				// 사이클이 없음 -> 합치기
				if (union(edge.start, edge.end)) {
					ans += edge.weight;
					// 선택한 간선 갯수가 V - 1 -> 모든 선 연결 완료.
					if(++count == V - 1) break;
				}
			}
			;
			sb.append(Math.round(ans)).append("\n");
		}
		System.out.println(sb);
	}
}

