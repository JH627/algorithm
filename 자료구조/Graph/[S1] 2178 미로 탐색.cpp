#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstdlib>
#include <queue>

using namespace std;

int maze[101][101];
int plus_x[4] = {-1, 1, 0, 0};
int plus_y[4] = {0, 0, -1, 1};
int n, m, dist;

void bfs(int x, int y) {
	queue<pair<int,int>> q;
	pair<int,int> now;
	int new_x, new_y;
	q.push(make_pair(x, y));
	dist = 0;
	while (!q.empty()) {
		now = q.front();
		q.pop();
		x = now.first;
		y = now.second;
		if ((x == n-1) && (y == m-1)) {
			break;
		}
		for (int i = 0; i < 4; i++) {
			new_x = x + plus_x[i];
			new_y = y + plus_y[i];
			if (new_x >= 0 && new_x < n && new_y >= 0 && new_y < m) {
				if (maze[new_x][new_y] == 1) {
					q.push(make_pair(new_x, new_y));
					maze[new_x][new_y] = maze[x][y] + 1;
				}
			}
		}
	}
}

int main(void) {
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%1d", &maze[i][j]);
		}
	}
	bfs(0, 0);
	printf("%d", maze[n - 1][m - 1]);
	return 0;
}