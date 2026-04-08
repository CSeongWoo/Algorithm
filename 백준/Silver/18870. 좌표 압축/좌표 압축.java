import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		Integer idxArr[] = new Integer[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			idxArr[i] = i;
		}
		Arrays.sort(idxArr, (o1, o2) -> Integer.compare(arr[o1], arr[o2]));
		int count = 0;
		for (int i = 0; i < N - 1; i++) {
			int next = idxArr[i + 1];
			int idx = idxArr[i];
			if (arr[next] == arr[idx]) {
				arr[idx] = count;
				continue;
			} else {
				arr[idx] = count++;
			}
		}
		arr[idxArr[N - 1]] = count;
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb);
	}
}
