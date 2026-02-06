import java.io.*;
import java.util.*;

public class Solution {
	static int[] dr = {0 ,0, -1};
	static int[] dc = {-1, 1, 0}; 
	static boolean[][] visited;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		// 좌 상 우

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			sb.append("#").append(n).append(" ");
			int[][] map = new int[100][100];
			boolean[][] visited = new boolean[100][100];
			int cr = 0, cc = 0;
			for(int i = 0; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j< 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 2) {
						cr = i;
						cc = j;
					}
				}
			}
			while (cr > 0) {
				for (int dir = 0; dir<3; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];
					if (nr < 0 || nr >= 100 || nc < 0 || nc >= 100) {
						continue;
					}
					if(map[nr][nc] == 1 && !visited[nr][nc]) {
						visited[cr][cc] = true;
						cr = nr;
						cc = nc;
						break;
					}
				}
			}
			sb.append(cc).append("\n");
		}
		System.out.println(sb);
	}
}
