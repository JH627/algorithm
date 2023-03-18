#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>

using namespace std;

int sum[1025][1025]{};

int main(void) {
	int n, m, k;
	int x1, y1, x2, y2;
	scanf("%d %d", &n, &m);
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			scanf("%d ", &k);
			sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i-1][j-1] + k;
		}
	}
	for (int i = 0; i < m; i++) {
		scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
		printf("%d\n", sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1]);
	}
	return 0;
}