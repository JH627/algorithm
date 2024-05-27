#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#define MAX 500

using namespace std;

int dp[MAX][MAX]{};
int h[MAX][MAX]{};
int n, m;

int dx[4] = { -1, 0, 0, 1 };
int dy[4] = { 0, 1, -1, 0 };

int dfs(int x, int y) {
	if (x == n - 1 && y == m - 1) {
		return 1;
	}
	if (dp[x][y] != -1) {
		return dp[x][y];
	}
	dp[x][y] = 0;
	int nx, ny;
	for (int k = 0; k < 4; k++) {
		nx = x + dx[k];
		ny = y + dy[k];
		if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
			if (h[nx][ny] < h[x][y]) {
				dp[x][y] = dp[x][y] + dfs(nx, ny);
			}
		}
	}
	return dp[x][y];
}

int main(void){
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%d", &h[i][j]);
			dp[i][j] = -1;
		}
	}
	printf("%d", dfs(0, 0));
	return 0;
}