import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String args[]) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T;
		T= Integer.parseInt(st.nextToken());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			int N, M;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			int res = 0;
			HashSet<String> hashSet = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				String line = st.nextToken();
				hashSet.add(line);
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				String line = st.nextToken();
				if (hashSet.contains(line)) {
					res++;
				}
			}
			sb.append("#").append(test_case).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
	}
}
