import java.io.IOException;
import java.util.*;

public class Main {
	// dp[i][j] -> 문자열 s1의 i번째 이후부터, 그리고 문자열 s2의 j번째 이후에 중복되는 가장 긴 문자열의 길이.

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
        // 테스트 케이스 개수를 모르기 때문에 EOF까지 반복
        while (sc.hasNext()) {
            String s1 = sc.next();
            String s2 = sc.next();
            
            int n = s1.length();
            int m = s2.length();
            int[][] dp = new int[n + 1][m + 1];

            // 1. LCS 길이 테이블 채우기
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) 
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    else 
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            StringBuilder sb = new StringBuilder();
            int i = n;
            int j = m;
            while(i > 0 && j > 0) {
            	if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            		sb.append(s1.charAt(i - 1));
            		i--;
            		j--;
            	} else if (dp[i - 1][j] >= dp[i][j - 1]) {
            		sb.append(s1.charAt(i - 1));
            		i--;
            	} else {
            		sb.append(s2.charAt(j - 1));
            		j--;
            	}
            }
            while(i > 0) sb.append(s1.charAt(--i));
            while(j > 0) sb.append(s2.charAt(--j));
            System.out.println(sb.reverse().toString());
        }
	}
}
