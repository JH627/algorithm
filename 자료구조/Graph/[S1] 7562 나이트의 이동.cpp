#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <queue>

using namespace std;

queue<pair<int, int>> q;
int map[300][300];

int addx[8] = {-1, -2, -2, -1, 1, 2, 2, 1};
int addy[8] = {-2, -1, 1, 2, 2 ,1 ,-1, -2};

void clearMap() {
	for (int i = 0; i < 300; i++) {
		for (int j = 0; j < 300; j++) {
			map[i][j] = 0;
		}
	}
}

void clearQueue() {
	while (!q.empty()) {
		q.pop();
	}
}

int main(void) {
	int t, l;
	int x1, y1, x2, y2;
	pair<int, int> now, next;
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		scanf("%d", &l);
		scanf("%d %d", &x1, &y1);
		scanf("%d %d", &x2, &y2);

		clearMap();
		q.push(make_pair(x1, y1));
		while (!q.empty()) {
			now = q.front();
			q.pop();
			if (now.first == x2 && now.second == y2) {
				break;
			}
			for (int i = 0; i < 8; i++) {
				next.first = now.first + addx[i];
				next.second = now.second + addy[i];
				if (map[next.first][next.second] != 0) {
					continue;
				}
				if (next.first >= l || next.first < 0) {
					continue;
				}
				if (next.second >= l || next.second < 0) {
					continue;
				}
				map[next.first][next.second] = map[now.first][now.second] + 1;
				q.push(make_pair(next.first, next.second));
			}
		}
		clearQueue();
		printf("%d\n", map[x2][y2]);
	}
	return 0;
}