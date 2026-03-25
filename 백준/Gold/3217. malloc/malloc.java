import java.io.*;
import java.util.*;

public class Main {
	static class Point {
	    int start, end;
	    Point(int start, int end) {
	        this.start = start;
	        this.end = end;
	    }

	}
	/**
	 * 1. malloc -> 현재 남은 공간을 start 포인터 순으로 정렬 + 사이즈 맞는거 찾기
	 * 2. free -> 현재 점유중인 공간 + 직후 남은 공간의 start까지 연결
	 */
	static int[] isUsed = new int[100001]; // 사용중인 공간 - start 공간에서 시작해서 몇개 쓰는지 -> 그냥 해당 위치값 넣어주면 됨
	static TreeSet<Point> remainSet = new TreeSet<>((a, b) -> a.start - b.start); // 남은 공간 -> free할 블록의 시작 - 1, 끝 + 1확인
	static Map<String, Integer> arrMap = new HashMap<String, Integer>(); // 이름 - 현재 메모리 내 인덱스 시작 위치 저장.
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		remainSet.add(new Point(1, 100000));
		int N = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; i++) {
			String line = br.readLine().trim();
			if(line.isEmpty()) continue;
			String[] tokens = line.split("[=();]+"); // 대괄호 안에 있는 문자들을 잘라라, +는 연속해서 있어도 자르라는 뜻
			if (line.contains("malloc")) {
				malloc(tokens[0], Integer.parseInt(tokens[2]));
			} else if (tokens[0].equals("free")) {
				free(tokens[1]);
			} else if (tokens[0].equals("print")) {
				sb.append(print(tokens[1])).append('\n');
			}
        }
        System.out.print(sb);
	}
	
	static int malloc(String name, int size) {
		Point target = null;
		for (Point p : remainSet) {
			if (p.end - p.start + 1 >= size) {
				target = p;
				arrMap.put(name, 0);
				break;
			}
		}
		if (target == null) {
			arrMap.put(name, 0);
			return 0;
		}
		remainSet.remove(target);

		if (target.end - target.start + 1 > size) {
			remainSet.add(new Point(target.start + size, target.end));
		}
		arrMap.put(name, target.start);
		isUsed[target.start] = size;
		return target.start;
	}
	static void free(String name) {
		if (!arrMap.containsKey(name) || arrMap.get(name) == 0) return;
		int start = arrMap.get(name);
		int end = start + isUsed[start] - 1;
		isUsed[start] = 0;
		arrMap.put(name, 0);
		Point curr = new Point(start, end);
		Point prev = remainSet.lower(curr);
		Point next = remainSet.higher(curr);
		if (next != null && curr.end + 1 == next.start) {
			end = next.end;
			remainSet.remove(next);
		}
		if(prev != null && prev.end + 1 == curr.start) {
			start = prev.start;
			remainSet.remove(prev);
		}
		remainSet.add(new Point(start, end));
	}
	static int print(String name) {
		if (arrMap.containsKey(name) && arrMap.get(name) != 0) {
			return arrMap.get(name);
		}
		return 0;
	}
}
