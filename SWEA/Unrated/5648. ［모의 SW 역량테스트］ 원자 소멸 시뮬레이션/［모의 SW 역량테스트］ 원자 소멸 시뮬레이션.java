import java.io.*;
import java.util.*;

public class Solution {
	static class Atomic {
		int x;
		int y;
		int dir;
		int force;
		int time;
		Atomic () {}
		Atomic (int x, int y, int dir, int force, int time) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.force = force;
			this.time = time;
		}
	}
	static int map[][] = new int[4001][4001]; // 힘과 방향 저장
	static Deque<Atomic> atomicQueue;
	// 상 하 좌 우
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			atomicQueue = new ArrayDeque<>();
			for(int i = 0; i<N; i++) { // N개의 원자
				st = new StringTokenizer(br.readLine());
				int y = (Integer.parseInt(st.nextToken()) + 1000) * 2; // 0-index 
				int x = (1000 - (Integer.parseInt(st.nextToken()))) * 2; // 0-index
				int dir = Integer.parseInt(st.nextToken());
				int force = Integer.parseInt(st.nextToken());
				
				atomicQueue.offer(new Atomic(x, y, dir, force, 0));
				map[x][y] = force;
			}
			// 만난 원자가 있다면, 일단 x, y값 저장.
			Queue<int[]> deleteQueue = new ArrayDeque<>();
			int currTime = 0;
			int ans = 0;
			while(!atomicQueue.isEmpty()) {
				Atomic atom = atomicQueue.poll();
				if (atom.time > currTime) { // 시간이 갱신됨. 현재까지 deleteQueue 갱신.
					while(!deleteQueue.isEmpty()) {
						int[] point = deleteQueue.poll();
						int cx = point[0];
						int cy = point[1];
						ans += map[cx][cy];
						map[cx][cy] = 0;
					}
					currTime++;
				}
				// 소멸된 원자임.
				if (map[atom.x][atom.y] == 0) continue;
				map[atom.x][atom.y] = 0; // 이전 자리는 복구
				int nx = atom.x + dx[atom.dir];
				int ny = atom.y + dy[atom.dir];
				if (nx >= 4001 || nx < 0 || ny >= 4001 || ny < 0) { 
					map[atom.x][atom.y] = 0; // 원상복구
					continue; // 인덱스 나감.
				}
				
				if (map[nx][ny] != 0) { // 원자값 존재 == 소멸예정
					int point[] = new int[2];
					point[0] = nx;
					point[1] = ny;
					deleteQueue.offer(point);
					map[nx][ny] += atom.force;
				} else { // 원자값 존재하지 않음
					map[nx][ny] = atom.force;
					atom.x = nx;
					atom.y = ny;
					atom.time += 1;
					atomicQueue.offer(atom);
				}
			}

			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

}
