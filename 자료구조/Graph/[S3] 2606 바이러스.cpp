#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstdlib>
#include <vector>
#include <algorithm>
#include <queue>
#define SIZE 10001

using namespace std;

vector<int> g[SIZE];
queue<int> visit;
int pre[SIZE], post[SIZE];
int clock;

void initVisit() {
	for (int i = 0; i < SIZE; i++) {
		clock = 1;
		pre[i] = post[i] = -1;
	}
}
void dfs(int v) {
	pre[v] = clock++;
	visit.push(v);
	for (int i = 0; i < g[v].size(); i++) {
		if (pre[g[v][i]] == -1) {
			dfs(g[v][i]);
		}
	}
	post[v] = clock++;
}

int main(void) {
	int n, m;
	int u, v;
	scanf("%d %d", &n, &m);

	for (int i = 0; i < m; i++) {
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
		g[v].push_back(u);
	}

	for (int i = 1; i <= n; i++) {
		sort(g[i].begin(), g[i].end());
	}
	initVisit();
	dfs(1);
	printf("%d", visit.size() - 1);
	return 0;
}