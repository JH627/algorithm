#define _CRT_SECURE_NO_WARNINGS
#define INF 10000001

#include <cstdio>
#include <algorithm>

using namespace std;

int dist[101][101];
int n, m;

void clearDist() {
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			dist[i][j] = INF;
		}
		dist[i][i] = 0;
	}
}

int main(void) {

	scanf("%d %d", &n, &m);
	clearDist();

	int a, b, c;
	for (int i = 0; i < m; i++) {
		scanf("%d %d %d", &a, &b, &c);
		if (dist[a][b] > c) {
			dist[a][b] = c;
		}
	}

	for (int k = 1; k <= n; k++) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
			}
		}
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			printf("%d ", (dist[i][j] == INF) ? 0 : dist[i][j]);
		}
		printf("\n");
	}

	return 0;
}