import java.io.*;
import java.util.*;

public class Main {
/**
 * 스택 -> 기폭제가 들어오면 해당 스택의 단어가 팝.
 * 기폭제는 폭발 문자열의 끝 문자.
 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] line = br.readLine().toCharArray();
		char[] boom = br.readLine().toCharArray(); // 기폭제
		int button = boom.length - 1;
		Deque<Character> stack = new ArrayDeque<>();
		for(int i = 0; i<line.length; i++) {
			stack.push(line[i]);
			if (stack.peek() == boom[button]) {
				char[] words = new char[button + 1];
				int cnt = 0;
				words[cnt++] = stack.pop();
				
				for(int j = button - 1; j >= 0; j--) {
					if (stack.isEmpty()) { // 스택이 비어서 더이상 pop 불가
						for(int k = cnt - 1; k >= 0; k--) {
							stack.push(words[k]);
						}
						break;
					}
					words[cnt] = stack.pop();
					if (boom[j] != words[cnt++]) {
						// 기폭 실패
						for(int k = cnt - 1; k >= 0; k--) {
							stack.push(words[k]);
						}
						break;
					}
				}
			}
		}
		if (stack.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			while(!stack.isEmpty()) {
				sb.append(stack.pollLast());
			}
			System.out.println(sb);
		}
	}
}
