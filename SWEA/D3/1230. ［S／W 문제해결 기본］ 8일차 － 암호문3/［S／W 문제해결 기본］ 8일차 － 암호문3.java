import java.util.*;
import java.io.*;
/**
 * 메모리: 24,576 kb 시간: 80 ms
 */
public class Solution {
	static List<String> line;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			sb.append("#").append(test_case);

			st = new StringTokenizer(br.readLine());
			line = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				line.add(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			int commandCount = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < commandCount; i++) {
				char command = st.nextToken().charAt(0);
				if (command == 'I') {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					String[] appendLine = new String[y];
					for (int j = 0; j < y; j++) {
						appendLine[j] = st.nextToken();
					}
					
					InsertReader ir = new InsertReader(x, y, appendLine);
					insertPw(ir);
				} else if (command == 'D') {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					deletePw(x, y);
				}else if (command == 'A') {
					int y = Integer.parseInt(st.nextToken());
					String[] appendLine = new String[y];
					for (int j = 0; j < y; j++) {
						appendLine[j] = st.nextToken();
					}
					InsertReader ir = new InsertReader(line.size() - 1, y, appendLine);
					insertPw(ir);
				}
				
			}
			for(int i = 0; i<10; i++) {
				sb.append(" ").append(line.get(i));
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	static class InsertReader {
		int x;
		int y;
		String[] appendLine;
		
		InsertReader() {}
		InsertReader(int x, int y, String[] appendLine) {
			this.x = x;
			this.y = y;
			this.appendLine = appendLine;
		}
	}
	
	static void insertPw(InsertReader ir) {
		int start = ir.x;
		int end = ir.y;
		String[] appendLine = ir.appendLine;
		for(int k = 0; k< end; k++) {
			int idx = start + k;
			line.add(idx, appendLine[k]);
		}
	}
	
	static void deletePw(int idx, int length) {
		for (int i = 0; i< length; i++) {
			line.remove(idx);
		}
	}
}
