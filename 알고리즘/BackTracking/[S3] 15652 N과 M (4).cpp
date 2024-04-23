#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>

using namespace std;

int n, m;
int num[9]{};

void dfs(int cnt) {
	if (cnt == n) {
		for (int i = 0; i < n; i++) {
			printf("%d ", num[i]);
		}
		printf("\n");
		return;
	}
	int i = (cnt == 0) ? 1 : num[cnt - 1];
	for (; i <= m; i++) {
		num[cnt] = i;
		dfs(cnt + 1);
	}
}

int main(void) {
	scanf("%d %d", &m, &n);
	dfs(0);
	return 0;
}