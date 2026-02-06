import java.io.*;
import java.util.*;

/**
 * N개의 과자 중에 두 개를 골랐을 때, M을 넘지 않는 최댓값 찾기.
 */
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case);
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			int start = 0;
			int end = N - 1;
			int maxVal = -1;
			if(arr[0] + arr[1] > M) {
				sb.append(" ").append(-1).append("\n");
				continue;
			}
			while(start < end) {
				int nowVal = arr[start] + arr[end];
				if (nowVal > M) {
					end--;
				} else if (nowVal < M){
					maxVal = Math.max(maxVal, nowVal);
					start++;
				} else {
					maxVal = M;
					break;
				}
			}
			sb.append(" ").append(maxVal).append("\n");
		}
		System.out.println(sb);
		
	}
}

