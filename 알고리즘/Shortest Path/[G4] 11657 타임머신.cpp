#define _CRT_SECURE_NO_WARNINGS
#define VSIZE 501
#define INF 5000002

#include <cstdio>
#include <vector>
#include <tuple>

using namespace std;

vector<tuple<int, int, int>> g;
long long dist[VSIZE];

int n, m;
bool cycle = false;

void bf(int start) {
	dist[start] = 0;
	int now, next, cost;
	for (int i = 1; i <= n; i++) {
		for (int j = 0; j < m; j++) {
			now = get<0>(g[j]);
			next = get<1>(g[j]);
			cost = get<2>(g[j]);

			if (dist[now] != INF && dist[next] > dist[now] + cost) {
				dist[next] = dist[now] + cost;
				if (i == n) {
					cycle = true;
				}
			}
		}
	}
}

int main(void) {
	scanf("%d %d", &n, &m);

	int a, b, c;
	for (int i = 0; i < m; i++) {
		scanf("%d %d %d", &a, &b, &c);
		g.push_back({a, b, c});
	}

	for (int i = 1; i <= n; i++) {
		dist[i] = INF;
	}

	bf(1);

	if (cycle == true) { 
		printf("-1");
	}
	else {
		for (int i = 2; i <= n; i++) {
			printf("%lld\n", (dist[i] == INF) ? -1 : dist[i]);
		}
	}

	return 0;
}