import java.io.*;
import java.util.*;

public class Main {
	static class Edge {
		int to, weight; // 다음 노드, 무게

		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	static class Node implements Comparable<Node> {
		int idx, dist; // 현재 노드 번호, 현재 누적거리

		Node(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	static int N, M, K;
	static List<Edge>[] adjList; // adjList[from].add(new Edge(to, weight));
	static int minDist[][]; // n노드의 0부터 k개 까지의 최소거리를 저장.
	static int count[]; // 현재 노드에 몇번째 방문인지

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N + 1];
		minDist = new int[N + 1][K];
		count = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(minDist[i], -1);
		}
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Edge(to, weight));
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 시작도시는 무조건 1
		int start = 1;
		pq.offer(new Node(start, 0));
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			if(count[curr.idx] >= K) continue;
			minDist[curr.idx][count[curr.idx]++] = curr.dist;
			for(Edge next : adjList[curr.idx]) {
				if (count[next.to] >= K) continue;
				pq.offer(new Node(next.to, curr.dist + next.weight));
			}
		}
		for(int i = 1; i <= N; i++) {
			if (count[i] < K) {
					sb.append("-1\n");
			} else {
				// 정확히 K번째로 확정된(K-1번 인덱스) 거리를 출력합니다.
				sb.append(minDist[i][K - 1]).append("\n");
			}
		}
		System.out.println(sb);
	}

}
