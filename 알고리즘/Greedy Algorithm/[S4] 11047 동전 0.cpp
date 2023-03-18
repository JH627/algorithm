#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

using namespace std;

int main(void) {
	int n, k;
	int coin[10];
	int count = 0;

	scanf("%d %d", &n, &k);

	for (int i = 0; i < n; i++) {
		scanf("%d", &coin[i]);
	}

	for (int i = n - 1; i >= 0; i--) {
		while (k > 0) {
			k -= coin[i];
			count++;
		}
		if (k < 0) {
			k += coin[i];
			count--;
		}
	}
	printf("%d", count);
	return 0;
}