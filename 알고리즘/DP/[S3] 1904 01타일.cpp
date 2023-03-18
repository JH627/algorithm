#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
using namespace std;

int fib[1000001];

int main(void) {
	int n;
	scanf("%d", &n);
	fib[0] = fib[1] = 1;
	for (int i = 2; i <= n; i++) {
		fib[i] = (fib[i - 1] + fib[i - 2]) % 15746;
	}
	printf("%d", fib[n]);
	return 0;
}
