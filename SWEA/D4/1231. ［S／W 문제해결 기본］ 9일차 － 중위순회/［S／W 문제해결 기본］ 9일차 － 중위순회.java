import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static char arr[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new char[N + 1];
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				char c = st.nextToken().charAt(0);
				arr[n] = c;
			}
			sb.append("#").append(test_case).append(" ");
			DFS(1);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	static void DFS(int idx) {
		if (idx > N) return;

		DFS(idx * 2);
		sb.append(arr[idx]);
		DFS(idx * 2 + 1);
	}
}
