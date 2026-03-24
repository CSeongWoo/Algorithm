import java.io.*;
import java.util.*;
 
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

        int K = 1999;
        System.out.println(K);

        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 1000; i++) {
            sb.append(1).append(" ");
        }
        for (int i = 0; i < 999; i++) {
            sb.append(1000).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
	