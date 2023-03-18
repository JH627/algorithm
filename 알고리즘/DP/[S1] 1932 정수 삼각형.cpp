#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>

using namespace std;

int v[501][501]{};

int max(int a, int b) {
	return (a > b) ? a : b;
}

int main(void) {
	int n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j <= i; j++) {
			scanf("%d", &v[i][j]);
		}
	}

	for (int i = n - 2; i >= 0; i--) {
		for (int j = 0; j <= i; j++) {
			v[i][j] += max(v[i + 1][j], v[i + 1][j + 1]);
		}
	}

	printf("%d", v[0][0]);
	return 0;
}