#include <iostream>
#include <vector>

using namespace std;

int main(void) {

	//knapsack
	/* max(어제의 최대가치 + 오늘 상담을 진행했을때 얻는 가치,
	* 오늘의 최대가치
	*/

	int N;
	cin >> N;
	vector<vector<int>> counselor(N + 1, vector<int>(2,0));
	vector<vector<int>> knapsack(N + 1, vector<int>(N + 1, 0));
	for (int i = 1; i <= N; i++) {
		cin >> counselor[i][0] >> counselor[i][1];
	}

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			int time = counselor[i][0] - 1;
			int value = counselor[i][1];

			if (i + time <= N) {
				knapsack[i + time][j] = max(knapsack[i-1][j] + value, knapsack[i + time][j]);
			}
			knapsack[i][j] = max(knapsack[i-1][j], knapsack[i][j]);
		}
	} 
	
	cout << knapsack[N][N];

	return 0;
}