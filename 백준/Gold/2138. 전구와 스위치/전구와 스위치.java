import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N;
	static char[] currStat;
	static char[] goal;
	static int minVal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		currStat = new char[N];
		currStat = br.readLine().toCharArray();
		goal = new char[N];
		goal = br.readLine().toCharArray();
		minVal = Integer.MAX_VALUE;
		char[] originStat = Arrays.copyOf(currStat, N);
		findGoal(1, 0);
		currStat = Arrays.copyOf(originStat, N);
		for (int i = 0; i < 2; i++) {
			currStat[i] = currStat[i] == '0' ? '1' : '0';
		}
		findGoal(1, 1);
		System.out.println(minVal == Integer.MAX_VALUE ? -1 : minVal);
	}

	static void findGoal(int idx, int count) {
		if (idx == N) {
			if (Arrays.equals(currStat, goal)) {
				minVal = Math.min(count, minVal);
			}
			return;
		}
		if (currStat[idx - 1] != goal[idx - 1]) {
			for (int i = idx - 1; i < idx + 2; i++) {
				if (i < 0 || i >= N)
					continue;
				currStat[i] = currStat[i] == '0' ? '1' : '0';
			}
			findGoal(idx + 1, count + 1);
		} else {
			findGoal(idx + 1, count);
		}
	}
}
