#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstdlib>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int n, m, s;
int u, v, x, y;
int maxd = -1;
int vcount = 0;
int box[1002][1002];
int dist[1000002];
vector<int> edge[1000002];
queue<int> q;

int add_x[4] = {-1, 1, 0, 0};
int add_y[4] = {0, 0, -1, 1};

int main(void) {
	scanf("%d %d", &m, &n);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			box[i][j] = 0;
		}
	}

	for (int i = 0; i < 1000002; i++) {
		dist[i] = -1;
		edge[i].clear();
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%d", &box[i][j]);
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (box[i][j] == -1) {
				continue;
			}
			u = i * m + j;
			vcount++;
			if (box[i][j] == 1) {
				q.push(u);
				dist[u] = 0;
			}
			for (int k = 0; k < 4; k++) {
				x = i + add_x[k];
				y = j + add_y[k];
				if (x < 0 || x >= n || y < 0 || y >= m) {
					continue;
				}
				if (box[x][y] == -1) {
					continue;
				}
				v = x * m + y;
				edge[u].push_back(v);
			}
		}
	}

	for (int i = 0; i < n * m; i++) {
		if (edge[i].size() == 0) {
			continue;
		}
		sort(edge[i].begin(), edge[i].end());
	}

	if (q.empty()) {
		printf("-1");
		return 0;
	}

	while (!q.empty()) {
		u = q.front();
		q.pop();
		vcount--;
		for (int i = 0; i < edge[u].size(); i++) {
			if (dist[edge[u][i]] == -1) {
				dist[edge[u][i]] = dist[u] + 1;
				maxd = (dist[edge[u][i]] > maxd) ? dist[edge[u][i]] : maxd;
				q.push(edge[u][i]);
			}
		}
	}

	if (maxd <= 0) {
		printf("%d", 0);
		return 0;
	}
	printf("%d", (vcount == 0) ?  maxd : -1);
	return 0;
}