#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstdlib>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

vector<int> edge[64];
queue<int> q;
int add_x[4] = {-1, 1, 0, 0};
int add_y[4] = {0, 0, -1, 1};
int map[8][8];
int dist[64];
int n, m, i, j, u, v;
int x, y, safe, smax = 0;

void make_edge() {
	for (i = 0; i < n; i++) {
		for (j = 0; j < m; j++) {
			if (map[i][j] == 1) {
				safe--;
				continue;
			}
			u = i * m + j;
			if (map[i][j] == 2) {
				q.push(u);
				dist[u] = 0;
			}
			for (int k = 0; k < 4; k++) {
				x = i + add_x[k];
				y = j + add_y[k];
				if (x < 0 || x >= n || y < 0 || y >= m) {
					continue;
				}
				if (map[x][y] == 1) {
					continue;
				}
				v = x * m + y;
				edge[u].push_back(v);
			}
		}
	}
}

void sort_edge() {
	for (i = 0; i < n; i++) {
		sort(edge[i].begin(), edge[i].end());
	}
}

void init() {
	for (i = 0; i < 64; i++) {
		dist[i] = -1;
		edge[i].clear();
	}
	safe = n * m;
}

void bfs() {
	while (!q.empty()) {
		safe--;
		u = q.front();
		q.pop();
		for (i = 0; i < edge[u].size(); i++) {
			if (dist[edge[u][i]] == -1) {
				dist[edge[u][i]] = dist[u] + 1;
				q.push(edge[u][i]);
			}
		}
	}
}

void make_wall(int x, int depth) {
	if (depth == 3) {
		init();
		make_edge();
		sort_edge();
		bfs();
		smax = (safe > smax) ? safe : smax;
		return;
	}
	for (int i = x; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (map[i][j] == 0) {
				map[i][j] = 1;
				make_wall(i, depth + 1);
				map[i][j] = 0;
			}
		}
	}
}

int main(void) {
	scanf("%d %d", &n, &m);

	for (i = 0; i < n; i++) {
		for (j = 0; j < m; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	make_wall(0, 0);
	printf("%d", smax);
	return 0;
}