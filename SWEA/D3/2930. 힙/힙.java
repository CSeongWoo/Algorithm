import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	static class MaxHeap {
		private int[] heap;
		private int size;

		MaxHeap(int capacity) {
			this.heap = new int[capacity + 1];
			this.size = 0;
		}

		public void add(int value) {
			heap[++size] = value; // 1번 인덱스부터 사용하면 부모 계산이 편함 (index/2)
			siftUp(size);
		}

		public Integer pop() {
			if (size == 0)
				return -1;
			int popVal = heap[1];
			heap[1] = heap[size--]; // 마지막 요소를 루트로 이동
			siftDown(1);
			return popVal;
		}

		// 위로 올라가며 부모와 비교
		public void siftUp(int index) {
			while (index > 1) {
				int parentIndex = index / 2;
				// 최대 힙 -> 큰 값인 경우 바꿈.
				if (heap[index] > heap[parentIndex]) {
					swap(index, parentIndex);
					index = parentIndex;
				} else {
					break;
				}
			}
		}

		// 아래로 내려가며 자식과 비교
		public void siftDown(int index) {
			while (index * 2 <= size) {
				int leftChild = index * 2;
				int rightChild = index * 2 + 1;
				int largerChild = leftChild;
				if (rightChild <= size && heap[rightChild] > heap[leftChild]) {
					largerChild = rightChild;
				}
				if (heap[index] < heap[largerChild]) {
					swap(index, largerChild);
					index = largerChild;
				} else {
					break;
				}
			}
		}

		private void swap(int i, int j) {
			int temp = heap[i];
			heap[i] = heap[j];
			heap[j] = temp;
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			MaxHeap mainHeap = new MaxHeap(N);
			sb.append("#").append(test_case);
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				if (command == 1) { // 삽입.
					int num = Integer.parseInt(st.nextToken());
					mainHeap.add(num);
				} else if (command == 2) {
					int popVal = mainHeap.pop();
					sb.append(" ").append(popVal);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
