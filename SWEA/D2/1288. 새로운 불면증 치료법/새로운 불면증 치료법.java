import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T;
		T= Integer.parseInt(st.nextToken());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int sheeeeeeep = N;
			char[] arr = ("" + sheeeeeeep).toCharArray();
			int bitmask = 0;
			int ans = 1023; // 0부터 9번 비트까지 1 = 2^10 - 1
			while(bitmask != ans) {
				for (char c : arr) {
					int num = c - '0';
					bitmask |= (1 << num);
				}
				sheeeeeeep += N; // 양에 N 더하기
				arr = ("" + sheeeeeeep).toCharArray();
			}
			sb.append("#").append(test_case).append(" ").append(sheeeeeeep - N).append("\n");
		}
		System.out.println(sb);
	}
}
