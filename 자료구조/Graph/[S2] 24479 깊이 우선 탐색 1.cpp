#include <cstdio>
#include <cstdlib>
#include <vector>
#include <algorithm>

#define _CRT_SECURE_NO_WARNINGS
#define V_SIZE 100001

using namespace std;

vector<int> g[V_SIZE];
int visited[V_SIZE];
int clock = 1;

void initVisit() {
	for (int i = 0; i < V_SIZE; i++) {
		visited[i] = 0;
	}
}

void dfs(int r) {
	visited[r] = clock++;
	for (int i = 0; i < g[r].size(); i++) {
		if (visited[g[r][i]] == 0) {
			dfs(g[r][i]);
		}
	}
}

int main(void) {
	int n, m, r;
	int i;
	int u, v;

	scanf("%d %d %d", &n, &m, &r);
	for (i = 0; i < m; i++) {
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
		g[v].push_back(u);
	}
	
	for (i = 1; i <= n; i++) {
		sort(g[i].begin(), g[i].end());
	}

	initVisit();
	dfs(r);
	for (i = 1; i <= n; i++) {
		printf("%d\n", visited[i]);
	}

	return 0;
}
