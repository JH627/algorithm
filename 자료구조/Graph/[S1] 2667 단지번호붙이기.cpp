#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstdlib>
#include <algorithm>
#define SIZE 26

using namespace std;

int house[SIZE][SIZE];
int house_count[SIZE];
int x_dir[4] = { -1, 1, 0, 0 };
int y_dir[4] = { 0, 0, -1, 1 };
int n, m, num;

void init() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			house[i][j] = 0;
		}
	}
	num = 0;
	m = 0;
}

void dfs(int x, int y) {
	m++;
	house[x][y] = -1;
	int new_x, new_y;
	for (int i = 0; i < 4; i++) {
		new_x = x + x_dir[i];
		new_y = y + y_dir[i];
		if (new_x >= 0 && new_x < n && new_y >= 0 && new_y < n && house[new_x][new_y] == 1) {
			dfs(new_x, new_y);
		}
	}
}

int main(void) {
	scanf("%d", &n);
	init();

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf("%1d", &house[i][j]);
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (house[i][j] == 1) {
				dfs(i, j);
				house_count[num++] = m;
				m = 0;
			}
		}
	}

	sort(house_count, house_count + num);

	printf("%d\n", num);
	for (int i = 0; i < num; i++) {
		printf("%d\n", house_count[i]);
	}
	return 0;
}