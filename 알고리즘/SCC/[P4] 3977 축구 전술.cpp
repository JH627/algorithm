#define _CRT_SECURE_NO_WARNINGS
#define SIZE 100002

#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> g[SIZE];
vector<int> gr[SIZE];
vector<pair<int, int>> sorted;

int clock = 0;
int pre[SIZE], post[SIZE];

int scc_count = 0;
int scc_group[SIZE];
bool indegree[SIZE];

void dfs_r(int v) {
	pre[v] = clock++;
	for (int i = 0; i < gr[v].size(); i++) {
		if (pre[gr[v][i]] < 0) {
			dfs_r(gr[v][i]);
		}
	}
	post[v] = clock++;
	sorted.push_back(make_pair(post[v], v));
}

void dfs(int v) {
	pre[v] = clock++;
	scc_group[v] = scc_count;
	for (int i = 0; i < g[v].size(); i++) {
		if (pre[g[v][i]] < 0) {
			dfs(g[v][i]);
		}
	}
	post[v] = clock++;
}

void beforeVisit(int v) {
	for (int i = 0; i < v; i++) {
		pre[i] = post[i] = -1;
	}
	clock = 0;
}

void beforeEach(int v, int e) {
	for (int i = 0; i <= v; i++) {
		indegree[i] = true;
		g[i].clear();
		gr[i].clear();
	}
	sorted.clear();
	scc_count = 0;
}

int main(void) {
	int t, v, e;
	int a, b;

	scanf("%d", &t);
	for (int k = 0; k < t; k++) {
		scanf("%d %d", &v, &e);

		beforeEach(v, e);

		for (int i = 0; i < e; i++) {
			scanf("%d %d", &a, &b);
			g[a].push_back(b);
			gr[b].push_back(a);
		}

		for (int i = 0; i < v; i++) {
			sort(g[i].begin(), g[i].end());
			sort(gr[i].begin(), gr[i].end());
		}

		beforeVisit(v);
		for (int i = 0; i < v; i++) {
			if (pre[i] < 0) {
				dfs_r(i);
			}
		}

		reverse(sorted.begin(), sorted.end());

		beforeVisit(v);
		for (int i = 0; i < v; i++) {
			if (pre[sorted[i].second] < 0) {
				dfs(sorted[i].second);
				scc_count++;
			}
		}

		for (int i = 0; i < v; i++) {
			for (int j = 0; j < g[i].size(); j++) {
				if (scc_group[i] != scc_group[g[i][j]]) {
					indegree[scc_group[g[i][j]]] = false;
				}
			}
		}

		int count = 0;
		int idx;
		for (int i = 0; i < scc_count; i++) {
			if (indegree[i] == true) {
				count++;
				idx = i;
			}
		}
		if (count == 1) {
			for (int j = 0; j < v; j++) {
				if (scc_group[j] == idx) {
					printf("%d\n", j);
				}
			}
		}
		else {
			printf("Confused\n");
		}
		printf("\n");
	}

	return 0;
}