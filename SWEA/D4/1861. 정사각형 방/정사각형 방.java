import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int map[][];
	static int arr[][];
	static boolean visited[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	
	static int N;
	public static void main(String args[]) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			map = new int[N][N];
			visited = new boolean[N][N];
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int maxN = 0;
			int maxC = 0;
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					int currCount = DFS(i, j);
					if (maxC < currCount) {
						maxC = currCount;
						maxN = arr[i][j];
					} else if (maxC == currCount) {
						maxN = Math.min(maxN, arr[i][j]);
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(maxN).append(" ").append(maxC).append("\n");
		}
		System.out.println(sb);
	}
	
	/**
	 * + 1 방향으로 이동.
	 * 이동 불가능시 1 return -> 기저조건
	 * 
	 */
	static int DFS(int cr, int cc) {
		if (visited[cr][cc]) { //방문했으면 return
			return map[cr][cc];
		}
		visited[cr][cc] = true;
		int count = 0;
		for(int dir = 0; dir<4; dir++) {
			int nr = cr + dr[dir];
			int nc = cc + dc[dir];
			if (nr >= N || nr < 0 || nc >= N || nc < 0) continue;
			if (arr[cr][cc] + 1 == arr[nr][nc]) { // 방문 달기.
				int currCount = DFS(nr, nc); 
				count = Math.max(count, currCount);
			}
		}
		map[cr][cc] = 1 + count;
		return map[cr][cc];
	}
}
