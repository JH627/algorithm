#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>

using namespace std;

vector<pair<int, int>> g[10001];
int cost[10001];
int maxVertex, maxDist = 0;

void dfs(int v) {
	int next;
	for (int i = 0; i < g[v].size(); i++) {
		next = g[v][i].first;
		if (cost[next] == -1) {
			cost[next] = g[v][i].second + cost[v];
			if (cost[next] > maxDist) {
				maxDist = cost[next];
				maxVertex = next;
			}
			dfs(next);
		}
	}
}

void clear(int v, int s) {
	for (int i = 1; i <= v; i++) {
		cost[i] = -1;
	}
	cost[s] = maxDist = 0;
}

int main(void) {
	int v, a, b, c;
	scanf("%d", &v);

	for (int i = 1; i < v; i++) {
		scanf("%d %d %d", &a, &b, &c);
		g[a].push_back(make_pair(b, c));
		g[b].push_back(make_pair(a, c));
	}

	clear(v, 1);
	dfs(1);

	clear(v, maxVertex);
	dfs(maxVertex);

	printf("%d", maxDist);
	return 0;
}