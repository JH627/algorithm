#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstdlib>
#include <vector>
#include <queue>
#include <algorithm>
#define SIZE 10001

using namespace std;

vector<int> g[SIZE];
queue<int> visit;
queue<int> gr;
int dist[SIZE];
int pre[SIZE], post[SIZE];
int clock;

void initVisit() {
	for (int i = 0; i < SIZE; i++) {
		clock = 1;
		pre[i] = post[i] = -1;
		dist[i] = 100000;
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

void bfs(int v) {
	queue<int> q;
	int now, next;
	q.push(v);
	dist[v] = 0;
	while (!q.empty()) {
		now = q.front();
		q.pop();
		printf("%d ", now);
		for (int i = 0; i < g[now].size(); i++) {
			next = g[now][i];
			if (dist[next] == 100000) {
				dist[next] = dist[now] + 1;
				q.push(next);
			}
		}
	}

}

int main(void) {
	int n, m, s;
	int u, v;
	scanf("%d %d %d", &n, &m, &s);

	for (int i = 0; i < m; i++) {
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
		g[v].push_back(u);
	}

	for (int i = 1; i <= n; i++) {
		sort(g[i].begin(), g[i].end());
	}

	initVisit();
	dfs(s);
	while (!visit.empty()){
		printf("%d ", visit.front());
		visit.pop();
	}
	printf("\n");

	initVisit();
	bfs(s);

	return 0;
}