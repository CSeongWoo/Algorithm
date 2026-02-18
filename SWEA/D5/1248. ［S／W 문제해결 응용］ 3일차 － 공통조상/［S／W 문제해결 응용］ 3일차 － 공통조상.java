import java.io.*;
import java.util.*;

/**
 * 정점갯수, 간선 갯수, 공통 조상을 찾는 두 정점 번호 각자 레벨에 대한 구분은 연결 리스트로 진행.
 */
public class Solution {
	static class Point {
		int node;
		int lv;

		Point(int node, int lv) {
			this.node = node;
			this.lv = lv;
		}
	}
	static List<Integer> tree[];
	static List<Integer> reverseTree[];
	static int subTrees[];
	static int depth[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			// 정점, 간선, 정점 번호 1 2
			int V, E, n1, n2;
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			n1 = Integer.parseInt(st.nextToken()) - 1;
			n2 = Integer.parseInt(st.nextToken()) - 1;
			tree = new ArrayList[V];
			reverseTree = new ArrayList[V];
			for (int i = 0; i < V; i++) {
				tree[i] = new ArrayList<>();
				reverseTree[i] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				tree[start].add(end);
				reverseTree[end].add(start);
			}
			// 각 정점들의 깊이를 저장하는 배열.
			depth = new int[V];
			// 서브트리의 크기를 저장하는 배열
			subTrees = new int[V];
			// 0번 노드(==1)은 lv 1
			depth[0] = 1;
			findSubTree(0, 0);
			int lv1 = depth[n1];
			int lv2 = depth[n2];
			// 두 노드 중 더 낮은 레벨의 노드에 맞추기
			// lv1이 더 깊음
			if (lv1 > lv2) {
				while(lv1 != lv2) {
					// 모든 reverseTree는 각 리스트마다 1개만 가지고 있음.
					n1 = reverseTree[n1].get(0);
					lv1 = depth[n1];
				}
			} else if (lv1 < lv2) { // l2가 더 깊음
				while(lv2 != lv1) {
					n2 = reverseTree[n2].get(0);
					lv2 = depth[n2];
				}
			}
			// 같은 번호 찾을때까지.
			while (n1 != n2) {
				n1 = reverseTree[n1].get(0);
				n2 = reverseTree[n2].get(0);
			}
			sb.append("#").append(test_case).append(" ")
				.append(n1 + 1).append(" ").append(subTrees[n1]).append("\n");
		}
		System.out.println(sb);
	}
	// 각 연결된 노드마다 전부 탐색하면 됨.
	static int findSubTree(int idx, int lv) {
		subTrees[idx] = 1;
		depth[idx] = lv;
		for (int next : tree[idx]) {
			subTrees[idx] += findSubTree(next, lv + 1);
		}
		return subTrees[idx];
	}
}
