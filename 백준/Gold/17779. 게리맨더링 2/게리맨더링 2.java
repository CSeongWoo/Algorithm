import java.util.*;
import java.io.*;

/**
 * 1. 0 <= x -d1, x + d2 <N, 0 <= y, y + d1 + d2 - 1 < N
 *
 * 3. 탐색 완료했을때 최댓값 - 최솟값의 최솟값이 현 최솟값보다 낮으면 바꾸기.
 */
public class Main {
	static int N;
	static int[][] arr;
	static int minDiff = Integer.MAX_VALUE;
	static int arrSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				arrSum += arr[r][c];
			}
		}
		for (int r = 0; r < N; r++) { // y값
			for (int c = 0; c < N; c++) { // x값
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {
						if (r + d1 + d2 >= N)
							continue;
						if (c - d1 < 0 || c + d2 >= N)
							continue;

						solve(r, c, d1, d2);
					}
				}
			}
		}

		System.out.println(minDiff);
	}

	static void solve(int x, int y, int d1, int d2) {
		int[] counts = new int[5];
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= y; j++) {
				if (i >= x && j >= y - (i - x))
					continue;
				counts[0] += arr[i][j];
			}
		}
		for (int i = 0; i <= x + d2; i++) {
			for (int j = y + 1; j < N; j++) {
				if (i >= x && j <= y + (i - x))
					continue;
				counts[1] += arr[i][j];
			}
		}
		for (int i = x + d1; i < N; i++) {
			for (int j = 0; j < y - d1 + d2; j++) {
				if (i <= (x + d1 + d2) && j >= (y - d1 + d2) - (x + d1 + d2 - i))
					continue;
				counts[2] += arr[i][j];
			}
		}
		for (int i = x + d2 + 1; i < N; i++) {
			for (int j = y - d1 + d2; j < N; j++) {
				if (i <= (x + d1 + d2) && j <= (y - d1 + d2) + (x + d1 + d2 - i))
					continue;
				counts[3] += arr[i][j];
			}
		}
		counts[4] = arrSum - (counts[0] + counts[1] + counts[2] + counts[3]);
		Arrays.sort(counts);
		int nowDiff = counts[4] - counts[0];
		minDiff = Math.min(minDiff, nowDiff);
	}

}
