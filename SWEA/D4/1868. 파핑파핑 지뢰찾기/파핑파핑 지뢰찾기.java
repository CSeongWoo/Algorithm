import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 1. 0을 클릭시 -> 8방의 모든 숫자 출력
 * 0인데 주변도 0 -> 1을 반복함.
 */
public class Solution {
	static int N;
	static char map[][];
	static int numMap[][];
	
	static int dr[] = {1, 1, 1, 0, 0, -1, -1, -1};
	static int dc[] = {1, 0, -1, 1, -1, 0, 1, -1};
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
			map = new char[N][N];
			numMap = new int[N][N];
			boolean visited[][] = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				map[i] = line.toCharArray();
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if (map[i][j] == '.') {
						findBomb(i, j);
					}
				}
			}
			int count = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if (map[i][j] == '.' && !visited[i][j] && numMap[i][j] == 0) {
						count++;
						Deque<int[]> deque = new ArrayDeque<>();
						deque.offer(new int[] {i, j});
						visited[i][j] = true;
						
						while(!deque.isEmpty()) {
							int[] node = deque.poll();
							int r = node[0];
							int c = node[1];
							
							for(int dir = 0; dir < 8; dir++) {
								int nr = r + dr[dir];
								int nc = c + dc[dir];
								if (nr >= N || nr < 0 || nc >= N || nc < 0 || map[nr][nc] == '*' || visited[nr][nc]) continue;

								if (numMap[nr][nc] == 0) deque.offer(new int[] {nr, nc});
								visited[nr][nc] = true;
							}
						}
					}
				}
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == '.' && !visited[i][j]) {
						count++;
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(count).append("\n");
		}
		System.out.println(sb);
	}
	
	static void findBomb(int r, int c) {
		for(int dir = 0; dir < 8; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if (nr >= N || nr < 0 || nc >= N || nc < 0) continue;
			if (map[nr][nc] == '*') {
				numMap[r][c]++;
			}
		}
	}
}
