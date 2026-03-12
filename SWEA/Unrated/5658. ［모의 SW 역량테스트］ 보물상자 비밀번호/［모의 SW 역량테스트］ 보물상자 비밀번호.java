import java.io.*;
import java.util.*;

public class Solution {
	static class Node {
		int val;
		Node next = null;
		
		Node(int val) {
			this.val = val;
		}
	}
	static int N, K;
	static Node head;
	static Node tail;
	static TreeSet<Integer> set = new TreeSet<>();
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			head = null;
			tail = null;
			set.clear();
			String line = br.readLine();
			for(int i = 0; i < N; i++) {
				int val = Integer.parseInt(String.valueOf(line.charAt(i)), 16);
				Node node = new Node(val);
				if (head == null) {
					head = node;
					tail = node;
					node.next = head;
				} else {
					tail.next = node;
					node.next = head;
					tail = node;
				}
			}
			for(int i = 0; i < N / 4; i++) {
				moveNodes();
			}
			List<Integer> list = new ArrayList<>(set);
			int ans = list.get(list.size() - K);
			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	static void moveNodes() {
		head = head.next;
		tail = tail.next;
		addTreeSet();
	}
	static void addTreeSet() {
		Node curr = head;
		int len = N / 4;
		int nodeCount = 0;
		int count = 0;
		int num = 0;
		while(nodeCount < N) {
			count++;
			nodeCount++;
			num = num * 16 + curr.val;
			if (count == len) {
				set.add(num);
				count = 0;
				num = 0;
			}
			curr = curr.next;
		}
	}
}
