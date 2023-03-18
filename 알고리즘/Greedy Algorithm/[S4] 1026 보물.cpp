#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>

using namespace std;

int main(void) {
	int n, i, ans = 0;
	int a[50], b[50];

	scanf("%d", &n);
	
	for (i = 0; i < n; i++) {
		scanf("%d", &a[i]);
	}
	for (i = 0; i < n; i++) {
		scanf("%d", &b[i]);
	}

	sort(&a[0], &a[n]);
	sort(&b[0], &b[n], greater<int>());

	for (i = 0; i < n; i++) {
		ans += a[i] * b[i];
	}

	printf("%d", ans);

	return 0;
}