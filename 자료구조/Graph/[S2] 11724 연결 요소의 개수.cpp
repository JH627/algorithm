#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstdlib>
#include <algorithm>
#include <vector>
#define SIZE 1001

using namespace std;

vector<int> g[SIZE];
int pre[SIZE], post[SIZE];
int clock;

void init() {
	for (int i = 0; i < SIZE; i++) {
		pre[i] = post[i] = 0;
	}
	clock = 0;
}

void dfs(int v) {
	pre[v] = clock++;
	for (int i = 0; i < g[v].size(); i++) {
		if (pre[g[v][i]] == 0) {
			dfs(g[v][i]);
		}
	}
	post[v] = clock++;
}

int main(void) {
	int n, m;
	int u, v;
	int count = 0;
	scanf("%d %d", &n, &m);
	for (int i = 0; i < m; i++) {
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
		g[v].push_back(u);
	}

	init();

	for (int i = 1; i <= n; i++) {
		if (pre[i] == 0) {
			dfs(i);
			count++;
		}
	}
	
	printf("%d", count);

	return 0;
}