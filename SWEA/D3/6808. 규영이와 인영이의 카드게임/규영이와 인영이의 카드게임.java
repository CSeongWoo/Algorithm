import java.io.*;
import java.util.*;

public class Solution {
	static int[] arr1;
	static int[] arr2;
	static int count1;
	static int count2;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			arr1 = new int[9];
			arr2 = new int[9];
			visited = new boolean[19];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<9; i++) {
				int num = Integer.parseInt(st.nextToken());
				arr1[i] = num;
				visited[num] = true;
			} 
			int cnt = 0;
			for(int i = 1; i<=18; i++) {
				if (!visited[i]) {
					arr2[cnt++] = i;
				}
			}
			count1 = 0;
			count2 = 0;
			permutation(0, 0, 0);
			sb.append(count1).append(" ").append(count2).append("\n");
		} 
		System.out.println(sb);
	}
	static void permutation(int index, int sum1, int sum2) {
		if (index == 9) {
			if (sum1 > sum2) {
				count1++;
			} else if (sum2 > sum1) {
				count2++;
			}
			return;
		}
		int num1 = arr1[index];
		for(int i = 0; i< 9; i++) {
			int num2 = arr2[i];
			if (visited[num2]) continue;
			visited[num2] = true;
			if (num1 > num2) {
				permutation(index + 1, sum1 + num1 + num2, sum2);
			} else if (num2 > num1) {
				permutation(index + 1, sum1, sum2  + num1 + num2);
			} else {
				permutation(index + 1, sum1, sum2);
			}
			visited[num2] = false;
		}
	}
}
