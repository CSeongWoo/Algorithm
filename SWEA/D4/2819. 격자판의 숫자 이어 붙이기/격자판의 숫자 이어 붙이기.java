import java.io.*;
import java.util.*;

public class Solution {
	static final int END_COUNT = 7;
	
	static char[][] map;
	static HashSet<String> set;
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			map = new char[4][4];
			set = new HashSet<>();
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 4; j++) {
					map[i][j] = st.nextToken().charAt(0);
				}
			}
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					DFS(i, j, String.valueOf(map[i][j]), 0);
				}
			}
			sb.append("#").append(test_case).append(" ").append(set.size()).append("\n");
		}
		System.out.println(sb);
	}
	
	static void DFS(int r, int c, String line, int count) {
		if (count == END_COUNT) {
			set.add(line);
			return;
		}
		for(int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
			String nextLine = line + String.valueOf(map[r][c]);
			DFS(nr, nc, nextLine, count + 1);
		}
		return;
	}
}
