#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <algorithm>

using namespace std;

int main(void) {
	int n;
	scanf("%d", &n);

	int count2 = 0;
	int count5 = 0;
	for (int i = 1; i <= n; i++) {
		int tem = i;
		while (tem % 2 == 0) {
			tem /= 2;
			count2++;
		}
		while (tem % 5 == 0) {
			tem /= 5;
			count5++;
		}
	}

	printf("%d", min(count2, count5));

	return 0;
}