#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>
#include <queue>

using namespace std;

int dist[101]{};
vector<int> g[101];
queue<int> q;

int main(void) {
	int n, m, x, y;
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n+m; i++) {
		scanf("%d %d", &x, &y);
		g[x].push_back(y);
	}

	q.push(1);
	int now, next;
	while (!q.empty()) {
		now = q.front();
		q.pop();
		if (g[now].size() == 1) {
			next = g[now][0];
			dist[next] = dist[now];
			q.push(next);
		}
		else {
			for (int i = 1; i <= 6; i++) {
				next = now + i;
				if (next <= 100) {
					if (dist[next] > dist[now] + 1 || dist[next] == 0) {
						dist[next] = dist[now] + 1;
						q.push(next);
					}
				}
			}
		}
	}

	printf("%d", dist[100]);
	return 0;
}