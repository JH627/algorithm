#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstdlib>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

vector<int> edge[2500];
queue<int> visit;
int w, h;
int map[50][50];
int pre[2500], post[2500];
int clock, vcount;

int x, y, u, v;
int add_x[8] = {-1, -1, -1, 0, 0, 1, 1 ,1};
int add_y[8] = {-1, 0, 1, -1, 1, -1, 0 ,1};

void init() {
	for (int i = 0; i < w; i++) {
		for (int j = 0; j < h; j++) {
			map[i][j] = 0;
		}
	}
	for (int i = 0; i < w * h; i++) {
		edge[i].clear();
		pre[i] = -1;
		post[i] = -1;
	}
	clock = 0;
	vcount = 0;
}

void dfs(int v) {
	pre[v] = clock++;
	for (int i = 0; i < edge[v].size(); i++) {
		if (pre[edge[v][i]] == - 1) {
			dfs(edge[v][i]);
		}
	}
	post[v] = clock++;
}

void add_edge() {
	for (int i = 0; i < w; i++) {
		for (int j = 0; j < h; j++) {
			if (map[i][j] == 0) {
				continue;
			}
			u = i * h + j;
			for (int k = 0; k < 8; k++) {
				x = i + add_x[k];
				y = j + add_y[k];
				if (x < 0 || x >= w || y < 0 || y >= h) {
					continue;
				}
				v = x * h + y;
				if (map[x][y] == 0) {
					continue;
				}
				edge[u].push_back(v);
			}
		}
	}
}

void sort_edge() {
	for (int i = 0; i < w; i++) {
		if (edge[i].size() == 0) {
			continue;
		}
		sort(edge[i].begin(), edge[i].end());
	}
}

int main(void) {
	while (1) {
		scanf("%d %d", &h, &w);
		if (w == 0 && h == 0) {
			break;
		}
		init();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				scanf("%d", &map[i][j]);
			}
		}
		add_edge();
		sort_edge();

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				v = i * h + j;
				if (map[i][j] == 1 && pre[v] == -1) {
					dfs(v);
					vcount++;
				}
			}
		}
		printf("%d\n", vcount);
	}
	return 0;
}