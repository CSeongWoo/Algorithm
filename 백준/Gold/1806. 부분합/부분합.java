import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int sum = 0;
		int arr[] = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		if (sum < S) {
			System.out.println(0);
			return;
		}
		int left = 0;
		int right = 0;
		int count = 0;
		int window = 0;
		int minCount = N + 1;
		while(left <= right && right < N) {
			window += arr[right];
			count++;
			while(left <= right && window >= S) {
				minCount = Math.min(count, minCount);
				window -= arr[left];
				left++;
				count--;
			}
			right++;
		}
		System.out.println(minCount);
	}

}
