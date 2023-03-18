#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

using namespace std;

int count = 0;

void knap(int cur, int max) {
	if (cur == max) {
		count++;
		return;
	}
	knap(cur + 1, max);
	if ((max - cur) >= 2) {
		knap(cur + 2, max);
	}
	if ((max - cur) >= 3) {
		knap(cur + 3, max);
	}
}

int main(void) {
	int t, n;
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		scanf("%d", &n);
		count = 0;
		knap(0, n);
		printf("%d\n", count);
	}
	return 0;
}