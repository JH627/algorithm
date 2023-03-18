#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>
#include <queue>
#include <algorithm>

#define INF 8000001

using namespace std;

vector<pair<int, int>> g[801];
int dist[801];

int v, e, s;

void clearDist() {
	for (int i = 1; i <= v; i++) {
		dist[i] = INF;
	}
}

void dijkstra(int start) {
	clearDist();
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

	int a, b, u;
	for (int i = 0; i < e; i++) {
		scanf("%d %d %d", &a, &b, &u);
		g[a].push_back(make_pair(u, b));
		g[b].push_back(make_pair(u, a));
	}

	int v1, v2;
	scanf("%d %d", &v1, &v2);

	dijkstra(1);
	int dist_V1 = dist[v1];
	int dist_V2 = dist[v2];

	dijkstra(v1);
	int dist_V1_V2 = dist[v2];
	int dist_V1_N = dist[v];

	dijkstra(v2);
	int dist_V2_N = dist[v];

	int minDist = min(dist_V1 + dist_V1_V2 + dist_V2_N, dist_V2 + dist_V1_V2 + dist_V1_N);

	printf("%d", (minDist >= INF) ? -1 : minDist);

	return 0;
}
