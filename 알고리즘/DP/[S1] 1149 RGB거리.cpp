#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <algorithm>

using namespace std;

int cost[1001][3]{};

int main(void) {
	int n, r, g, b;
	scanf("%d", &n);
	for (int i = 1; i <= n; i++) {
		scanf("%d %d %d", &r, &g, &b);
		cost[i][0] = min(cost[i - 1][1], cost[i - 1][2]) + r;
		cost[i][1] = min(cost[i - 1][0], cost[i - 1][2]) + g;
		cost[i][2] = min(cost[i - 1][0], cost[i - 1][1]) + b;
	}
	int m = min(cost[n][0], cost[n][1]);
	m = min(m, cost[n][2]);
	printf("%d", m);
	return 0;
}