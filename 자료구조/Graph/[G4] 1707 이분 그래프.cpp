#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>
#include <queue>

using namespace std;

vector<int> ve[20002];
int color[20002]{};
bool bipartite = true;

void bfs(int s) {
	queue<int> q;
	int now, next;
	q.push(s);
	color[s] = 1;
	while (!q.empty()) {
		now = q.front();
		q.pop();
		for (int i = 0; i < ve[now].size(); i++) {
			next = ve[now][i];
			if (color[next] == 0) {
				color[next] = color[now] * (-1);
				q.push(next);
			}
			else {
				if (color[next] == color[now]) {
					bipartite = false;
					return;
				}
			}
		}
	}
}

void init(int v) {
	for (int i = 1; i <= v; i++) {
		color[i] = 0;
		ve[i].clear();
	}
	bipartite = true;
}

int main(void) {
	int t;
	int v, e, x, y;
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		scanf("%d %d", &v, &e);
		init(v);

		for (int j = 0; j < e; j++) {
			scanf("%d %d", &x, &y);
			ve[x].push_back(y);
			ve[y].push_back(x);
		}

		for (int k = 1; k <= v; k++) {
			if (color[k] == 0 && ve[k].size() > 0) {
				bfs(k);
				if (bipartite == false) {
					break;
				}
			}
		}

		printf("%s\n", (bipartite) ? "YES" : "NO");
	}
	return 0;
}