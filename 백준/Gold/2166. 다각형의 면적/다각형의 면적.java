import java.io.*;
import java.util.*;

public class Main {
	/**
	 * x[i] * y[j] - x[j] * y[j]
	 * 하고 절댓값 / 2
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		
		long[] x = new long[N];
        long[] y = new long[N];
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }
        double ans = 0;
        
        for(int i = 0; i < N; i++) {
        	int j = (i + 1) % N;
        	ans += (double)x[i] * y[j];
        	ans -= (double)x[j] * y[i];
        }
        ans = Math.abs(ans) / 2;
        String str = String.format("%.1f", ans);
        System.out.println(str);
	}
}
