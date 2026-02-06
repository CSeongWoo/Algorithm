import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayDeque<Character> leftQueue = new ArrayDeque<>();
		ArrayDeque<Character> rightQueue = new ArrayDeque<>();
		char[] leftLine = st.nextToken().toCharArray();
		for (char c : leftLine) {
			leftQueue.push(c);
		}
		
		int N = leftLine.length;
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			char command = st.nextToken().charAt(0);
			if (command == 'L' && !leftQueue.isEmpty()) {
				char c = leftQueue.pop();
				rightQueue.push(c);
			} else if (command == 'D' && !rightQueue.isEmpty()) {
				char c = rightQueue.pop();
				leftQueue.push(c);
			} else if (command == 'B' && !leftQueue.isEmpty()) {
				leftQueue.pop();
			} else if (command == 'P') {
				char append = st.nextToken().charAt(0);
				leftQueue.push(append);
			}
		}
		while (!leftQueue.isEmpty()) {
			char c = leftQueue.pop();
			rightQueue.push(c);
		}
		char[] ans = new char[rightQueue.size()];
		int cnt = 0;
		while (!rightQueue.isEmpty()) {
			char c = rightQueue.pop();
			ans[cnt++] = c;
		}
		StringBuilder sb = new StringBuilder();
		for (char c : ans) {
			sb.append(c);
		}
		System.out.println(sb);
	}
}
