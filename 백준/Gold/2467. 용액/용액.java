import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		// 배열은 오름차순으로 정렬되어 있음.
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int start = 0;
		int end = N - 1;
		int minVal = Integer.MAX_VALUE;
		int minS = 0;
		int minE = N -1;
		while (start < end) {
			int sum = arr[start] + arr[end];
			if (Math.abs(sum) < minVal) {
				minS = start;
				minE = end;
				minVal = Math.abs(sum);
			}
			if (sum < 0) {
				start += 1;
			} else if (sum > 0) {
				end -= 1;
			} else {
				minVal = 0;
				break;
			}
		}
		System.out.println(arr[minS] + " " + arr[minE]);
	}

}
