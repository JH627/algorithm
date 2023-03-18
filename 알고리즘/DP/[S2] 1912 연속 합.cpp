#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>

using namespace std;

int main(void) {

	int n, k;
	scanf("%d", &n);

	int sum = 0;
	int max = -1001;
	for (int i = 0; i < n; i++) {
		scanf("%d", &k);
		sum += k;
		max = (sum > max) ? sum : max;
		if (sum < 0) {sum = 0;}
	}
	printf("%d", max);
	return 0;
}