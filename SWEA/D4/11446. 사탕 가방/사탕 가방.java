import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. N개의 종류의 사탕
 * 2. 각 사탕의 갯수는 다름
 * 3. 이를 가방에 각 종류의 사탕을 합쳐서 M개를 가방에 넣음
 * 4. 이때 모든 가방안의 사탕은 같은 구성이어야 함
 * ex) N = 3, M = 4
 * 4 8 12 -> 각 가방에 사탕이 각각 1, 2, 3개 있는 가방 3개가 만들어짐.
 */

/**
 * 이 문제의 답을 구할 방법
 * 1. 최대 가방 갯수를 정한다. 모든 사탕의 합 / M = X개.
 * 1-1.만약, X가 <= 1이면 return.
 * 1-2. 만약 값이 overflow일 수 있으므로 합 M을 넘
 * 2. N 종류의 사탕을 각각 X만큼 나눈다. 이때, 몫을 전부 더했을때 X를 넘는다면 이는 가능.
 * 2-1. 만약 불가능하다면 이분탐색 end = mid - 1
 * 2-2. 만약 가능하다면 이분탐색 start = mid + 1
 */
public class Solution {
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			int N;
			long M;
			N = Integer.parseInt(st.nextToken());
			M = Long.parseLong(st.nextToken());
			long arr[] = new long[N];
			long maxVal = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Long.parseLong(st.nextToken());
				if(arr[i] > maxVal) {
					maxVal = arr[i];
				}
			}
			// left = 1 right = maxVal
			/**
			 *  left가 0이 아닌 이유 -> 어차피 while문 동안 최소 mid는 1.
			 *  여기서도 조건 만족 못하면 ans는 0에서 안바뀜!
			 */
			long left = 1;
			long right = maxVal; // 최대 maxVal개의 가방 생성 가능.
			long ans = 0;
			while(left <= right) {
				long mid = (left + right) / 2;
				long sum = 0;
				for(int i = 0; i < N; i++) {
					sum += arr[i] / mid;
					if(sum >= M) break;
				}
				if (sum >= M) { // 가방 생성 가능함.
					ans = mid;
					left = mid + 1; // 더 큰 가방 찾기.
				} else {
					right = mid - 1; // mid는 불가능하므로 더 작은 가방 찾기
				}
			}
			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
