import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int H, W;
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int[] arr = new int[W];
		st = new StringTokenizer(br.readLine());
		int maxLeftTop = 0, maxRightTop = 0;
		for (int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[maxLeftTop] < arr[i]) {
				maxLeftTop = i;
				maxRightTop = i;
			}
		}
		int startLeft = maxLeftTop - 1;
		int startRight = maxRightTop + 1;		
		int waterSum = 0;
		// 왼쪽 순회
		while (startLeft > 0) {

			int maxLeft = startLeft;
			// 가장 높은 탑 기준 왼쪽에서 그 다음 높은 탑 찾기.
			for (int i = startLeft; i >= 0; i--) {
				if (arr[maxLeft] <= arr[i]) {
					maxLeft = i;
				}
			}
			
			// 가장 높은 탑의 높이
			int height = arr[maxLeft];
			// 가장 높은 탑의 높이에서 그 다음 높은탑까지 전부 빼기.
			for (int i = startLeft; i >= maxLeft; i--) {
				waterSum += height - arr[i];
			}
			// 그 다음 높은탑이 다시 위의 과정 반복
			maxLeftTop = maxLeft;
			startLeft = maxLeftTop - 1;
			
		}
		
		// 오른쪽 순회
		while(startRight < W - 1) {

			int maxRight = startRight;
			
			for(int i = startRight; i< W; i++) {
				if (arr[maxRight] <= arr[i]) {
					maxRight = i;
				}
			}
			// 가장 높은 탑의 높이
			int height = arr[maxRight];
			// 가장 높은 탑의 높이에서 그 다음 높은탑까지 전부 빼기.
			for (int i = startRight; i< maxRight; i++) {
				waterSum += height - arr[i];
			}
			// 그 다음 높은탑이 다시 위의 과정 반복
			maxRightTop = maxRight;
			startRight = maxRightTop + 1;
		}
		
		System.out.println(waterSum);
	}
}
