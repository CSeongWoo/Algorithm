import java.util.ArrayDeque;
import java.util.Scanner;

public class Solution {
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		StringBuilder sb = new StringBuilder();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int arr[] = new int[N];
			for(int i = 0; i<N; i++) {
				arr[i] = sc.nextInt();
			}

			int ans = 0;
			int leftCount = 0;
			int rightCount = 0;
			ArrayDeque<Integer> stack = new ArrayDeque<>();
			stack.push(arr[0]);
			boolean isRaise = true;
			for(int i = 1; i<N; i++) {
				if (isRaise) { // 현재 증가 상태
					if (arr[i] < stack.peek()) { // 증가 상태 꺠짐
						leftCount = 0;
						while (!stack.isEmpty()) {
							stack.pop(); // pop
							leftCount++;
						}
						isRaise = false; // 감소 상태로 변경
						if(leftCount != 0) leftCount--; // 엄높산도 포함했으므로 - 1
					}
					stack.push(arr[i]);
				} else { // 현재 감소 상태
					 if (arr[i] > stack.peek()) { // 감소 상태 꺠짐
						 // == 엄높산 계산
						 rightCount = 0;
						 int next = stack.peek(); // 다음 스택 시작 지점.
						 while(!stack.isEmpty()) {
							 stack.pop();
							 rightCount++;
						 }
						 stack.push(next); // 다음 스택 시작 지점
						 isRaise = true; // 증가 상태로 변경
						 int currCount = leftCount * rightCount; // 경우의 수 = 왼쪽 * 오른쪽
//						 System.out.println("idx: " + i + " lCount :" + leftCount + " rCount :" + rightCount);
						 ans += currCount;
					 }
					 stack.push(arr[i]); // 현재 값도 넣기.
				}
			}
			if (!isRaise) { // 감소 중 끝남.
				 rightCount = 0;
				 while(!stack.isEmpty()) {
					 stack.pop();
					 rightCount++;
				 }
				 int currCount = leftCount * rightCount; // 경우의 수 = 왼쪽 * 오른쪽
				 ans += currCount;
			}
			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
