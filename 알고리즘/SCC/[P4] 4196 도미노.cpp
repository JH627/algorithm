#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>
#include <algorithm>

#define VER_NUM 100002

using namespace std;

vector<int> g[VER_NUM];
vector<int> gr[VER_NUM];
vector<pair<int, int>> visited;

int clock = 0;
int pre[VER_NUM], post[VER_NUM];
int scc_count = 0;

int group_num[VER_NUM];
bool indegree[VER_NUM];

void dfs_r(int v) {
	pre[v] = clock++;
	for (int i = 0; i < gr[v].size(); i++) {
		if (pre[gr[v][i]] < 0) {
			dfs_r(gr[v][i]);
		}
	}
	post[v] = clock++;
	visited.push_back(make_pair(post[v], v));
}

void dfs(int v) {
	pre[v] = clock++;
	group_num[v] = scc_count;
	for (int i = 0; i < g[v].size(); i++) {
		if (pre[g[v][i]] < 0) {
			dfs(g[v][i]);
		}
	}
}

void beforeEach(int v, int e) {
	for (int i = 0; i <= v; i++) {
		indegree[i] = true;
		g[i].clear();
		gr[i].clear();
	}
	visited.clear();
	scc_count = 0;
}

void initVisit(int v) {
	clock = 0;
	for (int i = 1; i <= v; i++) {
		pre[i] = post[i] = -1;
	}
}

int main(void) {
	int t, v, e;
	int a, b, i, j;
	scanf("%d", &t);

	for (int k = 0; k < t; k++) {
		scanf("%d %d", &v, &e);

		beforeEach(v, e);

		for (i = 0; i < e; i++) {
			scanf("%d %d", &a, &b);
			g[a].push_back(b);
			gr[b].push_back(a);
		}

		for (int i = 1; i <= v; i++) {
			sort(g[i].begin(), g[i].end());
			sort(gr[i].begin(), gr[i].end());
		}

		initVisit(v);

		for (i = 1; i <= v; i++) {
			if (pre[i] < 0) {
				dfs_r(i);
			}
		}

		reverse(visited.begin(), visited.end());

		initVisit(v);

		for (i = 0; i < v; i++) {
			if (pre[visited[i].second] < 0) {
				dfs(visited[i].second);
				scc_count++;
			}
		}

		for (i = 1; i <= v; i++) {
			for (j = 0; j < g[i].size(); j++) {
				if (group_num[i] != group_num[g[i][j]]) {
					indegree[group_num[g[i][j]]] = false;
				}
			}
		}

		int count = 0;

		for (i = 0; i < scc_count; i++) {
			if (indegree[i] == true) {
				count++;
			}
		}

		printf("%d\n", count);
	}
	return 0;
}