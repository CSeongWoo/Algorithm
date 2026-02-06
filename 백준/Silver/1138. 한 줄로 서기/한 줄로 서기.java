import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] line = new int[N];
		Arrays.fill(line, -1);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int tall = 1; tall < N + 1; tall++) {
			int leftCount = Integer.parseInt(st.nextToken());
			
			int count = 0;
			for(int i = 0; i<N; i++) {
				if(line[i] == -1) {
					if (count == leftCount) {
						line[i] = tall;
						break;
					}
					count++;
				}
			}
		}
		for(int i = 0; i<N; i++) {
			System.out.print(line[i] + " ");
		}

		return;
	}
}