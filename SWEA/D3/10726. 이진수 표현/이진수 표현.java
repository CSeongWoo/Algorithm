import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();	
		int T;
		T= Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int ans = 0;
			for(int i = 0; i<N; i++) {
				ans |= (1 << i);
			}
			sb.append("#").append(test_case).append(" ");
			if ((ans & M) == ans) {
				sb.append("ON");
			}
			else {
				sb.append("OFF");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
