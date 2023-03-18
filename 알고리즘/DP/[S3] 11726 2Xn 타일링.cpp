#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

using namespace std;

int main(void) {
	int n;
	scanf("%d", &n);

	int tile[1001];
	tile[0] = tile[1] = 1;
	for (int i = 2; i <= n; i++) {
		tile[i] = tile[i - 1] + tile[i - 2];
		if (tile[i] > 10007) {
			tile[i] %= 10007;
		}
	}
	printf("%d", tile[n]);
	return 0;
}