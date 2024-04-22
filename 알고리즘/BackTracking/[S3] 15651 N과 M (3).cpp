#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>

using namespace std;

int n, m;
vector<int> v;

void dfs(int cnt) {
	if (cnt == n) {
		for (int i = 0; i < n; i++) {
			printf("%d ", v[i]);
		}
		printf("\n");
		return;
	}

	for (int i = 1; i <= m; i++) {
		v.push_back(i);
		dfs(cnt + 1);
		v.pop_back();
	}
}

int main(void) {
	scanf("%d %d", &m, &n);
	dfs(0);
	return 0;
}