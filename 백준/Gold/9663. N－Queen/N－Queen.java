import java.io.*;
import java.util.*;

public class Main {
	static int col[];
	static int N;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new int[N];
		ans = 0;
		nQueen(0);
		System.out.println(ans);
	}
	static void nQueen(int depth) {
		if (depth == N) {
			ans++;
			return;
		}
		for(int i = 0; i<N; i++) {
			col[depth] = i; // chess[depth][i]에 퀸을 두었음. == depth행 i열에 퀸을 둠.
			if (promising(depth)) {
				nQueen(depth + 1);
			}
		}
	}
	static boolean promising(int depth) {
		for(int i = 0; i<depth; i++) {
			// 같은 행에 퀸이 있음.
			if (col[i] == col[depth]) return false;
			// 두 퀸 간에 열의 거리 == 행의 거리면 대각선
			else if (depth - i == (Math.abs(col[i] - col[depth]))) return false;
		}
		// 조건 통과시 퀸 놓을 수 있음
		return true;
	}
}
