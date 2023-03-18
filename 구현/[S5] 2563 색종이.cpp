#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>

using namespace std;

int p[101][101]{};

int main(void) {
	int n, x, y;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d %d", &x, &y);
		for (int n = 0; n < 10; n++) {
			for (int m = 0; m < 10; m++) {
				p[x + n][y + m] = 1;
			}
		}
	}

	int ans = 0;
	for (int i = 0; i < 101; i++) {
		for (int j = 0; j < 101; j++) {
			ans += p[i][j];
		}
	}
	printf("%d", ans);
	return 0;
}