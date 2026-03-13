import java.io.*;
import java.util.*;

/**
 * 메모리: 110,264 kb 시간: 2428 kb
 * 
 * @author SSAFY
 *
 */
public class Main {
	static class Node {
		int vertex, weight;
		Node next;

		Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}

	static class Vertex {
		int idx;
		int dist;

		Vertex(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}

	static final int INF = Integer.MAX_VALUE;

	static Node[] adjList;
	static boolean[] visited;
	static int[] minDist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		adjList = new Node[V + 1];
		visited = new boolean[V + 1];
		minDist = new int[V + 1];
		int start = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}

		Arrays.fill(minDist, INF);
		minDist[start] = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.dist, v2.dist));
		pq.offer(new Vertex(start, minDist[start]));
		while (!pq.isEmpty()) {
			Vertex vertex = pq.poll();
			if (visited[vertex.idx])
				continue;
			visited[vertex.idx] = true;
			for (Node temp = adjList[vertex.idx]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && minDist[temp.vertex] > temp.weight + vertex.dist) {
					minDist[temp.vertex] = temp.weight + vertex.dist;
					pq.offer(new Vertex(temp.vertex, minDist[temp.vertex]));
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			System.out.println(minDist[i] == INF ? "INF" : minDist[i]);
		}
	}
}
