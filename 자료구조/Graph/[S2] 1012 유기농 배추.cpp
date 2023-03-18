#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstdlib>
#include <algorithm>

using namespace std;

int m, n, num;
int field[51][51];
int x_dir[4] = { -1, 1, 0, 0 };
int y_dir[4] = { 0, 0, -1, 1 };

void init() {
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			field[i][j] = 0;
		}
	}
	num = 0;	
}

void dfs(int x, int y) {
	field[x][y] = -1;
	int new_x, new_y;
	for (int i = 0; i < 4; i++) {
		new_x = x + x_dir[i];
		new_y = y + y_dir[i];
		if (new_x >= 0 && new_x < m && new_y >= 0 && new_y < n && field[new_x][new_y] == 1) {
			dfs(new_x, new_y);
		}
	}
}

int main(void) {
	int t, k;
	int x, y;
	scanf("%d", &t);

	for (int q = 0; q < t; q++) {
		scanf("%d %d %d", &m, &n, &k);
		init();
		for (int i = 0; i < k; i++) {
			scanf("%d %d", &x, &y);
			field[x][y] = 1;
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (field[i][j] == 1) {
					dfs(i, j);
					num++;
				}
			}
		}
		printf("%d\n", num);
	}

	return 0;
}