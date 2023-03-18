#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>

#define VER_NUM 10001

using namespace std;

vector<int> g[VER_NUM];
vector<int> gr[VER_NUM];
vector<int> ans[VER_NUM];
vector<pair<int, int>> visited;
queue<int> scc;

int clock = 0;
int pre[VER_NUM], post[VER_NUM];
int scc_count = 0;

void initVisit() {
	clock = 0;
	for (int i = 0; i < VER_NUM; i++) {
		pre[i] = post[i] = -1;
	}
}

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

void dfs2(int v) {
	pre[v] = clock++;
	scc.push(v);
	for (int i = 0; i < g[v].size(); i++) {
		if (pre[g[v][i]] < 0) {
			dfs2(g[v][i]);
		}
	}
}

bool mycompare(vector<int> a, vector<int> b) {
	return a[0] < b[0];
}

int main(void) {
	int v, e;
	int a, b, i, j;
	scanf("%d %d", &v, &e);

	for (i = 0; i < e; i++) {
		scanf("%d %d", &a, &b);
		g[a].push_back(b);
		gr[b].push_back(a);
	}

	for (i = 1; i <= v; i++) {
		sort(g[i].begin(), g[i].end());
		sort(gr[i].begin(), gr[i].end());
	}

	initVisit();
	for (i = 1; i <= v; i++) {
		if (pre[i] < 0) {
			dfs_r(i);
		}
	}
	reverse(visited.begin(), visited.end());

	initVisit();
	for (i = 0; i < v; i++){
		if (pre[visited[i].second] < 0) {
			dfs2(visited[i].second);
			if (!scc.empty()) {
				while (!scc.empty()) {
					printf("%d -> ", scc.front());
					ans[scc_count].push_back(scc.front());
					scc.pop();
				}
				printf("\n");
				scc_count++;
			}
		}
	}

	for (i = 0; i < scc_count; i++) {
		sort(ans[i].begin(), ans[i].end());
	}
	sort(ans, ans + scc_count, mycompare);

	printf("%d\n", scc_count);
	for (i = 0; i < scc_count; i++) {
		for (j = 0; j < ans[i].size(); j++) {
			printf("%d ", ans[i][j]);
		}
		printf("-1\n");
	}
	return 0;
}