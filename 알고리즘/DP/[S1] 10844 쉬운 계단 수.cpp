#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#define N 1000000000

using namespace std;

long long num[101][10]{};

int main(void) {
	num[1][0] = 0;	num[1][1] = 1;	num[1][2] = 1;
	num[1][3] = 1;	num[1][4] = 1;	num[1][5] = 1;
	num[1][6] = 1;	num[1][7] = 1;	num[1][8] = 1;	num[1][9] = 1;

	int n;
	scanf("%d", &n);

	for (int i = 2; i <= n; i++) {
		num[i][0] = num[i-1][1] % N;
		num[i][9] = num[i-1][8] % N;
		for (int j = 1; j < 9; j++) {
			num[i][j] = (num[i - 1][j - 1] + num[i - 1][j + 1]) % N;
		}
	}

	long long sum = 0;
	for (int i = 0; i < 10; i++) {
		sum += num[n][i] % N;
	}
	printf("%ld", sum % N);

	return 0;
}