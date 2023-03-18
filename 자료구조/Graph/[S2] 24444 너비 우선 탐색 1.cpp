#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

vector<int> g[100001];
queue<int> q;
int post[100001] = { 0, };
int clock = 1;

int main(void) {
	int n, m, r;
	int u, v;

	scanf("%d %d %d", &n, &m, &r);

	for (int i = 0; i < m; i++) {
		scanf("%d %d", &u, &v);
		g[u].push_back(v);
		g[v].push_back(u);
	}
	for (int i = 1; i <= n; i++) {
		sort(g[i].begin(), g[i].end());
	}

	q.push(r);
	post[r] = clock++;
	int now, next;

	while (!q.empty()) {
		now = q.front();
		q.pop();
		for (int i = 0; i < g[now].size(); i++) {
			next = g[now][i];
			if (post[next] == 0) {
				post[next] = clock++;
				q.push(next);
			}
		}
	}

	for (int i = 1; i <= n; i++) {
		printf("%d\n", post[i]);
	}
	return 0;
}