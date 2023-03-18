#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

using namespace std;

long long num[101];

int main(void) {
	int t, n;
	num[1] = num[2] = num[3] = 1;
	for (int i = 4; i < 101; i++) {
		num[i] = num[i - 2] + num[i - 3];
	}
	scanf("%d", &t);

	for (int i = 0; i < t; i++) {
		scanf("%d", &n);
		printf("%lld\n", num[n]);
	}
	return 0;
}