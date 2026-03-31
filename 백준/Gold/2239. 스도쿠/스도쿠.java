import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[][] map;
	static boolean[][] rowCheck;
	static boolean[][] colCheck;
	static boolean[][] gridCheck;
	static boolean finished = false;
	static List<Point> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		rowCheck = new boolean[9][9]; // 1 ~ 9
		colCheck = new boolean[9][9];
		gridCheck = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			char[] arr = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				map[i][j] = arr[j] - '0';
				if (map[i][j] != 0) { // 숫자가 아님!
					rowCheck[i][map[i][j] - 1] = true;
					colCheck[j][map[i][j] - 1] = true;
					int index = (i / 3) * 3 + (j / 3); // 1, 1 = 1 // 2, 4 = 1 // 7, 1 = 6
					gridCheck[index][map[i][j] - 1] = true;
				} else {
					list.add(new Point(i, j));
				}
			}
		}
		DFS(0);
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	static void DFS(int cnt) {
	    // 1. 종료 조건: 모든 빈칸을 다 채웠을 때
	    if (cnt == list.size()) {
	        finished = true; // 탐색 종료 플래그
	        return;
	    }
	    Point p = list.get(cnt);
		for(int i = 0; i < 9; i++) {
			int index = (p.x / 3) * 3 + (p.y / 3);
			if (!rowCheck[p.x][i] && !colCheck[p.y][i] && !gridCheck[index][i]) {
				map[p.x][p.y] = i + 1;
				rowCheck[p.x][i] = colCheck[p.y][i] = gridCheck[index][i] = true;
				DFS(cnt + 1);
				if (finished) return;
				map[p.x][p.y] = 0;
				rowCheck[p.x][i] = colCheck[p.y][i] = gridCheck[index][i] = false;
			}
		}
	}
}
