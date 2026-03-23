import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 트라이
/**
 * 최대 깊이가 10인 숫자 -> 끝 노드에는 check
 */
public class Main {
	static class NumberNode {
		boolean endPoint;
		NumberNode[] nextNodes = new NumberNode[10];
		NumberNode(boolean endPoint) {
			this.endPoint = endPoint;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 0; test_case < T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			NumberNode root = new NumberNode(false);
			boolean flag = false;
			for(int i = 0; i < n; i++) {
				char[] line = br.readLine().toCharArray();
				if (flag == false && !insert(root, line)) {
					flag = true;
				}
			}
			if (flag) {
				sb.append("NO").append("\n");
			} else {
				sb.append("YES").append("\n");
			}
		}
		System.out.println(sb);
	}
	static boolean insert(NumberNode root, char[] number) {
		NumberNode curr = root;
		for(char c : number) {
			int digit = c - '0';
			if (curr.nextNodes[digit] == null) {
				curr.nextNodes[digit] = new NumberNode(false);
			} else {
				if (curr.nextNodes[digit].endPoint == true) return false;
			}
			curr = curr.nextNodes[digit];
		}
		curr.endPoint = true;
		for(int i = 0; i < 10; i++) {
			if (curr.nextNodes[i] != null) {
				return false;
			}
		}
		return true;
	}
}
