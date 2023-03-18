#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

int main(void) {
	int n, count = 0;
	scanf("%d", &n);

	while (1) {
		if (n % 5 == 0) {
			count += n / 5;
			printf("%d", count);
			break;
		}
		n -= 3;
		count++;

		if (n < 0) {
			printf("-1");
			break;
		}
	}
	return 0;
}