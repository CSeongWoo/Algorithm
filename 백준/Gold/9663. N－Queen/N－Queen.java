import java.io.*;
import java.util.*;

public class Main {
	static boolean isCol[];
	static boolean diag1[]; // 우상향 대각선 -> row + col; (4,1) (3,2), (2,3) (4, 1)...
	static boolean diag2[]; // 우하향 대각선 -> row - col + N; (1,1), (2,2), (3,3)...
	static int N;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isCol = new boolean[N];
		diag1 = new boolean[N * 2];
		diag2 = new boolean[N * 2];
		ans = 0;
		nQueenUsingBoolean(0);
		System.out.println(ans);
	}
	static void nQueenUsingBoolean(int row) {
		if (row == N) {
			ans++;
			return;
		}
		for(int col = 0; col<N; col++) {
			if (isCol[col] || diag1[row + col] || diag2[col - row + N]) continue;
			isCol[col] = true;
			diag1[row + col] = true;
			diag2[col - row + N] = true;
			nQueenUsingBoolean(row + 1);
			isCol[col] = false;
			diag1[row + col] = false;
			diag2[col - row + N] = false;

		}
	}
//	static void nQueen(int depth) {
//		if (depth == N) {
//			ans++;
//			return;
//		}
//		for(int i = 0; i<N; i++) {
//			col[depth] = i; // chess[depth][i]에 퀸을 두었음. == depth행 i열에 퀸을 둠.
//			if (promising(depth)) {
//				nQueen(depth + 1);
//			}
//		}
//	}
//	static boolean promising(int depth) {
//		for(int i = 0; i<depth; i++) {
//			// 같은 행에 퀸이 있음.
//			if (col[i] == col[depth]) return false;
//			// 두 퀸 간에 열의 거리 == 행의 거리면 대각선
//			else if (depth - i == (Math.abs(col[i] - col[depth]))) return false;
//		}
//		// 조건 통과시 퀸 놓을 수 있음
//		return true;
//	}
}
