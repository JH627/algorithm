#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>
#include <queue>

#define INF 300000

using namespace std;

vector<pair<int, int>> g[20001];
int dist[20001];

int v, e, s;

void dijkstra(int start) {
	priority_queue<pair<int, int>> q;
	q.push(make_pair(0, start));
	dist[start] = 0;
	int now, next, nowdist, nextdist;
	while (!q.empty()) {
		now = q.top().second;
		nowdist = -q.top().first;
		q.pop();
		if (dist[now] < nowdist) {
			continue;
		}
		for (int i = 0; i < g[now].size(); i++) {
			next = g[now][i].second;
			nextdist = g[now][i].first + nowdist;
			if (dist[next] > nextdist) {
				dist[next] = nextdist;
				q.push(make_pair(-nextdist, next));
			}
		}
	}
}

int main(void) {
	scanf("%d %d", &v, &e);
	scanf("%d", &s);

	int a, b, u;
	for (int i = 0; i < e; i++) {
		scanf("%d %d %d", &a, &b, &u);
		g[a].push_back(make_pair(u, b));
	}

	for (int i = 1; i <= v; i++) {
		dist[i] = INF;
	}

	dijkstra(s);

	for (int i = 1; i <= v; i++) {
		if (dist[i] == INF) {
			printf("INF\n");
		}
		else {
			printf("%d\n", dist[i]);
		}
	}
	return 0;
}
