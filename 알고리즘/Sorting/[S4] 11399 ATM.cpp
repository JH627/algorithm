#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <cstdlib>

using namespace std;

int mycompare(const void *a, const void *b) {
	return *(int*)a - *(int*)b;
}

int main(void) {
	int n, sum = 0;
	int time[1001];
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &time[i]);
	}
	qsort(time, n, sizeof(int), mycompare);

	for (int i = 0; i < n; i++) {
		sum += time[i] * (n - i);
	}

	printf("%d", sum);
	return 0;
}

