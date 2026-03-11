import java.io.*;
import java.util.*;
/**
 * 메모리: 96,324 kb
 * 시간: 746 ms
 */

public class Solution {
	
	static class Node implements Comparable<Node> {
		int vertex;
		double weight;
		Node next; // 노드에 연결된 간선들
		
		Node(int vertex, double weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.weight, o.weight);
		}
		
	}

	static int V;
	
	static int[] xArr;
	static int[] yArr;
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
			xArr = new int[V];
			yArr = new int[V];
			Node[] adjList = new Node[V]; // 노드 갯수
			double[] visited = new double[V]; // 연결 여부
			
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
			// 모든 섬 간의 거리 구하기 + 간선 추가하기. => E
			for(int from = 0; from < V - 1; from++) {
				for(int to = from + 1; to < V; to++) {
					double distance = Math.pow(Math.abs(xArr[from] - xArr[to]), 2)  +  Math.pow(Math.abs(yArr[from] - yArr[to]), 2);
					adjList[from] = new Node(to, distance * E, adjList[from]);
					adjList[to] = new Node(from, distance * E, adjList[to]);
				}
			}
			
			Arrays.fill(visited, -1);
			
			visited[0] = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			for(Node temp = adjList[0]; temp != null; temp = temp.next) {
				pq.offer(temp);
			}
			
			double ans = 0;
			while(!pq.isEmpty()) {
				Node curr = pq.poll();
				if (visited[curr.vertex] != -1) continue; // 이미 방문 완료된 노드.
				
				ans += curr.weight;
				visited[curr.vertex] = curr.weight;
				for(Node next = adjList[curr.vertex]; next != null; next = next.next) {
					if (visited[next.vertex] == -1) {
						pq.offer(next);
					}
				}
			}
			sb.append(Math.round(ans)).append("\n");
		}
		System.out.println(sb);
	}
}

