#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstdlib>

using namespace std;

int min(int a, int b) {
	return (a > b) ? b : a;
}

int main(void) {
	int n;
	scanf("%d", &n);
	int* num = (int*)malloc(sizeof(int) * (n + 1));
	for (int i = 0; i <= n; i++) {
		num[i] = n + 1;
	}
	num[1] = 0;

	for (int i = 1; i < n; i++) {
		num[i + 1] = min(num[i + 1], num[i] + 1);
		if (2 * i <= n) {
			num[2 * i] = min(num[2 * i], num[i] + 1);
		}
		if (3 * i <= n) {
			num[3 * i] = min(num[3 * i], num[i] + 1);
		}
	}

	printf("%d", num[n]);
	free(num);
	return 0;
}