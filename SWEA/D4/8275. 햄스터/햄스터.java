import java.io.*;
import java.util.*;

public class Solution {
	static class Save {
		int start;
		int end;
		int sum;

		public Save(int start, int end, int sum) {
			this.start = start;
			this.end = end;
			this.sum = sum;
		}
	}

	static int N, X, M;
	static int maxSum;
	static int[] maxCage;
	static int[] cage;
	static Save[] saves;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case);
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 케이지 수
			X = Integer.parseInt(st.nextToken()); // 케이지 당 최대 햄스터
			M = Integer.parseInt(st.nextToken()); // 기록 수
			cage = new int[N];
			maxCage = new int[N];
			saves = new Save[M];
			
			// 햄스터가 0인 경우 생각 필요...............
			maxSum = -1;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				// 0-index
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				int sum = Integer.parseInt(st.nextToken());
				saves[i] = new Save(start, end, sum);
			}
			solve(0, 0);
			if (maxSum != -1) {
				for (int hamZzi : maxCage) {
					sb.append(" ").append(hamZzi);
				}
			} else sb.append(" ").append(maxSum);
			sb.append("\n");

		}
		System.out.println(sb);
	}

	// 1. 모든 우리에 0~X의 햄스터 배치
	// 2. 이 cage가 모든 기록 만족하는지를 check.
	// O(M * (X + 1)^N)
	static void solve(int index, int currSum) {
		// 모든 케이지에 햄스터 넣기 완료.
		if (index == N) {
			// 기록과 일치하는지.
			if (checkSaveFile()) {
				if (currSum > maxSum) {
					maxSum = currSum;
					updateMaxCage();
				}
			}
			return;
		}

		// 0부터 X까지 햄스터 넣기.
		for (int i = 0; i <= X; i++) {
			cage[index] = i;
			solve(index + 1, currSum + i);
		}
	}

	static boolean checkSaveFile() {
		for (int i = 0; i < M; i++) {
			// 현 save
			Save save = saves[i];
			int nowSum = 0;
			for (int k = save.start; k <= save.end; k++) {
				nowSum += cage[k];
				// 현재 합이 이 기록의 합을 넘으면 실패. false
				if (nowSum > save.sum)
					return false;
			}
			if (nowSum != save.sum)
				return false;
		}
		return true;
	}

	static void updateMaxCage() {
		// maxSum이 갱신될때만
		// 0부터 재귀가 시작되었으므로 어차피 먼저 기록되는 것이 사전순.
		// ==> 굳이 같은 값일 경우 비교 하지 않음
		for (int i = 0; i < N; i++) {
			maxCage[i] = cage[i];
		}
	}
}