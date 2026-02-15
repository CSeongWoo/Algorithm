import java.io.*;
import java.util.*;

public class Main {
	static int map[][];
	static boolean visited[][];
	static List<List<int[]>> islandMap;
	static int[][] islands;
	static int dr[] = { 1, -1, 0, 0 };
	static int dc[] = { 0, 0, 1, -1 };
	
	static int N;
	static int M;
	static int islandCount;
	static int minVal;
	static class Node {
		int n;
		int length;
		Node (int n, int length) {
			this.n = n;
			this.length = length;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 섬의 최대갯수 및 최대 연결수
		islands = new int[6][6];
		visited = new boolean[N][M];
		// 각 섬이 어떤 모양과 몇개인지를 확인 및 마킹
		islandCount = 0;
		islandMap = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {// 해당 섬에 처음으로 도착
					islandCount++;
					findIsland(i, j, islandCount);
				}
			}
		}

		// MTX 준비물 - 해당 섬 번호, 거리.
		for (int i = 0; i < islandCount; i++) {
			findBridge(i);
		}
		
		// 최소비용찾기
		System.out.println(MTX());
	}
	static void findIsland(int r, int c, int num) {
		Deque<int[]> dq = new ArrayDeque<>();
		List<int[]> marking = new ArrayList<>();
		dq.offer(new int[] {r, c});
		map[r][c] = num;
		visited[r][c] = true;
		while(!dq.isEmpty()) {
			int curr[] = dq.poll();
			int cr = curr[0];
			int cc = curr[1];
			marking.add(new int[] {cr, cc});
			for (int dir = 0; dir < 4; dir++) {
				int nr = curr[0] + dr[dir];
				int nc = curr[1] + dc[dir];
				if (outOfBound(nr, nc)) continue;
				if(map[nr][nc] == 1) { // 같은 섬
					if (!visited[nr][nc]) { // 방문한거 아니라면
						visited[nr][nc] = true;
						map[nr][nc] = num;
						dq.offer(new int[] {nr, nc});
					}
				}	
			}
		}	
		islandMap.add(marking);
	}

	static void findBridge(int idx) {
		for (int[] point : islandMap.get(idx)) {
			int cr = point[0];
			int cc = point[1];
			// TODO 섬을 리스트 생성하고 삽입했음. 이제 섬을 for문을 돌면서 섬이 존재하는지만 확인.
			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				if (outOfBound(nr, nc)) continue;
				if(map[nr][nc] == map[cr][cc]) continue; // 같은 섬인지
				findIsland(nr, nc, dir, idx);// 해당방향 일직선으로 섬이 존재하는지.
			}
		}

	}
	
	static boolean outOfBound(int r, int c) {
		if (r >= N || r < 0 || c >= M || c < 0) return true;
		return false; // false = 인덱스 안
	}
	
	static void findIsland(int nr, int nc, int dir, int idx) { 
		int cnt = 0;
		while(!outOfBound(nr, nc)) {
			if(map[nr][nc] == idx + 1) {
				return;
			}
			if(map[nr][nc] != 0 && map[nr][nc] != idx + 1) { // 다른섬이며 방문하지 않은 노드
				if (cnt != 1) {
					if (islands[idx][map[nr][nc] - 1] == 0) {
						islands[idx][map[nr][nc] - 1] = cnt;
					}
					else { 
						islands[idx][map[nr][nc] - 1] = Math.min(islands[idx][map[nr][nc] - 1], cnt); 
					}
				}
				return;
			}
			nr += dr[dir];
			nc += dc[dir];
			cnt += 1;
		}
		return;
	}
	
	static int MTX() {
		boolean dfsVisited[] = new boolean[islandCount];
		// 0번 섬부터
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2)
				-> Integer.compare(n1.length, n2.length));
		pq.add(new Node(0, 0));
		int totalCost = 0;
		int connectedIslands = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			/**
			 * 두번 방문 확인하는 이유:
			 * pq 특성상 큐 삽입시에는 방문하지 않은 섬이 큐에서 나왔을땐 이미 방문했을 수도 있음.
			 */
			if (dfsVisited[node.n]) continue;
			// 방문 표시
			dfsVisited[node.n] = true;
			totalCost += node.length;
			connectedIslands++;
			// 모든 섬 방문시 그냥 break
			if (connectedIslands == islandCount) break;
			
			for(int i = 0; i < islandCount; i++) {
				if (islands[node.n][i] != 0 && !dfsVisited[i]) {
					pq.offer(new Node(i, islands[node.n][i]));
				}
			}
		}
		// 모든 섬 방문시.
		if (connectedIslands == islandCount) {
			return totalCost;
		}
		return -1;
	}
}
