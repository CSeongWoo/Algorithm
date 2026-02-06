import java.util.*;
import java.io.*;

public class Main {
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		/**
		 * @Command
		 * add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
		 * remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
		 * check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
		 * toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
		 * all: S를 {1, 2, ..., 20} 으로 바꾼다.
		 * empty: S를 공집합으로 바꾼다.
		 */
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("add")) {
				int num = Integer.parseInt(st.nextToken());
				if (!list.contains(num)) {
					list.add(num);
				}
			} else if (command.equals("remove")) {
				int num = Integer.parseInt(st.nextToken());
				if (list.contains(num)) {
					list.remove(findNumIndex(num));
				}
			} else if (command.equals("check")) {
				int num = Integer.parseInt(st.nextToken());
				if (list.contains(num)) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			} else if (command.equals("toggle")) {
				int num = Integer.parseInt(st.nextToken());
				if (list.contains(num)) {
					list.remove(findNumIndex(num));
				} else {
					list.add(num);
				}
			} else if (command.equals("all")) {
				list.clear();
				for(int k = 1; k<= 20; k++) {
					list.add(k);
				}
			} else { // empty
				list.clear();
			}
		}
		System.out.println(sb);
	}
	
	static int findNumIndex(int num) {
		for(int i = 0; i<list.size(); i++) {
			if (list.get(i) == num) {
				return i;
			}
		}
		return 0;
	}
}
